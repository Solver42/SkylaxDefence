package se.SkyLax.MPT.Events;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;





import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Enemy.Enemy;
import se.SkyLax.MPT.Enemy.EnemyList;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Utility.Money;



public class ButtonHandler implements ActionListener{
	Money casch = null;
	private JLabel money = null;
	private JLabel tower1 = null;
	private JLabel tower2 = null;
	private JLabel tower3 = null;
	private JLabel level = null;
	private JButton startRound = null;
	private int startCasch = 3400;
	EnemyList enemyList = null;
//	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	public ButtonHandler(SwingTemplateJPanel swing, EnemyList enemy){
		swing.setLayout(null);
		enemyList = enemy;
		startRound = new JButton("Next Round");
		money = new JLabel("DOUGH:");
		tower1 = new JLabel("100");
		tower2 = new JLabel("1000");
		tower3 = new JLabel("750");
		level = new JLabel("Level: 1");
		startRound.setBounds(840, 410, 120, 30);
		money.setBounds(840, 20, 120, 30);
		tower1.setBounds(900,90, 30, 30);
		tower2.setBounds(900,150, 30, 30);
		tower3.setBounds(900, 210, 30, 30);
		level.setBounds(840, 380, 120, 30);
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
		
		level.setForeground(new Color(55, 255, 55));
		level.setOpaque(true);
		level.setBackground(new Color(0, 0, 0, 100));

		startRound.setActionCommand("startButton");
		startRound.addActionListener(this);
		swing.add(money);
		swing.add(startRound);
		swing.add(tower1);
		swing.add(tower2);
		swing.add(tower3);
		swing.add(level);
		updateCash(startCasch);

	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(enemyList.getEnemyList().isEmpty())
		{
			enemyList.getUpdater().resetIterator();
			
		}
		enemyList.getUpdater().setGo(true);
	}
	private void updateCash(int dough)
	{
		money.setText("DOUGH " + Integer.toString(dough));

	}
	public void modifyCasch(int amount)
	{
		startCasch+=amount;
		updateCash(startCasch);
		if(startCasch<100) tower1.setBackground(new Color(255, 0, 0, 100));
		else if(startCasch>=100) tower1.setBackground(new Color(0, 255, 0, 100));
		if (startCasch<750) tower3.setBackground(new Color(255, 0, 0, 100));
		else if(startCasch>=750) tower3.setBackground(new Color(0, 255, 0, 100));
		if (startCasch<1000) tower2.setBackground(new Color(255, 0, 0, 100));
		else if(startCasch>=1000) tower2.setBackground(new Color(0, 255, 0, 100));
	}
	public int getCasch()
	{
		return this.startCasch;
	}
	public void setToggleButton(boolean on)
	{
		startRound.setSelected(false);
	}
	public void setLevel (String which)
	{
		level.setText("Level: " + which);
	}
}
