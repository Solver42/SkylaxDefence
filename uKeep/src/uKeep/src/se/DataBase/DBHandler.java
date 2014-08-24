package se.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PerConnectionLRUFactory;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

//This class should handle everything to do with SQL queries
//and the connection to your database. It should provide 
//public methods for each of the actions that can be chosen 
//from the console menu in the UI class.
public class DBHandler {
	private MysqlDataSource ds = null;
	private Connection con = null;

	private PreparedStatement delFromContentTable = null;
	private PreparedStatement delFromListTable = null;
	private PreparedStatement getEditedBy = null;
	private PreparedStatement insertNameToList = null;
	private PreparedStatement getUserData = null;
	private PreparedStatement insertNewUpdate = null;
	private PreparedStatement selectAllFromFileList = null; 
	private PreparedStatement loadContent = null;
	private PreparedStatement getListedData = null;
	private PreparedStatement getFiles = null;
	private PreparedStatement getSearchedFiles = null;
	
	private Statement stmt = null;

	private String username = "";
	private String password = "";

	public DBHandler(String username, String password){
		this.username = username;
		this.password = password;
		ds = new MysqlDataSource();
		ds.setServerName("localhost");
		ds.setDatabaseName("filestorage");
		ds.setPort(3306);		
	}

	public boolean connect(){
		try {
			con = ds.getConnection(username, password);
			getSearchedFiles = con.prepareStatement("SELECT * FROM listoffiles WHERE fileName LIKE ?");
			getFiles = con.prepareStatement("SELECT * FROM listoffiles");
			getListedData = con.prepareStatement("SELECT fileholder.content, fileholder.fileName, fileholder.updatedAt, fileholder.editedBy, fileholder.userComment, listoffiles.createdBy FROM fileholder JOIN listoffiles on fileholder.fileName = listoffiles.fileName WHERE listoffiles.fileName = ?");
			loadContent = con.prepareStatement("SELECT content from fileholder WHERE fileName = ?");
			delFromContentTable = con.prepareStatement("DELETE FROM fileholder WHERE `fileName`= ?");
			getEditedBy = con.prepareStatement("SELECT createdBy FROM listoffiles WHERE filename = ?");
			getUserData = con.prepareStatement("SELECT inetadresses.validAdress, users.userName, users.userPassword FROM inetadresses JOIN users ON inetadresses.userName=users.userName WHERE inetadresses.userName=? and users.userPassword=? and inetadresses.validAdress=?");
			delFromListTable = con.prepareStatement("DELETE FROM listoffiles WHERE `fileName`= ?");
			insertNameToList = con.prepareStatement("insert into listoffiles values (?, ?)");
			selectAllFromFileList = con.prepareStatement("SELECT fileName, createdBy FROM listoffiles");

			stmt = con.createStatement();
			
			System.out.println("Connection to database succeeded");

		} catch (SQLException e) {
			System.out.println("Connecting and setting up statements failed! " + e.getMessage());
			return false;
		}
		return true;
	}

	public String getListOfOneFile(String file) throws SQLException
	{
		String resultStr = "";

		ResultSet rs = null;

		getListedData.setString(1, file);

		rs = getListedData.executeQuery();

		if(rs.next())
		{
			resultStr += "File: " + rs.getString("fileName") + " Creator: " + rs.getString("createdBy") + "\n";
		}
		rs.beforeFirst();

		int itr = 0;
		while(rs.next())
		{
			itr++;
			resultStr += "[" + itr + "] " + rs.getString("updatedAt") + " edit: " + rs.getString("editedBy") + " \"" + rs.getString("userComment") + "\"\n";

		}
		return itr + " " + resultStr;
	}
	public String getListContent(String searchWord) throws SQLException
	{
		String returnString = "";
		ResultSet rs = null;

		if(!searchWord.isEmpty())
		{
			
			getSearchedFiles.setString(1, "%" + searchWord + "%");
			rs = getSearchedFiles.executeQuery();
		}
		else
		{
			
			rs = getFiles.executeQuery();
			
		}
		while (rs.next())
		{
			returnString += rs.getString(1) + ", by " + rs.getString(2) + "\n";
		}
		return returnString;
	}
	public boolean getIfExcist(String fileName) throws SQLException
	{
		ResultSet rs = null;

		rs = selectAllFromFileList.executeQuery();

		while(rs.next())
		{
			if(fileName.equals(rs.getString(1)))
			{
				return true;
			}
		}
		return false;
	}
	public boolean userDataCorrect(String userName, String password, String adress) throws SQLException
	{
		ResultSet rs = null;

		getUserData.setString(1, userName);
		getUserData.setString(2, password);
		getUserData.setString(3, adress);

		rs = getUserData.executeQuery();

		if(rs.next())
		{
			return true;
		}
		return false;
	}
	public byte[] fetchFromDB(String fileName, int which) throws SQLException
	{
		byte[] returnBlob = null;

		ResultSet rs = null;

		loadContent.setString(1, fileName);

		rs = loadContent.executeQuery();

		java.sql.Blob blob = null;
		if(which == 0)
		{
			rs.afterLast();
			if(rs.previous())
			{

				blob = rs.getBlob(1);
			}
		}else
		{
			int itr = 1;
			while(rs.next())
			{

				if(itr == which)
				{
					blob = rs.getBlob(1);
					break;
				}
				itr++;
			}
		}
		returnBlob = blob.getBytes(1, (int)blob.length());

		return returnBlob;
	}
	public void upload(byte[] data, String fileName, String user, String comment, boolean isNew) throws SQLException
	{
		insertNewUpdate = con.prepareStatement("insert into fileholder values( ?, ?, (select now()), ?, ?)");
		insertNewUpdate.setObject(1, data);
		insertNewUpdate.setString(2, fileName);
		insertNewUpdate.setString(3, user);
		insertNewUpdate.setString(4, comment);
		insertNewUpdate.executeUpdate();
		if(isNew)
		{
			insertNameToList.setString(1, fileName);
			insertNameToList.setString(2, user);
			insertNameToList.executeUpdate();
		}
	}
	public void deleteFile(String fileName) throws SQLException
	{

		delFromContentTable.setString(1, fileName);
		delFromContentTable.executeUpdate();

		delFromListTable.setString(1, fileName);
		delFromListTable.executeUpdate();
	}
	public boolean mayDelete(String filename, String user) throws SQLException
	{
		ResultSet rs = null;

		getEditedBy.setString(1, filename);

		rs = getEditedBy.executeQuery();

		while(rs.next())
		{
			if(rs.getString("createdBy").equals(user))
			{
				return true;
			}
		}
		return false;
	}
	public String time() throws SQLException
	{
		String time = null;
		ResultSet rs = stmt.executeQuery("SELECT now()");
		if(rs.next())
		{
			time = rs.getString(1);
		}

		return time;
	}
}