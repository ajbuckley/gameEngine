package gameObject;

import java.util.Vector;

public interface Locatable {
	public void setLoc(Vector loc);
	public Vector getLoc();
	public void setVisible();
	public boolean isVisible();
}
