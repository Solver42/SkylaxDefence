package se.MinimalisticPerfectionTechnology.start;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatchingWordGenerator implements Callable<ArrayList<String>>{
	private ArrayList<String> machingDocuments = new ArrayList<String>();
	FileOfDirFetcher dirFetcher = null;
	/**
	 * The FileOfDirFetcher-class receives
	 * the list which will contain the files
	 * that contains the string value of the
	 * "searchWord"-argument, as it's been looked
	 * though in all of the applicable folders.
	 * the line "dirFetcher.addMatchingDocuments"...
	 * will only fill the matchingDocuments-list
	 * whit wanted files of a certain folders.
	 * But as this is being done in a loop
	 * that passes all of the folders
	 * as an argument to the line mentioned above
	 * the entire map-system will be looked trouhg.
	 */
	public MatchingWordGenerator (String searchWord, ArrayList<String> foldersToLookThough)
	{
		//dirFetcher = new FileOfDirFetcher(machingDocuments, searchWord);
		
		ArrayList<FileOfDirFetcher> runnables = new ArrayList<FileOfDirFetcher>();
		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (String folderToSerach : foldersToLookThough)
		{
			runnables.add(new FileOfDirFetcher(machingDocuments, searchWord, folderToSerach));
			threads.add(new Thread(runnables.get(runnables.size()-1)));
			threads.get(threads.size()-1).start();
		}
		
		for(Thread thread : threads)
		{
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(FileOfDirFetcher runnable : runnables)
		{
			runnable.add();
		}
		
		
	}
	@Override
	public ArrayList<String> call() throws Exception {
		// TODO Auto-generated method stub
		return machingDocuments;
	}
}
