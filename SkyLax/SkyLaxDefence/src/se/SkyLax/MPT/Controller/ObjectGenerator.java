package se.SkyLax.MPT.Controller;

import java.util.ArrayList;

import se.SkyLax.MPT.Enemy.Enemy;
import se.SkyLax.MPT.Enemy.EnemyList;
import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.GameObjects.TowerOfDoom;
import se.SkyLax.MPT.Utility.Money;
import se.SkyLax.MPT.Utility.TowerAimer;

public class ObjectGenerator{


	/*
	 * this class creates some towers
	 * and shots. Whichever class
	 * wants to be able do this,
	 * must have access to GameObjectList.
	 */
	private int i;

	GameObjectList gameObjectList = null;
	private ArrayList<Tower> towersThatJustShoot = null;;
	private EnemyList enemyList = null;
	private TowerAimer towAim = null;
	private ArrayList<Tower> justShootString = null;
	private Money money = null;
	private boolean theRest;
	private boolean sniperMayShoot;
	private boolean missileTowerMayShoot;
	private boolean laserTowerMayShoot;
	public  ObjectGenerator(EnemyList enemyL, TowerAimer tow)
	{
		towersThatJustShoot = new ArrayList<Tower>();
		towAim = tow;
		enemyList = enemyL;
		gameObjectList = new GameObjectList();
		justShootString = new ArrayList<Tower>() ;
		money = new Money();

	}

	

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
	
	
	
	public synchronized void fillPlanWithRocketShot()
	{
		i++;
		for(Tower tower : gameObjectList.getTowerList())
		{
			theRest = (i%tower.getRepeat()==0) && (towAim.aimHere(tower, enemyList.getEnemyList(), true/*, 0*/)) < tower.getRangeInPix()/2;
			sniperMayShoot = tower instanceof SniperCastle && theRest;
			missileTowerMayShoot = tower instanceof MissileTower && theRest;
			laserTowerMayShoot = tower instanceof TowerOfDoom && theRest;
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
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Money getMoneyClass()
	{
		return money;
	}


}
