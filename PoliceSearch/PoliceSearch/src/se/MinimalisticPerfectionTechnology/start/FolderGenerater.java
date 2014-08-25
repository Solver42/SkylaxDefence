package se.MinimalisticPerfectionTechnology.start;

import java.io.File;
import java.util.ArrayList;

public class FolderGenerater {
	/**
	 * the "folderList" will consist
	 * of the absolute paths to each
	 * directory under the "topFolder"
	 * that is passed in the constructor.
	 * "peers" will consist of every
	 * directory that are peers (used later)
	 * starting off with only the super-folder.
	 */
	private ArrayList<String> folderList = null;
	private ArrayList<String> peers = new ArrayList<String>();
	public FolderGenerater(String topFolder, ArrayList<String> folderList) {
		peers.add(topFolder);	
		this.folderList = folderList;
		getSubFolders(peers);
	}
	/**
	 * the method takes a list as
	 * a single argument and that list will
	 * vary because this is a recursive
	 * method and when it calls itself it
	 * passes a unique value. In the
	 * method the list mentioned above is
	 * first added to "folderList" (found
	 * in the PathPrinter-class). Then, one by one,
	 * all the folders that are in "currentDirs"
	 * this "lap" are being looked trough after
	 * sub folders. If some are found, they are
	 * added to the list that is later passed
	 * as an argument to this method (recursively).
	 * Note that these folders are not added
	 * to the "main" list immediately as they're
	 * found, but at the beginning of this method.
	 */
	private void getSubFolders(ArrayList<String> currentDirs)
	{
		ArrayList<String> newPath = new ArrayList<String>();
		for(String path : currentDirs)
		{
			folderList.add(path);
			File dir = new File(path);
			File[] files = dir.listFiles();
			for (File file : files)
			{
				if(file.isDirectory())
				{
					newPath.add(file.getAbsolutePath());
				}
			}
		}
		if(!newPath.isEmpty())
		{
			getSubFolders(newPath);
		}
	}
}
