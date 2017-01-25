/**
 * 
 */
package event;

/**
 * @author Drew
 *
 */
public class TeleportEvent extends Event{

	private String message;
	
	public TeleportEvent(EventHandler listener, String message) {
		super(listener);
		this.message = message;
		this.preformAction();
	}

	@Override
	public int getType() {
		return Event.TYPE_TELEPORT;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
