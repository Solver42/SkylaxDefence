package se.SkyLax.MPT.Enemy;

public class Enemy {

	private int step = 0;
	private String sort = "";
	private int health = 1000;
	private final int START_HEALT = 1000;
	
	public Enemy(String sort)
	{
		this.sort = sort;
		this.health = START_HEALT;
	}
	
	public void walk()
	{
		step++;
		if(step>60) step = 0;

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
	public int getStartHelth()
	{
		return START_HEALT;
	}

}
