package se.SkyLax.MPT.Graphics;


import javax.swing.JFrame;

import se.SkyLax.MPT.Controller.ObjectGenerator;


public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private SwingTemplateJPanel myPanel = null;

	public TheFrame(ObjectGenerator obj)
	{
		myPanel = new SwingTemplateJPanel(obj);
		this.setResizable( false );
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

}