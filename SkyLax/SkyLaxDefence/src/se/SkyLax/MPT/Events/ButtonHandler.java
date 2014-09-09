package se.SkyLax.MPT.Events;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.JLabel;
import javax.swing.JToggleButton;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Utility.Money;



public class ButtonHandler {
	Money casch = null;
	private JLabel money = null;
	private JLabel tower1 = null;
	private JLabel tower2 = null;
	private JLabel tower3 = null;
	private JToggleButton startRound = null;
	private int startCasch = 3400;
	public ButtonHandler(SwingTemplateJPanel swing) {
		swing.setLayout(null);
		startRound = new JToggleButton("Start Round");
		money = new JLabel("DOUGH:");
		tower1 = new JLabel("100");
		tower2 = new JLabel("1000");
		tower3 = new JLabel("750");
		startRound.setBounds(840, 410, 120, 30);
		money.setBounds(840, 20, 120, 30);
		tower1.setBounds(900,90, 30, 30);
		tower2.setBounds(900,150, 30, 30);
		tower3.setBounds(900, 210, 30, 30);
		money.setForeground(Color.WHITE);
		money.setOpaque(true);
		money.setBackground(new Color(0, 0, 0, 100));
		
		tower1.setForeground(Color.WHITE);
		tower1.setOpaque(true);
		tower1.setBackground(new Color(0, 255, 0, 100));
		
		tower2.setForeground(Color.WHITE);
		tower2.setOpaque(true);
		tower2.setBackground(new Color(0, 255, 0, 100));
		
		tower3.setForeground(Color.WHITE);
		tower3.setOpaque(true);
		tower3.setBackground(new Color(0, 255, 0, 100));
		
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
		swing.add(tower1);
		swing.add(tower2);
		swing.add(tower3);
		updateCash(startCasch);
		
	}
	private void updateCash(int dough)
	{
		money.setText("DOUGH " + Integer.toString(dough));
		
	}
	public void subCasch(int amount)
	{
		startCasch-=amount;
		updateCash(startCasch);
		if(startCasch<100) tower1.setBackground(new Color(255, 0, 0, 100));
		if (startCasch<750) tower2.setBackground(new Color(255, 0, 0, 100));
		if (startCasch<1000) tower3.setBackground(new Color(255, 0, 0, 100));
	}
	public int getCasch()
	{
		return this.startCasch;
	}
}
