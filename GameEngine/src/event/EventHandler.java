/**
 * 
 */
package event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



/**
 * @author Drew
 *
 */
public class EventHandler {

	private Queue<Event> eQueue;
	private ArrayList<EventListener> spawnDeathRegistry;
	private ArrayList<EventListener> inputRegistry;
	private ArrayList<EventListener> collisionRegistry;
	private ArrayList<EventListener> replayRegistry;
	private ArrayList<EventListener> movementRegistry;
	
	private static EventHandler eventHand;
	
	public static EventHandler getInstance(){
		if (eventHand == null){
			eventHand = new EventHandler();
		}
		return eventHand;
	}
	private EventHandler(){
		eQueue = new LinkedList<Event>();
		spawnDeathRegistry = new ArrayList<EventListener>();
		inputRegistry = new ArrayList<EventListener>();
		collisionRegistry = new ArrayList<EventListener>();
		replayRegistry = new ArrayList<EventListener>();
		movementRegistry = new ArrayList<EventListener>();
		
	}
	
	public void actionPerformed(Event ae) {
		eQueue.add(ae);
	}
	
	public void registerForInput(EventListener eL){
		inputRegistry.add(eL);
		
	}
	public void registerForCollision(EventListener eL){
		collisionRegistry.add(eL);
		
	}
	public void registerForReplay(EventListener eL){
		replayRegistry.add(eL);
		
	}
	public void registerForSpawnDeath(EventListener eL){
		spawnDeathRegistry.add(eL);
		
	}
	public void registerForMovement(EventListener eL){
		movementRegistry.add(eL);
		
	}
	
	public void handleEvents(){
		for(int i = 0; i < eQueue.size(); i++){
			Event e = eQueue.poll();
			switch(e.getType()){
			case Event.TYPE_COLLISION:
				for (EventListener eL : collisionRegistry){
					eL.notify(e);
				}
				break;
			case Event.TYPE_DEATH:
				for(EventListener eL : spawnDeathRegistry){
					eL.notify(e);
				}
				break;
			case Event.TYPE_INPUT:
				for(EventListener eL : inputRegistry){
					eL.notify(e);
				}
				break;
			case Event.TYPE_REPLAY_START:
				for(EventListener eL : replayRegistry){
					eL.notify(e);
				}
				break;
			case Event.TYPE_REPLAY_STOP:
				for(EventListener eL : replayRegistry){
					eL.notify(e);
				}
				break;
			case Event.TYPE_SPAWN:
				for(EventListener eL : spawnDeathRegistry){
					eL.notify(e);
				}
				break;
			case Event.TYPE_TELEPORT:
				for(EventListener eL : movementRegistry){
					eL.notify(e);
				}
				break;
			default: break;
			
			
			}
			
						
		}
		
	}
	
	

}
