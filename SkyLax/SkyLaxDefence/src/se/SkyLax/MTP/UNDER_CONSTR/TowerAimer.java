package se.SkyLax.MTP.UNDER_CONSTR;

import java.util.ArrayList;

import se.SkyLax.MPT.GameObjects.Tower;
import se.SkyLax.MPT.Levels.Levels;

public class TowerAimer {

	public TowerAimer() {
		// TODO Auto-generated constructor stub
	}


	private int currenPositionX;
	private int currenPositionY;
	private int towX;
	private int towY;
	private double currentLengthX;
	private double currentLengthY;
	private double lengthTillTarget;
	private int nrOfItrNeeded;
	public double aimHere(Tower t, ArrayList<Enemy> enList)
	{
		currenPositionX = Levels.levelList[0][enList.get(0).getStep()];
		currenPositionY = Levels.levelList[1][enList.get(0).getStep()];
		
		towX = t.getX();
		towY = t.getY();
		
		currentLengthX = towX-currenPositionX;
		currentLengthY = towY-currenPositionY;
		
			lengthTillTarget = Math.sqrt((Math.pow(currentLengthX, 2) + Math.pow(currentLengthY, 2)));
			
			
			
			nrOfItrNeeded = (int) (lengthTillTarget/50);
//			if(nrOfItrNeeded<=100)
//			{
				return (Math.atan2(currentLengthY, currentLengthX)+Math.PI);
//			}


	}

}
