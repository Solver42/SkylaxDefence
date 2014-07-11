package se.SkyLax.MPT.GameObjects;

import java.util.ArrayList;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.Utility.TowerInformation;

public class GameObjectList{


	ArrayList<ConcreteShot> rockets;
	ArrayList<Tower> towers;
	Tower t;
	Tower t2;
	Tower t3;
	
	/*
	 * this class contains the list
	 * of the different shots and
	 * towers. Contains two methods
	 * for adding towers or shots
	 * to a certain tower.
	 */

	public GameObjectList()
	{
		rockets = new ArrayList<ConcreteShot>();
		towers = new ArrayList<Tower>();
		
//		towers.add(new SimpleTower(new TowerInformation("SimpleTower"), 59, 33));
//		towers.add(new SimpleTower(new TowerInformation("SimpleTower"), 3, 4));
//		towers.add(new SimpleTower(new TowerInformation("SimpleTower"), 122, 3));
		
//		t = new SimpleTower(new TowerInformation("SimpleTower"), 59, 33);
//		t2 = new SimpleTower(new TowerInformation("SimpleTower"), 3, 4);
//		t3 = new SimpleTower(new TowerInformation("SimpleTower"), 122, 3);
		
		
//		addTower(new SimpleTower(new TowerInformation("SimpleTower"), 59, 33));
//		addTower(new SimpleTower(new TowerInformation("SimpleTower"), 3, 4));
//		addTower(new SimpleTower(new TowerInformation("SimpleTower"), 122, 3));
		
		//shoot();


	}
	public void addTower(Tower t)
	{
		towers.add(t);
	}
//	public void shoot()
//	{
//		rockets.add(t.getNewShot("Rocket"));
//		rockets.add(t2.getNewShot("Rocket"));
//		rockets.add(t3.getNewShot("Rocket"));
//		
//	}
	public void addShot(int index, String kindOfShot)
	{
		rockets.add(towers.get(index).getNewShot(kindOfShot));
	}

	public ArrayList<ConcreteShot> getRocketList()
	{
		return this.rockets;
	}
	
	public ArrayList<Tower> getTowerList()
	{
		return this.towers;
	}

//	public void run() {
//		while(true)
//		{
//			for(ShootActions rocket: rockets)
//			{
//				rocket.run();
//			}
//
//			Map.notifyMap(this);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
}
