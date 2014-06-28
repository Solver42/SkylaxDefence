package se.akerlund.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandlerPrepared extends FileHandlerSuper{
	

	private static String[] textData;
	
	public FileHandlerPrepared(String filePath)
	{
		try {
			textData = buffIn(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String [] getRows()
	{
		return textData;
	}
}
