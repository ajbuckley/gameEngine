package gameObject;

/**
 * Interface for a object that has gravity.
 * @author Drew
 *
 */
public interface GravityAffected {
	
	/**
	 * Updates the objects VELOCITY with the acceleration value of gravity.
	 */
	public void fall();
	/**
	 * True if the object is in the air.
	 * @return
	 */
	public boolean inAir();
	/**
	 * Sets the gravity to an object specific value.
	 * @param grav
	 */
	public void setGravity(float grav);
	/**
	 * Sets the gravity to the universal system gravity.
	 * @param grav
	 */
	public void setGravity();
	/**
	 * Returns the gravity value for this object.
	 * @return
	 */
	public double getGravity();
	/**
	 * Sets the ground the object is on
	 * @param ground
	 */
	public void setGround(CollisionVolume ground);
	/**
	 * Changes the air status of the object.
	 * @param inAir
	 */
	public void setInAir(boolean inAir);
	/**
	 * Returns the ground for this object, if it has one.
	 * @return
	 */
	public CollisionVolume getGround();
}
