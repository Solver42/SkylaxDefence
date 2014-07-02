package se.SkyLax.MPT.GameObjects;

import java.util.ArrayList;

public class FireController implements Runnable{

	
	ArrayList<Rocket> rockets;
	Tower t;
	ShootActions actions;
	
	public FireController()
	{
		rockets = new ArrayList<Rocket>();
		t = new SimpleTower(1, 59, 33);
		rockets.add((Rocket) t.getNewShot());
		

	}
	
	public ArrayList<Rocket> getRocketList()
	{
		return this.rockets;
	}

	@Override
	public void run() {
		for(Rocket rocket: rockets)
		{
			actions = rocket;
			actions.run();
		}
		Map.notifyMap(this);
	}
}
