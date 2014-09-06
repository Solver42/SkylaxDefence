package se.SkyLax.MPT.UNDER_CONSTR;

import java.util.ArrayList;

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
}
