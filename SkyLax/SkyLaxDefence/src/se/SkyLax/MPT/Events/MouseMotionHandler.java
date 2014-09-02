package se.SkyLax.MPT.Events;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.Graphics.GUIHelper;
import se.SkyLax.MPT.Levels.Levels;

public class MouseMotionHandler {
	
	private int mouseX;
	private int mouseY;
	private ObjectGenerator objGen = null;
	GUIHelper gui = null;
	
	public MouseMotionHandler (ObjectGenerator obj, GUIHelper gui)
	{
		this.objGen = obj;
		this.gui = gui;
	}
	
	public void setCoordinates(int x, int y)
	{
		this.mouseX = x;
		this.mouseY = y;
	}

	
	//The following has to be in a separate class
	//to avoid that we neet to have a ObjectGenarator in the
	//constructor. this is because the GHIHElper needs to
	//be able to reach thi class without
	//passing an ObjectGen
	
	private boolean creationOfTowerIsApproved()
	{
		if(objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] !=2)
		return true;
		return false;
	}
	
	public void creationOfTowerOK()
	{
		if(objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] !=2)
		gui.setAllowedToBuild(true);
		else
		gui.setAllowedToBuild(false);
	}
	
	public void mabyCreateTower(String kind)
	{
		if(creationOfTowerIsApproved())
		{
			if(kind.equals("SniperCastle"))
				objGen.getGameObjectContainer().addTower(new SniperCastle("SniperCastle", this.mouseX, this.mouseY, 1));
			else if(kind.equals("MissileTower"))
				objGen.getGameObjectContainer().addTower(new MissileTower("RocketTower", this.mouseX, this.mouseY, 1));
		}
	}
}