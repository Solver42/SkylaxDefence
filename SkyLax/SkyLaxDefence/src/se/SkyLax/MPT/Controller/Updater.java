package se.SkyLax.MPT.Controller;

import java.util.ArrayList;

import se.SkyLax.MPT.Enemy.Enemy;
import se.SkyLax.MPT.Enemy.EnemyList;
import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.TheFrame;
import se.SkyLax.MPT.Levels.Levels;
import se.SkyLax.MPT.Utility.TowerAimer;

public class Updater implements Runnable{

	public final static int NR_OF_ITR_ENEMY_STAYS = 5;
	private int nrOfEnemies = 0;
	private ObjectGenerator objGen = null;
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
		enemyList.setHandler(screen.getPanel().getHandler());
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
		objGen.waitASec();
	}
	int i = 1;
	private boolean go = false;
	public void resetIterator()
	{
		i = 0;
		nrOfEnemies++;
		screen.getPanel().getHandler().setLevel(Integer.toString(nrOfEnemies));

	}
	public void setGo(boolean goRound)
	{
		go = goRound;
	}
	public void run() {
		int mod = 10;
		while (true)
		{
			if(go)
			{
				if(mod%NR_OF_ITR_ENEMY_STAYS == 0 && !(enemyList.getEnemyList().isEmpty()))
				{
					makeEnemiesWalk();
					setRandomTowerAngle(/*mod/10*/);
					if(enemyList.getEnemyList().get(0).getStep()<Levels.mapList[0].length-1)
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
