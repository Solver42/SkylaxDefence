package se.SkyLax.MPT.GameObjects;

public class Rocket extends Shot implements ShootActions{

	private int speed;

	public Rocket(float angle, int X, int Y)
	{
		super(angle, X, Y);
		speed = 10;
	}

	public synchronized void run()
	{
		this.X += (trig.getSimpleXY("X"))*speed;
		this.Y += (trig.getSimpleXY("Y"))*speed;
	}

}
