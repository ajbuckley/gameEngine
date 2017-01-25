/**
 * 
 */
package event;

/**
 * @author Drew
 *
 */
public class DeathEvent extends Event {

	private String message;
	public DeathEvent(EventHandler listener, String message) {
		super(listener);
		this.message = message;
		this.preformAction();
	}

	/* (non-Javadoc)
	 * @see event.Event#getType()
	 */
	@Override
	public int getType() {
		
		return Event.TYPE_DEATH;
	}

	/* (non-Javadoc)
	 * @see event.Event#getMessage()
	 */
	@Override
	public String getMessage() {
		
		return message;
	}

}
