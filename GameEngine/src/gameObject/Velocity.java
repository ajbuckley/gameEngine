/**
 * 
 */
package gameObject;

import java.io.Serializable;

/**
 * @author Drew
 *
 */
public class Velocity implements Serializable {
	
	public static final double MIN_BOUNCE_VELOCITY = 3;
	public static final double REBOUND_RATIO = -.1;
	public static final double COEFICIENT_OF_FRICTION = .1;
	public static final double MAX_VELOCITY = 10;
	private double deltaX;
	private double deltaY;
	private double deltaZ;
	
	
	public Velocity(){
		this.setDeltaX(0);
		this.setDeltaY(0);
		this.setDeltaZ(0);
	}
	
	
	public Velocity(double deltaX, double deltaY, double deltaZ){
		this.setDeltaX(deltaX);
		this.setDeltaY(deltaY);
		this.setDeltaZ(deltaZ);
	}
	/**
	 * Updates the velocity.
	 * @param deltaX
	 * @param deltaY
	 * @param deltaZ
	 */
	public void update(double deltaX, double deltaY, double deltaZ){
		this.setDeltaX(deltaX);
		this.setDeltaY(deltaY);
		this.setDeltaZ(deltaZ);
		checkMax();
	}
	
	/**
	 * Make sure were not moving too fast
	 */
	private void checkMax() {
		if(deltaX > MAX_VELOCITY){
			deltaX = MAX_VELOCITY;
			
		}
		
		if(deltaX < -MAX_VELOCITY){
			deltaX = -MAX_VELOCITY;
			
		}
		
		if(deltaY > MAX_VELOCITY){
			deltaY = MAX_VELOCITY;
			
		}
		
		if(deltaY < -MAX_VELOCITY){
			deltaY = -MAX_VELOCITY;
			
		}
		
		if(deltaZ > MAX_VELOCITY){
			deltaZ = MAX_VELOCITY;
			
		}
		
		if(deltaZ < -MAX_VELOCITY){
			deltaZ = -MAX_VELOCITY;
			
		}
	}

	/**
	 * Increases the velocity by the given value
	 * @param dpX
	 * @param dpY
	 * @param dpZ
	 */
	public void Accelerate(double dpX, double dpY, double dpZ){
		this.setDeltaX(deltaX + dpX);
		this.setDeltaY(deltaY + dpY);
		this.setDeltaZ(deltaZ + dpZ);
		checkMax();
		
	}
	/**
	 * @return the deltaX
	 */
	public double getDeltaX() {
		return deltaX;
	}
	/**
	 * @param deltaX the deltaX to set
	 */
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}
	/**
	 * @return the deltaZ
	 */
	public double getDeltaZ() {
		return deltaZ;
	}
	/**
	 * @param deltaZ the deltaZ to set
	 */
	public void setDeltaZ(double deltaZ) {
		this.deltaZ = deltaZ;
	}
	/**
	 * @return the deltaY
	 */
	public double getDeltaY() {
		return deltaY;
	}
	/**
	 * @param deltaY the deltaY to set
	 */
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	public double magnitude(){
		double mag = Math.sqrt(deltaX*deltaX + deltaY*deltaY +deltaZ*deltaZ);
		return mag;
		
	}
	
}
