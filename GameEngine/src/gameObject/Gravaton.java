/**
 * 
 */
package gameObject;

/**
 * @author Drew
 * 
 * Class for providing gravity. Follows singleton pattern.
 * If Objects want different gravity they can define their own field.
 */
public class Gravaton {
	/** Default gravity if not set**/
	public static final double DEFAULT_GRAVITY = 9.8;
	/** The gravity instance**/
	private static Gravaton grav = null;
	/** The gravity value**/
	private double gravity;
	
	/**
	 * Creates the gravity instance if not made.
	 */
	protected Gravaton(){
		this.setGravity(DEFAULT_GRAVITY);
	}
	/**
	 * Gets the instance of gravity
	 * 
	 * @return
	 */
	public static Gravaton getInstance(){
		if(grav == null){
			grav = new Gravaton();
		}
		return grav;
		
	}

	/**
	 * @return the gravity
	 */
	public double getGravity() {
		return gravity;
	}

	/**
	 * @param gravity the gravity to set
	 */
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
}
