package se.SkyLax.MPT.Graphics;
import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Events.MouseClass;

public class SkylaxDefence {

	/*
	 * main method.
	 */

	public static void main(String[] args) {

		//new TheFrame();
		Updater map = new Updater();
		Thread t = new Thread(map);
		t.start();
	}


}
