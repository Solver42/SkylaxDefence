package se.akerlund.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandlerSuper {
	private static String[] textData;
	
	protected String[] buffIn(String path) throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numberOfLines = 255;
		String[] textData = new String[numberOfLines];
		
		int i;
		
		for (i = 0; i<numberOfLines; i++)
		{
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
}