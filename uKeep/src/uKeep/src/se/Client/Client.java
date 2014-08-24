package se.Client;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import se.Utility.Utility;


public class Client {

	private Socket socket = null;

	private Utility utility =  null;

	private OutputStream out = null;
	private BufferedOutputStream bout = null;
	private Writer writer = null;

	private InputStream in = null;
	private Reader r = null;


	private BufferedReader stdIn = null;

	String userNameClassLevel = "";


	public Client(String IP)
	{
		try {

			InetAddress addr = InetAddress.getByName(IP);
			utility = new Utility();

			socket = new Socket(addr, 5300);

			out = socket.getOutputStream();
			bout = new BufferedOutputStream(out);
			writer = new OutputStreamWriter(out);

			in = socket.getInputStream();
			in = new BufferedInputStream(in);
			r = new InputStreamReader(in);

			stdIn = new BufferedReader( new InputStreamReader(System.in));


			userData(stdIn);


			System.out.println("list - list files in data base\ndown + <file name> - download latest version of a file\nup + <file name>' - upload file\ndel + <file name> - delete remote file\nsearch + <any char sequence> - search for files\nchoose <file name> - lists commits of file\ndis - disconnect");
			System.out.print(userNameClassLevel + ">>");

			actAccToCommand();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(socket != null)
			{
				try
				{
					socket.close();
				} catch (Exception ex)
				{

				}
			}
		}
	}


	private boolean fileExcists(String fileName)
	{
		File folder = new File(".");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
		{
			if(listOfFiles[i].getName().equals(fileName))
			{
				return true;
			}

		}
		return false;
	}
	private void downCommand(String string, String[] tokens) throws IOException
	{
		writer.write(string + "?");
		writer.flush();
		String isDownloadable = utility.getMessage(r);
		if(isDownloadable.equals("downloadable"))
		{
			flushToDisk(tokens[1]);

		} else if(isDownloadable.equals("notDownloadable"))
		{
			System.out.println();
			System.out.println("Not downloadable");
		}
	}
	private void upCommand(String string, String[] tokens) throws IOException
	{
		if(fileExcists(tokens[1]))
		{
			File file = new File(tokens[1]);

			int size = (int) file.length();

			writer.write(string + " " +size + "?");
			writer.flush();
			String message = utility.getMessage(r);
			if(message.equals("fine"))
			{
				System.out.println("Enter comment: ");
				String comment = stdIn.readLine();

				writer.write(comment + "?");
				writer.flush();

				utility.getMessage(r);

				upload(string, tokens, size);
			}else
			{
				System.out.println("Upload new commit? y/n");
				String overwrite = stdIn.readLine();
				if(overwrite.equals("y"))
				{
					writer.write("overwrite?");
					writer.flush();

					utility.getMessage(r);

					System.out.println("Enter comment: ");
					String comment = stdIn.readLine();

					writer.write(comment + "?");
					writer.flush();

					utility.getMessage(r);

					upload(string, tokens, size);
				}
				else{
					writer.write("break?");
					writer.flush();
				}
			}
		}
		else
		{
			System.out.println();
			System.out.println("No such file found on your machine.");
		}
	}
	private void delCommand(String string, String[] tokens) throws IOException
	{
		writer.write(string + "?");
		writer.flush();
		String message = utility.getMessage(r);


		switch(message)
		{
		case "fine":
			System.out.println("Delete file? y/n");
			String confirm = stdIn.readLine();

			if(confirm.equals("y"))
			{
				writer.write("confirmed?");
				writer.flush();
				String completed = utility.getMessage(r);
				if(completed.equals("deleted"))
				{
					System.out.println();
					System.out.println(tokens[1] + " has been deleted.");
				}
			}else
			{
				writer.write("cancelDelete?");
				writer.flush();
			}
			break;

		case "notAllowed":

			System.out.println();
			System.out.println("You have to be author to delete a file.");
			break;

		case "canNotFindFile":
			System.out.println();
			System.out.println("File does not excist on remote machine");
			break;

		default:
			System.out.println("unknown error...");
		}
	}


	private void listCommand() throws IOException
	{
		writer.write("list?");
		writer.flush();

		System.out.println();
		System.out.println("Database content:");

		System.out.print(utility.getMessage(r));
		System.out.println();
	}
	private void searchCommand(String string) throws IOException
	{
		writer.write(string + "?");
		writer.flush();
		System.out.println();

		System.out.println("Search results:");

		String searchResult = utility.getMessage(r);
		System.out.print(searchResult);
		System.out.println();
	}






	private void upload(String string, String[] tokens, int size) throws IOException
	{
		System.out.println("Processing a file of " + size + " bytes...");

		FileInputStream fileIn = new FileInputStream(tokens[1]);
		byte[] tmp = new byte[size];
		byte[] data = null;
		int sz, len = 0;

		while ( (sz = fileIn.read(tmp)) != -1 )
		{
			if ( data == null )
			{
				len = sz;
				data = tmp;
			}
			else
			{
				byte[] narr;
				int nlen;

				nlen = len + sz;
				narr = new byte[nlen];
				System.arraycopy(data, 0, narr, 0, len);
				System.arraycopy(tmp, 0, narr, len, sz);

			}
		}
		if ( len != data.length)
		{
			byte[] narr = new byte[len];

			System.arraycopy(data, 0, narr, 0, len);
			data = narr;
		}
		bout.write(data, 0, (int)data.length);
		bout.flush();

		String uploaded = utility.getMessage(r);
		if(uploaded.equals("uploaded"))
		{
			System.out.println();
			System.out.println("File \"" + tokens[1] + "\" is uploaded.");
		}

		fileIn.close();
	}
	private void userData(BufferedReader stdIn) throws IOException
	{
		Console cnsl = System.console();
		while(true)
		{
			System.out.println("Enter name:");

			String username = stdIn.readLine();

			String password = "before fill";

			if(cnsl != null)
			{
				System.out.println("Enter password: ");
				char[] pwd = cnsl.readPassword();
				password = String.valueOf(pwd);
			}

			if(username.isEmpty() || password.isEmpty() || username.contains(" ") || password.contains(" ")) continue;
			userNameClassLevel = username;
			writer.write(username + " " + password + "?");
			writer.flush();

			String isValidReply = utility.getMessage(r);

			if(isValidReply.equals("valid"))
			{
				break;
			}
		}
	}

	private void askForChoosenDownload(int nrOf, String fileName) throws IOException
	{
		System.out.println("down <NR> - download certain commit.");
		String answere = stdIn.readLine();

		String[] tokens = answere.split(" ");

		if(tokens.length == 2 && tokens[0].equals("down"))
		{
			try
			{
				int choosenNumber = Integer.parseInt(tokens[1]);

				boolean nrIsAcceptable = choosenNumber > 0 && choosenNumber <= nrOf;

				if(!nrIsAcceptable)
				{
					writer.write("pass?");
					writer.flush();
					return;
				}

				if(nrIsAcceptable)
				{
					writer.write(choosenNumber + "?");
					writer.flush();

					flushToDisk(fileName);
				}
			} catch(NumberFormatException ex)
			{
				writer.write("pass?");
				writer.flush();
				return;
			}
		}else
		{
			writer.write("pass?");
			writer.flush();
		}
	}



	private void flushToDisk(String fileName) throws IOException
	{
		String length = utility.getMessage(r);

		System.out.println("Processing a file of " + length + " bytes.");

		byte[] bytesRecieved = new byte[Integer.parseInt(length)];

		writer.write("OK?");
		writer.flush();

		int bytesRead = in.read(bytesRecieved);

		File file = new File(fileName);

		BufferedOutputStream os;
		os = new BufferedOutputStream(new FileOutputStream(fileName));
		os.write(bytesRecieved, 0, (int)bytesRecieved.length);
		os.flush();
		os.close();
		System.out.println();
		System.out.println("File \"" + fileName + "\" was downloaded.");
	}

	private void actAccToCommand() throws IOException
	{
		String string = "";

		while((string = stdIn.readLine()) != null)
		{
			if(!string.contains("?"))
			{
				String[] tokens = string.split(" ");

				if(tokens.length == 1)
				{
					switch (tokens[0])
					{
					case "list":
						listCommand();
						break;

					case "dis":
						socket.close();
						System.exit(0);
						break;
					}
				}


				else if(tokens.length == 2)
				{
					switch (tokens[0])
					{
					case "down":
						downCommand(string, tokens);
						break;
					case "up":
						upCommand(string, tokens);
						break;
					case "del":
						delCommand(string, tokens);
						break;
					case "search":
						searchCommand(string);
						break;
					case "choose":
						choose(string, tokens[1]);
						break;
					}
				}
			}
			System.out.println();
			System.out.println("list - list files in data base\ndown + <file name> - download latest version of a file\nup + <file name>' - upload file\ndel + <file name> - delete remote file\nsearch + <any char sequence> - search for files\nchoose <file name> - list commits of file\ndis - disconnect");
			System.out.print(userNameClassLevel + ">>");
		}
	}
	private void choose(String string, String fileName) throws IOException
	{
		writer.write(string + "?");
		writer.flush();

		String temp = utility.getMessage(r);

		if(temp.equals("fileDoesNotExcist"))
		{
			System.out.println();
			System.out.println("File does not excist on remote machine.");
		}
		else
		{
			System.out.println();
			String fullContent = utility.getMessage(r);

			try{
				int numberOf = Integer.parseInt((fullContent.substring(0, fullContent.indexOf(" "))));

				System.out.print(fullContent.substring((fullContent.indexOf(" ")+1), (fullContent.length()-1)));

				System.out.println();
				System.out.println();
				askForChoosenDownload(numberOf, fileName);

			} catch(NumberFormatException ex)
			{
				writer.write("pass?");
				writer.flush();
				return;
			}
		}

	}
}
