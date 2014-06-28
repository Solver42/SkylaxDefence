package Logic;
import java.util.Random;

public class Fruits implements LevelsModifier{

	//setting some variables and constants
	//at class-level
	private final int SIZE = 20;
	private int fruitsTaken = 0;
	//private boolean[][] level;
	private final boolean[][] fruitArray = new boolean[SIZE][SIZE];
	private Random gen = new Random();

	private int[] place = new int[2];
        
        private static int fruitsTakenStatic = 0;

	//Entire array of fruits needs to be
	//initiated to false at creation
	public Fruits()
               
	{
		generateFruit();
	}
	public void generateFruit()
	{
		int ran1;
		int ran2;
		while(true)
		{
			ran1 = gen.nextInt(SIZE);
			ran2 = gen.nextInt(SIZE);
			if(LEVEL_ONE[ran1][ran2])
			{
                            
				continue;
			}
			place[0]=ran1;
			place[1] = ran2;
			break;
		}
	}
	
	public int[] getPlaceOfFruit()
	{
		return place;
	}

        public void gainFruitsEaten()
        {
            this.fruitsTaken ++;
            fruitsTakenStatic ++;
            
        }
        public static int fruitsTakenStatic()
        {
            return fruitsTakenStatic;
        }
}
