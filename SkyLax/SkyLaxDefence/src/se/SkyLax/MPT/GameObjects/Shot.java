package se.SkyLax.MPT.GameObjects;
import se.SkyLax.MPT.Logics.*;

public class Shot {
	
	protected int speed;
	protected double angle;
	
	protected int X;
	protected int Y;
	
	protected Trigonometry trig = null;
	

	public Shot(Tower t)
	{
		this.angle = t.getAngle();
		this.X = t.getX();
		this.Y = t.getY();
		trig = new Trigonometry(angle);
	}
	public int getX()
	{
		return this.X;
	}
	
	public int getY()
	{
		return this.Y;
	}
}
