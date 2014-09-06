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

//		if(i%4==0)
//		{
//			justShootString.add("SniperCastle");
//		}
//		if((i%10==0))
//		{
//			justShootString.add("MissileTower");
//		}
//		if((i%2==0))
//		{
//			justShootString.add("LaserTower");
//		}
		
		for(Tower tower : gameObjectList.getTowerList())
		{
			
			boolean sniperMayShoot = tower instanceof SniperCastle &&(i%tower.getRepeat()==0) && (towAim.aimHere(tower, enemyList.getEnemyList(), true)) < tower.getRangeInPix();
			boolean missileTowerMayShoot = tower instanceof MissileTower && (i%tower.getRepeat()==0) && (towAim.aimHere(tower, enemyList.getEnemyList(), true)) < tower.getRangeInPix();
			boolean laserTowerMayShoot = tower instanceof TowerOfDoom && (i%tower.getRepeat()==0) && (towAim.aimHere(tower, enemyList.getEnemyList(), true)) < tower.getRangeInPix();
			
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
