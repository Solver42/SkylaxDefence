package se.SkyLax.MPT.GameObjects;

import se.SkyLax.MPT.Logics.Trigonometry;

public class Rocket extends Shot implements ConcreteShot{
	
	
	/*
	 * this shot gets a certain
	 * speed and perhaps other
	 * properties. the must-implemented
	 * method travel() changes this
	 * class's inherited variables
	 * 'X' and 'Y', according to
	 * how we use the trig-object.
	 */
	
	private int speed;
	
	public Rocket(Tower t) {
		super(t);
		speed = 9;
	}
	
	public synchronized void travel()
	{
		
		//this.X += 1;
		//this.Y += 1;
		
		
		this.X += (trig.getSpacedXY("X"))*speed;
		this.Y += (trig.getSpacedXY("Y"))*speed;
	}
	
	public int getX()
	{
		return super.X;
	}
	public int getY()
	{
		return super.Y;
	}
	public double getAngle()
	{
		return super.trig.getAngle();
	}

}
