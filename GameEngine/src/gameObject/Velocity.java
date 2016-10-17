/**
 * 
 */
package gameObject;

/**
 * @author Drew
 *
 */
public class Velocity {
	
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
	public void Update(double deltaX, double deltaY, double deltaZ){
		this.setDeltaX(deltaX);
		this.setDeltaY(deltaY);
		this.setDeltaZ(deltaZ);
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
	
	
}
