package se.SkyLax.MPT.Controller;

import java.util.ArrayList;

import se.SkyLax.MPT.Enemy.Enemy;
import se.SkyLax.MPT.Enemy.EnemyList;
import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.TheFrame;
import se.SkyLax.MPT.Utility.TowerAimer;

public class Updater implements Runnable{

	public final static int NR_OF_ITR_ENEMY_STAYS = 15;
	private final static int NR_OF_ENEMIES = 5;

	private int nrOfEnemies = 1;

	private ObjectGenerator objGen = null;
	//	private Random gen = new Random();
	private ArrayList<ConcreteShot> shotsToRemove = null;;
	private TheFrame screen = null;
	private EnemyList enemyList;
	private TowerAimer towAim;
	private ArrayList<ConcreteShot> shotList = null;
	private ArrayList<Tower> towers;
	public Updater()
	{
		shotsToRemove = new ArrayList<>();
		enemyList = new EnemyList(this);
		towAim = new TowerAimer();
		objGen = new ObjectGenerator(enemyList, towAim);
		screen = new TheFrame(objGen, enemyList);
		enemyList.addEnemy();
		enemyList.setHandler(screen.getPanel().getHandler());

		//		screen.getPanel().getHandler().setCasch(3400);
		//		objGen.getMoneyClass().updatePanText();
	}
	private void updateShots()
	{
		shotList = objGen.getGameObjectContainer().getListOfAllShots();
		for(ConcreteShot rocket: shotList)
		{
			if(!enemyList.getEnemyList().isEmpty())
				enemyList.checkIfTargetIsHit(rocket);

			rocket.travel();
		}
	}
	private void removeNAShot()
	{
		shotList = objGen.getGameObjectContainer().getListOfAllShots();
		for(ConcreteShot rocket: shotList)
		{
			if(enemyList.getEnemyList().isEmpty()) break;
			if(enemyList.checkIfTargetIsHit(rocket))
			{
				shotsToRemove.add(rocket);
			}
		}

		shotList.removeAll(shotsToRemove);
		shotsToRemove.clear();
	}
	public void setRandomTowerAngle(/*int diff*/)
	{
		towers = objGen.getGameObjectContainer().getTowerList();
		for(Tower tower : towers)
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



	private void update()
	{
		screen.update();
		//			objGen.waitASec();
		objGen.waitASec();
	}
	int i = 1;
	public void resetIterator()
	{
		i = 0;
		nrOfEnemies++;
		screen.getPanel().getHandler().setLevel(Integer.toString(nrOfEnemies));
	}
	
	
	public void run() {
		int mod = 10;
		while (true)
		{

			if(mod%NR_OF_ITR_ENEMY_STAYS == 0 && !(enemyList.getEnemyList().isEmpty()))
			{

				makeEnemiesWalk();
				setRandomTowerAngle(/*mod/10*/);
				objGen.fillPlanWithRocketShot();
				if(i<nrOfEnemies)
					i++;
			}
			if (mod%NR_OF_ITR_ENEMY_STAYS == 0)
			{
				if(i<nrOfEnemies)
				{

					enemyList.getEnemyList().add(new Enemy("Standard"));

				}
			}
			


			updateShots();
			removeNAShot();

			update();
			objGen.clearJustShoot();

			mod++;
			if(mod>100) mod = 10;



		}

	}

}
