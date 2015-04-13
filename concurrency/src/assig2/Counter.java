package assig2;

import java.util.concurrent.locks.Lock;

/**
 * Implements a simple counter that can be locked using the generalized Peterson algorithm (filter lock).
 * @author Misch
 *
 */
public abstract class Counter {
	protected Lock lock;
	
	public Counter(Lock lock){
		this.lock = lock;
	}
	
	public abstract void increment();
	
	public abstract int getValue();
}
