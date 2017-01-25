/**
 * 
 */
package worldObjects;

import java.util.ArrayList;

import gameObject.Appearance;
import gameObject.CollisionVolume;
import gameObject.GameObject;
import gameObject.Location;
import gameObject.Velocity;

/**
 * @author Drew
 *
 */
public class Spaceship extends GameObject{

	private CollisionVolume hitBox;
	private Velocity vel;
	private PlayerSpawn spawn;
	private Rectangle wing1, wing2, body, nose;
	private Location loc;
	
	/**
	 * @return the loc
	 */
	public Location getLoc() {
		return loc;
	}



	/**
	 * @param loc the loc to set
	 */
	public void setLoc(Location loc) {
		this.loc = loc;
	}



	public Spaceship(String name, Location loc) {
		super(name);
		this.loc = loc;
		hitBox = new CollisionVolume(loc, 50, 50, 0, this.getName());
		int [] stroke ={0,0,0,255};
		int []fill = {0,200,20,255};
		Appearance looks = new Appearance();
		looks.setColor(stroke, fill);
		//this.setLooks(looks);
		
		wing1 = new Rectangle(name + "Wing1" , 12, 25 , loc.getX(), loc.getY() +25, 0 ) ;
		wing2 = new Rectangle(name + "Wing2" , 12, 25 , loc.getX() + 38, loc.getY() +25, 0 );
		body =  new Rectangle(name + "Body" , 26, 35 , loc.getX() + 12, loc.getY() +2, 0 );
		nose =  new Rectangle(name + "Nose" , 4, 4 , loc.getX() + 23, loc.getY(), 0 );
		
		wing1.setLooks(looks);
		wing2.setLooks(looks);
		body.setLooks(looks);
		nose.setLooks(looks);
		
		vel = new Velocity(0,0,0);
		
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
	
	public void update(){
		this.getLoc().translate(vel);
		
		wing1.getLocation().update( loc.getX(), loc.getY() +25, 0 ) ;
		wing2.getLocation().update( loc.getX() + 38, loc.getY() +25, 0 );
		body.getLocation().update(loc.getX() + 12, loc.getY() +2, 0 );
		nose.getLocation().update(loc.getX() + 23, loc.getY(), 0 );
		
		if(this.getHitBox().getCollided()){
			this.loc.setY(-200);
		}
		
	}
	
	public ArrayList<Rectangle> getShapes(){
		ArrayList<Rectangle> shapes = new ArrayList<Rectangle>();
		shapes.add(nose);
		shapes.add(wing1);
		shapes.add(wing2);
		shapes.add(body);
				
		return shapes;
		
	}




}
