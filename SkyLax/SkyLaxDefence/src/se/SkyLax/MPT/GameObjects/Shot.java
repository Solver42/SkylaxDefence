package se.SkyLax.MPT.GameObjects;
import se.SkyLax.MPT.Logics.*;

public class Shot {
	
	/*
	 * this class will produce
	 * a shot, which will
	 * get the following properties.
	 */
	
	protected int speed;
	protected double angle;
	
	protected int X;
	protected int Y;
	
	protected Trigonometry trig = null;
	

	/*
	 * trough passing a certain tower
	 * in this constructor we will
	 * get information on the starting
	 * position and in what direction
	 * i should travel. depending
	 * on that towers towerInformaion,
	 * more variables might be reached
	 * from the tower.
	 */
	
	
	
	public Shot(Tower t)
	{
		this.angle = t.getAngle();
		this.X = t.getX();
		this.Y = t.getY();
		trig = new Trigonometry(angle);
	}

	
	
	public int getX()
	{
		return X;
	}
	public int getY()
	{
		return Y;
	}
	
	public double getAngle()
	{
		return trig.getAngle();
	}

}
