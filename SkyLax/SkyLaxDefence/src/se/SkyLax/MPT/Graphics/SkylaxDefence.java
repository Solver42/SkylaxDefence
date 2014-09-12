package se.SkyLax.MPT.Graphics;
import se.SkyLax.MPT.Controller.Updater;

public class SkylaxDefence {
	public static void main(String[] args) {
		Updater map = new Updater();
		Thread t = new Thread(map);
		t.start();
	}
}
