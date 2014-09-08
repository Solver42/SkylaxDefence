package se.SkyLax.MPT.Events;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Utility.Money;



public class ButtonHandler {
	Money casch = null;
	private static JLabel money = null;
	private JToggleButton startRound = null;
	public ButtonHandler(SwingTemplateJPanel swing) {
		swing.setLayout(null);
		startRound = new JToggleButton("Start Round");
		money = new JLabel("DOUGH:");
		startRound.setBounds(840, 410, 120, 30);
		money.setBounds(840, 20, 120, 30);
		money.setForeground(Color.GREEN);
		money.setOpaque(true);
		money.setBackground(new Color(0, 255, 0, 100));
		
		startRound.addItemListener(new ItemListener() {
		   public void itemStateChanged(ItemEvent ev) {
		      if(ev.getStateChange()==ItemEvent.SELECTED){
		        Updater.setGO(true);
		        startRound.setText("Pause");
		      } else if(ev.getStateChange()==ItemEvent.DESELECTED){
		    	  startRound.setText("Start Round");
		        Updater.setGO(false);
		      }
		   }
		}); 
		swing.add(money);
		swing.add(startRound);
		
	}
	public static void setCasch(int dough)
	{
		money.setText("DOUGH " + Integer.toString(dough));
	}
}
