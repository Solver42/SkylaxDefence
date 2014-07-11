package se.SkyLax.MPT.GameObjects;

import se.SkyLax.MPT.Controller.ObjectGenerator;

public class Updater implements Runnable{

	ObjectGenerator objGen = null;
	GameObjectList container = null;

	public Updater()
	{
		objGen = new ObjectGenerator();
		container = objGen.getGameObjectContainer();
	}




	public void run() {
		int i = 0;
		while(true)
		{

			if(i == 3) 
			{
				objGen.fillPlan();
				System.out.println("Filled Game With Objects!");

			}
			if(i == 5) objGen.getGameObjectContainer().addShot(3, "Rocket");
			System.out.println("Lap... ");
			for(ConcreteShot rocket: objGen.getGameObjectContainer().getRocketList())
			{
				rocket.travel();
			}

			for(ConcreteShot r : objGen.getGameObjectContainer().getRocketList())
			{
				System.out.println(((Shot) r).getX());
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			i++;

		}
	}

}
