/**
 * 
 */
package collision;

import event.CollisionEvent;
import event.EventHandler;
import gameObject.CollisionVolume;
import gameObject.Location;

import java.util.ArrayList;

/**
 * @author Drew
 * 
 * A handler that handles collisions
 *
 */
public class CollisionHandler{

	
	private ArrayList<CollisionVolume> hitBoxes;
	/**
	 * 
	 */
	public CollisionHandler(ArrayList<CollisionVolume> hitBoxes) {
		this.hitBoxes = hitBoxes;
		
	}
	
	public void runCheck() {
		//while(true){
			for(int i = 0; i < hitBoxes.size() - 1; i++){
				for(int j = i+1; j < hitBoxes.size(); j++){
					checkCollision(hitBoxes.get(i), hitBoxes.get(j));
				}
				
			}
		}	
		
	//}
	
	private void checkCollision(CollisionVolume b1, CollisionVolume b2){
		double b1Top, b1Bottom, b1Right, b1Left, b2Top, b2Bottom, b2Right, b2Left;
		//System.out.println("b1: " + b1.getID()+ " | b2: " + b2.getID());
		
		
		
		//Define all sides of both boxes
		b1Top = b1.getLoc().getY();
		b1Bottom = b1.getLoc().getY() + b1.getHeight();
		b1Right = b1.getLoc().getX() + b1.getWidth();
		b1Left = b1.getLoc().getX();
		
		b2Top = b2.getLoc().getY();
		b2Bottom = b2.getLoc().getY() + b2.getHeight();
		b2Right = b2.getLoc().getX() + b2.getWidth();
		b2Left = b2.getLoc().getX();
		
		
		//System.out.println("b1 top: " + b1Top + "b2 bottom: " + b2Bottom);
		
		//If b1 Collides on top of b2
		if(b1Bottom > b2Top && b1Bottom < b2Bottom && b1Left < b2Right && b1Right > b2Left ){
			/*b1.setCollided(true);
			b2.setCollided(true);
			
			
			
			b1.setCollisionPoint(new Location(b1.getLoc().getX() + b1.getWidth()/2, b1.getLoc().getY()+b1.getHeight(), 0));
			b2.setCollisionPoint(new Location(b2.getLoc().getX() + b2.getWidth()/2, b2.getLoc().getY(), 0));
			b1.setOther(b2);
			b2.setOther(b1);
			*/
			Location l = new Location(b1.getLoc().getX() + b1.getWidth()/2, b1.getLoc().getY()+b1.getHeight(), 0);
			CollisionEvent ce = new CollisionEvent(EventHandler.getInstance(), "" + b1.getID() + " " + b2.getID() + " " + l.getX() + " "  + l.getY() + " 0");
			ce.preformAction();
			
		} 
		
		//If b1 Collides on bottom of b2
		else if(b1Top < b2Bottom && b1Top > b2Top && b1Left < b2Right && b1Right > b2Left ){
			/*b1.setCollided(true);
			b2.setCollided(true);
			
			
			b1.setCollisionPoint(new Location(b1.getLoc().getX() + b1.getWidth()/2, b1.getLoc().getY(), 0));
			b2.setCollisionPoint(new Location(b2.getLoc().getX() + b2.getWidth()/2, b2.getLoc().getY()+b2.getHeight(), 0));
			b1.setOther(b2);
			b2.setOther(b1);*/
			
			Location l = new Location(b1.getLoc().getX() + b1.getWidth()/2, b1.getLoc().getY()+b1.getHeight(), 0);
			CollisionEvent ce = new CollisionEvent(EventHandler.getInstance(), "" + b1.getID() + " " + b2.getID() + " " + l.getX() + " "  + l.getY() + " 0");
			ce.preformAction();
		}
		
		//If b1 Collides on the left of b2
		else if(b1Bottom > b2Top && b1Top < b2Bottom && b1Right > b2Left && b1Right < b2Right ){
			/*b1.setCollided(true);
			b2.setCollided(true);
			
			
			
			b1.setCollisionPoint(new Location(b1Right, b1Top+b1.getHeight()/2, 0));
			b2.setCollisionPoint(new Location(b2Left, b2Top+b2.getHeight()/2, 0));
			b1.setOther(b2);
			b2.setOther(b1);*/
			
			Location l = new Location(b1.getLoc().getX() + b1.getWidth()/2, b1.getLoc().getY()+b1.getHeight(), 0);
			CollisionEvent ce = new CollisionEvent(EventHandler.getInstance(), "" + b1.getID() + " " + b2.getID() + " " + l.getX() + " "  + l.getY() + " 0");
			ce.preformAction();
		}
		
		//If b1 Collides on the right of b2
		else if(b1Bottom > b2Top && b1Top < b2Bottom && b1Left < b2Right && b1Left > b2Left ){
			/*b1.setCollided(true);
			b2.setCollided(true);*/
			
			Location l = new Location(b1.getLoc().getX() + b1.getWidth()/2, b1.getLoc().getY()+b1.getHeight(), 0);
			
			/*b1.setCollisionPoint(new Location(b1Left, b1Top + b1.getHeight()/2, 0));
			b2.setCollisionPoint(new Location(b2Right, b2Top + b2.getHeight()/2, 0));*/
			CollisionEvent ce = new CollisionEvent(EventHandler.getInstance(), "" + b1.getID() + " " + b2.getID() + " " + l.getX() + " "  + l.getY() + " 0");
			/*b1.setOther(b2);
			b2.setOther(b1);*/
			ce.preformAction();
		}
		
		
	}
	
	
	

}
