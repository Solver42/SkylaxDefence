package Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Logic.Level;
import Logic.LevelsModifier;

public class GameFrame extends JFrame implements LevelsModifier, KeyListener{
    private Level level = new Level();
    private Squares squares = new Squares();
    public GameFrame() {
        
        super("Snake It 'Till You Make It");
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(squares);
        this.addKeyListener(this);
        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (LEVEL_ONE[j][i])
                {
                    squares.addSquare(i * 20, j * 20, 20, 20);
                }
            }
            
        }
        for(int i = level.getSprite().getCurrentLength(); i>0 ; i--)
        {
            squares.addSnake(level.getSprite().getWorm()[1][i-1]*20, level.getSprite().getWorm()[0][i-1]*20, 20, 20);
            
        }
        
        squares.addFruit(level.getFruit().getPlaceOfFruit()[1] * 20, level.getFruit().getPlaceOfFruit()[0] * 20, 20, 20);
        //System.out.print(level.getFruit().getPlaceOfFruit()[0]);
    }
    
    
    public void update()
    {
        
        squares.addFruit(level.getFruit().getPlaceOfFruit()[1] * 20, level.getFruit().getPlaceOfFruit()[0] * 20, 20, 20);
        squares.getFruit().remove(0);
        level.getSprite().updateWornArray();
        level.update();
        
        
        
        
        squares.addSnake(level.getSprite().getWorm()[1][0]*20, level.getSprite().getWorm()[0][0]*20, 20, 20);
        if(!level.getSprite().getJustEat())
        {
            squares.getSnake().remove(0);
        }
        pack();
        setVisible(true);
        repaint();
        //System.out.println(level.getSprite().getXY());
    }
    
    public Level getLevel()
    {
        return level;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            level.getSprite().setCharacter('a');
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            level.getSprite().setCharacter('d');
        }
        
        if (key == KeyEvent.VK_UP) {
            level.getSprite().setCharacter('w');
        }
        
        if (key == KeyEvent.VK_DOWN) {
            level.getSprite().setCharacter('s');
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

