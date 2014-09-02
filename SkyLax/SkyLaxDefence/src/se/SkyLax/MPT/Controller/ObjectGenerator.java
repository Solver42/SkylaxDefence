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
	private ArrayList<Tower> towersThatJustShoot = new ArrayList<Tower>() ;
	public  ObjectGenerator()
	{

		gameObjectList = new GameObjectList();
//		gameObjectList.addTower(new MissileTower("RocketTower", 450, 100, 0.9));
//		gameObjectList.addTower(new MissileTower("RocketTower", 410, 450, 0.4));


	}

	private ArrayList<String> justShootString = new ArrayList<String>() ;

	public  boolean ifJustShoot(Tower t)
	{
		if(towersThatJustShoot.contains(t))
		{
			return true;
		}
		return false;

	}
	public  synchronized void clearFireArray()
	{
		towersThatJustShoot.clear();
	}
	public synchronized ArrayList<String>getJustShootList()
	{
		return this.justShootString;
	}
	public synchronized void clearJustShoot()
	{
		this.justShootString.clear();
	}
	

	int i;
	public synchronized void fillPlanWithRocketShot()
	{
		i++;

		if(i%3==0)
		{
			justShootString.add("SniperCastle");
		}
		if((i%10==0))
		{
			justShootString.add("MissileTower");
		}
		for(Tower tower : gameObjectList.getTowerList())
		{
			if(tower instanceof SniperCastle && (i%3==0))
			{
				gameObjectList.addShotGeneric(tower, "GunShot");
				//							System.out.println("Shoooooot");
				//towersThatJustShoot.add(tower);
			}
			else if(tower instanceof MissileTower && (i%tower.getTowerInfo().getRepeat()==0))
			{
				gameObjectList.addShotGeneric(tower, "Rocket");
				//towersThatJustShoot.add(tower);
			}	
		}
		
		if(i>=100) i = 0;
		
	}
//	public void hej()
//	{
//
//		for(Tower tower : gameObjectList.getTowerList())
//		{
//			if(gen.nextInt(50)<tower.getTowerInfo().getRepeat())
//			{
//				if(tower instanceof SniperCastle)
//				{
//					gameObjectList.addShotGeneric(tower, "GunShot");
//				}else if(tower instanceof MissileTower)
//				{
//					gameObjectList.addShotGeneric(tower, "Rocket");
//
//				}
//				this.setJustShoot(tower);
//			}
//		}
//	}
	public synchronized GameObjectList getGameObjectContainer()
	{
		return this.gameObjectList;
	}


}
