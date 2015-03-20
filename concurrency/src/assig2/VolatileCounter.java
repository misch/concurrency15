package assig2;

/**
 * Implements a {@link Counter} whose value is volatile.
 * @author Misch
 * 
 */
public class VolatileCounter extends Counter {
	volatile int value;
	
	public VolatileCounter(FilterLock lock){
		super(lock);
	}
	
	public void increment(){
		value++;
	}
	
	public int getValue(){
		return this.value;
	}
}