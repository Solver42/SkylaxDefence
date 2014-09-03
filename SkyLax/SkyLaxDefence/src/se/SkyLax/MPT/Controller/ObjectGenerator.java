package se.SkyLax.MPT.Controller;

import java.util.ArrayList;

import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.GameObjects.TowerOfDoom;

public class ObjectGenerator{


	/*
	 * this class creates some towers
	 * and shots. Whichever class
	 * wants to be able do this,
	 * must have access to GameObjectList.
	 */

	GameObjectList gameObjectList = null;
	private ArrayList<Tower> towersThatJustShoot = new ArrayList<Tower>() ;
	public  ObjectGenerator()
	{

		gameObjectList = new GameObjectList();


	}

	private ArrayList<String> justShootString = new ArrayList<String>() ;

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

		if(i%4==0)
		{
			justShootString.add("SniperCastle");
		}
		if((i%10==0))
		{
			justShootString.add("MissileTower");
		}
		if((i%2==0))
		{
			justShootString.add("LaserTower");
		}
		
		for(Tower tower : gameObjectList.getTowerList())
		{
			if(tower instanceof SniperCastle && (i%tower.getRepeat()==0))
			{
				gameObjectList.addShotGeneric(tower, "GunShot");
			}
			else if(tower instanceof MissileTower && (i%tower.getRepeat()==0))
			{
				gameObjectList.addShotGeneric(tower, "Rocket");
			}
			else if(tower instanceof TowerOfDoom && (i%tower.getRepeat()==0))
			{
				gameObjectList.addShotGeneric(tower, "Laser");
			}	
		}
		
		if(i>=100) i = 0;
		
	}
	public synchronized GameObjectList getGameObjectContainer()
	{
		return this.gameObjectList;
	}
	
	public void waitASec()
	{
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
