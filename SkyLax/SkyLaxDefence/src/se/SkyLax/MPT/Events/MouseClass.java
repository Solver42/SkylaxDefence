package se.SkyLax.MPT.Events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Levels.Levels;

public class MouseClass{


	ObjectGenerator objGen = null;
	SwingTemplateJPanel swing = null;
	private MouseProperties properties = null;

	public MouseClass(SwingTemplateJPanel swing, ObjectGenerator obj){
		this.swing = swing;
		this.objGen = obj;
		properties = new MouseProperties();
		clicked();
		moved();
	}

	private void clicked()
	{
		swing.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(objGen.getGameObjectContainer().getLevel().getMap()[e.getY()/(Levels.UNIT_HEIGHT)][e.getX()/(Levels.UNIT_WIDTH)] != 1)
				{
				objGen.getGameObjectContainer().addTower(new SniperCastle("SniperCastle", e.getX(), e.getY(), 1));
				}
			}
		});
	}


	private void moved()
	{
		swing.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				properties.setMouseX(e.getX());
				properties.setMouseY(e.getY());
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}


}
