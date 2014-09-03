package se.SkyLax.MPT.GameObjects;

public class Laser extends Shot implements ConcreteShot{

	private int speed;
	
	public Laser(Tower t) {
		super(t);
		speed = 30;
	}

	@Override
	public void travel() {
		this.X += (trig.getSimpleXY("X"))*speed;
		this.Y += (trig.getSimpleXY("Y"))*speed;
		
	}


}
