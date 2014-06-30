package se.SkyLax.MPT.GameObjects;
import se.SkyLax.MPT.Logics.*;

public class Shot {
	
	protected int speed;
	protected float angle;
	
	protected int X;
	protected int Y;
	
	protected Trigonometry trig = null;
	

	public Shot(float angle, int X, int Y)
	{
		this.angle = angle;
		trig = new Trigonometry(angle);
	}
	
	
}
