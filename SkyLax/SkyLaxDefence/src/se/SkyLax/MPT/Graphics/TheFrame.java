package se.SkyLax.MPT.Graphics;

import javax.swing.JFrame;

import se.SkyLax.MPT.GameObjects.GameObjectList;


public class TheFrame extends JFrame {

	
	
	private GameObjectList objList = new GameObjectList();
	private SwingTemplateJPanel myPanel = new SwingTemplateJPanel();
	public TheFrame()
	{
		
		this.setContentPane(myPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();             // "this" JFrame packs its components
		this.setLocationRelativeTo(null); // center the application window
		this.setVisible(true);
		
		

		
		
	}

	public void update(GameObjectList things)
	{
		myPanel.setGameObj(things);
		validate();
		repaint();
		
	}
}