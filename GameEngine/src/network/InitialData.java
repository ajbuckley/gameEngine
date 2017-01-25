/**
 * 
 */
package network;

import gameObject.Drawable;
import gameObject.GameObject;

import java.io.Serializable;
import java.util.ArrayList;

import worldObjects.PlayerCharacter;
import worldObjects.PlayerController;

/**
 * @author Drew
 *
 */
public class InitialData implements Serializable {

	private ArrayList<Drawable> objects;
	private PlayerController p;
	
	public InitialData(ArrayList<Drawable> objects, PlayerController p2){
		this.setObjects(objects);
		this.setP(p2);
	}

	/**
	 * @return the objects
	 */
	public ArrayList<Drawable> getObjects() {
		return objects;
	}

	/**
	 * @param objects the objects to set
	 */
	public void setObjects(ArrayList<Drawable> objects) {
		this.objects = objects;
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
