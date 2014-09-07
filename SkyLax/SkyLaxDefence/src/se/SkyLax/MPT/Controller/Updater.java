package se.SkyLax.MPT.Controller;

import java.util.ArrayList;
import java.util.Random;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Graphics.TheFrame;
import se.SkyLax.MPT.Levels.Levels;
import se.SkyLax.MPT.UNDER_CONSTR.Enemy;
import se.SkyLax.MPT.UNDER_CONSTR.EnemyList;
import se.SkyLax.MPT.UNDER_CONSTR.TowerAimer;

public class Updater implements Runnable{
	private ObjectGenerator objGen = null;
	private Random gen = new Random();
	private ArrayList<ConcreteShot> shotsToRemove = new ArrayList<>();
	private TheFrame screen = null;
	private EnemyList enemyList;
	private TowerAimer towAim;
	public Updater()
	{

		enemyList = new EnemyList();
		towAim = new TowerAimer();
		objGen = new ObjectGenerator(enemyList, towAim);
		screen = new TheFrame(objGen, enemyList);

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
			boolean thisShotShallBeRemoved = rocket.getX() > SwingTemplateJPanel.CANVAS_WIDTH || rocket.getX() < 0 || rocket.getY() > SwingTemplateJPanel.CANVAS_HEIGHT || rocket.getY() < 0;

			if((thisShotShallBeRemoved) || (enemyList.checkIfTargetIsHit(rocket)))
			{
				shotsToRemove.add(rocket);
			}
			if(enemyList.getEnemyList().isEmpty()) break;
		}
		objGen.getGameObjectContainer().getListOfAllShots().removeAll(shotsToRemove);
		shotsToRemove.clear();
	}
	public void setRandomTowerAngle()
	{
		for(Tower tower : objGen.getGameObjectContainer().getTowerList())
		{
			//			tower.setAngle(gen.nextDouble()*(Math.PI*2));
			tower.setAngle(towAim.aimHere(tower, enemyList.getEnemyList(), false));
		}
	}

	private void makeEnemiesWalk()
	{
		for (Enemy enemy : enemyList.getEnemyList())
		{
			enemy.walk();
		}
	}


	public static int NR_OF_ITR_ENEMY_STAYS = 10;

	public void run() {
		int mod = 1;
		while(true)
		{
			if(mod%NR_OF_ITR_ENEMY_STAYS == 0 && !(enemyList.getEnemyList().isEmpty()))
			{
				makeEnemiesWalk();
				setRandomTowerAngle();
				objGen.fillPlanWithRocketShot();
			}
			//Just dummy code:
			else if(mod%NR_OF_ITR_ENEMY_STAYS == 0) enemyList.getEnemyList().add(new Enemy("Standard"));


			updateShots();

			removeNAShot();
			screen.update();
			objGen.waitASec();
			objGen.clearJustShoot();


			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			mod++;
			if(mod>100) mod = 1;

		}
	}

}
