package se.SkyLax.MPT.Controller;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.SwingConstants;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.SwingTemplateJPanel;
import se.SkyLax.MPT.Graphics.TheFrame;

public class Updater implements Runnable{
	ObjectGenerator objGen = null;
	private Random gen = new Random();
	private ArrayList<ConcreteShot> shotsToRemove = new ArrayList<>();
	TheFrame screen = new TheFrame();
	public Updater()
	{
		objGen = new ObjectGenerator();
		screen.setObjectContainerOfJPanel(objGen);
	}
	private void updateShots()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getListOfAllShots())
		{
			rocket.travel();
		}
	}
	private void removeNAShot()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getListOfAllShots())
		{
			if(rocket.getX() > SwingTemplateJPanel.CANVAS_WIDTH || rocket.getX() < 0 || rocket.getY() > SwingTemplateJPanel.CANVAS_HEIGHT || rocket.getY() < 0)
			{
				shotsToRemove.add(rocket);
			}
		}
		objGen.getGameObjectContainer().getListOfAllShots().removeAll(shotsToRemove);
		shotsToRemove.clear();
	}
	public void setRandomTowerAngle()
	{
		for(Tower tower : objGen.getGameObjectContainer().getTowerList())
		{
			tower.setAngle(gen.nextDouble()*(Math.PI*2));
		}
	}
	public void run() {
		int mod = 10;
		int i;
		while(true)
		{
			//TODO
//JAHAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA det är därför som skotten bara kommmer ibland: (fillPlanithRocketShots körs ju bara ibland)
			i = gen.nextInt(10);
			if(( mod%5==0 ) && ( i < 5))
			{
				setRandomTowerAngle();
				objGen.fillPlanWithRocketShot();
			}

			updateShots();

			removeNAShot();

			screen.update();

			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			mod++;
			if(mod>100) mod = 0;

		}
	}

}
