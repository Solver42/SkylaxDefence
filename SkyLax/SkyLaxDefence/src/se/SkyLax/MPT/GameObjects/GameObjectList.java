package se.SkyLax.MPT.GameObjects;

import java.util.ArrayList;

import se.SkyLax.MPT.Levels.Levels;

public class GameObjectList{
	private ArrayList<ConcreteShot> rockets;
	private ArrayList<Tower> towers;
	private Levels level = null;
	private int atMapX;
	private int atMapY;
	
	/*
	 * this class contains the list
	 * of the different shots and
	 * towers. Contains two methods
	 * for adding towers or shots
	 * to a certain tower.
	 */

	public GameObjectList()
	{
		rockets = new ArrayList<ConcreteShot>();
		towers = new ArrayList<Tower>();
		level = new Levels();

	}
	public void addTower(Tower t)
	{
		towers.add(t);
		//TODO
		atMapX = t.getX()/(Levels.UNIT_WIDTH);
		atMapY = t.getY()/(Levels.UNIT_HEIGHT);
		level.setMap(atMapX, (atMapY), 2);
	}
	public void addShot(int index, String kindOfShot)
	{
		rockets.add(towers.get(index).getNewShot(kindOfShot));
	}
	public void addShotGeneric(Tower tower, String kindOfShot)
	{
		rockets.add(tower.getNewShot(kindOfShot));
	}
	public ArrayList<ConcreteShot> getListOfAllShots()
	{
		return this.rockets;
	}
	public ArrayList<Tower> getTowerList()
	{
		return this.towers;
	}
	public Levels getLevel()
	{
		return this.level;
	}
}
