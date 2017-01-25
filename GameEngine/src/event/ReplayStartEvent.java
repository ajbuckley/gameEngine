/**
 * 
 */
package event;

/**
 * @author Drew
 *
 */
public class ReplayStartEvent extends Event {

	private String message;
	public ReplayStartEvent(EventHandler listener, String message) {
		super(listener);
		this.message = message;
		this.preformAction();
	}

	/* (non-Javadoc)
	 * @see event.Event#getType()
	 */
	@Override
	public int getType() {
		
		return Event.TYPE_REPLAY_START;
	}

	/* (non-Javadoc)
	 * @see event.Event#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
