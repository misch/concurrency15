package assig1;

import java.util.concurrent.Semaphore;
/**
 * Represents a pot with a given capacity of portions in it. The current available portions are managed by a Semaphore.
 * There's more Semaphores to handle who is allowed to use the pot at a time and to make clear when it should be refilled.
 * @author Misch
 *
 */
public class Pot {
	int capacity;
	Semaphore portionCount;
	Semaphore eatFromPot = new Semaphore(1);
	Semaphore refillPot = new Semaphore(0);
	
	/**
	 * Class constructor
	 * @param portions int number of portions the pot can contain.
	 */
	public Pot(int portions){
		this.capacity = portions;
		this.portionCount = new Semaphore(capacity);
		
	}
	
	/**
	 * 
	 * @return boolean true if there's still some portions available, false otherwise.
	 */
	public boolean isEmpty(){
		return portionCount.availablePermits() == 0;
	}
}
