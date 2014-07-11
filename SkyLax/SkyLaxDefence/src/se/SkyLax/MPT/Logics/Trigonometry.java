package se.SkyLax.MPT.Logics;

public class Trigonometry {

	private final double angle;
	
	public Trigonometry(double angle)
	{
		this.angle = angle;
		
	}
	
	/*
	 * this public method will return
	 * something between 0 and 1
	 * depending on the class variable
	 * angle.
	 */
	
	public double getSimpleXY(String XorY){
		if(XorY.equals("X"))
		{
			return Math.cos(angle);
		}else if (XorY.equals("Y"))
		{
			return Math.sin(angle);
		}
		return -1;
	}
}
