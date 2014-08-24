package se.Utility;

import java.io.IOException;
import java.io.Reader;



public class Utility {

	public String getMessage(Reader r)
	{

		int c;

		String string = "";
		try{
			while ((c = r.read()) != -1)
			{
				if((char) c != '?') string += (char) c;
				else break;
			}
			
		} catch (IOException ex)
		{
			System.err.println(ex.getMessage());
		}
		return string;
	}


}
