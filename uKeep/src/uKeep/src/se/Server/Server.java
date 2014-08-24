package se.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import se.DataBase.DBHandler;

public class Server {


	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private DBHandler db= null;

	public Server()
	{
		db = new DBHandler("me", "password");
		db.connect();
		try
		{
			serverSocket = new ServerSocket(5300);
			
			while(true)
			{
				socket = serverSocket.accept();
				new Thread(new SocketThread(socket, db)).start();
			}
		} catch (IOException ex)
		{
			System.err.print(ex.getMessage());
		}
		finally
		{
			if(socket != null)
			{
				try
				{
					serverSocket.close();
					socket.close();
				} catch (Exception ex)
				{
				}
			}
		}

	}
}
