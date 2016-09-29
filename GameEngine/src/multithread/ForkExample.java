/**
 * Changes:
 * 	-Added new thread
 *  -Added waiting queue index
 *  -Changed how waiting works; threads now decrement their waiting position each time they are signalled
 *  -Thread 3 now adds thread 1 to the end of the queue 
 * 
 */

package multithread;

//Demonstrating multithreading and thread synchronization in Java
public class ForkExample implements Runnable {
	static int queue = 0;// Maintains the length of the queue

	public int pos; // The posistion of the thread in the queue
	int i; // the ID of the thread, so we can control behavior
	boolean busy; // the flag, Thread 1 will wait until Thread 0 is no longer
					// busy before continuing
	public ForkExample other; // reference to the other thread we will synchronize on.
						// This is needed so we can control behavior.

	// create the runnable object
	public ForkExample(int i, ForkExample other) {
		this.i = i; // set the thread ID (0 or 1)
		busy = true; // set the busy flag so Threads wait
		this.other = other;
		pos = queue;
		queue ++;
	}

	// synchronized method to test if thread is busy or not
	public synchronized boolean isBusy() {
		return busy;
	} // What happens if this isn't synchronized?

	// run method needed by runnable interface
	public void run() {
		while (queue > 0) {
			
			if (pos < 0){ //If thread has completed, break and decrement queue size
				queue --;
				break;
			}
			else if (pos == 0) { // 1st thread, sleep for a while, then notify
							// threads waiting
				try {
					if(i == 2){// if 3rd thread, add first thread to the back of the queue
						this.other.other.other = this; //Make first thread depend on the thread
						this.other.other.pos = queue; //add first thread to the back of the queue
						queue++;					//increment the queue
						(new Thread(this.other.other)).start(); //start up thread one again
						
					}
					Thread.sleep(4000); // What happens if you put this sleep
										// inside the synchronized block?
					synchronized (this) {
						notify(); // notify() will only notify threads waiting
									// on *this* object;
					}
					Thread.sleep(4000); // What happens if you put this sleep
										// inside the synchronized block?
					synchronized (this) {
						this.pos --; //move upp the queue
						
						busy = false; // must synchronize while editing the flag
						notify(); // notify() will only notify threads waiting
									// on *this* object;
					}
				} catch (InterruptedException tie) {
					tie.printStackTrace();
				}
			} else {
				while (this.pos > 0) { // check if other thread is still
											// working
					System.out.println("Thread: " + i +" Waiting!");
					// must sychnronize to wait on other object
					try {
						synchronized (other) {
							other.wait();
							
						}
					} // note we have synchronized on the object we are going to
						// wait on
					catch (InterruptedException tie) {
						tie.printStackTrace();
					}
					if (this.other.pos >= -1){ // keep thread in right place in the queue
						this.pos = other.pos + 1;}
				}
				
				
			}
		}
		System.out.println("Thread: "+ i + " Finished!");
	}

	public static void main(String[] args) {

		ForkExample t1 = new ForkExample(0, null);
		ForkExample t2 = new ForkExample(1, t1);
		ForkExample t3 = new ForkExample(2, t2);
		(new Thread(t3)).start();
		(new Thread(t2)).start();
		(new Thread(t1)).start();
	}

}