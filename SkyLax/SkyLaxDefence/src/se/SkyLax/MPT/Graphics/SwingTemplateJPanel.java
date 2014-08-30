package se.SkyLax.MPT.Graphics;
import java.awt.*;

import javax.swing.*;

import se.SkyLax.MPT.GameObjects.GameObjectList;


@SuppressWarnings("serial")
public class SwingTemplateJPanel extends JPanel {

	private GameObjectList gameObj = null;

	public static final int CANVAS_WIDTH = 640;
	public static final int CANVAS_HEIGHT = 480;
	public static final String TITLE = "...Title...";

	public SwingTemplateJPanel() {

		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  // paint background
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);

		if(gameObj != null)
		{
			
			for(int i = 0; i <gameObj.getRocketList().size(); i++)
			{
			
				g2d.fillOval(gameObj.getRocketList().get(i).getX(), gameObj.getRocketList().get(i).getY(), 19, 19);
			}
		}

	}

	public void setGameObj(GameObjectList gameObj) {
		this.gameObj = gameObj;
	}

}