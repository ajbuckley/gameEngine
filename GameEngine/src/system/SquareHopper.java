/**
 * 
 */
package system;

import gameObject.Player;
import gameObject.Rectangle;
import gameObject.GameObject;
import gameObject.Square;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * @author Drew
 *
 */
public class SquareHopper extends PApplet {
	static ArrayList<GameObject> objects;
	/** Default stroke(outline) = black **/
	int strokeDef = 0;
	/** Default fill = black **/
	int fillDef = 0;
	static private Square player;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PApplet.main("system.SquareHopper");
		objects = new ArrayList<GameObject>();

		Square s = new Square(1, 1, 50);
		s.setGravity((float) 9.8);
		float vel[] = { 0, 0, 0 };
		s.setVelocity(vel);
		Rectangle r = new Rectangle(1, 350, 800, 50);
		Rectangle m = new Rectangle(300, 200, 200, 25);
		Rectangle k = new Rectangle(1, 1, 10, 400);
		Rectangle l = new Rectangle(790, 1, 10, 400);
		player = s;
		
		objects.add(k);
		objects.add(l);
		objects.add(s);
		objects.add(r);
		objects.add(m);

	}

	public void settings() {
		size(800, 500);
	}

	public void setup() {
		background(0);
		stroke(0);
		frameRate(60);
	}

	public void draw() {

		handleCollisions();
		updateObjects();
		background(0);
		for (int i = 0; i < objects.size(); i++) {
			GameObject x = objects.get(i);
			if (x.isVisible()) {

				int[] fill = x.getFill();
				int[] stroke = x.getStroke();
				stroke(stroke[0], stroke[1], stroke[2], stroke[3]);
				fill(fill[0], fill[1], fill[2], fill[3]);

				switch (x.drawType()) {
				case GameObject.SQUARE_DRAW_VAL:
					drawSquare(x);
					break;
				case GameObject.RECT_DRAW_VAL:
					drawRect(x);
					break;
				case GameObject.LINE_DRAW_VAL:
					drawLine(x);
					break;
				default:
					break;
				}
				fill(fillDef);
				stroke(strokeDef);

			}
		}

	}

	private void drawSquare(GameObject quad) {

		rect(quad.getX(), quad.getY(), ((Square) quad).getWidth(),
				((Square) quad).getHeight());

	}

	private void drawRect(GameObject quad) {

		rect(quad.getX(), quad.getY(), ((Rectangle) quad).getWidth(),
				((Rectangle) quad).getHeight());
	}

	private void drawLine(GameObject line) {

	}

	private void handleCollisions() {
		for (int i = 0; i < objects.size() - 1; i++) {

			GameObject o1 = objects.get(i);
			// System.out.println(o1.drawType());
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o2 = objects.get(j);
				o1.handleCollisions(o2);
				// System.out.println(o2.drawType());
			}
		}

	}

	private void updateObjects() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update();
		}
	}

	public void keyPressed() {
		if ((key == ' ')) {

			if (!player.inAir()) {
				float vel[] = { player.getVelocity()[0], -10,
						player.getVelocity()[2] };
				player.setVelocity(vel);
				player.setAir(true);
			}
		}
		if (key == CODED) {
			if (keyCode == LEFT) {
				float vel[] = { player.getVelocity()[0] - 10,
						player.getVelocity()[1], player.getVelocity()[2] };
				player.setVelocity(vel);
			}
			if (keyCode == RIGHT) {
				float vel[] = { player.getVelocity()[0] + 10,
						player.getVelocity()[1], player.getVelocity()[2] };
				player.setVelocity(vel);
			}

		}

	}

	public void keyReleased() {
		if (key == CODED) {
			if (keyCode == LEFT) {
				float vel[] = { 0,
						player.getVelocity()[1], player.getVelocity()[2] };
				player.setVelocity(vel);
			}
			if (keyCode == RIGHT) {
				float vel[] = { 0,
						player.getVelocity()[1], player.getVelocity()[2] };
				player.setVelocity(vel);
			}

		}

	}
}
