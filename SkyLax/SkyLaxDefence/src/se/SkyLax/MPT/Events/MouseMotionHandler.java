package se.SkyLax.MPT.Events;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.Levels.Levels;

public class MouseMotionHandler {
	
	private int mouseX;
	private int mouseY;
	private ObjectGenerator objGen = null;
	
	public MouseMotionHandler (ObjectGenerator obj)
	{
		this.objGen = obj;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	public int getMouseX() {
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	
	public boolean creationOfTowerIsApproved()
	{
		if(objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1)
		return true;
		return false;
	}
	public void mabyCreateTower()
	{
		if(creationOfTowerIsApproved())
		{
		objGen.getGameObjectContainer().addTower(new SniperCastle("SniperCastle", this.mouseX, this.mouseY, 1));
		}
	}
}
