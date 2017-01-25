/**
 * 
 */
package worldObjects;

import gameObject.CollisionVolume;
import gameObject.Location;

/**
 * @author Drew
 *
 */
public class Platform extends Rectangle {

	CollisionVolume hitBox;
	
	/**
	 * @param name
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param z
	 */
	public Platform(String name, double width, double height, double x,
			double y, double z) {
		super(name, width, height, x, y, z);
		
		hitBox = new CollisionVolume(this.getLocation(), width, height, 0, this.getName());
		
	}
	
	public Platform(String name, double width, double height, Location start) {
		super(name, width, height, start);
		hitBox = new CollisionVolume(this.getLocation(), width, height, 0, this.getName());
	}
	
	public CollisionVolume getHitBox(){
		return hitBox;
	}
	

}
