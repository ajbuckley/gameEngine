/**
 * 
 */
package time;

import java.util.LinkedList;
import java.util.Queue;

import event.Event;
import event.EventHandler;
import event.EventListener;
import event.TeleportEvent;

/**
 * @author Drew
 *
 */
public class Replay implements EventListener{
	
	private Queue<Event> events = new LinkedList<Event>();
	boolean started;
	
	public Replay(){
		started = false;
	}

	@Override
	public void notify(Event e) {
		if(e.getType() == Event.TYPE_REPLAY_START ){
			started = true;
			e = new TeleportEvent(EventHandler.getInstance(),e.getMessage());
			events.add(e);	
		}
		else if(e.getType() == Event.TYPE_REPLAY_STOP){
			e = new TeleportEvent(EventHandler.getInstance(),e.getMessage());
			events.add(e);	
			passReplayEvents();
		}
		else if(started){
			events.add(e);
		}
			
	}
	/**
	 * 
	 */
	public void passReplayEvents(){
		started = false;
		while(events.peek() != null){
			events.poll().preformAction();
		}
		
	}
	
	
}
