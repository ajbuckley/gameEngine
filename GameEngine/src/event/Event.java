/**
 * 
 */
package event;

/**
 * @author Drew
 *
 */
public abstract class Event {
	
	public static final int TYPE_COLLISION = 1;
	public static final int TYPE_DEATH = 2;
	public static final int TYPE_SPAWN = 3;
	public static final int TYPE_INPUT = 4;
	public static final int TYPE_REPLAY_START = 5;
	public static final int TYPE_REPLAY_STOP = 6;
	public static final int TYPE_TELEPORT = 7;
	
	private int time;
	private EventHandler listener;
	public Event(EventHandler listener){
		this.listener = listener;
		
	}
	public abstract int getType();
	public abstract String getMessage();
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	public void preformAction(){
		listener.actionPerformed(this);
	}

}
