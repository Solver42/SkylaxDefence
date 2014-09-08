package se.SkyLax.MPT.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;



public class ButtonHandler {
//	private Updater updater = new Updater();
	
	private JToggleButton startRound = new JToggleButton("Start Round");
	public ButtonHandler(SwingTemplateJPanel swing) {
		startRound.setBounds(840, 410, 120, 30);
		swing.setLayout(null);
		startRound.addItemListener(new ItemListener() {
		   public void itemStateChanged(ItemEvent ev) {
		      if(ev.getStateChange()==ItemEvent.SELECTED){
		        Updater.setGO(true);
		      } else if(ev.getStateChange()==ItemEvent.DESELECTED){
		        Updater.setGO(false);
		      }
		   }
		}); 
		swing.add(startRound);
	}

}
