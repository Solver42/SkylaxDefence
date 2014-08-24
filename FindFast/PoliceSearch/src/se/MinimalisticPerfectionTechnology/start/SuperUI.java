package se.MinimalisticPerfectionTechnology.start;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SuperUI {
	private ArrayList<String> foldersToLookThough = new ArrayList<String>();
	private FolderGenerater folderGenerater = null;
	public SuperUI (String superPath, String[] searchWords)
	{
		folderGenerater = new FolderGenerater(superPath, foldersToLookThough);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<ArrayList<String>>> list = new ArrayList<Future<ArrayList<String>>>();
		for(int i = 1; i<searchWords.length; i++)
		{
			Callable<ArrayList<String>> callable = new UI(searchWords[i], foldersToLookThough);	
			Future<ArrayList<String>> future = executor.submit(callable);
			list.add(future);
		}
		int i = 1;
		for(Future<ArrayList<String>> fut : list)
		{
			System.out.println(">>\"" + searchWords[i] + "\":");
			try {
				
				for (String str : fut.get())
				{
					System.out.println(str);
				}
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		executor.shutdown();
	}
	
}
