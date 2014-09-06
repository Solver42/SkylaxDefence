package se.SkyLax.MPT.GameObjects;

public class Laser extends Shot implements ConcreteShot{

	private int speed;
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


}
