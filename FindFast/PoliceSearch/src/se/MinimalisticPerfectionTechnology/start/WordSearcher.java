package se.MinimalisticPerfectionTechnology.start;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordSearcher implements Runnable{
	private String word = "";
	private String path;
	private boolean hasWord = false;
	/**
	 * This is a very simple class
	 * that looks trough a file
	 * and sees whether it contains a
	 * certain word or not.
	 * the word is defined as soon as
	 * an object of this class is being
	 * initialized.
	 */
	public WordSearcher(String word, String path)
	{
		this.word = word;
		this.path = path;
	}
	private void hasPreDefinedSearchWord()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(path));

			String line = null;
			while ((line = reader.readLine()) != null){

				if(line.contains(word))
				{
					hasWord = true;
				}
				else
				{
					hasWord = false;
				}
			}
		} catch (IOException ex)
		{
			System.err.print(ex.getMessage());
		}
	}
	
	public boolean hasWord()
	{
		return hasWord;
	}
	public String getPath()
	{
		return this.path;
	}
	
	@Override
	public void run() {
		hasPreDefinedSearchWord();
	}
}
