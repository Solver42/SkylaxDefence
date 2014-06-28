package Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class returnField implements Runnable{


	private GameFrame g = new GameFrame();
	@Override
	public void run() {
		while(true)
		{
			g.update();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
