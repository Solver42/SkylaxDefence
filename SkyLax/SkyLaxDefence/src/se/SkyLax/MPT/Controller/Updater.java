package se.SkyLax.MPT.Controller;

import java.util.ArrayList;
import java.util.Random;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Graphics.TheFrame;
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
		objGen = new ObjectGenerator();
		enemyList = new EnemyList();
		screen = new TheFrame(objGen, enemyList);
		towAim = new TowerAimer();
	}
	private void updateShots()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getListOfAllShots())
		{
			rocket.travel();
		}
	}
	private void removeNAShot()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getListOfAllShots())
		{
			if(rocket.getX() > SwingTemplateJPanel.CANVAS_WIDTH || rocket.getX() < 0 || rocket.getY() > SwingTemplateJPanel.CANVAS_HEIGHT || rocket.getY() < 0)
			{
				shotsToRemove.add(rocket);
			}
		}
		objGen.getGameObjectContainer().getListOfAllShots().removeAll(shotsToRemove);
		shotsToRemove.clear();
	}
	public void setRandomTowerAngle()
	{
		for(Tower tower : objGen.getGameObjectContainer().getTowerList())
		{
//			tower.setAngle(gen.nextDouble()*(Math.PI*2));
			tower.setAngle(towAim.aimHere(tower, enemyList.getEnemyList()));
		}
	}
	
	private void makeEnemiesWalk()
	{
		for (Enemy enemy : enemyList.getEnemyList())
		{
			enemy.walk();
		}
	}
	public void run() {
		int mod = 1;
		while(true)
		{
			if(( mod%10==0 ))
			{
				setRandomTowerAngle();
				objGen.fillPlanWithRocketShot();
			}
			
			
//			To Get the enemies to walk, uncomment this!
			if(mod==100)
			{
				makeEnemiesWalk();
			}

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
