package se.SkyLax.MPT.Events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.Graphics.GUIHelper;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Levels.Levels;

public class MouseClass{


	private ObjectGenerator objGen = null;
	private SwingTemplateJPanel swing = null;
	private MouseGUIConnection properties = null;
	private GUIHelper gui = null;
	private int map[][];
	private int mapX;
	private int mapY;

	public MouseClass(SwingTemplateJPanel swing, ObjectGenerator obj, GUIHelper gui){
		this.gui = gui;
		this.swing = swing;
		this.objGen = obj;
		properties = new MouseGUIConnection(obj, gui, swing);
		clicked();
		moved();
	}
	private void clicked()
	{
		swing.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					properties.IClicked();
				}
			}
		});
	}
	private void moved()
	{
		swing.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				gui.setGUI_XY(e.getX(), e.getY());

				properties.setCoordinates(e.getX(), e.getY());
				creationOfTowerOK(e);
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
	private void creationOfTowerOK(MouseEvent e)
	{
		map = objGen.getGameObjectContainer().getLevel().getMap();
		mapY = e.getY()/(Levels.UNIT_HEIGHT);
		mapX = e.getX()/(Levels.UNIT_WIDTH);
		if(map[mapY][mapX] != 1 && map[mapY][mapX] !=2 &&(e.getX()<SwingTemplateJPanel.CANVAS_WIDTH-(Levels.UNIT_WIDTH*3)))
			gui.setAllowedToBuild(true);
		else
			gui.setAllowedToBuild(false);
	}
}
