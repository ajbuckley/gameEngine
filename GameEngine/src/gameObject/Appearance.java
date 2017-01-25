package gameObject;

import java.io.Serializable;

/**
 * 
 * @author Drew
 *
 */
public class Appearance implements Serializable{

	boolean visible;
	private int[] fill = { 100, 0, 100, 255 };
	private int[] stroke = { 0, 0, 0, 0 };
	private int drawType;
	
	public Appearance(){
		visible = true;
	}
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
