package se.SkyLax.MPT.Controller;

import java.util.Random;

import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.SimpleTower;

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
		gameObjectList.addTower(new SimpleTower("SimpleTower", 150, 450, 0.4));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 450, 100, 0.1));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 170, 200, 0.9));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 410, 490, 0.4));


	}

	
	public void fillPlanWithGunShot()
	{

		
		int i = gen.nextInt(gameObjectList.getTowerList().size());
		
		gameObjectList.addShot(i, "GunShot");
	}
	
	public void fillPlanWithRocketShot()
	{

		
		int i = gen.nextInt(gameObjectList.getTowerList().size());
		
		gameObjectList.addShot(i, "Rocket");
	}
	
	
//	public void fillPlan()
//	{
//
//		
//		int i = 0;
//		
//		for(Tower tower : gameObjectList.getTowerList())
//		{
//			if(tower instanceof SimpleTower)
//			{
//				gameObjectList.addShot(i, "Rocket");
//			}
//			i++;
//		}
//	}
	public GameObjectList getGameObjectContainer()
	{
		return this.gameObjectList;
	}


}
