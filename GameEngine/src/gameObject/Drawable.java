/**
 * 
 */
package gameObject;

/**
 * @author Drew
 *
 */
public interface Drawable {
	public static int RECT_DRAW_VAL = 1;
	public static int LINE_DRAW_VAL = 2;
	
	public boolean isVisible();
	
	public void setVisible(boolean vis);
	
	public Appearance getLooks();
	
	public Location getLocation();
}
