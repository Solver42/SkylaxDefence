package se.SkyLax.MPT.GameObjects;

import java.util.ArrayList;


public class GameObjectList{


	ArrayList<ConcreteShot> rockets;
	ArrayList<Tower> towers;
	
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

	}
	public void addTower(Tower t)
	{
		towers.add(t);
	}
	public void addShot(int index, String kindOfShot)
	{
		rockets.add(towers.get(index).getNewShot(kindOfShot));
	}
	

	public ArrayList<ConcreteShot> getListOfAllShots()
	{
		return this.rockets;
	}
	
	public ArrayList<Tower> getTowerList()
	{
		return this.towers;
	}
}
