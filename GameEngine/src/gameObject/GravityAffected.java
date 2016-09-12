package gameObject;

import java.util.Vector;

public interface GravityAffected extends Locatable {
	public void fall();
	public boolean inAir();
	void setLoc(Vector loc);
	public Vector getLoc();
	void setVisible();
	boolean isVisible();
}
