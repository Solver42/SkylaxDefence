package se.SkyLax.MTP.Events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.SniperCastle;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;

public class MouseClass implements MouseListener {
	
	
	ObjectGenerator objGen = null;
	
	public MouseClass(SwingTemplateJPanel swing, ObjectGenerator obj){
		swing.addMouseListener(this);
		this.objGen = obj;
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		objGen.getGameObjectContainer().addTower(new SniperCastle("SniperCastle", m.getX(), m.getY(), 0.4));

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
