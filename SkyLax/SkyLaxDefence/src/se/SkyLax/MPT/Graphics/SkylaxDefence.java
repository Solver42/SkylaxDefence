package se.SkyLax.MPT.Graphics;
import se.SkyLax.MPT.Controller.ObjectGenerator;
import se.SkyLax.MPT.GameObjects.*;

public class SkylaxDefence {
	
	/*
	 * main method.
	 */

	public static void main(String[] args) {
		
		Updater map = new Updater();
		Thread t = new Thread(map);
		t.start();

	}

}
