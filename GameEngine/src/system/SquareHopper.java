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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import network.ClientData;
import network.InitialData;
import network.Server;
import network.ServerData;
import collision.CollisionHandler;
import processing.core.PApplet;
import worldObjects.MovingPlatform;
import worldObjects.Platform;
import worldObjects.PlayerCharacter;
import worldObjects.PlayerSquare;
import worldObjects.Rectangle;

/**
 * @author Drew
 *
 */
public class SquareHopper extends PApplet {
	
	private static CollisionHandler cHandle;
	
	static ArrayList<CollisionVolume> hitBoxes;
	
	static ArrayList<GameObject> objects;
	
	static ArrayList<Drawable> drawObjects;
	/** Default stroke(outline) = black **/
	int strokeDef = 0;
	/** Default fill = black **/
	int fillDef = 0;
	
	static ArrayList<PlayerCharacter> players;
	
	//static ArrayList<Drawable> movingObjects;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SquareHopper sh = new SquareHopper();
		sh.startSim();
		Listener listen = sh.new Listener();
		(new Thread(listen)).start();
		
		

	}
	
	private ArrayList<Drawable> getDrawables() {
		return drawObjects;
	}

	public void startSim(){
		
		
		hitBoxes = new ArrayList<CollisionVolume>();
		drawObjects = new ArrayList<Drawable>();
		objects = new ArrayList<GameObject>();
		//movingObjects = new ArrayList<Drawable>();
		players = new ArrayList<PlayerCharacter>();
		
		Appearance plats = new Appearance(); 
		plats.setDrawType(Drawable.RECT_DRAW_VAL);
		
		Platform p1 = new Platform("p1", 900, 50, 50, 525, 0 );
		p1.setLooks(plats);
		
		Platform p3 = new Platform("p3", 200, 50, 600, 400, 0 );
		p3.setLooks(plats);
		
		Platform p4 = new Platform("p4", 100, 400, 850, 175, 0 );
		p4.setLooks(plats);
		
		Location start , end;
		start = new Location(50, 175, 0);
		end = new Location(750, 300,0);
		MovingPlatform p2 = new MovingPlatform("p2", 150, 25, start, end, 240 );
		p2.setLooks(plats);
		
		
		
				
		
		drawObjects.add(p1);
		objects.add(p1);
		drawObjects.add(p2);
		objects.add(p2);
		drawObjects.add(p3);
		objects.add(p3);
		drawObjects.add(p4);
		objects.add(p4);
		
		//movingObjects.add(p2);
		
		hitBoxes.add(p1.getHitBox());
		hitBoxes.add(p2.getHitBox());
		hitBoxes.add(p3.getHitBox());
		hitBoxes.add(p4.getHitBox());
		
		
		
		cHandle = new CollisionHandler(hitBoxes);
		PApplet.main("system.SquareHopper");
		
	}

	public void settings() {
		size(1000, 600);
	}

	public void setup() {
		
		background(0);
		stroke(0);
		frameRate(60);
	}

	public void draw() {

		cHandle.runCheck();
		updateObjects();
		background(0);
		for (int i = 0; i < drawObjects.size(); i++) {
			Drawable x = drawObjects.get(i);
			if (x.isVisible()) {
				Appearance xAP = x.getLooks();
				stroke(xAP.getStroke()[0], xAP.getStroke()[1],xAP.getStroke()[2], xAP.getStroke()[3]);
				fill(xAP.getFill()[0], xAP.getFill()[1], xAP.getFill()[2], xAP.getFill()[3]);

				switch (xAP.getDrawType()) {
				case Drawable.RECT_DRAW_VAL:
					drawRect(x);
					break;
				case Drawable.LINE_DRAW_VAL:
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


	private void drawRect(Drawable x) {

		rect((float)x.getLocation().getX(),(float) x.getLocation().getY(), (float)((Rectangle) x).getWidth(),
				(float)((Rectangle) x).getHeight());
	}

	private void drawLine(Drawable line) {

	}


	private void updateObjects() {
		for(PlayerCharacter p : players){
			p.updateControlledObjects();
		}
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update();
		}
		
	}
	
	public class Listener extends Thread{
		
		public void run(){
			
			ServerSocket sSock = null;
			int i = 10000;
				try {
					int playerCount = 0;
					while (true) {
						try {
							sSock = new ServerSocket(i);
						} catch (IOException e) {
							System.out.println(e.toString());
							System.exit(1);
						}
						System.out.println("Listening for clients");
						System.out.println("port: " + i);
						playerCount++;
						ClientThread c = new ClientThread(sSock.accept(), playerCount);
						System.out.println("port: " + i);
						(new Thread(c)).start();
						//clientList.add(listener);
						i++;

					}
				
				
			} catch (IOException e) {
				System.out.println(e.toString());
				System.exit(1);
			}

			try {
				sSock.close();
			} catch (IOException e) {
				System.out.println(e.toString());
				System.exit(1);
			}
			
			}
			
		}
		
	
	
	private class ClientThread extends Thread {
		
		PlayerCharacter p;
		Socket socClient;
		
		public ClientThread(Socket accept, int playerNum) {
			this.socClient = accept;
			this.AddPlayer(playerNum);
			
			
		}

		public void run(){
			
			ObjectOutputStream socketOut = null;
			ObjectInputStream socketIn = null;
			
			try {
				
				socketOut = new ObjectOutputStream(socClient.getOutputStream());
				socketIn = new ObjectInputStream(socClient.getInputStream());
				
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			synchronized(drawObjects){
				ArrayList<Drawable> draws = new ArrayList<Drawable>();
				draws.addAll(drawObjects);
			}
				while(true){
					try {
						socketOut.reset();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					synchronized(drawObjects){
						ArrayList<Drawable> draws = new ArrayList<Drawable>();
						draws.addAll(drawObjects);
						//socketOut.writeObject(new ServerData(draws,p) );
						
					}
					
					synchronized(p){
						ClientData cd;
						try {
							cd = (ClientData) socketIn.readObject();
							if(cd.isLeftPressed()){
								p.handleLeft();
							}
							else{
								p.releaseLeft();
							}
							if(cd.isRightPressed()){
								p.handleRight();
								
							}
							else{
								p.releaseRight();
							}
							if(cd.isJump()){
								p.handleSpace();
							}
							
							//p.getPlayer().getVelocity().Accelerate(0, v.getDeltaY(), 0);
								
							
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					
				

			
		}
		public void AddPlayer(int playerNum){
			
			Appearance playa = new Appearance(); 
			playa.setDrawType(Drawable.RECT_DRAW_VAL);
			int[] fill = { 100, 100, 0, 255 };
			int[] stroke = { 0, 50, 100, 255 };
			playa.setColor(stroke, fill);
			Location start = new Location(50*playerNum, 400,0);
			p = new PlayerCharacter("player"+ playerNum,start,playa, 50,50);
			
			System.out.println("Name: "+ p.getPlayer().getName());
			
			synchronized(players){
				players.add(p);
			}
			synchronized(drawObjects){
				drawObjects.add(p.getPlayer());
			}
			synchronized(objects){
				objects.add(p.getPlayer());
			
			}
			synchronized(hitBoxes){
				hitBoxes.add(p.getPlayer().getHitBox());
			}

			
		}
	}
}
