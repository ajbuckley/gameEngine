package event;

public class SpawnEvent extends Event {

	private String message;
	public SpawnEvent(EventHandler listener,String message) {
		super(listener);
		this.message = message;
		this.preformAction();
	}

	@Override
	public int getType() {
		
		return Event.TYPE_SPAWN;
	}

	@Override
	public String getMessage() {
		
		return message;
	}
 
}
