package gameObject;

/**
 * Interface for creating a player.
 * 
 * @author Drew
 *
 */
public interface Player {
	public void handleKeys(char key);
	public void updateControlledObjects();
}
