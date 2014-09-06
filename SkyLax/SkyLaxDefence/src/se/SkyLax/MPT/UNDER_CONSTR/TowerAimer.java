package se.SkyLax.MPT.UNDER_CONSTR;

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
		currenPositionX = ((Levels.mapList[0][enList.get(0).getStep()]+1)*(Levels.UNIT_WIDTH*2))-Levels.UNIT_WIDTH;
		currenPositionY = ((Levels.mapList[1][enList.get(0).getStep()]+1)*(Levels.UNIT_HEIGHT*2))-Levels.UNIT_HEIGHT;
		
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