/**
 * 
 */
package network;

import gameObject.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

import worldObjects.PlayerCharacter;
import worldObjects.PlayerController;

/**
 * @author Drew
 *
 * For sending Server data to clients.
 */
public class ServerData implements Serializable {
	
	private ArrayList<Drawable> drawObjects;
	private PlayerController p;
	public  ServerData(ArrayList<Drawable> drawable, PlayerController p2){
		this.setDrawObjects(drawable);
		this.setP(p2);
	}

	/**
	 * @return the movingObjects
	 */
	public ArrayList<Drawable> getDrawObjects() {
		return drawObjects;
	}

	/**
	 * @param movingObjects the movingObjects to set
	 */
	public void setDrawObjects(ArrayList<Drawable> drawObjects) {
		this.drawObjects = drawObjects;
	}

	/**
	 * @return the p
	 */
	public PlayerController getP() {
		return p;
	}

	/**
	 * @param p2 the p to set
	 */
	public void setP(PlayerController p2) {
		this.p = p2;
	}
	
	
	
}
