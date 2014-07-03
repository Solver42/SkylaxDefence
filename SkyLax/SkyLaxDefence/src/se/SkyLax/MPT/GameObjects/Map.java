package se.SkyLax.MPT.GameObjects;

public class Map {
	
	
	public static void notifyMap(FireController fire)
	{
		for(ShootActions r : fire.getRocketList())
		{
			System.out.println(r.getX());
		}
		
	}
	
}
