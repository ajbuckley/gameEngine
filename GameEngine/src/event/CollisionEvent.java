/**
 * 
 */
package event;

/**
 * @author Drew
 *
 */
public class CollisionEvent extends Event {
	
	private String message;
	
	public CollisionEvent(EventHandler listener, String message) {
		super(listener);
		this.message = message;
		this.preformAction();
	}

	/* (non-Javadoc)
	 * @see event.Event#getType()
	 */
	@Override
	public int getType() {
		return Event.TYPE_COLLISION;
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
