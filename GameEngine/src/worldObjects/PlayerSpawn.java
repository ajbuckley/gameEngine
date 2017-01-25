/**
 * 
 */
package worldObjects;

import gameObject.GameObject;
import gameObject.Location;

/**
 * Provides a spawn point for players
 * @author Drew
 *
 */
public class PlayerSpawn extends GameObject{
	private Location spawnPoint;
	
	/**
	 * 
	 */
	public PlayerSpawn(double x, double y, double z) {
		spawnPoint = new Location(x, y, z);
	}
	
	public PlayerSpawn(Location spawnPoint){
		this.spawnPoint = spawnPoint;
	}

	/**
	 * @return the spawnPoint
	 */
	public Location getSpawnPoint() {
		return spawnPoint;
	}

	/**
	 * @param spawnPoint the spawnPoint to set
	 */
	public void setSpawnPoint(Location spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	@Override
	public void update() {
		
		
	}

}
