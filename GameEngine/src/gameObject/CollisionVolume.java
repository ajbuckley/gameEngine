/**
 * 
 */
package gameObject;

/**
 * @author Drew
 *
 */
public class CollisionVolume {
	
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
	
	
	public CollisionVolume(Location loc, double width, double height, double depth){
		this.setDepth(depth);
		this.setHeight(height);
		this.setWidth(width);
		this.setLoc(loc);
		this.setxRotation(0);
		this.setyRotation(0);
		this.setzRotation(0);
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
	
	
	
	
}
