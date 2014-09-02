package se.SkyLax.MPT.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.GunShot;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.Rocket;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Levels.Levels;

public class GUIHelper{

	private BufferedImage rocketImg;
	//	private TheFrame screen = null;
	private ObjectGenerator objGen = null;

	private int GUN_WIDTH = 10;
	private int ROCKET_WIDTH = 70;
	private int ROCKET_EXP = 200;
	private int mouseX;
	private int mouseY;
	private boolean mayBuild = false;

	private Color gray = new Color(204, 204, 204, 128);
	private Color red = new Color(204, 51, 51, 128);
	

	public GUIHelper()
	{
		//		screen = new TheFrame();
		//		screen.setObjectContainerOfJPanel(objGen);
		try {
			rocketImg = ImageIO.read(new File("img/Rocket.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setGUI_XY(int x, int y)
	{
		this.mouseX = x;
		this.mouseY = y;
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
				if(objGen.getGameObjectContainer().getLevel().getMap()[i][j]==0)
				{
					g2d.setColor(Color.DARK_GRAY);
					g2d.fillRect(j*Levels.UNIT_WIDTH, i*Levels.UNIT_HEIGHT, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
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
				g2d.setColor(Color.RED);
				g2d.fillOval(shot.getX()-ROCKET_WIDTH/2, shot.getY()-ROCKET_WIDTH/2, ROCKET_WIDTH, ROCKET_WIDTH);
			}
		}
		objGen.clearJustShoot();
		if(mayBuild)
		{
			g2d.setColor(gray);
			g2d.fillOval(mouseX-150, mouseY-150, 300, 300);
		}else
		{
			g2d.setColor(red);
			g2d.fillOval(mouseX-150, mouseY-150, 300, 300);
		}

	}


}
