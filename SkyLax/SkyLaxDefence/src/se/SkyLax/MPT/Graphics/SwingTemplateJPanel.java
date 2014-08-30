package se.SkyLax.MPT.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.GameObjectList;
import se.SkyLax.MPT.GameObjects.GunShot;
import se.SkyLax.MPT.GameObjects.Rocket;
import se.SkyLax.MPT.GameObjects.Tower;


@SuppressWarnings("serial")
public class SwingTemplateJPanel extends JPanel {

	private GameObjectList gameObj = null;

	public static final int CANVAS_WIDTH = 600;
	public static final int CANVAS_HEIGHT = 600;
	public static final String TITLE = "...Title...";

	public SwingTemplateJPanel() {

		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  // paint background
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.DARK_GRAY);

		if(gameObj != null)
		{

			for(Tower tower : gameObj.getTowerList()){

				g2d.fillRect(tower.getX()-24, tower.getY()-24, 48, 48);
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
					g2d.fillOval(shot.getX()-50, shot.getY()-50, 100, 100);
				}
			}
		}

	}

	public void setGameObj(GameObjectList gameObj) {
		this.gameObj = gameObj;
	}

}