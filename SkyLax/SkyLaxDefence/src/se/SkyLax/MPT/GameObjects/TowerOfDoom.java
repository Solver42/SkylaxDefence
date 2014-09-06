package se.SkyLax.MPT.GameObjects;

public class TowerOfDoom extends Tower{

	public TowerOfDoom(int X, int Y, double angle) {
		super(X, Y, angle, 2, 500, 50);
	}

	
	public int getRepeat()
	{
		return this.repeat;
	}
}
