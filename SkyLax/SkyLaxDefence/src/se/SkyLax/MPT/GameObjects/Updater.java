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


	private void updateShots()
	{
		for(ConcreteShot rocket: objGen.getGameObjectContainer().getRocketList())
		{
			rocket.travel();
		}

		for(ConcreteShot r : objGen.getGameObjectContainer().getRocketList())
		{
			System.out.println(((Shot) r).getX());
		}
	}

	public void run() {
		int i = 0;
		while(true)
		{

			if(i == 3) 
			{
				objGen.fillPlan();
				System.out.println("Filled Game With Objects!");

			} else if (i == 5)
			{
				objGen.fillPlan();
				System.out.println("Filled it up again");
			}
			System.out.println("Lap... ");
			
			updateShots();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			i++;

		}
	}

}
