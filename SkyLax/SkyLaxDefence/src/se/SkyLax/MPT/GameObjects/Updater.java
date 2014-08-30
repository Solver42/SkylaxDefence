package se.SkyLax.MPT.GameObjects;

import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.Graphics.TheFrame;

public class Updater implements Runnable{

	ObjectGenerator objGen = null;
	//GameObjectList container = null;
	
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

	public void run() {
		int i = 0;
		while(true)
		{

			if(i == 0) 
			{
				objGen.fillPlan();
				System.out.println("Filled Game With Objects!");

			} else if (i == 15)
			{
				objGen.fillPlan();
				System.out.println("Filled it up again");
			}
//			System.out.println("Lap... ");
			
			updateShots();
			screen.update(objGen.getGameObjectContainer());

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			i++;

		}
	}

}
