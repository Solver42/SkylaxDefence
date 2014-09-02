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
	private MouseMotionHandler properties = null;

	public MouseClass(SwingTemplateJPanel swing, ObjectGenerator obj){
		this.swing = swing;
		this.objGen = obj;
		properties = new MouseMotionHandler(obj);
		clicked();
		moved();
	}

	private void clicked()
	{
		swing.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				{
				properties.mabyCreateTower("SniperCastle");
				}
				else if (e.getButton() == MouseEvent.BUTTON3)
				{
				properties.mabyCreateTower("MissileTower");
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
