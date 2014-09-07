package se.SkyLax.MPT.UNDER_CONSTR;

import java.util.ArrayList;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.Levels.Levels;

public class EnemyList {
	
	private ArrayList<Enemy> enemyList= null;
	
	public EnemyList() {
		enemyList = new ArrayList<Enemy>();
		enemyList.add(new Enemy("Standard"));
	}
	
	public ArrayList<Enemy> getEnemyList()
	{
		return enemyList;
	}
	public void checkIfTargetIsHit(ConcreteShot shot)
	{
		boolean lessThanX = shot.getX()<((Levels.mapList[0][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_WIDTH*2));
		boolean moreThanX = shot.getX()>((Levels.mapList[0][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_WIDTH*2)-(Levels.UNIT_WIDTH*2));
		
		boolean lessThanY = shot.getY()<((Levels.mapList[1][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_HEIGHT*2));
		boolean moreThanY = shot.getY()>((Levels.mapList[1][(enemyList.get(0).getStep())]+1)*(Levels.UNIT_HEIGHT*2)-(Levels.UNIT_HEIGHT*2));
		
		if(lessThanX && moreThanX && lessThanY && moreThanY)
		{
			enemyList.remove(0);
		}
	}
}
