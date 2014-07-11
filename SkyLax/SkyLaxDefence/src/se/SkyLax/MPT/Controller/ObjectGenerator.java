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

		gameObjectList = new GameObjectList();

		gameObjectList.addTower(new SimpleTower("SimpleTower", 59, 33));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 3, 4));
		gameObjectList.addTower(new SimpleTower("SimpleTower", 122, 3));



		gameObjectList.addShot(0, "Rocket");
		gameObjectList.addShot(1, "Rocket");
//		gameObjectList.addShot(2, "Rocket");
	}

	//		if(gameObjectList.getTowerList().get(1) instanceof SimpleTower)
	//		{
	//			for(int i = 0; i < 3; i++)
	//			{
	//				hej();
	//				try {
	//					Thread.sleep(500);
	//				} catch (InterruptedException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				}
	//			}
	//		}
	//	}
	//
	//	public void hej()
	//	{
	//
	//		gameObjectList.addShot(1, "Rocket");
	//
	//	}
	public GameObjectList getGameObjectContainer()
	{
		return this.gameObjectList;
	}


}
