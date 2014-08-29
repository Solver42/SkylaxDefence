package se.MinimalisticPerfectionTechnology.start;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.filechooser.FileNameExtensionFilter;

public class FileOfDirFetcher implements Runnable{
	/**
	 * "matchingFiles" will have the
	 * ArrayList in "MatchingWordGenerator"-
	 * class passed as value used to fill
	 * with applicable file paths in a
	 * certain folder.
	 */
	private ArrayList<String> matchingFiles;
	private String word;
	private ArrayList<WordSearcher> runnables = new ArrayList<WordSearcher>();
	private String path;
	public FileOfDirFetcher(ArrayList<String> files, String word, String path)
	{
		this.path = path;
		this.word = word;
		this.matchingFiles = files;
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
	 * @throws InterruptedException 
	 */
	private void addMachingDocuments() throws InterruptedException {
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
		ExecutorService executor = Executors.newFixedThreadPool(20);

		for(File file : myFiles)
		{
			if(file.isFile())
			{
				runnables.add(new WordSearcher(word, file.getAbsolutePath()));
				executor.execute(runnables.get(runnables.size()-1));
			}
		}
		executor.shutdown();
		while(!executor.isTerminated())
		{	
		}
	}
	public void add()
	{
		for(WordSearcher runnable : runnables)
		{
			if(runnable.hasWord())
			{
				matchingFiles.add(runnable.getPath());
			}
		}
	}
	@Override
	public void run() {
		try {
			addMachingDocuments();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

