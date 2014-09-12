package se.SkyLax.MPT.GameObjects;

public class Laser extends Shot implements ConcreteShot{
	private int damage = 70;
	Tower t = null;
	
	public Laser(Tower t) {
		super(t);
		this.t = t;
		speed = 50;
	}
	@Override
	public void travel() {
		this.X += (trig.getSimpleXY("X"))*t.getSpeedOfShot();
		this.Y += (trig.getSimpleXY("Y"))*t.getSpeedOfShot();
		
	}
	@Override
	public int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
}
