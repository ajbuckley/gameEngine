/**
 * 
 */
package worldObjects;

import gameObject.Location;
import gameObject.Velocity;

/**
 * @author Drew
 *
 */
public class MovingPlatform extends Platform {

	private double distance;
	private double travelled;
	private Location end;
	private Location start;
	private Velocity speed;
	
	/**
	 * @param name
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param z
	 */
	public MovingPlatform(String name, double width, double height, double x,
			double y, double z, double fx, double fy, double fz, double time) {
		super(name, width, height, x, y, z);
		this.end = new Location(fx,fy,fz);
		this.start = new Location(x,y,z);
		double dx = (fx - x)/time;
		double dy = (fy - y)/time;
		double dz = (fz - z)/time;
		speed = new Velocity(dx,dy,dz);
		calcDistVals(time);
	}
	
	public MovingPlatform(String name, double width, double height, Location start, Location end, double time) {
		super(name, width, height, start);
		this.end = end;
		this.start = new Location(start.getX(),start.getY(),start.getZ());
		double dx = (end.getX() - start.getX())/time;
		double dy = (end.getY() - start.getY())/time;
		double dz = (end.getZ() - start.getZ())/time;
		speed = new Velocity(dx, dy, dz);
		calcDistVals(time);
	}
	
	private void calcDistVals(double time) {
		this.travelled = 0;
		this.distance = speed.magnitude() * time;
		
	}

	public void update(){
		getLocation().translate(speed);
		travelled += speed.magnitude();
		if(travelled >= distance){
			speed.setDeltaX(speed.getDeltaX()* -1);
			speed.setDeltaY(speed.getDeltaY()* -1);
			speed.setDeltaZ(speed.getDeltaZ()* -1);
			travelled = 0;
			
		}
		
		
	}

}
