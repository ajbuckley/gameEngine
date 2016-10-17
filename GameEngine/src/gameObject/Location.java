/**
 * 
 */
package gameObject;

/**
 * @author Drew
 *
 */
public class Location {
	
	private double x;
	private double y;
	private double z;
	
	/**
	 * Constructor for location without initial location.
	 */
	public Location(){
		this.setX(0);
		this.setY(0);
		this.setZ(0);
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(double z) {
		this.z = z;
	}
	


	/**
	 * Constructor for Location with initial location
	 * @param x
	 * @param y
	 * @param z
	 *
	*/	
	public Location(double x, double y, double z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	/**
	 * Translates the location by the given amount.
	 * @param v : The Velocity to translate the location by.
	 */
	public void translate(Velocity v){
		this.setX(x + v.getDeltaX());
		this.setY(y + v.getDeltaY());
		this.setZ(z + v.getDeltaZ());
		
	}
	/**
	 *  Translates the location by the given amount.
	 *  
	 * @param deltaX
	 * @param deltaY
	 * @param deltaZ
	 */
	public void translate(double deltaX, double deltaY, double deltaZ){
		this.setX(x + deltaX);
		this.setY(y + deltaY);
		this.setZ(z + deltaZ);
	}
	/**
	 * Updates the location to the desired coordinates.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void update(double x, double y, double z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
}
