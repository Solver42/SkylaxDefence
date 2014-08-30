package se.SkyLax.MPT.GameObjects;

import se.SkyLax.MPT.Utility.TowerInformation;

/*
 * creates a simple tower,
 * though passing a string
 * that will do so that certain
 * information can be retrieved
 * from a tower information object
 */

public class SimpleTower extends Tower{

	public SimpleTower(String kind, int X, int Y, double angle) {
		super("SimpleTower" , X, Y, angle);
	}



}
