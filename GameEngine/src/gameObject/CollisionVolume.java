/**
 * 
 */
package gameObject;

import java.io.Serializable;
import java.util.Scanner;

import event.Event;
import event.EventHandler;
import event.EventListener;

/**
 * @author Drew
 *
 */
public class CollisionVolume implements Serializable, EventListener{
	
	private String ID;

	//If collided with an object
	private Boolean collided;
	
	private Location collisionPoint;
	
	private CollisionVolume other;
	
	//Location of the volume
	private Location loc;
	
	//Dimensions of the volume
	private double width;
	private double height;
	private double depth;
	
	//Angle of rotation of the volume
	private double zRotation;
	private double xRotation;
	private double yRotation;
	
	
	public CollisionVolume(Location loc, double width, double height, double depth, String ID){
		this.setDepth(depth);
		this.setHeight(height);
		this.setWidth(width);
		this.setLoc(loc);
		this.setxRotation(0);
		this.setyRotation(0);
		this.setzRotation(0);
		EventHandler en = EventHandler.getInstance();
		en.registerForCollision(this);
		this.setID("CV" + ID);
		collided = false;
	}
	
	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	/**
	 * @return the depth
	 */
	public double getDepth() {
		return depth;
	}
	/**
	 * @param depth the depth to set
	 */
	public void setDepth(double depth) {
		this.depth = depth;
	}
	/**
	 * @return the zRotation
	 */
	public double getzRotation() {
		return zRotation;
	}
	/**
	 * @param zRotation the zRotation to set
	 */
	public void setzRotation(double zRotation) {
		this.zRotation = zRotation;
	}
	/**
	 * @return the xRotation
	 */
	public double getxRotation() {
		return xRotation;
	}
	/**
	 * @param xRotation the xRotation to set
	 */
	public void setxRotation(double xRotation) {
		this.xRotation = xRotation;
	}
	/**
	 * @return the yRotation
	 */
	public double getyRotation() {
		return yRotation;
	}
	/**
	 * @param yRotation the yRotation to set
	 */
	public void setyRotation(double yRotation) {
		this.yRotation = yRotation;
	}

	/**
	 * @return the collisionPoint
	 */
	public Location getCollisionPoint() {
		return collisionPoint;
	}

	/**
	 * @param collisionPoint the collisionPoint to set
	 */
	public void setCollisionPoint(Location collisionPoint) {
		this.collisionPoint = collisionPoint;
	}

	/**
	 * @return the collided
	 */
	public Boolean getCollided() {
		return collided;
	}

	/**
	 * @param collided the collided to set
	 */
	public void setCollided(Boolean collided) {
		this.collided = collided;
	}

	/**
	 * @return the other
	 */
	public CollisionVolume getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(CollisionVolume other) {
		this.other = other;
	}


	@Override
	public void notify(Event e) {
		Scanner scan = new Scanner(e.getMessage());
		String iD1 = scan.next();
		String iD2 = scan.next();
		if(iD1.equals(this.ID) || iD2.equals(this.ID)){
			this.setCollided(true);
			System.out.println(this.ID);
			double xLoc = Double.parseDouble(scan.next());
			double yLoc = Double.parseDouble(scan.next());
			double zLoc = Double.parseDouble(scan.next());
			this.setCollisionPoint(new Location(xLoc, yLoc, zLoc));
		}
		
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	
	
	
	
}
