package se.SkyLax.MPT.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.GunShot;
import se.SkyLax.MPT.GameObjects.Laser;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.Rocket;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.GameObjects.TowerOfDoom;
import se.SkyLax.MPT.Levels.Levels;

public class GUIHelper{


	//	private TheFrame screen = null;
	private ObjectGenerator objGen = null;

	private final int GUN_WIDTH = 10;
	private final int ROCKET_WIDTH = 70;
	private final int ROCKET_EXP = 200;
	private final int LASER_L = 50;
	private final int LASER_EXP = 15;
	private int mouseX;
	private int mouseY;
	private boolean mayBuild = false;

	private int kindOfTower = 0;

	private Color gray = new Color(204, 204, 204, 128);
	private Color red = new Color(204, 51, 51, 128);

	private int range;


	public GUIHelper()
	{

	}

	public boolean mayBuild()
	{
		return mayBuild;
	}


	public void setKindOfTown(int kind)
	{
		this.kindOfTower = kind;
	}

	public void setGUI_XY(int x, int y)
	{
		this.mouseX = x;
		this.mouseY = y;
	}

	public void setRange(int rang)
	{
		this.range = rang;
	}

	public void setAllowedToBuild(boolean is)
	{
		mayBuild = is;
	}


	public void drawThis(Graphics2D g2d, ObjectGenerator objGen)
	{
		for (int i = 0; i<objGen.getGameObjectContainer().getLevel().getMap().length; i++)
		{
			for (int j = 0; j<objGen.getGameObjectContainer().getLevel().getMap()[1].length; j++)
			{
				switch (objGen.getGameObjectContainer().getLevel().getMap()[i][j])
				{
				case 0:

					g2d.setColor(Color.DARK_GRAY);
					g2d.fillRect(j*Levels.UNIT_WIDTH, i*Levels.UNIT_HEIGHT, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
					break;

				case 3:

					g2d.setColor(Color.GRAY);
					g2d.fillRect(j*Levels.UNIT_WIDTH, i*Levels.UNIT_HEIGHT, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
					break;
				case 4:

					g2d.setColor(Color.WHITE);
					g2d.fillRect(j*Levels.UNIT_WIDTH, i*Levels.UNIT_HEIGHT, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
					break;
				case 5:

					g2d.setColor(Color.GREEN);
					g2d.fillRect(j*Levels.UNIT_WIDTH, i*Levels.UNIT_HEIGHT, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
					break;
				}
			}
		}
		for(Tower tower : objGen.getGameObjectContainer().getTowerList()){
			if(tower instanceof SniperCastle)
			{
				if(objGen.getJustShootList().contains("SniperCastle"))
				{
					g2d.setColor(Color.WHITE);
					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
				else
				{
					g2d.setColor(Color.GRAY);
					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
			else if(tower instanceof MissileTower)
			{
				if(objGen.getJustShootList().contains("MissileTower"))
				{
					g2d.setColor(Color.ORANGE);
					g2d.fillOval(tower.getX()-ROCKET_EXP/2, tower.getY()-ROCKET_EXP/2, ROCKET_EXP, ROCKET_EXP);
				}
				else
				{
					g2d.setColor(Color.WHITE);
					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
			else if(tower instanceof TowerOfDoom)
			{
				if(objGen.getJustShootList().contains("LaserTower"))
				{
					g2d.setColor(Color.YELLOW);
					g2d.fillOval(tower.getX()-LASER_EXP/2, tower.getY()-LASER_EXP/2, LASER_EXP, LASER_EXP);
				}
				else
				{
					g2d.setColor(Color.GREEN);
					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
		}

		for(ConcreteShot shot : objGen.getGameObjectContainer().getListOfAllShots())
		{
			if(shot instanceof GunShot)
			{
				g2d.setColor(Color.GRAY);
				g2d.fillOval(shot.getX()-GUN_WIDTH/2, shot.getY()-GUN_WIDTH/2, GUN_WIDTH, GUN_WIDTH);
			}
			else if(shot instanceof Rocket)
			{
				g2d.setColor(Color.CYAN);
				g2d.fillOval(shot.getX()-ROCKET_WIDTH/2, shot.getY()-ROCKET_WIDTH/2, ROCKET_WIDTH, ROCKET_WIDTH);
			}
			else if(shot instanceof Laser)
			{
				g2d.setColor(Color.YELLOW);
				g2d.drawLine(shot.getX(), shot.getY(), (int)(shot.getX() + Math.cos(shot.getAngle())*LASER_L), (int)(shot.getY() + Math.sin(shot.getAngle())*LASER_L));
			}

		}
//		objGen.clearJustShoot();
		if(kindOfTower != 0)
		{
			if(mayBuild)
			{
				g2d.setColor(gray);
				g2d.fillOval(mouseX-range/2, mouseY-range/2, range, range);
			}else
			{
				g2d.setColor(red);
				g2d.fillOval(mouseX-range/2, mouseY-range/2, range, range);
			}
		}

	}


}
