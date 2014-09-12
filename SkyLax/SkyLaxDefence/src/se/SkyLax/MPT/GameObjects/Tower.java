package se.SkyLax.MPT.GameObjects;

import se.SkyLax.MPT.Levels.Levels;

public class Tower {
	
	protected double angle;
	protected int rangeInPix;
	protected Shot shot;
	protected int rotationSpeed;
	protected int X;
	protected int Y;
	protected int repeat;
	protected int speedOfShot;
//	protected TowerInformation towIn = null;
	
	public Tower (int X, int Y, double angle, int repeat, int range, int speedOfShoot)
	{
//		towIn = new TowerInformation(kind);
		this.speedOfShot = speedOfShoot;
		this.rangeInPix = range;
		this.repeat = repeat;
		this.angle = angle;
		this.X = (X-(X%Levels.UNIT_WIDTH))+(Levels.UNIT_WIDTH/2);
		this.Y = (Y-(Y%Levels.UNIT_HEIGHT))+(Levels.UNIT_WIDTH/2);
	}
	public ConcreteShot getNewShot(String shotType)
	{
		if(shotType.equals("GunShot"))
		{
			return new GunShot(this);
		}
		else if(shotType.equals("Rocket"))
		{
			return new Rocket(this);
		}
		else if(shotType.equals("Laser"))
		{
			return new Laser(this);
		}
		return new GunShot(this);
	}
	public double getAngle()
	{
		return this.angle;
	}
	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	public int getX()
	{
		return this.X;
	}
	public int getY()
	{
		return this.Y;
	}
	public int getRepeat()
	{
		return this.repeat;
	}
	public int getRangeInPix()
	{
		return rangeInPix;
	}
	public int getSpeedOfShot()
	{
		return this.speedOfShot;
	}
//	public TowerInformation getTowerInfo()
//	{
//		return this.towIn;
//	}

}
