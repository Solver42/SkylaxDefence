package se.SkyLax.MPT.GameObjects;

public interface ConcreteShot {
	/*
	 * this interface represents all
	 * sub shots. the travel method
	 * should contain a body
	 * that makes the shot travel.
	 */
	public void travel();
	public int getX();
	public int getY();
	public double getAngle();
	public int getDamage();
}
