/**
 * 
 */
package network;

import gameObject.Location;
import gameObject.Velocity;

import java.io.Serializable;

import worldObjects.PlayerCharacter;

/**
 * @author Drew
 *
 *Class for client packets to send to the server.
 *For now all we need to send is a velocity.
 */
public class ClientData implements Serializable {
	
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean jump;
	public ClientData(boolean left, boolean right, boolean jump){
		this.setLeftPressed(left);
		this.setRightPressed(right);
		this.setJump(jump);
	}
	/**
	 * @return the leftPressed
	 */
	public boolean isLeftPressed() {
		return leftPressed;
	}
	/**
	 * @param leftPressed the leftPressed to set
	 */
	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}
	/**
	 * @return the rightPressed
	 */
	public boolean isRightPressed() {
		return rightPressed;
	}
	/**
	 * @param rightPressed the rightPressed to set
	 */
	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}
	/**
	 * @return the jump
	 */
	public boolean isJump() {
		return jump;
	}
	/**
	 * @param jump the jump to set
	 */
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
}
