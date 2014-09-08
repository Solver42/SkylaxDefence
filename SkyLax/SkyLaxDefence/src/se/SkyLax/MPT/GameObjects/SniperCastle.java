package se.SkyLax.MPT.GameObjects;


/*
 * creates a simple tower,
 * though passing a string
 * that will do so that certain
 * information can be retrieved
 * from a tower information object
 */

public class SniperCastle extends Tower{

	public SniperCastle(int X, int Y, double angle) {
		super(X, Y, angle, 4, 350, 16);
	}
	
	public int getRepeat()
	{
		return this.repeat;
	}
	
}
