package se.SkyLax.MPT.Enemy;

import java.util.ArrayList;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Events.ButtonHandler;
import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.Levels.Levels;

public class EnemyList {

	private Updater updater = null;

	private ArrayList<Enemy> enemyList= null;
	private boolean hit = false;
	private boolean lessThanX;
	private boolean moreThanX;
	private boolean lessThanY;
	private boolean moreThanY;
	private ButtonHandler handler = null;

	public EnemyList(Updater upd) {
		updater = upd;
		enemyList = new ArrayList<Enemy>();
	}

	public ArrayList<Enemy> getEnemyList()
	{
		return enemyList;
	}
	public boolean checkIfTargetIsHit(ConcreteShot shot)
	{
		for(Enemy enemy : enemyList)
		{
			lessThanX = shot.getX()<((Levels.mapList[0][(enemy.getStep())]+1)*(Levels.UNIT_WIDTH*2));
			moreThanX = shot.getX()>((Levels.mapList[0][(enemy.getStep())]+1)*(Levels.UNIT_WIDTH*2)-(Levels.UNIT_WIDTH*2));

			lessThanY = shot.getY()<((Levels.mapList[1][(enemy.getStep())]+1)*(Levels.UNIT_HEIGHT*2));
			moreThanY = shot.getY()>((Levels.mapList[1][(enemy.getStep())]+1)*(Levels.UNIT_HEIGHT*2)-(Levels.UNIT_HEIGHT*2));

			if(lessThanX && moreThanX && lessThanY && moreThanY)
			{

				hit = true;
				enemy.setHealt(shot.getDamage());
				//			System.out.println(enemyList.get(0).getHealth());
				if(enemy.getHealth()<=0)
				{
					enemyList.remove(enemy);
					handler.modifyCasch(500);
				}
				return true;
			}
		}
		return false;
	}
	public boolean getHit()
	{
		return this.hit;
	}
	public void turOffHit()
	{
		this.hit = false;
	}
	public void addEnemy()
	{
		enemyList.add(new Enemy("Standard"));
	}
	public void setHandler(ButtonHandler bh)
	{
		this.handler = bh;
	}

	public Updater getUpdater()
	{
		return updater;
	}
}
