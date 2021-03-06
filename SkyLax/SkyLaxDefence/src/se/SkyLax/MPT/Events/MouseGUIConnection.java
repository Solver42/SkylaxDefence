package se.SkyLax.MPT.Events;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.TowerOfDoom;
import se.SkyLax.MPT.Graphics.GUIHelper;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Levels.Levels;
import se.SkyLax.MPT.Utility.Money;

public class MouseGUIConnection {
	private int mouseX;
	private int mouseY;
	private ObjectGenerator objGen = null;
	private GUIHelper gui = null;
	private int kindOfTower = 0;
	private int[][] map = null;
	private Money money = null;
	private GameObjectList list = null;
	private int mapY;
	private int mapX;
	private boolean clickedOnSniperCastle;
	private boolean clickedOnRocketTower;
	private boolean clickedOnLaserTower;
	private boolean isOnSolidGround;
	private SwingTemplateJPanel swing = null;

	public MouseGUIConnection (ObjectGenerator obj, GUIHelper gui, SwingTemplateJPanel swing)
	{
		this.swing = swing;
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
		map = objGen.getGameObjectContainer().getLevel().getMap();
		mapY = this.mouseY/(Levels.UNIT_HEIGHT);
		mapX = this.mouseX/(Levels.UNIT_WIDTH);
		clickedOnSniperCastle = map[mapY][mapX] != 1 && map[mapY][mapX] == 3; 
		clickedOnRocketTower = map[mapY][mapX] != 1 && map[mapY][mapX] == 4; 
		clickedOnLaserTower = map[mapY][mapX] != 1 && map[mapY][mapX] == 5; 
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
		money = objGen.getMoneyClass();
		if(clickedOnATowerFactory() == 3)
		{
			if(swing.getHandler().getCasch()>=600)
			{
				this.kindOfTower = 3;
				gui.setKindOfTown(3);
				gui.setRange(350);
			}
		} else if(clickedOnATowerFactory() == 4)
		{
			if(swing.getHandler().getCasch()>=10000)
			{
				this.kindOfTower = 4;
				gui.setKindOfTown(4);
				gui.setRange(440);
			}
		}
		else if(clickedOnATowerFactory() == 5)
		{
			if(swing.getHandler().getCasch()>=4000)
			{
				this.kindOfTower = 5;
				gui.setKindOfTown(5);
				gui.setRange(500);
			}
		}
		else if(creationOfTowerIsApproved())
		{
			list = objGen.getGameObjectContainer();
			money = objGen.getMoneyClass();
			if(this.kindOfTower == 3)
			{
				list.addTower(new SniperCastle(this.mouseX, this.mouseY, Math.PI*1.5));
				swing.getHandler().modifyCasch(-700);
			}
			else if(this.kindOfTower ==4)
			{
				list.addTower(new MissileTower(this.mouseX, this.mouseY, Math.PI*1.5));
				swing.getHandler().modifyCasch(-10000);
			}
			else if(this.kindOfTower ==5)
			{
				list.addTower(new TowerOfDoom(this.mouseX, this.mouseY, Math.PI*1.5));
				swing.getHandler().modifyCasch(-4000);
			}

			gui.setKindOfTown(0);
			kindOfTower = 0;
		}
	}
	private boolean creationOfTowerIsApproved()
	{
		map = objGen.getGameObjectContainer().getLevel().getMap();
		mapY = this.mouseY/(Levels.UNIT_HEIGHT);
		mapX = this.mouseX/(Levels.UNIT_WIDTH);
		isOnSolidGround = map[mapY][mapX] != 1 && map[mapY][mapX] !=2 && gui.mayBuild();
		if(isOnSolidGround)
			return true;
		return false;
	}
}