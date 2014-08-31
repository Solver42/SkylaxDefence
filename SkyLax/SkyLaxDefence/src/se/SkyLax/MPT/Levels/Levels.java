package se.SkyLax.MPT.Levels;

public class Levels {

	
	int[][] holder = new int[][]{
			{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0 },
			{ 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
			{ 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
			{ 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0 },
			{ 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0 },
			{ 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			
		};

	public int[][] getMap()
	{
		return holder;
	}
	public void setMap(int x, int y, int value)
	{
		this.holder[x][y] = value;
	}

}
