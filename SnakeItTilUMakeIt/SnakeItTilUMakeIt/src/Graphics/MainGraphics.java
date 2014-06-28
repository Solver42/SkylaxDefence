package Graphics;

public class MainGraphics {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            //StartPromp startPromt = new StartPromp();
            
		returnField r = new returnField();
                
		Thread t = new Thread(r);
		t.start();
	}

}
