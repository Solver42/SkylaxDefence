package se.MinimalisticPerfectionTechnology.start;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class PathPrinter {
	private ArrayList<String> foldersToLookThough = new ArrayList<String>();
	private FolderGenerater folderGenerater = null;
	private List<Future<ArrayList<String>>> futureList = null;
	
	
	private DefaultListModel<String> model;
	/**
	 * This class submits several (10) different
	 * Callable-object to different threads.
	 * Once they are done the result-list
	 * of each callable are being
	 * looked trough and printed
	 * out.
	 */
	
	public PathPrinter(DefaultListModel model)
	{
		this.model = model;
		futureList = new ArrayList<Future<ArrayList<String>>>();
		folderGenerater = new FolderGenerater();
	}
	
	public void printPaths (String superPath, String[] searchWords)
	{
		
		if(!foldersToLookThough.isEmpty())
		{
			foldersToLookThough.clear();
		}
		folderGenerater.getFolders(superPath, foldersToLookThough);
		ExecutorService executor = Executors.newFixedThreadPool(1);
		if(!futureList.isEmpty())
		{
			futureList.clear();
		}
		for(int i = 0; i<searchWords.length; i++)
		{
			Callable<ArrayList<String>> callable = new MatchingWordGenerator(searchWords[i], foldersToLookThough);	
			Future<ArrayList<String>> future = executor.submit(callable);
			futureList.add(future);
		}
		int i = 0;
		for(Future<ArrayList<String>> fut : futureList)
		{
//			System.out.println(">>\"" + searchWords[i] + "\":");
			try {
				for (String str : fut.get()/*fut.get() really is the list, neither more nor less*/)
				{
					/*add this line to the GUI-component*/
//					System.out.println(str);
					model.addElement(str);
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		i=0;
		executor.shutdown();
	}

}
