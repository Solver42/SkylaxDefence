package se.SkyLax.MPT.Logics;

public class Trigonometry {

	private double angle;

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

	boolean bool = true;

	int i = 5;
	private void modifyAngle()
	{
		if(i>=10) bool = false;
		else if(i<=0) bool = true;

		if(bool)
		{
			angle+=0.1;
			i++;
		}
		else
		{
			angle-=0.1;
			i--;
		}
	}



	public double getSpacedXY(String XorY){
		modifyAngle();
		if(XorY.equals("X"))
		{
			return Math.cos(angle);
		}else if (XorY.equals("Y"))
		{
			return Math.sin(angle);
		}
		return -1;
	}
	public double getAngle()
	{
		return this.angle;
	}
}
