package se.SkyLax.MPT.Graphics;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MTP.Events.MouseClass;


public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private SwingTemplateJPanel myPanel = new SwingTemplateJPanel();

	public TheFrame()
	{

		this.setContentPane(myPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();             // "this" JFrame packs its components
		this.setLocationRelativeTo(null); // center the application window
		this.setVisible(true);
		
		

		
		
	}

	public void update()
	{
		validate();
		repaint();
		
	}
	
	public void setObjectContainerOfJPanel(ObjectGenerator obj)
	{
		myPanel.setObjectContainer(obj);
	}
}