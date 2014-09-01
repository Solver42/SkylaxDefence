package se.SkyLax.MPT.Controller;

import java.util.ArrayList;
import java.util.Random;

import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.Rocket;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Levels.Levels;

public class ObjectGenerator{


	/*
	 * this class creates some towers
	 * and shots. Whichever class
	 * wants to be able do this,
	 * must have access to GameObjectList.
	 */

	GameObjectList gameObjectList = null;
	private Random gen = new Random();
	public  ObjectGenerator()
	{

		gameObjectList = new GameObjectList();
//		gameObjectList.addTower(new SniperCastle("SniperCastle", 150, 510, 0.4));
//		gameObjectList.addTower(new SniperCastle("SniperCastle", 450, 30, 0.1));
		gameObjectList.addTower(new MissileTower("RocketTower", 450, 100, 0.9));
		gameObjectList.addTower(new MissileTower("RocetTower", 410, 450, 0.4));



	}

	private ArrayList<Tower> towersThatJustShoot = new ArrayList<Tower>() ;

	private synchronized void setJustShoot(Tower t)
	{
		towersThatJustShoot.add(t);
	}

	public synchronized boolean ifJustShoot(Tower t)
	{
		if(towersThatJustShoot.contains(t))
		{
			return true;
		}
		return false;

	}
	public synchronized void clearFireArray()
	{
		towersThatJustShoot.clear();
	}


	public synchronized void fillPlanWithRocketShot()
	{

		//for gunshots:
		//		int i = gen.nextInt(gameObjectList.getTowerList().size());
		for(Tower tower : gameObjectList.getTowerList())
		{
			if(tower instanceof SniperCastle && (gen.nextInt(10)>2))
			{
				gameObjectList.addShotGeneric(tower, "GunShot");
				//							System.out.println("Shoooooot");
				this.setJustShoot(tower);
			}
			else if(tower instanceof MissileTower && (gen.nextInt(19)>16))
			{
				gameObjectList.addShotGeneric(tower, "Rocket");
				this.setJustShoot(tower);
			}	
		}
	}
	public void hej()
	{

		for(Tower tower : gameObjectList.getTowerList())
		{
			if(gen.nextInt(50)<tower.getTowerInfo().getRepeat())
			{
				if(tower instanceof SniperCastle)
				{
					gameObjectList.addShotGeneric(tower, "GunShot");
				}else if(tower instanceof MissileTower)
				{
					gameObjectList.addShotGeneric(tower, "Rocket");

				}
				this.setJustShoot(tower);
			}
		}
	}
	public synchronized GameObjectList getGameObjectContainer()
	{
		return this.gameObjectList;
	}


}
