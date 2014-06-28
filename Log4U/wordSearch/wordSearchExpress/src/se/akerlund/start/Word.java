package se.akerlund.start;

import java.util.ArrayList;

public class Word {

	private String wordString = "";
	private ArrayList<WordInfo> wordInfo;

	public Word(String w)
	{
		wordString = w;
		wordInfo = new ArrayList<WordInfo>();
	}

	public void add(int line, int atWordIndex)
	{
		wordInfo.add(new WordInfo(line, atWordIndex));
	}
	public String getString()
	{
		return this.wordString;
	}

	public void printThis()
	{
		String returnString = "";
		if(!wordInfo.isEmpty())
		{
			for(WordInfo w : wordInfo)
			{
				System.out.println("Found word \"" + wordString + "\" at line " + w.getLine() + " at index " + (w.getIndex() + 1));
			}
		}
	}
}
