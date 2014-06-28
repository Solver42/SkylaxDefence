package Logic;

public class Level
{
    
    private final int SIZE = 20;
    private boolean[][] course = new boolean[SIZE][SIZE];
    private Sprite sprite = new Sprite();
    private Fruits fruit = new Fruits();
    private HitTest hitFruit = new HitTest();
    private int fruitsEaten;
    
    public Level()
    {
        update();
    }
    
    //If the head of the worm/sprite
    //does not hit itself or the wall,
    //the level-field is being updated.
    //If the worm hits a fruit,
    //that ceratin element is set to
    //false.
    public void update()
    {
        if(this.getSprite().getHit().hit(sprite.getWorm()[0][0], sprite.getWorm()[1][0]))
        {
            System.out.println("SCORE: " + Fruits.fruitsTakenStatic());
            System.out.println("You hit the wall!");
            System.exit(0);
        }
        
        if ((sprite.getWorm()[0][0] == fruit.getPlaceOfFruit()[0]) && (sprite.getWorm()[1][0] == fruit.getPlaceOfFruit()[1]))
        {
            this.sprite.setJustEat(true);
            fruit.generateFruit();
            while((this.hitFruit.hitWorm(this.sprite.getWorm(), this.sprite.getCurrentLength(),fruit.getPlaceOfFruit())))
            {
                fruit.generateFruit();
            }
            fruit.gainFruitsEaten();
            //fruit.setBooleanValueOfFruitArray(sprite.getWorm()[0][0], sprite.getWorm()[1][0], false);
            
            
        }
        course = sprite.setBoolean(course);
    }
    public Sprite getSprite()
    {
        return sprite;
    }
    
    public Fruits getFruit()
    {
        return this.fruit;
    }
}
