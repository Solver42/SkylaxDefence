package se.SkyLax.MPT.GameObjects;

public class Tower {
	
	protected double angle;
	protected int rangeInPix;
	protected Shot shot;
	protected int rotationSpeed;
	
	protected int X;
	protected int Y;
	
	public Tower (int rotationSpeed, int X, int Y)
	{
		this.angle = 0.7;
		this.rotationSpeed = rotationSpeed;
		this.X = X;
		this.Y = Y;
	}
	
	public Shot getNewShot()
	{
		return new Shot(this);
	}
	
	public double getAngle()
	{
		return this.angle;
	}
	public int getX()
	{
		return this.X;
	}
	public int getY()
	{
		return this.Y;
	}
}
