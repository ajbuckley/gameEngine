/**
 * 
 */
package worldObjects;

import java.io.Serializable;
import java.util.ArrayList;

import event.Event;
import event.EventListener;
import gameObject.Location;
import gameObject.Player;

/**
 * @author Drew
 *
 */
public class PlayerController implements Player, EventListener, Serializable {

	
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private Spaceship player;
	ArrayList<Bullet> bullets;
	
	/**
	 * @return the player
	 */
	public Spaceship getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Spaceship player) {
		this.player = player;
	}

	/**
	 * 
	 */
	public PlayerController(ArrayList<Bullet> bullets) {
		player = new Spaceship("SSdb", new Location(425,520,0));
		this.bullets = bullets;
		
	}

	/* (non-Javadoc)
	 * @see gameObject.Player#handleKeys(char)
	 */
	@Override
	public void handleKeys(char key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notify(Event e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Handles action when pressing the space bar
	 */
	public void handleSpace(){
 		for(Bullet b : bullets){
			if(!b.isVisible()){
				b.setVisible(true);
				b.getHitBox().setCollided(false);
				b.getLocation().update(player.getLoc().getX() + 23, player.getLoc().getY() - 11,0);
				
				return;
			}
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
	
	public void releaseLeft() {
		leftPressed = false;
	}

	public void releaseRight() {
		rightPressed = false; 
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see gameObject.Player#updateControlledObjects()
	 */
	@Override
	public void updateControlledObjects() {
		if(leftPressed){
			player.getVel().setDeltaX(-4);;
		}
		else if(rightPressed){
			player.getVel().setDeltaX(4);;
		}
		else{
			player.getVel().setDeltaX(0);;
		}
	}


}
