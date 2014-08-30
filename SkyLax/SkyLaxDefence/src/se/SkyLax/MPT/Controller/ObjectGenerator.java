package se.SkyLax.MPT.Controller;

import se.SkyLax.MPT.GameObjects.GameObjectList;
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
		gameObjectList.addTower(new SimpleTower("SimpleTower", 320, 33, 0.4));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 301, 279, 0.1));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 122, 50, 0.9));

	}


	public void fillPlan()
	{

		
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
