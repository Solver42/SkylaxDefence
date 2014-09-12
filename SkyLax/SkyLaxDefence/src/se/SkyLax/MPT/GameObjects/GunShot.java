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
	
	private int damage = 100;
	Tower t = null;
	
	public GunShot(Tower t) {
		super(t);
		this.t = t;
		speed = 23;
	}
	
	public synchronized void travel()
	{

		this.X += (trig.getSimpleXY("X"))*t.getSpeedOfShot();
		this.Y += (trig.getSimpleXY("Y"))*t.getSpeedOfShot();
	}
	@Override
	public int getDamage() {
		return damage;
	}

}
