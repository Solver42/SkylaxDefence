package se.SkyLax.MPT.Controller;

import java.util.ArrayList;
import java.util.Random;

import se.SkyLax.MPT.Enemy.Enemy;
import se.SkyLax.MPT.Enemy.EnemyList;
import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Graphics.TheFrame;
import se.SkyLax.MPT.Levels.Levels;
import se.SkyLax.MPT.Utility.TowerAimer;

public class Updater implements Runnable{

	public final static int NR_OF_ITR_ENEMY_STAYS = 15;
	private final static int NR_OF_ENEMIES = 5;

	private ObjectGenerator objGen = null;
	private Random gen = new Random();
	private ArrayList<ConcreteShot> shotsToRemove = null;;
	private TheFrame screen = null;
	private EnemyList enemyList;
	private TowerAimer towAim;
	public Updater()
	{
		shotsToRemove = new ArrayList<>();
		enemyList = new EnemyList();
		towAim = new TowerAimer();
		objGen = new ObjectGenerator(enemyList, towAim);
		screen = new TheFrame(objGen, enemyList);
		enemyList.addEnemy();

	}
	private void updateShots()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getListOfAllShots())
		{
			if(!enemyList.getEnemyList().isEmpty())
				enemyList.checkIfTargetIsHit(rocket);

			rocket.travel();
		}
	}
	private void removeNAShot()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getListOfAllShots())
		{
			if(enemyList.getEnemyList().isEmpty()) break;
			if(enemyList.checkIfTargetIsHit(rocket))
			{
				shotsToRemove.add(rocket);
			}
		}
		objGen.getGameObjectContainer().getListOfAllShots().removeAll(shotsToRemove);
		shotsToRemove.clear();
	}
	public void setRandomTowerAngle(/*int diff*/)
	{
		for(Tower tower : objGen.getGameObjectContainer().getTowerList())
		{
			//			tower.setAngle(gen.nextDouble()*(Math.PI*2));
			tower.setAngle(towAim.aimHere(tower, enemyList.getEnemyList(), false/*, diff*/));
		}
	}
	private void makeEnemiesWalk()
	{
		for (Enemy enemy : enemyList.getEnemyList())
		{
			enemy.walk();
		}
	}


	private void round(int mod)
	{
		if(mod%NR_OF_ITR_ENEMY_STAYS == 0 && !(enemyList.getEnemyList().isEmpty()))
		{
			makeEnemiesWalk();
			setRandomTowerAngle(/*mod/10*/);
			objGen.fillPlanWithRocketShot();

			if((mod<70) && create)
				enemyList.addEnemy();
			else create = false;
		}
		updateShots();
		removeNAShot();

		update();
		objGen.clearJustShoot();

	}

	private void update()
	{
		screen.update();
		//			objGen.waitASec();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	private boolean create = true;
	private static boolean go = false;

	public static void setGO(boolean bool)
	{
		go = bool;
	}
	public void run() {
		int mod = 10;
		while(!go) update();
		while(go)
		{

			round(mod);

			mod++;
			if(mod>100) mod = 10;

		}


	}

}
