package gameObject;
/**
 * 
 * @author Drew
 *
 */
public class Colored {
	public static int SQUARE_DRAW_VAL = 1;
	public static int RECT_DRAW_VAL = 2;
	public static int LINE_DRAW_VAL = 3;
	boolean visible;
	private int[] fill = { 100, 0, 100, 255 };
	private int[] stroke = { 0, 50, 100, 255 };
	private int drawType;
	
	/**
	 * Sets the visibility of the Object. A visibility of true means the object will be drawn
	 * 	False means the object does not get drawn.
	 *  @param visible: The desired visibility
	 **/
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	/**
	 * 
	 * @return The visibility of the object
	 */
	public boolean isVisible(){
		return visible;
		
	}
	/**
	 * Flips the state of the visible boolean.
	 * 
	 */
	public void ToggleVisible(){
	
		visible = !visible;
	}
	
	/**
	 * Gets the color of the outline
	 * @return stroke: the objects outline
	 */
	public int[] getStroke(){
		return stroke;
	}
	/**
	 * Gets the color of the object.
	 * @return fill: the objects color
	 */
	public int[] getFill(){
		return fill;
	}
	/**
	 * Sets the color of the object
	 * @param 
	 * 	fill: the desired color of the object
	 * 	stroke: the desired color of the outline
	 */
	public void setColor(int[] stroke, int[] fill){
		this.stroke = stroke;
		this.fill = fill;
	}
	public void setDrawType(int drawType){
		this.drawType = drawType;
		
	}
	public int getDrawType(){
		return drawType;
	}

}
