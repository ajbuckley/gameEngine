/**
 * 
 */
package worldObjects;


import event.Event;
import event.EventHandler;
import event.EventListener;
import gameObject.Appearance;
import gameObject.CollisionVolume;
import gameObject.Location;
import gameObject.Velocity;

/**
 * @author Drew
 *
 */
public class Bullet extends Rectangle {

	private CollisionVolume hitBox;
	private Velocity vel;
	/**
	 * @param name
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param z
	 */
	public Bullet(String name) {
		super(name, 4, 10, -12, -12, 0);
		vel = new Velocity(0,-7,0);
		hitBox = new CollisionVolume(this.getLocation(), 4, 10, 0, this.getName());
		int [] stroke ={0,0,0,255};
		int []fill = {200,20,200,255};
		Appearance looks = new Appearance();
		looks.setColor(stroke, fill);
		this.setLooks(looks);
		this.getLooks().setVisible(false);
	}

	/**
	 * @param name
	 * @param width
	 * @param height
	 * @param loc
	 */
	public Bullet(String name, double width, double height, Location loc) {
		super(name, width, height, loc);
		// TODO Auto-generated constructor stub
	}

	public void update(){
		if(this.isVisible()){
			if(hitBox.getCollided()){
				this.getLocation().setY(-12);
				hitBox.setCollided(false);
				this.setVisible(false);
				
				
				return;
			}
			this.getLocation().translate(vel.getDeltaX(), vel.getDeltaY() ,0);
		}
		if(this.getLocation().getY() < -15){
			this.setVisible(false);
			this.getLocation().setY(-12);
			return;
		}
	}

	/**
	 * @return the hitBox
	 */
	public CollisionVolume getHitBox() {
		return hitBox;
	}

	/**
	 * @param hitBox the hitBox to set
	 */
	public void setHitBox(CollisionVolume hitBox) {
		this.hitBox = hitBox;
	}



}
