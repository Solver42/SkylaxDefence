package se.SkyLax.MPT.GameObjects;


/*
 * creates a simple tower,
 * though passing a string
 * that will do so that certain
 * information can be retrieved
 * from a tower information object
 */

public class MissileTower extends Tower{

	public MissileTower(String kind, int X, int Y, double angle) {
		super("SimpleTower" , X, Y, angle);
	}



}