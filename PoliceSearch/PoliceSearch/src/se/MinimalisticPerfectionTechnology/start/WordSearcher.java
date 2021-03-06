package se.MinimalisticPerfectionTechnology.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordSearcher {
	String word = "";
	/**
	 * This is a very simple class
	 * that looks trough a file
	 * and sees whether it contains a
	 * certain word or not.
	 * the word is defined as soon as
	 * an object of this class is being
	 * initialized.
	 */
	public WordSearcher(String word)
	{
		this.word = word;
	}
	public boolean hasPreDefinedSearchWord(String path)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(path));

			String line = null;
			while ((line = reader.readLine()) != null){

				if(line.contains(word))
				{
					return true;
				}
			}
		} catch (IOException ex)
		{
			System.err.print(ex.getMessage());
		}
		return false;
	}
}
