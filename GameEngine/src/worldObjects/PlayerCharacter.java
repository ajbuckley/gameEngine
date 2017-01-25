/**
 * 
 */
package worldObjects;

import java.io.Serializable;

import gameObject.Appearance;
import gameObject.Location;
import gameObject.Player;
import gameObject.Velocity;

/**
 * @author Drew
 *
 */
public class PlayerCharacter implements Player, Serializable {
	private static final double JUMP_SPEED = -7;
	private static final double RUN_ACCEL = 1;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private PlayerSquare player;
	
	
	/**
	 * 
	 */
	public PlayerCharacter(String name, Location start, Appearance looks, int width, int height) {
		player = new PlayerSquare(name, width, height, start);
		player.setLooks(looks);
		
	}

	/* (non-Javadoc)
	 * @see gameObject.Player#handleKeys(char)
	 */
	@Override
	public void handleKeys(char key) {
		

	}
	/**
	 * Handles action when pressing the space bar
	 */
	public void handleSpace(){
		if(!player.inAir()){
			player.getVelocity().setDeltaY(JUMP_SPEED);
		}
	}
	/**
	 * Handles action when pressing the Left arrow
	 */
	public void handleLeft(){
		leftPressed = true;
		
	}
	/**
	 * Handles action when pressing the Right Arrow
	 */
	public void handleRight(){
		rightPressed = true;
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see gameObject.Player#updateControlledObjects()
	 */
	@Override
	public void updateControlledObjects() {
		if(leftPressed){
			player.getVelocity().Accelerate(-RUN_ACCEL, 0, 0);
		}
		if(rightPressed){
			player.getVelocity().Accelerate(RUN_ACCEL, 0, 0);
		}
		/*if(!rightPressed && !leftPressed){
			if(player.getVelocity().magnitude() > .3){
				player.getVelocity().setDeltaX(player.getVelocity().getDeltaX() * (1 - Velocity.COEFICIENT_OF_FRICTION));
			}
			else player.getVelocity().setDeltaX(0);
		}*/
		//player.update();
	}

	/**
	 * @return the player
	 */
	public PlayerSquare getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(PlayerSquare player) {
		this.player = player;
	}

	public void releaseLeft() {
		leftPressed = false;

		
	}

	public void releaseRight() {
		rightPressed = false; 


		
	}

}
