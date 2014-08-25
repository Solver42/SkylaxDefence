package se.MinimalisticPerfectionTechnology.start;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOfDirFetcher {
	/**
	 * "matchingFiles" will have the
	 * ArrayList in "MatchingWordGenerator"-
	 * class passed as value used to fill
	 * with applicable file paths in a
	 * certain folder.
	 */
	private ArrayList<String> matchingFiles;
	private WordSearcher wordSearcher = null;
	public FileOfDirFetcher(ArrayList<String> files, String word)
	{
		this.matchingFiles = files;
		wordSearcher = new  WordSearcher(word);
	}
	/**
	 * "myFiles" will be filled with
	 * all the text files of the particular
	 * folder defined in the "path"-argument.
	 * The loop at the end adds the file-path
	 * to the "matchingFiles" list mentioned
	 * above if it contains the word passes
	 * as an argument to the "WordSearcher"-
	 * objects constructor.
	 */
	public void addMachingDocuments(String path) {
		File directory = new File(path);
		File[] myFiles = directory.listFiles(new FileFilter() {
		    private final FileNameExtensionFilter filter =
		        new FileNameExtensionFilter("Text files",
		        		"doc", "docx", "log", "msg", "odt" +
		        		"pages", "rtf", "tex", "txt", "wpd", "wps");
		    public boolean accept(File file) {
		        return filter.accept(file);
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
