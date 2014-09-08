package se.SkyLax.MPT.Enemy;

import java.util.ArrayList;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.Levels.Levels;

public class EnemyList {
	
	private ArrayList<Enemy> enemyList= null;
	private boolean hit = false;
	
	public EnemyList() {
		enemyList = new ArrayList<Enemy>();
	}
	
	public ArrayList<Enemy> getEnemyList()
	{
		return enemyList;
	}
	public boolean checkIfTargetIsHit(ConcreteShot shot)
	{
		boolean lessThanX = shot.getX()<((Levels.mapList[0][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_WIDTH*2));
		boolean moreThanX = shot.getX()>((Levels.mapList[0][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_WIDTH*2)-(Levels.UNIT_WIDTH*2));
		
		boolean lessThanY = shot.getY()<((Levels.mapList[1][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_HEIGHT*2));
		boolean moreThanY = shot.getY()>((Levels.mapList[1][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_HEIGHT*2)-(Levels.UNIT_HEIGHT*2));
		
		if(lessThanX && moreThanX && lessThanY && moreThanY)
		{
			
			hit = true;
			enemyList.get(0).setHealt(shot.getDamage());
//			System.out.println(enemyList.get(0).getHealth());
			if(enemyList.get(0).getHealth()<=0)
			{
				enemyList.remove(0);
			}
			return true;
			
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
}
