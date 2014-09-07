package se.SkyLax.MPT.UNDER_CONSTR;

public class Enemy {

	private int step = 0;
	private String sort = "";
	private int health = 1000;
	
	public Enemy(String sort)
	{
		this.sort = sort;
	}
	
	public void walk()
	{
		step++;
		if(step>30) step = 0;
		this.health = 1000;
	}
	
	public int getStep()
	{
		return this.step;
	}
	public int getHealth()
	{
		return this.health;
	}
	public void setHealt(int i)
	{
		this.health-=i;
	}

}
