package se.Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.sql.SQLException;

import se.DataBase.DBHandler;
import se.Utility.Utility;

public class SocketThread implements Runnable {

	private Socket socket = null;
	private DBHandler db= null;

	private InputStream in = null;
	private Reader r = null;

	private OutputStream out = null;
	private BufferedOutputStream bout = null;
	private Writer writer = null;

	private Utility utility = null;

	private String userName;

	public SocketThread(Socket socket, DBHandler db) {
		this.socket = socket;

		userName = "";

		this.db = db;
		utility = new Utility();
		try
		{
			in = socket.getInputStream();
			in = new BufferedInputStream(in);
			r = new InputStreamReader(in);

			out = socket.getOutputStream();
			bout = new BufferedOutputStream(out);
			writer = new OutputStreamWriter(out);
		} catch (IOException ex)
		{
			System.err.print(ex.getMessage());
		}
	}
	private void recieveUserName()
	{
		try
		{
			String userData = "";
			while(true)
			{
				userData = utility.getMessage(r);
				String[] userTokens = userData.split(" ");

				if(db.userDataCorrect(userTokens[0], userTokens[1], socket.getInetAddress().toString()))
				{
					writer.write("valid?");
					writer.flush();

					System.out.println("User " + userTokens[0] + " is now connected to the server. at(" + db.time() + ")");

					userName = userTokens[0];
					break;
				}else
				{
					writer.write("notValid?");
					writer.flush();
				}
			}

		}
		catch (IOException ex)
		{
			System.err.print(ex.getMessage());
		}
		catch (SQLException ex)
		{
			System.err.print(ex.getMessage());
		}
	}
	private void listen()
	{
		int c;

		String string = "";
		try{
			while(true)
			{				
				string = utility.getMessage(r);
				actAccToCommand(string);
				string = "";
			}
		} catch (IOException ex)
		{
			System.err.println(ex.getMessage());
		}
		catch (SQLException ex)
		{
			System.err.println(ex.getMessage());
		}
	}
	private void download(String[] tokens) throws IOException, SQLException
	{
		if(db.getIfExcist(tokens[1]))
		{
			writer.write("downloadable?");
			writer.flush();

			byte[] fetchedBytes = db.fetchFromDB(tokens[1], 0);
			System.out.println("Size of file (" + tokens[1] + ") fetched from database: " + fetchedBytes.length + " bytes. at(" + db.time() + ")");
			writer.write(fetchedBytes.length + "?");
			writer.flush();

			utility.getMessage(r);

			bout.write(fetchedBytes, 0, (int)fetchedBytes.length);
			bout.flush();
			System.out.println("File (" + tokens[1] + ") sent to client. (" + userName + ")");
		}
		else
		{
			writer.write("notDownloadable?");
			writer.flush();
		}
	}
	private void delete(String[] tokens) throws IOException, SQLException
	{
		if(db.getIfExcist(tokens[1]))
		{
			if(db.mayDelete(tokens[1], userName))
			{

				writer.write("fine?");
				writer.flush();

				String confirmed = utility.getMessage(r);
				if(confirmed.equals("confirmed"))
				{
					db.deleteFile(tokens[1]);

					System.out.println("File \"" + tokens[1] + "\" deleted from database. at(" + db.time() + ")");
					writer.write("deleted?");
					writer.flush();
				}
				else
				{
					System.out.println("Canseled!");
				}
			} else
			{
				System.out.println("Access denied, client is not author of the file \"" + tokens[1] + "\". at(" + db.time() + ")");
				writer.write("notAllowed?");
				writer.flush();
			}
		}
		else
		{
			writer.write("canNotFindFile?");
			writer.flush();
			System.out.println("Action reverted, no file found with the name \"" + tokens[1] + "\". at(" + db.time() + ")");
		}
	}
	private void actAccToCommand(String string) throws IOException, SQLException
	{
		String[] tokens = string.split(" ");

		if(tokens.length == 1 && tokens[0].equals("list"))
		{
			System.out.println("Recieved List request from " + userName + "... at(" + db.time() + ")");
			writer.write(db.getListContent("") + "?");
			writer.flush();
		}
		else if(tokens.length == 3 && tokens[0].equals("up"))
		{
			System.out.println("Recieved upload request of file on client side called \"" + tokens[1] + "\" from " + userName + "... at(" + db.time() + ")");
			upload(tokens);
		}
		if(tokens.length == 2)
		{

			switch (tokens[0])
			{
			case "down":

				System.out.println("Recieved download request of file \"" + tokens[1] + "\" from " + userName + "... at(" + db.time() + ")");

				download(tokens);
				break;
			case "del":

				System.out.println("Recieved delete request of file \"" + tokens[1] + "\" from " + userName + "... at(" + db.time() + ")");
				delete(tokens);
				break;
			case "search":

				System.out.println("Recieved search request from " + userName + "... at(" + db.time() + ")");
				writer.write(db.getListContent(tokens[1]) + "?");
				writer.flush();
				break;
			case "choose":

				System.out.println("Recieved choose request of file \"" + tokens[1] + "\" from " + userName + "... at(" + db.time() + ")");
				chooseCommand(tokens);
				break;
			}
		}
	}
	private void upload(String[] tokens) throws IOException, SQLException
	{
		String comment = "";
		boolean isNew = false;

		if(!db.getIfExcist(tokens[1])){

			writer.write("fine?");
			writer.flush();

			comment = utility.getMessage(r);

			writer.write("commentRecieved?");
			writer.flush();

			isNew = true;
		}else
		{
			writer.write("allreadyExcist?");
			writer.flush();

			String overwrite = utility.getMessage(r);

			if(overwrite.equals("overwrite"))
			{
				writer.write("overWriting?");
				writer.flush();

				comment = utility.getMessage(r);

				writer.write("commentRecieved?");
				writer.flush();

				isNew = false;
			}
			else if(overwrite.equals("break"))
			{
				return;
			}
		}
		byte[] bytesFile = new byte[Integer.parseInt(tokens[2])];
		in.read(bytesFile);

		System.out.println("Size of file \"" + tokens[1] + "\" recieved from client (" + userName + "): " + tokens[2] + " bytes. at(" + db.time() + ")");

		db.upload(bytesFile, tokens[1], userName, comment, isNew);
		if(isNew) System.out.println("File uploaded");
		else System.out.println("New version of file \"" + tokens[1] + "\" uploaded.");
		writer.write("uploaded?");
		writer.flush();
	}
	private void chooseCommand(String[] tokens) throws IOException, NumberFormatException, SQLException
	{
		if(db.getIfExcist(tokens[1]))
		{
			writer.write("excists?");
			writer.flush();

			writer.write(db.getListOfOneFile(tokens[1]) + "?");
			writer.flush();

			String getWhichFile = utility.getMessage(r);

			if(!getWhichFile.equals("pass"))
			{
				int getWhichFileInt = Integer.parseInt(getWhichFile);

				byte[] fetchedBytes = db.fetchFromDB(tokens[1], getWhichFileInt);

				System.out.println("Size of file \"" + tokens[1] + "\" fetched from database: " + fetchedBytes.length + " bytes. at(" + db.time() + ")");
				writer.write(fetchedBytes.length + "?");
				writer.flush();

				utility.getMessage(r);

				bout.write(fetchedBytes, 0, (int)fetchedBytes.length);
				bout.flush();
				System.out.println("File \"" + tokens[1] + "\"sent to client. (" + userName + ")");
			}
		}
		else
		{
			writer.write("fileDoesNotExcist?");
			writer.flush();
		}
	}
	@Override
	public void run() {
		recieveUserName();
		listen();
	}
}