package Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

class Squares extends JPanel {
	private static final int PREF_W = 400;
	private static final int PREF_H = PREF_W;
	private List<Rectangle> squares = new ArrayList<Rectangle>();
	private List<Rectangle> snake = new ArrayList<Rectangle>();
	private List<Rectangle> fruit = new ArrayList<Rectangle>();
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(PREF_W, PREF_H);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Rectangle rect : squares) {
			g2.draw(rect);
		}
		for (Rectangle rect : snake) {
			g2.setColor(Color.red);
			g2.draw(rect);
		}
		for (Rectangle rect : fruit) {
			g2.setColor(Color.blue);
			g2.draw(rect);
		}
		
	}

	public void addSquare(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		squares.add(rect);
	}
	
	public void addSnake(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		snake.add(rect);
	}
	
	public void addFruit(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		fruit.add(rect);
	}

	public List<Rectangle> getSnake() {
		return snake;
	}

	public List<Rectangle> getFruit() {
		return fruit;
	}

	public List<Rectangle> getSquares() {
		return squares;
	}

	
}