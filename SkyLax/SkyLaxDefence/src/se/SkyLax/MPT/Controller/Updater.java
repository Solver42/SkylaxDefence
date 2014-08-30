package se.SkyLax.MPT.Controller;

import java.util.Random;

import se.SkyLax.MPT.GameObjects.ConcreteShot;
import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Graphics.TheFrame;

public class Updater implements Runnable{

	ObjectGenerator objGen = null;
	private Random gen = new Random();

	TheFrame screen = new TheFrame();


	public Updater()
	{
		objGen = new ObjectGenerator();
		//container = objGen.getGameObjectContainer();
	}


	private void updateShots()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getRocketList())
		{

				rocket.travel();

		}

	}

	public void setRandomTowerAngle()
	{
		for(Tower tower : objGen.getGameObjectContainer().getTowerList())
		{
			tower.setAngle(gen.nextDouble()*3);
		}
	}

	public void run() {

		int mod = 10;
		int i;
		while(true)
		{
			
			
//			for(ConcreteShot rocket: objGen.getGameObjectContainer().getRocketList())
//			{
//				if(rocket.getX() > 400 || rocket.getX() < 0 || rocket.getY() > 400 || rocket.getY() < 0)
//				{
//					System.out.println("Hello");
//				}
//
//			}
			
			
			
			i = gen.nextInt(10);
			if(( mod%5==0 ) && ( i > 4))
			{
				setRandomTowerAngle();
				objGen.fillPlan();
			}
			else if(mod<=10)
			{setRandomTowerAngle();
			objGen.fillPlan();
			}

			updateShots();
			screen.update(objGen.getGameObjectContainer());

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			mod++;

		}
	}

}
