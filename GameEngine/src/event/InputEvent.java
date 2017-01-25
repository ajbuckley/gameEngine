package event;

public class InputEvent extends Event {

	public InputEvent(EventHandler listener) {
		super(listener);
		
	}

	@Override
	public int getType() {
		
		return Event.TYPE_INPUT;
	}

	@Override
	public String getMessage() {
		
		return null;
	}

}
