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

	public GUIHelper()
	{
		this.objGen = objGen;
//		screen = new TheFrame();
//		screen.setObjectContainerOfJPanel(objGen);
		try {
			rocketImg = ImageIO.read(new File("img/Rocket.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				if(objGen.ifJustShoot(tower))
				{
					g2d.setColor(Color.GREEN);
					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
				else
				{
					g2d.setColor(Color.RED);
					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
			else if(tower instanceof MissileTower)
			{
				if(objGen.ifJustShoot(tower))
				{
					g2d.setColor(Color.WHITE);
					g2d.fillOval(tower.getX()-100, tower.getY()-100, 200, 200);
				}
				else
				{
					g2d.setColor(Color.CYAN);
					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
		}
		objGen.clearFireArray();
		for(ConcreteShot shot : objGen.getGameObjectContainer().getListOfAllShots())
		{
			if(shot instanceof GunShot)
			{
				g2d.setColor(Color.GRAY);
				g2d.fillOval(shot.getX(), shot.getY(), 10, 10);
			}
			else if(shot instanceof Rocket)
			{
				g2d.setColor(Color.RED);
				g2d.drawImage(rocketImg, shot.getX()-128, shot.getY()-128,null);
			}
		}
	}


}
