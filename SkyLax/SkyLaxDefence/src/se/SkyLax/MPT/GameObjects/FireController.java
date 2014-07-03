package se.SkyLax.MPT.GameObjects;

import java.util.ArrayList;

public class FireController implements Runnable {


	ArrayList<ShootActions> rockets;
	Tower t;
	Tower t2;
	Tower t3;

	public FireController()
	{
		rockets = new ArrayList<ShootActions>();
		t = new SimpleTower(1, 59, 33);
		t2 = new SimpleTower(45, 3, 4);
		t3 = new SimpleTower(4, 122, 3);
		shoot();



	}
	public void shoot()
	{
		rockets.add(t.getNewShot("Rocket"));
		rockets.add(t2.getNewShot("Rocket"));
		rockets.add(t3.getNewShot("Rocket"));
	}

	public ArrayList<ShootActions> getRocketList()
	{
		return this.rockets;
	}

	public void run() {
		while(true)
		{
			for(ShootActions rocket: rockets)
			{
				rocket.run();
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
