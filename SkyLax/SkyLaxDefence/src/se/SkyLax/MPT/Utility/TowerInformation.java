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
		case "SniperCastle":
			this.rotationSpeed = 1;
			this.repeat = 4;
			this.range = 210;
			break;
		case "MissileTower":
			this.rotationSpeed = 1;
			this.repeat = 10;
			this.range = 300;
			break;
		case "TowerOfDoom":
			this.rotationSpeed = 1;
			this.repeat = 2;
			this.range = 500;
			break;
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
	public int getRepeat()
	{
		return this.repeat;
	}
}
