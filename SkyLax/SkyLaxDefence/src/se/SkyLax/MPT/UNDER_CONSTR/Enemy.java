package se.SkyLax.MPT.UNDER_CONSTR;

public class Enemy {

	private int step = 0;
	private String sort = "";
	private int health;
	
	public Enemy(String sort)
	{
		this.sort = sort;
	}
	
	public void walk()
	{
		step++;
		System.out.println("I walked, am now on step " + step);
	}
	
	public int getStep()
	{
		return this.step;
	}

}
