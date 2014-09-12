package se.SkyLax.MPT.Utility;

import java.util.ArrayList;

import se.SkyLax.MPT.Controller.Updater;
import se.SkyLax.MPT.Enemy.Enemy;
import se.SkyLax.MPT.GameObjects.MissileTower;
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
	private int x = 0;
	private int enItr = 0;
	public double aimHere(Tower t, ArrayList<Enemy> enList, boolean onlyLength/*, int diff*/)
	{
		enItr = 0;
		int i = 0;
		x = 0;
		start = Updater.NR_OF_ITR_ENEMY_STAYS;
		//		if((t instanceof MissileTower) && (enList.size()>1) && enList.get(0).getStep()>2 && (enList.get(0).getStep()< Levels.mapList[0].length-1))
		//		{
		//			for(int j = 0; i<enList.size(); i++)
		//			{
		//				if(j==enList.size()-1)
		//				{
		//					x = 1;
		//					break;
		//				}
		//				if (enList.get(j).getHealth()<enList.get(j).getStartHelth())
		//				x++;
		//				else break;
		//			}
		//		}
		while(enItr<enList.size())
		{
			while(!enList.isEmpty() && i<(Levels.mapList[0].length-enList.get(enItr).getStep())-1)
			{


				currenPositionX = (((Levels.mapList[0][(enList.get(enItr).getStep())+i/*-x*/])+1)*(Levels.UNIT_WIDTH*2))-Levels.UNIT_WIDTH;
				currenPositionY = (((Levels.mapList[1][(enList.get(enItr).getStep())+i/*-x*/])+1)*(Levels.UNIT_HEIGHT*2))-Levels.UNIT_HEIGHT;

				towX = t.getX();
				towY = t.getY();

				currentLengthX = towX-currenPositionX;
				currentLengthY = towY-currenPositionY;

				lengthTillTarget = Math.sqrt((Math.pow(currentLengthX, 2) + Math.pow(currentLengthY, 2)));

				//			if(lengthTillTarget/t.getSpeedOfShot()>Updater.NR_OF_ITR_ENEMY_STAYS) System.out.println("Will  NOT Make it");
				//			else System.out.println("Will Make it");



				if(lengthTillTarget/t.getSpeedOfShot()>start)
				{
					start+=Updater.NR_OF_ITR_ENEMY_STAYS;
					i++;
					continue;
				}
				else break;




			}
			if(lengthTillTarget>t.getRangeInPix()/2) enItr++;
			else break;
		}
		if(onlyLength)
		{
			return lengthTillTarget;
		}
		return (Math.atan2(currentLengthY, currentLengthX)+Math.PI);
		//		}

	}

}
