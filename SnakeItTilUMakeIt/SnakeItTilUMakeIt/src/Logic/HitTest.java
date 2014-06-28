package Logic;
public class HitTest implements LevelsModifier{
    
    //This method checks if
    //the elements at index
    //x- an y of the boolean level
    //array is true - that is
    //if the level has a "wall"
    //there, and in that case
    //returns true.
    
    public boolean hit(int x, int y)
    {
        if(LEVEL_ONE[x][y])
        {
            return true;
        }
        return false;
    }
    
    //This method iterates trough
    //the body of the worm and checks
    //if the position of sprite[0][0]
    //and Sprite[1][0], is at the
    //same place as any other part of the
    //worm. In that case it returns true.
    
    public boolean hitWormByHead(int[][] sprite, int currentLength)
    {
        for(int i = 1; i<currentLength; i++)
        {
            if(sprite[0][0] == sprite[0][i] && sprite[1][0] == sprite[1][i])
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean hitWorm(int[][] sprite, int currentLength, int[] fruit)
    {
        for(int i = 0; i<currentLength; i++)
        {
            if(fruit[0] == sprite[0][i] && fruit[1] == sprite[1][i])
            {
                return true;
            }
        }
        return false;
    }
}
