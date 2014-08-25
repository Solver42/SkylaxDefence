package se.MinimalisticPerfectionTechnology.start;

import java.util.ArrayList;
import java.util.concurrent.Callable;

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
		dirFetcher = new FileOfDirFetcher(machingDocuments, searchWord);
		for (String folderToSerach : foldersToLookThough)
		{
			dirFetcher.addMachingDocuments(folderToSerach);
		}
	}
	@Override
	public ArrayList<String> call() throws Exception {
		// TODO Auto-generated method stub
		return machingDocuments;
	}
}
