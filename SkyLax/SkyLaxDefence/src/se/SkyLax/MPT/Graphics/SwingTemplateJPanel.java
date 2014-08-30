package se.SkyLax.MPT.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.GunShot;
import se.SkyLax.MPT.GameObjects.MissileTower;
import se.SkyLax.MPT.GameObjects.Rocket;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.GameObjects.Tower;


@SuppressWarnings("serial")
public class SwingTemplateJPanel extends JPanel {

	private BufferedImage rocketImg;
	
	
	private GameObjectList gameObj = null;

	public static final int CANVAS_WIDTH = 600;
	public static final int CANVAS_HEIGHT = 600;
	public static final String TITLE = "...Title...";

	public SwingTemplateJPanel() {

		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		try {
			rocketImg = ImageIO.read(new File("img/Rocket.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  // paint background
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;

		if(gameObj != null)
		{

			for(Tower tower : gameObj.getTowerList()){
				
				if(tower instanceof SniperCastle)
				{
					g2d.setColor(Color.DARK_GRAY);
					g2d.fillRect(tower.getX()-24, tower.getY()-24, 48, 48);
				}
				else if(tower instanceof MissileTower)
				{
					g2d.setColor(Color.WHITE);
					g2d.fillRect(tower.getX()-24, tower.getY()-24, 48, 48);
				}
			}


			
			
			for(ConcreteShot shot : gameObj.getListOfAllShots())
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

	public void setGameObj(GameObjectList gameObj) {
		this.gameObj = gameObj;
	}

}