package se.SkyLax.MPT.Controller;

import java.util.ArrayList;

import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.GameObjects.TowerOfDoom;
import se.SkyLax.MPT.UNDER_CONSTR.EnemyList;
import se.SkyLax.MPT.UNDER_CONSTR.TowerAimer;

public class ObjectGenerator{


	/*
	 * this class creates some towers
	 * and shots. Whichever class
	 * wants to be able do this,
	 * must have access to GameObjectList.
	 */

	GameObjectList gameObjectList = null;
	private ArrayList<Tower> towersThatJustShoot = new ArrayList<Tower>() ;
	private EnemyList enemyList = null;
	private TowerAimer towAim = null;
	public  ObjectGenerator(EnemyList enemyL, TowerAimer tow)
	{
		towAim = tow;
		enemyList = enemyL;
		gameObjectList = new GameObjectList();


	}

	private ArrayList<Tower> justShootString = new ArrayList<Tower>() ;

	public  synchronized void clearFireArray()
	{
		towersThatJustShoot.clear();
	}
	public synchronized ArrayList<Tower>getJustShootList()
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

		
		for(Tower tower : gameObjectList.getTowerList())
		{
			boolean theRest = (i%tower.getRepeat()==0) && (towAim.aimHere(tower, enemyList.getEnemyList(), true)) < tower.getRangeInPix()/2;
			boolean sniperMayShoot = tower instanceof SniperCastle && theRest;
			boolean missileTowerMayShoot = tower instanceof MissileTower && theRest;
			boolean laserTowerMayShoot = tower instanceof TowerOfDoom && theRest;
			
			if(sniperMayShoot)
			{
				
				gameObjectList.addShotGeneric(tower, "GunShot");
				justShootString.add(tower);
			}
			else if(missileTowerMayShoot)
			{
				gameObjectList.addShotGeneric(tower, "Rocket");
				justShootString.add(tower);
			}
			else if(laserTowerMayShoot)
			{
				gameObjectList.addShotGeneric(tower, "Laser");
				justShootString.add(tower);
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
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
