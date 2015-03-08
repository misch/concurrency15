package assig1;

import java.util.concurrent.Semaphore;

public class Pot {
	int capacity;
	Semaphore portionCount;
	Semaphore eatFromPot = new Semaphore(1);
	Semaphore refillPot = new Semaphore(0);
	
	public Pot(int portions){
		this.capacity = portions;
		this.portionCount = new Semaphore(capacity);
		
	}
	
	public boolean isEmpty(){
		return portionCount.availablePermits() == 0;
	}
}
