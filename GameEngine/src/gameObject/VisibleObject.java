package gameObject;



public interface VisibleObject {
	public static int SQUARE_DRAW_VAL = 1;
	public static int RECT_DRAW_VAL = 2;
	public static int LINE_DRAW_VAL = 3;
	
	/**
	 * Sets the location of the object
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLoc(int x, int y, int z);
	/**
	 * 
	 * @return The x coord of the object
	 */
	public int getX();
	/**
	 * 
	 * @return The y coord of the object
	 */
	public int getY();
	/**
	 * 
	 * @return The z coord of the object
	 */
	public int getZ();
	/**
	 * 
	 * @return Returns an int array of the objects location
	 */
	public int[] getLoc();
	/**
	 * Sets the visibility of the Object. A visibility of true means the object will be drawn
	 * 	False means the object does not get drawn.
	 *  @param visible: The desired visibility
	 **/
	public void setVisible(boolean visible);
	/**
	 * 
	 * @return The visibility of the object
	 */
	public boolean isVisible();
	/**
	 * tells the system what to draw it as.
	 *  @return A string representation of the draw type
	 */
	public int drawType();
	/**
	 * Updates the location of the object
	 *
	 */
	public void update();
	/**
	 * Gets the color of the outline
	 * @return stroke: the objects outline
	 */
	public int[] getStroke();
	/**
	 * Gets the color of the object.
	 * @return fill: the objects color
	 */
	public int[] getFill();
	/**
	 * Sets the color of the object
	 * @param 
	 * 	fill: the desired color of the object
	 * 	stroke: the desired color of the outline
	 */
	public void setColor(int[] stroke, int[] fill);
	
	/**
	 * Sets the Velocity of the object
	 * @param vel: the Velocity
	 */
	public void setVelocity(float [] vel);
	/**
	 * 
	 * @return The velocity of the object
	 */
	public float[] getVelocity();
	/**
	 * Handles collisions with the object 
	 * @param o: the comparison object
	 */
	public void handleCollisions(VisibleObject o);

	
}
