package se.SkyLax.MPT.Utility;

public class TowerInformation {
	private int rotationSpeed;
	private int repeat;
	private String kind = "";
	private int range;

	
	/*
	 * this class will contain
	 * information about the tower
	 * depending on which sort it is.
	 * It will contain all the properties
	 * that certain tower has.
	 */
	
	public TowerInformation(String kindOfTower) {
	
		setInformation(kindOfTower);
		kind = kindOfTower;
		
	}
	private void setInformation(String kind)
	{
		
		/*
		 * depending on which sort of tower it
		 * is, the instance variables
		 * will get different values.
		 */
		switch (kind)
		{
		case "SimpleTower":
			this.rotationSpeed = 1;
			this.repeat = 1000;
			this.range = 48;
		}
	}
	public int getRotationSpeed()
	{
		return this.rotationSpeed;
	}
	public int getRange()
	{
		return this.range;
	}
	public String getKindOfTower()
	{
		return this.kind;
	}
	

}
