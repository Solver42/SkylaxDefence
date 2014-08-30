package se.SkyLax.MPT.Controller;

import java.util.ArrayList;
import java.util.Random;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.TheFrame;

public class Updater implements Runnable{
	ObjectGenerator objGen = null;
	private Random gen = new Random();
	private ArrayList<ConcreteShot> shotsToRemove = new ArrayList<>();
	TheFrame screen = new TheFrame();
	public Updater()
	{
		objGen = new ObjectGenerator();
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
			if(rocket.getX() > 600 || rocket.getX() < 0 || rocket.getY() > 600 || rocket.getY() < 0)
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

			//Just shit code, to get something on the screen
			i = gen.nextInt(15);
			if(( mod%5==0 ) && ( i < 5))
			{
				setRandomTowerAngle();
				objGen.fillPlanWithGunShot();
			}
			else if(( mod%5==0 ) && ( i > 10))
			{
				setRandomTowerAngle();
				objGen.fillPlanWithRocketShot();
			}
			else if(mod<=10)
			{
				setRandomTowerAngle();
				objGen.fillPlanWithGunShot();
			}
			//Stops here

			updateShots();

			removeNAShot();

			screen.update(objGen.getGameObjectContainer());

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			mod++;

		}
	}

}
