package se.SkyLax.MPT.GameObjects;

import se.SkyLax.MPT.Utility.TowerInformation;

public class Tower {
	
	protected double angle;
	protected int rangeInPix;
	protected Shot shot;
	protected int rotationSpeed;
	
	protected int X;
	protected int Y;
	protected TowerInformation towIn = null;
	
	public Tower (String kind, int X, int Y, double angle)
	{
		
		//TODO get this modulos right!
		towIn = new TowerInformation(kind);
		this.angle = angle;
		this.X = X/*-(SwingTemplateJPanel.CANVAS_WIDTH%X+15)*/;
		this.Y = Y/*-(SwingTemplateJPanel.CANVAS_HEIGHT%Y+15)*/;
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
	public TowerInformation getTowerInfo()
	{
		return this.towIn;
	}

}
