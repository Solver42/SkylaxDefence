package se.SkyLax.MPT.GameObjects;

public class Map {
	
	
	public static void notifyMap(FireController fire)
	{
		for(Rocket r : fire.getRocketList())
		{
			r.getX();
			r.getY();
		}
		
	}
	
}
