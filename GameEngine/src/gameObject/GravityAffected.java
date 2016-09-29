package gameObject;

import java.util.Vector;

public interface GravityAffected extends VisibleObject {
	public void fall();
	public boolean inAir();
	public int[] getLoc();
	void setVisible(boolean visible);
	boolean isVisible();
	public void setGravity(float grav);
	public float getGravity();
	public void setGround(VisibleObject o);
	public void setAir(boolean b);
}
