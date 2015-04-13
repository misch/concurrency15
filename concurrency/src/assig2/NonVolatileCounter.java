package assig2;

import java.util.concurrent.locks.Lock;

/**
 * Implements a {@link Counter} whose value is volatile
 * @author Misch
 * 
 */
public class NonVolatileCounter extends Counter {
	int value;

	public NonVolatileCounter(Lock lock){
		super(lock);
	}
	
	public void increment(){
		value++;
	}
	
	public int getValue(){
		return this.value;
	}
}