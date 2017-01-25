/**
 * 
 */
package worldObjects;

import gameObject.CollisionVolume;
import gameObject.Gravaton;
import gameObject.GravityAffected;
import gameObject.Location;
import gameObject.Velocity;

/**
 * @author Drew
 *
 */
public class PlayerSquare extends Rectangle implements GravityAffected {

	private CollisionVolume hitBox;
	private Velocity vel;
	private PlayerSpawn spawn;
	private Gravaton grav;
	private double gravity;
	private boolean customGravity;
	private CollisionVolume ground;
	private boolean inAir;
	private Location groundLoc;
	Velocity gVel;
	/**
	 * @param name
	 * @param width
	 * @param height
	 * @param x
	 * @param y
	 * @param z
	 */
	public PlayerSquare(String name, double width, double height, double x,
			double y, double z) {
		super(name, width, height, x, y, z);
		spawn = new PlayerSpawn(x, y, z);
		hitBox = new CollisionVolume(this.getLocation(), width, height, 0, name);
		grav = Gravaton.getInstance();
		customGravity = false;
		vel = new Velocity(0,0,0);
		inAir = true;
		gVel= new Velocity(0,0,0);
		groundLoc = new Location();
	}
	
	public PlayerSquare(String name, double width, double height, Location start) {
		super(name, width, height, start);
		spawn = new PlayerSpawn(start.getX(),start.getY(),0);
		hitBox = new CollisionVolume(this.getLocation(), width, height, 0, this.getName());
		grav = Gravaton.getInstance();
		customGravity = false;
		vel = new Velocity(0,0,0);
		inAir = true;
		gVel= new Velocity(0,0,0);
		groundLoc = new Location();
	}
	
	
	/**
	 * Returns the hitBoxx of the object
	 * @return
	 */
	public CollisionVolume getHitBox(){
		return hitBox;
	}
	
	public void update(){
		
		checkCollisions();
		
		if(!inAir){
			if(ground != null){
				if(!groundLoc.equalsVals(ground.getLoc())){
					gVel.update(ground.getLoc().getX() - groundLoc.getX(), ground.getLoc().getY() - groundLoc.getY(),0);
					groundLoc.update(ground.getLoc().getX(), ground.getLoc().getY(), 0);
					
					if(ground.getLoc().getX() > this.getLocation().getX() + this.getWidth()){
						inAir = true;
						ground = null;
						gVel.update(0, 0, 0);
					}
					else if(ground.getLoc().getX() + ground.getWidth() < this.getLocation().getX()){
						inAir = true;
						ground = null;
						gVel.update(0, 0, 0);
					}
				}
				else{
					if(ground.getLoc().getY() != this.getLocation().getY() + this.getHeight()){
						inAir = true;
						ground = null;
						gVel.update(0, 0, 0);
					}
					else if(ground.getLoc().getX() > this.getLocation().getX() + this.getWidth()){
						inAir = true;
						ground = null;
						gVel.update(0, 0, 0);
					}
					else if(ground.getLoc().getX() + ground.getWidth() < this.getLocation().getX()){
						inAir = true;
						ground = null;
						gVel.update(0, 0, 0);
					}

				}
			
			}
		}
		
		if(inAir){
			fall();
		}
		
		vel.setDeltaX(vel.getDeltaX()* (1 - Velocity.COEFICIENT_OF_FRICTION));
		if(vel.magnitude() < .02){
			vel.setDeltaX(0);
		}
		this.getLocation().translate(vel.getDeltaX() + gVel.getDeltaX(), vel.getDeltaY() + gVel.getDeltaY(),0);
		
	}

	private void checkCollisions() {
		if(hitBox.getCollided()){
			if(hitBox.getCollisionPoint().getY() >= this.getLocation().getY() + this.getHeight()
					&& this.getVelocity().getDeltaY() < Velocity.MIN_BOUNCE_VELOCITY){
				this.getLocation().setY(hitBox.getOther().getLoc().getY() - this.getHeight());
				this.setGround(hitBox.getOther());
				groundLoc = new Location(hitBox.getOther().getLoc().getX(),hitBox.getOther().getLoc().getY(),0);
				hitBox.setCollided(false);
				hitBox.setCollisionPoint(null);
				hitBox.setOther(null);
				this.setInAir(false);
				vel.setDeltaY(0);
				
			}
			
			else if(hitBox.getCollisionPoint().getY() == this.getLocation().getY() + this.getHeight()){
				this.getLocation().setY(hitBox.getOther().getLoc().getY() - this.getHeight());
				hitBox.setCollided(false);
				hitBox.setCollisionPoint(null);
				hitBox.setOther(null);
				this.setInAir(true);
				vel.setDeltaY(vel.getDeltaY() * Velocity.REBOUND_RATIO);
			}
			
			else if(hitBox.getCollisionPoint().getY() == this.getLocation().getY()){
				this.getLocation().setY(hitBox.getOther().getLoc().getY() + hitBox.getOther().getHeight());
				hitBox.setCollided(false);
				hitBox.setCollisionPoint(null);
				hitBox.setOther(null);
				vel.setDeltaY(vel.getDeltaY() * Velocity.REBOUND_RATIO);
			}
			 
			else if(hitBox.getCollisionPoint().getX() == this.getLocation().getX()){
				this.getLocation().setX(hitBox.getOther().getLoc().getX() + hitBox.getOther().getWidth());
				hitBox.setCollided(false);
				hitBox.setCollisionPoint(null);
				hitBox.setOther(null);
				vel.setDeltaX(vel.getDeltaX() * Velocity.REBOUND_RATIO);
			}
			
			else if(hitBox.getCollisionPoint().getX() == this.getLocation().getX() + this.getWidth()){
				this.getLocation().setX(hitBox.getOther().getLoc().getX() - this.getWidth());
				hitBox.setCollided(false);
				hitBox.setCollisionPoint(null);
				hitBox.setOther(null);
				vel.setDeltaX(vel.getDeltaX() * Velocity.REBOUND_RATIO);
			}
			
		}
		
		if(this.getLocation().getX()< -50 || this.getLocation().getY() > 900){
			respawn();
		}
		
	}

	/* (non-Javadoc)
	 * @see gameObject.GravityAffected#fall()
	 */
	public void fall() {
		if(customGravity){
			vel.Accelerate(0, gravity/60, 0);
		}
		else{
			vel.Accelerate(0, grav.getGravity()/60, 0);
		}
		
	}

	/* (non-Javadoc)
	 * @see gameObject.GravityAffected#inAir()
	 */
	public boolean inAir() {
		
		return inAir;
	}

	/* (non-Javadoc)
	 * @see gameObject.GravityAffected#setGravity(float)
	 */
	public void setGravity(float grav) {
		gravity = grav;
		this.customGravity = true;

	}

	/* (non-Javadoc)
	 * @see gameObject.GravityAffected#setGravity(gameObject.Gravaton)
	 */
	public void setGravity() {
		grav = Gravaton.getInstance();
		gravity = Gravaton.getInstance().getGravity();
		this.customGravity = false;
		

	}

	/* (non-Javadoc)
	 * @see gameObject.GravityAffected#getGravity()
	 */
	public double getGravity() {
		if(customGravity){
			return gravity;
		}
		return grav.getGravity();
	}

	/* (non-Javadoc)
	 * @see gameObject.GravityAffected#setGround(gameObject.CollisionVolume)
	 */
	public void setGround(CollisionVolume ground) {
		this.ground = ground;

	}
	/**
	 * Returns the ground for this object, if it has one.
	 * @return
	 */
	
	public CollisionVolume getGround(){
		return this.ground;
	}

	/* (non-Javadoc)
	 * @see gameObject.GravityAffected#setInAir(boolean)
	 */
	public void setInAir(boolean inAir) {
		this.inAir = inAir;

	}

	/**
	 * @return the spawn
	 */
	public PlayerSpawn getSpawn() {
		return spawn;
	}

	/**
	 * @param spawn the spawn to set
	 */
	public void setSpawn(PlayerSpawn spawn) {
		this.spawn = spawn;
	}
	/**
	 * Returns the velocity of the player
	 * @return
	 */
	public Velocity getVelocity(){
		return this.vel;
	}
	/**
	 * Sets the velocity of the player
	 * @param speed
	 */
	public void setVelocity(Velocity speed){
		this.vel = speed;
	}
	/**
	 * Moves the player to the spawn point
	 */
	public void respawn(){
		
		this.getLocation().setX(spawn.getSpawnPoint().getX());
		this.getLocation().setY(spawn.getSpawnPoint().getY());
		this.getLocation().setZ(spawn.getSpawnPoint().getZ());
		
		this.setVelocity(new Velocity(0,0,0));
		
	}
	
	

}
