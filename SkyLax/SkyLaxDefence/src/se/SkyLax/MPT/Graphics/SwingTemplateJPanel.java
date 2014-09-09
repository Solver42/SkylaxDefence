package se.SkyLax.MPT.Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.Enemy.EnemyList;
import se.SkyLax.MPT.Events.ButtonHandler;
import se.SkyLax.MPT.Events.MouseClass;


@SuppressWarnings("serial")
public class SwingTemplateJPanel extends JPanel {

	
	private GUIHelper guiHelper = null;
	
//	private BufferedImage rocketImg;

	
	
	private ButtonHandler buttonHandler = null;

	public static final int CANVAS_WIDTH = 960;
	public static final int CANVAS_HEIGHT = 540;
	public static final String TITLE = "...Title...";

	public SwingTemplateJPanel(ObjectGenerator obj, EnemyList enemy) {
//		this.objGen = obj;
		guiHelper = new GUIHelper(enemy, obj);
		new MouseClass(this, obj, guiHelper);
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		buttonHandler = new ButtonHandler(this);
	}




	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  // paint background
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		guiHelper.drawThis(g2d, g);
	}


	public ButtonHandler getHandler()
	{
		return buttonHandler;
	}

}