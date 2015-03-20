package assig2;

/**
 * Implements a {@link Counter} whose value is volatile
 * @author Misch
 * 
 */
public class NonVolatileCounter extends Counter {
	int value;

	public NonVolatileCounter(FilterLock lock){
		super(lock);
	}
	
	public void increment(){
		value++;
	}
	
	public int getValue(){
		return this.value;
	}
}