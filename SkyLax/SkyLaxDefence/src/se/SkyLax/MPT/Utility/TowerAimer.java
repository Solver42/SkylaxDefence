package se.SkyLax.MPT.Utility;

import java.util.ArrayList;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Enemy.Enemy;
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
	private int start;
	public double aimHere(Tower t, ArrayList<Enemy> enList, boolean onlyLength/*, int diff*/)
	{
		int i = 0;
		start = Updater.NR_OF_ITR_ENEMY_STAYS;
		while(!enList.isEmpty() && i<(Levels.mapList[0].length-enList.get(0).getStep())-1)
		{

			currenPositionX = (((Levels.mapList[0][(enList.get(0).getStep())+i])+1)*(Levels.UNIT_WIDTH*2))-Levels.UNIT_WIDTH;
			currenPositionY = (((Levels.mapList[1][(enList.get(0).getStep())+i])+1)*(Levels.UNIT_HEIGHT*2))-Levels.UNIT_HEIGHT;

			towX = t.getX();
			towY = t.getY();

			currentLengthX = towX-currenPositionX;
			currentLengthY = towY-currenPositionY;

			lengthTillTarget = Math.sqrt((Math.pow(currentLengthX, 2) + Math.pow(currentLengthY, 2)));

//			if(lengthTillTarget/t.getSpeedOfShot()>Updater.NR_OF_ITR_ENEMY_STAYS) System.out.println("Will  NOT Make it");
//			else System.out.println("Will Make it");
			
			if(onlyLength)
			{
				return lengthTillTarget;
			}
			if(lengthTillTarget/t.getSpeedOfShot()>start)
			{
				start+=Updater.NR_OF_ITR_ENEMY_STAYS;
				i++;
				continue;
			}
			else break;

			


		}
		return (Math.atan2(currentLengthY, currentLengthX)+Math.PI);
		//		}

	}

}
