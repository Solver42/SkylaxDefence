package se.SkyLax.MPT.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;



public class ButtonHandler {
//	private Updater updater = new Updater();
	
	private JButton startRound = new JButton("Start Round");
	public ButtonHandler(SwingTemplateJPanel swing) {
		startRound.setBounds(840, 410, 120, 30);
		swing.setLayout(null);
		 startRound.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	                Updater.setGO(true);
	            }
	        });  
		swing.add(startRound);
	}

}
