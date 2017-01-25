/**
 * 
 */
package system;

import event.EventHandler;
import gameObject.Appearance;
import gameObject.CollisionVolume;
import gameObject.Drawable;
import gameObject.GameObject;
import gameObject.Location;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import network.ClientData;
import network.InitialData;
import network.ServerData;
import processing.core.PApplet;
import collision.CollisionHandler;
import script.ScriptHandler;
import system.SpaceInvader.ClientThread;
import system.SquareHopper.Listener;
import time.TimeLine;
import worldObjects.Bullet;
import worldObjects.Invader;
import worldObjects.PlayerCharacter;
import worldObjects.PlayerController;
import worldObjects.Rectangle;

/**
 * @author Drew
 *
 */
public class SpaceInvader extends PApplet {

	private static CollisionHandler cHandle;

	static ArrayList<CollisionVolume> hitBoxes;

	static ArrayList<GameObject> objects;

	static ArrayList<Drawable> drawObjects;

	static ArrayList<PlayerController> players;

	static ArrayList<Invader> invaders;

	static ArrayList<Bullet> bullets;

	public static void main(String[] args) {

		SpaceInvader sh = new SpaceInvader();

		Listener listen = sh.new Listener();
		(new Thread(listen)).start();
		sh.startSim();

	}

	public void startSim() {

		hitBoxes = new ArrayList<CollisionVolume>();
		drawObjects = new ArrayList<Drawable>();
		objects = new ArrayList<GameObject>();
		// movingObjects = new ArrayList<Drawable>();
		players = new ArrayList<PlayerController>();

		invaders = new ArrayList<Invader>();
		bullets = new ArrayList<Bullet>();
		bullets.add(new Bullet("b1"));
		bullets.add(new Bullet("b2"));
		bullets.add(new Bullet("b3"));

		drawObjects.addAll(bullets);
		objects.addAll(bullets);

		for (Bullet b : bullets) {
			hitBoxes.add(b.getHitBox());
		}

		Invader i1 = new Invader("I1", new Location(25, 25, 0));
		objects.add(i1);
		hitBoxes.add(i1.getHitBox());
		invaders.add(i1);
		drawObjects.add(i1);

		Invader i2 = new Invader("I2", new Location(125, 25, 0));
		objects.add(i2);
		hitBoxes.add(i2.getHitBox());
		invaders.add(i2);
		drawObjects.add(i2);

		Invader i3 = new Invader("I3", new Location(225, 25, 0));
		objects.add(i3);
		hitBoxes.add(i3.getHitBox());
		invaders.add(i3);
		drawObjects.add(i3);

		Invader i4 = new Invader("I4", new Location(325, 25, 0));
		objects.add(i4);
		hitBoxes.add(i4.getHitBox());
		invaders.add(i4);
		drawObjects.add(i4);

		Invader i5 = new Invader("I5", new Location(425, 25, 0));
		objects.add(i5);
		hitBoxes.add(i5.getHitBox());
		invaders.add(i5);
		drawObjects.add(i5);

		Invader i6 = new Invader("I6", new Location(25, 125, 0));
		objects.add(i6);
		hitBoxes.add(i6.getHitBox());
		invaders.add(i6);
		drawObjects.add(i6);

		Invader i7 = new Invader("I7", new Location(125, 125, 0));
		objects.add(i7);
		hitBoxes.add(i7.getHitBox());
		invaders.add(i7);
		drawObjects.add(i7);

		Invader i8 = new Invader("I8", new Location(225, 125, 0));
		objects.add(i8);
		hitBoxes.add(i8.getHitBox());
		invaders.add(i8);
		drawObjects.add(i8);

		Invader i9 = new Invader("I9", new Location(325, 125, 0));
		objects.add(i9);
		hitBoxes.add(i9.getHitBox());
		invaders.add(i9);
		drawObjects.add(i9);

		Invader i10 = new Invader("I10", new Location(425, 125, 0));
		objects.add(i10);
		hitBoxes.add(i10.getHitBox());
		invaders.add(i10);
		drawObjects.add(i10);

		cHandle = new CollisionHandler(hitBoxes);
		PApplet.main("system.SpaceInvader");

		TimeLine tl = new TimeLine(10);
		while (true) {
			System.out.println(players.size());
			if (players.size() > 0) {
				if (tl.tick()) {
					cHandle.runCheck();
					EventHandler.getInstance().handleEvents();
					updateObjects();
					for (Invader i : invaders) {
						if (i.getLocation().getX() + i.getWidth() > 600) {
							ScriptHandler
									.loadScript("scripts/bouncebackleft.js");
							for (Invader j : invaders) {
								ScriptHandler.bindArg("game_object", j);
								ScriptHandler.executeScript();
							}
						} else if (i.getLocation().getX() < 0) {
							ScriptHandler
									.loadScript("scripts/bouncebackright.js");
							for (Invader j : invaders) {
								ScriptHandler.bindArg("game_object", j);
								ScriptHandler.executeScript();
							}
						}
					}
				}

			}
		}

	}

	public void settings() {
		size(600, 600);
	}

	public void setup() {

		background(0);
		stroke(0);
		frameRate(60);
	}

	public void draw() {

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

			}
		}

	}

	private void updateObjects() {
		for (PlayerController p : players) {
			p.updateControlledObjects();
		}
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update();
		}

	}

	private void drawRect(Drawable x) {

		rect((float) x.getLocation().getX(), (float) x.getLocation().getY(),
				(float) ((Rectangle) x).getWidth(),
				(float) ((Rectangle) x).getHeight());
	}

	public class Listener extends Thread {

		public void run() {

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
					ClientThread c = new ClientThread(sSock.accept(),
							playerCount);
					System.out.println("port: " + i);
					(new Thread(c)).start();
					// clientList.add(listener);
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

	public class ClientThread extends Thread {

		PlayerController p;
		Socket socClient;

		public ClientThread(Socket accept, int playerNum) {
			this.socClient = accept;
			this.AddPlayer(playerNum);

		}

		public void run() {

			ObjectOutputStream socketOut = null;
			ObjectInputStream socketIn = null;

			try {

				socketOut = new ObjectOutputStream(socClient.getOutputStream());
				socketIn = new ObjectInputStream(socClient.getInputStream());

			} catch (IOException e) {

				e.printStackTrace();
			}

			synchronized (drawObjects) {
				try {
					ArrayList<Drawable> draws = new ArrayList<Drawable>();
					draws.addAll(drawObjects);

					socketOut.writeObject(new InitialData(draws, p));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (true) {
				try {
					socketOut.reset();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				synchronized (drawObjects) {
					try {

						ArrayList<Drawable> draws = new ArrayList<Drawable>();
						draws.addAll(drawObjects);
						socketOut.writeObject(new ServerData(draws, p));

						// System.out.println("Height: " +
						// draws.get(1).getLocation().getX());
					} catch (IOException e) {

						e.printStackTrace();
					}

				}

				synchronized (p) {
					ClientData cd;
					try {
						cd = (ClientData) socketIn.readObject();
						if (cd.isLeftPressed()) {
							p.handleLeft();
						} else {
							p.releaseLeft();
						}
						if (cd.isRightPressed()) {
							p.handleRight();

						} else {
							p.releaseRight();
						}
						if (cd.isJump()) {
							p.handleSpace();
						}

						// p.getPlayer().getVelocity().Accelerate(0,
						// v.getDeltaY(), 0);

					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		public void AddPlayer(int playerNum) {

			p = new PlayerController(bullets);

			System.out.println("Name: " + p.getPlayer().getName());

			synchronized (players) {
				players.add(p);
			}
			synchronized (drawObjects) {
				drawObjects.addAll(p.getPlayer().getShapes());
			}
			synchronized (objects) {
				objects.add(p.getPlayer());

			}
			synchronized (hitBoxes) {
				hitBoxes.add(p.getPlayer().getHitBox());
			}

		}
	}

}
