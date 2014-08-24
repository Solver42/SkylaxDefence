package se.MinimalisticPerfectionTechnology.start;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileOfDirFetcher {
	private ArrayList<String> matchingFiles;
	private WordSearcher wordSearcher = null;
	public FileOfDirFetcher(ArrayList<String> files, String word)
	{
		this.matchingFiles = files;
		wordSearcher = new  WordSearcher(word);
	}
	public void addMachingDocuments(String path) {
		File directory = new File(path);
		File[] myFiles = directory.listFiles(new FilenameFilter(){
			public boolean accept(File directory, String fileName){
				return fileName.endsWith(".txt");
			}
		});

		for(File file : myFiles)
		{
			if(file.isFile() && wordSearcher.hasPreDefinedSearchWord(file.getAbsolutePath()))
			{
				matchingFiles.add(file.getAbsolutePath());
			}
		}
	}
}
