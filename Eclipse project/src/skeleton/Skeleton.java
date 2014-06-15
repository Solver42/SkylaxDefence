

//http://zetcode.com/tutorials/javagamestutorial/basics/

package skeleton;

import javax.swing.JFrame;

public class Skeleton extends JFrame {

    public Skeleton() {
        add(new Board());
        setTitle("Skeleton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}