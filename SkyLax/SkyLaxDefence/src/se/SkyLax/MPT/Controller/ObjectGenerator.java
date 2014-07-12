package se.SkyLax.MPT.Controller;

import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.Updater;
import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.SimpleTower;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Utility.TowerInformation;

public class ObjectGenerator{


	/*
	 * this class creates some towers
	 * and shots. Whichever class
	 * wants to be able do this,
	 * must have access to GameObjectList.
	 */

	GameObjectList gameObjectList = null;
	public  ObjectGenerator()
	{

		gameObjectList = new GameObjectList();

	}


	public void fillPlan()
	{
		gameObjectList.addTower(new SimpleTower("SimpleTower", 59, 33));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 3, 4));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 122, 3));
		
		int i = 0;
		
		for(Tower tower : gameObjectList.getTowerList())
		{
			if(tower instanceof SimpleTower)
			{
				gameObjectList.addShot(i, "Rocket");
			}
			i++;
		}
	}
	public GameObjectList getGameObjectContainer()
	{
		return this.gameObjectList;
	}


}
