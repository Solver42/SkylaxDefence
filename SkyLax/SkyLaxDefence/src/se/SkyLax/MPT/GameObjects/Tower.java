package se.SkyLax.MPT.GameObjects;

public class Tower {
	
	protected float angle;
	protected int rangeInPix;
	protected Shot shot;
	protected int rotationSpeed;
	
	protected int X;
	protected int Y;
	
	public Tower (int rotationSpeed, int X, int Y)
	{
		this.rotationSpeed = rotationSpeed;
		this.X = X;
		this.Y = Y;
	}
	
	public Shot getNewShot()
	{
		return new Shot(angle, X, X);
	}
}
