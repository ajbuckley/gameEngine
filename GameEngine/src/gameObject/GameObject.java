package gameObject;



public abstract class GameObject {

	private String name;
	
	public GameObject(String name){
		this.name = name;
	}
	
	/**
	 * Updates the object
	 *
	 */
	public abstract void update(); 
	/**
	 * sets the name of the object.
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * returns the name of the object
	 * @return
	 */
	public String getName(){
		return this.name;
	}

	
}
