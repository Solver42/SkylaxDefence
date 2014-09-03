package se.SkyLax.MPT.GameObjects;

public class GunShot extends Shot implements ConcreteShot{
	
	
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
	
	public GunShot(Tower t) {
		super(t);
		speed = 23;
	}
	
	public synchronized void travel()
	{

		this.X += (trig.getSimpleXY("X"))*speed;
		this.Y += (trig.getSimpleXY("Y"))*speed;
	}

}
