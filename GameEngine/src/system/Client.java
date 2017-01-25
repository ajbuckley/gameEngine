/**
 * 
 */
package system;

import gameObject.Appearance;
import gameObject.CollisionVolume;
import gameObject.Drawable;
import gameObject.GameObject;
import gameObject.Location;
import gameObject.Velocity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import network.ClientData;
import network.InitialData;
import network.ServerData;
import processing.core.PApplet;
import worldObjects.PlayerCharacter;
import worldObjects.PlayerController;
import worldObjects.Rectangle;

/**
 * @author Drew
 *
 */
public class Client extends PApplet {
	
	private  ArrayList<Drawable> drawObjects;
	
	/** Default stroke(outline) = black **/
	int strokeDef = 0;
	/** Default fill = black **/
	int fillDef = 0;
	
	PlayerController p;
	static boolean connected = false;
	
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	static Socket sSock;

	boolean leftPressed = false, rightPressed = false, jump = false;
	
	public static void main(String[] args) {
		
		String IP = new String("127.0.0.1");
		
		
		sSock = null;
		
		int i = 10000;
		while (!connected) {
			try {
				sSock = new Socket(IP, i);
				connected = true;
				
				System.out.println("Port count: " + i);

			} catch (UnknownHostException e) {
				i++;
				if (i > 11000){
					break;
				}

			} catch (IOException e) {
				System.out.println(e.toString());
				i++;
			}

		}
		if (!connected){
			System.out.println("Unable to connect");
			System.exit(1);
		}
		
		
		
		PApplet.main("system.Client");

		
	}

	public void settings() {
		size(600, 600);
	}

	public void setup() {
		try {
			out = new ObjectOutputStream(sSock.getOutputStream());
			in = new ObjectInputStream(sSock.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			InitialData id;
			id = (InitialData) in.readObject();
			//drawObjects = id.getObjects();
			p = id.getP();
			System.out.println("Name: " + p.getPlayer().getName());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		background(0);
		stroke(0);
		frameRate(60);
	}

	public void draw() {
		
		if(connected){
			updateObjects();
			background(0);
			for (int i = 0; i < drawObjects.size(); i++) {
				Drawable x = drawObjects.get(i);
				if (x.isVisible()) {
					Appearance xAP = x.getLooks();
					stroke(xAP.getStroke()[0], xAP.getStroke()[1],
							xAP.getStroke()[2], xAP.getStroke()[3]);
					fill(xAP.getFill()[0], xAP.getFill()[1], xAP.getFill()[2],
							xAP.getFill()[3]);
					drawRect(x);
				
					
					fill(fillDef);
					stroke(strokeDef);
	
				}
			}
		}

	}

	private void drawRect(Drawable x) {

		rect((float) x.getLocation().getX(), (float) x.getLocation().getY(),
				(float) ((Rectangle) x).getWidth(),
				(float) ((Rectangle) x).getHeight());
	}

	private void updateObjects() {
		try {
			out.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//p.updateControlledObjects();
		try{
			
			
			ClientData c = new ClientData(leftPressed, rightPressed, jump);
			out.flush();
			out.writeObject(c);
			jump = false;
			
			ServerData sd = (ServerData) in.readObject();
			drawObjects = sd.getDrawObjects();
			p = sd.getP();
			
			//System.out.println("square Height: "+ sd.getDrawObjects().get(1).getLocation().getX());
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		

	}

	public void keyPressed() {
		if ((key == ' ')) {
			jump = true;
		}
		if (key == CODED) {
			if (keyCode == LEFT) {
				leftPressed = true;
			}
			if (keyCode == RIGHT) {
				rightPressed = true;
			}

		}

	}

	public void keyReleased() {

		if (key == CODED) {
			if (keyCode == LEFT) {
				leftPressed = false;
			}
			if (keyCode == RIGHT) {
				rightPressed = false;
			}

		}

	}
	
}
