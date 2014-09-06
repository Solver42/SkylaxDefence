package se.SkyLax.MPT.Events;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.TowerOfDoom;
import se.SkyLax.MPT.Graphics.GUIHelper;
import se.SkyLax.MPT.Levels.Levels;

public class MouseMotionHandler {

	private int mouseX;
	private int mouseY;
	private ObjectGenerator objGen = null;
	GUIHelper gui = null;
	private int kindOfTower = 0;

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

	private int clickedOnATowerFactory()
	{
		boolean clickedOnSniperCastle = objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] == 3; 
		boolean clickedOnRocketTower = objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] == 4; 
		boolean clickedOnLaserTower = objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] == 5; 
		
		if(clickedOnSniperCastle)
			return 3;
		else if(clickedOnRocketTower)
			return 4;
		else if(clickedOnLaserTower)
			return 5;
		return 0;
	}

	public void IClicked()
	{
		if(clickedOnATowerFactory() == 3)
		{
			this.kindOfTower = 3;
			gui.setKindOfTown(3);
			gui.setRange(350);
			//			gui.setKindOfTown(3);
		} else if(clickedOnATowerFactory() == 4)
		{
			this.kindOfTower = 4;
			gui.setKindOfTown(4);
			gui.setRange(200);
			//			gui.setKindOfTown(3);
		}
		else if(clickedOnATowerFactory() == 5)
		{
			this.kindOfTower = 5;
			gui.setKindOfTown(5);
			gui.setRange(500);
			//			gui.setKindOfTown(3);x
		}

		else if(creationOfTowerIsApproved())
		{
			if(this.kindOfTower == 3)
			{
				objGen.getGameObjectContainer().addTower(new SniperCastle(this.mouseX, this.mouseY, 1));
				gui.setKindOfTown(0);
				kindOfTower = 0;
			}
			else if(this.kindOfTower ==4)
			{
				objGen.getGameObjectContainer().addTower(new MissileTower(this.mouseX, this.mouseY, 1));
				gui.setKindOfTown(0);
				kindOfTower = 0;
			}
			else if(this.kindOfTower ==5)
			{
				objGen.getGameObjectContainer().addTower(new TowerOfDoom(this.mouseX, this.mouseY, 1));
				gui.setKindOfTown(0);
				kindOfTower = 0;
			}
		}
	}

	private boolean creationOfTowerIsApproved()
	{
		boolean isOnSolidGround = objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] !=2 && gui.mayBuild();
		if(isOnSolidGround)
			return true;
		return false;
	}


}