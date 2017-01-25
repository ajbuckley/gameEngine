/**
 * 
 */
package time;

/**
 * @author Drew
 *
 */
public class TimeLine {
	private double tickSize;
	private TimeLine base;
	private double startTime;
	private double lastTick;
	public TimeLine(){
		this.setTickSize(166.6666667);
	}
	/**
	 * Construct a timeline with specified tick size
	 * @param tickSize
	 */
	public TimeLine(double tickSize){
		this.setTickSize(tickSize);
	}
	/**
	 * Construct a timeline based on another timeline
	 * @param base
	 */
	public TimeLine( TimeLine base){
		this.setTickSize(base.getTickSize());
		
	}
	/**
	 * @return the tick
	 */
	public double getTickSize() {
		return tickSize;
	}

	/**
	 * @param tick the tick to set
	 */
	public void setTickSize(double tickSize) {
		this.tickSize = tickSize;
	}
	/**
	 * @return the startTime
	 */
	public double getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the lastTick
	 */
	public double getLastTick() {
		return lastTick;
	}
	/**
	 * @param lastTick the lastTick to set
	 */
	public void setLastTick(double lastTick) {
		this.lastTick = lastTick;
	}
	/**
	 * @return the base
	 */
	public TimeLine getBase() {
		return base;
	}
	/**
	 * @param base the base to set
	 */
	public void setBase(TimeLine base) {
		this.base = base;
	}

	public boolean tick(){
		
		double time = System.currentTimeMillis(); 
		if(time > lastTick + tickSize){
			lastTick = time;
			return true;
		}
		
		
		return false;
		
	}
	
	
}
