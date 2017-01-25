package worldObjects;

import gameObject.Appearance;
import gameObject.CollisionVolume;
import gameObject.Location;
import gameObject.Velocity;

public class Invader extends Rectangle {

	private CollisionVolume hitBox;
	private Velocity vel;
	
	public Invader(String name, Location loc) {
		super(name, 50, 50, loc);
		Appearance looks = new Appearance();
		int [] stroke = {200,0,0,255};
		int [] fill = {200,0,0,255};
		looks.setColor(stroke, fill);
		this.setLooks(looks);
		vel = new Velocity(1,0,0);
		hitBox = new CollisionVolume(loc, 50, 50, 0, name);
	}

	/**
	 * @return the vel
	 */
	public Velocity getVel() {
		return vel;
	}

	/**
	 * @param vel the vel to set
	 */
	public void setVel(Velocity vel) {
		this.vel = vel;
	}
	
	public void update(){
		if(this.isVisible()){
			if(this.getHitBox().getCollided()){
				this.setVisible(false);
				this.getLocation().setY(-100);
				this.getLocation().setX(100);
			}
			
			this.getLocation().translate(vel);
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
