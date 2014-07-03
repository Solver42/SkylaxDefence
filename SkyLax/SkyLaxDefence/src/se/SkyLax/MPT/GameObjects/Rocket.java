package se.SkyLax.MPT.GameObjects;

public class Rocket extends Shot implements ShootActions{
	
	private int speed;
	
	public Rocket(Tower t) {
		super(t);
		speed = 10;
	}
	
	public synchronized void run()
	{
		
		//this.X += 1;
		//this.Y += 1;
		
		
		this.X += (trig.getSimpleXY("X"))*speed;
		this.Y += (trig.getSimpleXY("Y"))*speed;
	}
	public int getX()
	{
		return super.getX();
	}
	
	public int getY()
	{
		return super.getY();
	}

}
