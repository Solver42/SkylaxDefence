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
	private int kindOfTower = 3;

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
		if(objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] == 3)
			return 3;
		else if(objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] == 4)
			return 4;
		else if(objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] == 5)
			return 5;
		return 0;
	}

	public void IClicked()
	{
		if(clickedOnATowerFactory() == 3)
		{
			this.kindOfTower = 3;
			gui.setRange(210);
			//			gui.setKindOfTown(3);
		} else if(clickedOnATowerFactory() == 4)
		{
			this.kindOfTower = 4;
			gui.setRange(300);
			//			gui.setKindOfTown(3);
		}
		else if(clickedOnATowerFactory() == 5)
		{
			this.kindOfTower = 5;
			gui.setRange(500);
			//			gui.setKindOfTown(3);x
		}

		else if(creationOfTowerIsApproved())
		{
			if(this.kindOfTower == 3)
				objGen.getGameObjectContainer().addTower(new SniperCastle("SniperCastle", this.mouseX, this.mouseY, 1));
			else if(this.kindOfTower ==4)
				objGen.getGameObjectContainer().addTower(new MissileTower("MissileTower", this.mouseX, this.mouseY, 1));
			else if(this.kindOfTower ==5)
				objGen.getGameObjectContainer().addTower(new TowerOfDoom("TowerOfDoom", this.mouseX, this.mouseY, 1));
		}
	}

	private boolean creationOfTowerIsApproved()
	{
		if(objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] != 1 && objGen.getGameObjectContainer().getLevel().getMap()[this.mouseY/(Levels.UNIT_HEIGHT)][this.mouseX/(Levels.UNIT_WIDTH)] !=2 && gui.mayBuild())
			return true;
		return false;
	}


}