package se.akerlund.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandlerUser extends FileHandlerSuper{
	
	private static String[] textData;
	
	public FileHandlerUser(String filePath)
	{
		
		try {
			textData = buffIn(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String[] getAllWords()
	{
		String temp = "";
		for(int i = 0; i<textData.length; i++)
		{
			temp += textData[i];
		}
		String[] returnStr = temp.split("\\.");
		
		return returnStr;
	}
}
