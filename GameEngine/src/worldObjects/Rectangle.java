/**
 * 
 */
package worldObjects;

import gameObject.Appearance;
import gameObject.Drawable;
import gameObject.GameObject;
import gameObject.Location;

/**
 * @author Drew
 *
 */
public class Rectangle extends GameObject implements Drawable {
	private Location coords;
	private Appearance looks;
	private double width;
	private double height;
	
	/**
	 * 
	 * @param name
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param z
	 */
	public Rectangle(String name, double width, double height, double x, double y, double z) {
		super(name);
		this.setHeight(height);
		this.setWidth(width);
		setCoords(new Location(x, y, z));
		setLooks(new Appearance());
		
	}
	/**
	 * 
	 * @param name
	 * @param width
	 * @param height
	 * @param loc
	 */
	public Rectangle(String name, double width, double height, Location loc) {
		super(name);
		setCoords(loc);
		this.setHeight(height);
		this.setWidth(width);
		setLooks(new Appearance());
		
	}

	/* (non-Javadoc)
	 * @see gameObject.GameObject#update()
	 */
	public void update() {
		
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
	 * @return the coords
	 */
	public Location getLocation() {
		return coords;
	}

	/**
	 * @param coords the coords to set
	 */
	public void setCoords(Location coords) {
		this.coords = coords;
	}

	/**
	 * @return the looks
	 */
	public Appearance getLooks() {
		return looks;
	}

	/**
	 * @param looks the looks to set
	 */
	public void setLooks(Appearance looks) {
		this.looks = looks;
	}
	@Override
	public boolean isVisible() {
		return looks.isVisible();
	}
	@Override
	public void setVisible(boolean vis) {
		this.looks.setVisible(vis);
		
	}

	
	
	

}
