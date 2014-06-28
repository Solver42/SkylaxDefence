package Logic;

public class Sprite implements LevelsModifier
{
    
    private int[][] worm = new int[2][400];
    private char c = 's';
    private int currentLength = 1;
    private char currentDirection = 'w';
    private boolean justEat = false;
    //int[][] opitmizedLevel = this.getOptimizedLevel();
    private HitTest hit = new HitTest();
    
    
    //A worm is being created
    //in its constructor
    public Sprite()
    {
        
        worm[0][0] = 18;
        worm[1][0] = 8;
        /*worm[0][0] = 7;
        worm[1][0] = 8;
        
        worm[0][1] = 8;
        worm[1][1] = 8;
        
        worm[0][2] = 9;
        worm[1][2] = 8;
        
        worm[0][3] = 10;
        worm[1][3] = 8;
        
        worm[0][4] = 11;
        worm[1][4] = 8;
        
        worm[0][5] = 12;
        worm[1][5] = 8;*/
    }
    
    //where the worm is,
    //set the boolean element
    //to true, of the array
    //sent as a parameter
    //In this program,
    //the field-course
    public boolean[][] setBoolean(boolean [][] b)
    {
        
        
        for(int i = 0; i<worm[0].length; i++)
        {
            if(worm[0][i] != 0 && worm[1][i] != 0)
            {
                b[worm[0][i]][worm[1][i]] = true;
            }
            else
            {
                b[worm[0][i-1]][worm[1][i-1]] = false;
            }
        }
        return b;
    }
    
    //run applicable methods at
    //command an pass the correct
    //parameter.
    public void updateWornArray()
    {
        switch(c)
        {
            case 'w': if(currentDirection == 's') headAt('s');
            else headAt('w');
            break;
            case 'a':  if(currentDirection == 'd') headAt('d');
            else headAt('a');
            break;
            case 'd':  if(currentDirection == 'a') headAt('a');
            else headAt('d');
            break;
            case 's':  if(currentDirection == 'w') headAt('w');
            else headAt('s');
            break;
        }
    }
    
    //worm-array (that is an int-aray)
    //is being updated.
    private void headAt(char c)
    {
        modifyTail();
        switch (c)
        {
            case 's':
                worm[0][0] +=1;
                currentDirection = 's';
                break;
            case 'w':
                worm[0][0]-=1;
                currentDirection = 'w';
                break;
            case 'a':
                worm[1][0] -=1;
                currentDirection = 'a';
                break;
            case 'd':
                worm[1][0] +=1;
                currentDirection = 'd';
                break;
        }
        
        //see method in HitTest
        if(getHit().hitWormByHead(worm, currentLength))
        {
            System.out.println("SCORE: " + Fruits.fruitsTakenStatic());
            System.out.println("You fool, don't bite yourself!?");
            
            System.exit(0);
        }
    }
    
    //"Pushes the worm array of
    //ints one element closer to index 0
    private void modifyTail()
    {
        if(justEat)
        {
            currentLength++;
            this.justEat = false;
        }
        for(int i = 0; i<currentLength; i++)
        {
            worm[0][currentLength-i] = worm[0][currentLength-i-1];
            worm[1][currentLength-i] = worm[1][currentLength-i-1];
        }
    }
    
    public int[][] getWorm()
    {
        return worm;
    }
    
    //this.c is called in the
    //method updateWormArray()
    public void setCharacter(char c)
    {
        if(c == 'a' || c == 's' || c == 'd' || c == 'w')
        {
            this.c = c;
        }
    }
    
    public boolean[][] getLevelArray()
    {
        return LEVEL_ONE;
    }
    
    //Affects outcome of the
    //method modify tail.
    public void setJustEat(boolean did)
    {
        this.justEat = did;
    }
    public boolean getJustEat()
    {
        return this.justEat;
    }
    
    public HitTest getHit() {
        return hit;
    }
    
    public void setHit(HitTest hit) {
        this.hit = hit;
    }
    public int getCurrentLength()
    {
        return this.currentLength;
    }
    
    public String getXY()
    {
        return Integer.toString(this.worm[0][5]) + " " + Integer.toString(this.worm[1][5]);
    }
}
