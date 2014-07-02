package se.SkyLax.MPT.GameObjects;

import java.util.ArrayList;

public class FireController implements Runnable {


	ArrayList<Rocket> rockets;
	Tower t;
	Tower t2;
	Tower t3;
	ShootActions actions;

	public FireController()
	{
		rockets = new ArrayList<Rocket>();
		t = new SimpleTower(1, 59, 33);
		//t2 = new SimpleTower(45, 3, 4);
		//t3 = new SimpleTower(4, 122, 3);
		shoot();



	}
	public void shoot()
	{
		rockets.add(new Rocket(t));
		//rockets.add(new Rocket(t2));
		//rockets.add(new Rocket(t3));
	}

	public ArrayList<Rocket> getRocketList()
	{
		return this.rockets;
	}

	public void run() {
		while(true)
		{
			for(Rocket rocket: rockets)
			{
				actions = rocket;
				actions.run();
			}

			Map.notifyMap(this);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
