package se.SkyLax.MPT.GameObjects;

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
	private int damage = 250;
	private int speed;
	Tower t = null;
	
	public Rocket(Tower t) {
		super(t);
		this.t = t;
		speed = 3;
	}
	
	public synchronized void travel()
	{
		this.X += (trig.getSimpleXY("X"))*t.getSpeedOfShot();
		this.Y += (trig.getSimpleXY("Y"))*t.getSpeedOfShot();
	}

	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}


}
