package se.SkyLax.MPT.Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.Enemy.Enemy;
import se.SkyLax.MPT.Enemy.EnemyList;
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


	private Image img = null;
	private BufferedImage sniperOrig = null;
	private BufferedImage sniperShot = null;
	private BufferedImage rocketShot = null;
	private BufferedImage missileOrig = null;
	private BufferedImage laserOrig = null;
	private ObjectGenerator objGen = null;
	private EnemyList enemyList = null;

	private final int GUN_WIDTH = 10;
	private final int ROCKET_WIDTH = 70;
	private final int ROCKET_EXP = 200;
	private final int LASER_L = 50;
	private final int LASER_EXP = 15;
	private int mouseX;
	private int mouseY;
	private boolean mayBuild = false;
	private ArrayList<Tower> towers = null;
	
	private double laserStopX;
	private double laserStopY;
	
	private int towerXdiff;
	private int towerYdiff;

	private int kindOfTower = 0;

	private Color gray = new Color(0, 255, 0, 128);
	private Color red = new Color(204, 51, 51, 128);

	private int range;
	private int[][] map;


	public GUIHelper(EnemyList enemy, ObjectGenerator obj)
	{
		objGen = obj;
		enemyList = enemy;
		img = new ImageIcon("img/texture.jpg").getImage();
		try {
			sniperOrig = ImageIO.read(new File("img/towers/sniper_orig.png"));
			sniperShot = ImageIO.read(new File("img/towers/sniper_shot.png"));
			rocketShot = ImageIO.read(new File("img/Rocket.png"));
			missileOrig = ImageIO.read(new File("img/towers/rocket_orig.png"));
			laserOrig = ImageIO.read(new File("img/towers/laser_orig.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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


	public void drawThis(Graphics2D g2d, Graphics g)
	{
		g.drawImage(img, 0, 0, null);

		
		AffineTransform at = new AffineTransform();
		
		map = objGen.getGameObjectContainer().getLevel().getMap();
		
		for (int i = 0; i<map.length; i++)
		{
			for (int j = 0; j<map[1].length; j++)
			{
				switch (map[i][j])
				{
				case 1:

					g2d.setColor(new Color(0,0,0, 198));
					g2d.fillRect(j*Levels.UNIT_WIDTH, i*Levels.UNIT_HEIGHT, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
					break;

				case 3:

					g2d.setColor(Color.BLACK);
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
		
		

		towerXdiff = Levels.UNIT_WIDTH/2-Levels.UNIT_WIDTH;
		towerYdiff = Levels.UNIT_HEIGHT/2-Levels.UNIT_HEIGHT;
		towers = objGen.getGameObjectContainer().getTowerList();
		
		for(Tower tower : towers){
			at.translate(Levels.UNIT_WIDTH / 2, Levels.UNIT_HEIGHT / 2);
			at.setToTranslation(tower.getX(), tower.getY());
              // 3. do the actual rotation
              at.rotate(tower.getAngle()+Math.PI/2);
              at.translate(towerXdiff, towerYdiff);
			if(tower instanceof SniperCastle)
			{
				if(objGen.getJustShootList().contains(tower))
				{
					g2d.setColor(Color.WHITE);
//					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
					g2d.drawImage(sniperShot, at, null);
				}
				else
				{
					
					

//		              // 2. just a scale because this image is big
//		              at.scale(0.5, 0.5);
		              // 1. translate the object so that you rotate it around the 
		              //    center (easier :))
		              
					
					g2d.drawImage(sniperOrig, at, null);
//					g2d.setColor(new Color(0,0,0,200));
//					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
			else if(tower instanceof MissileTower)
			{
				if(objGen.getJustShootList().contains(tower))
				{
					g2d.setColor(Color.ORANGE);
					g2d.fillOval(tower.getX()-ROCKET_EXP/2, tower.getY()-ROCKET_EXP/2, ROCKET_EXP, ROCKET_EXP);
				}
				else
				{
					g2d.drawImage(missileOrig, at, null);
//					g2d.setColor(Color.WHITE);
//					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
			else if(tower instanceof TowerOfDoom)
			{
				if(objGen.getJustShootList().contains(tower))
				{
					g2d.setColor(Color.YELLOW);
					g2d.fillOval(tower.getX()-LASER_EXP/2, tower.getY()-LASER_EXP/2, LASER_EXP, LASER_EXP);
				}
				else
				{
					g2d.drawImage(laserOrig, at, null);
//					g2d.setColor(new Color(34,45,120,100));
//					g2d.fillRect(tower.getX()-(Levels.UNIT_WIDTH/2), tower.getY()-(Levels.UNIT_HEIGHT/2), Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
				}
			}
		}
		

		for(ConcreteShot shot : objGen.getGameObjectContainer().getListOfAllShots())
		{
			
			
			
			if(shot instanceof GunShot)
			{
				g2d.setColor(Color.WHITE);
				g2d.fillOval(shot.getX(), shot.getY(), GUN_WIDTH, GUN_WIDTH);
			}
			else if(shot instanceof Rocket)
			{
				at.translate(Levels.UNIT_WIDTH / 2, Levels.UNIT_HEIGHT / 2);
				at.setToTranslation(shot.getX(), shot.getY());
	              // 3. do the actual rotation
	              at.rotate(shot.getAngle()+Math.PI/2);
	              at.translate(rocketShot.getWidth()/2-rocketShot.getWidth(), rocketShot.getHeight()/2-rocketShot.getHeight());
	              
				g2d.drawImage(rocketShot, at, null);
//				g2d.setColor(Color.CYAN);
//				g2d.fillOval(shot.getX()-ROCKET_WIDTH/2, shot.getY()-ROCKET_WIDTH/2, ROCKET_WIDTH, ROCKET_WIDTH);
			}
			else if(shot instanceof Laser)
			{
				laserStopX = (shot.getX() + Math.cos(shot.getAngle())*LASER_L);
				laserStopY = (shot.getY() + Math.sin(shot.getAngle())*LASER_L);
				
				g2d.setColor(Color.GREEN);
				g2d.drawLine(shot.getX(), shot.getY(), (int)(laserStopX), (int)laserStopY);
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
		int enemyX;
		int enemyY;
		
		for(Enemy enemy : enemyList.getEnemyList())
		{
			enemyX = ((Levels.mapList[0][enemy.getStep()]+1)*(Levels.UNIT_WIDTH*2))-(Levels.UNIT_WIDTH);
			enemyY = ((Levels.mapList[1][enemy.getStep()]+1)*(Levels.UNIT_HEIGHT*2))-(Levels.UNIT_HEIGHT);
			
//			if(!enemyList.getHit()){
			g2d.setColor(new Color(0, 0, 255, (255*enemy.getHealth()/1000)));
			g2d.fillRect(enemyX-Levels.UNIT_WIDTH/2, enemyY-Levels.UNIT_HEIGHT/2, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
//			}
//			else
//			{
//				g2d.setColor(Color.RED);
				g2d.fillRect(enemyX-Levels.UNIT_WIDTH/2, enemyY-Levels.UNIT_HEIGHT/2, Levels.UNIT_WIDTH, Levels.UNIT_HEIGHT);
//				enemyList.turOffHit();
//			}
		}
		
		
		
	}
	
	


}
