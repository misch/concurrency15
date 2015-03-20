package assig2;

/**
 * Implements a simple counter that can be locked using the generalized Peterson algorithm (filter lock).
 * @author Misch
 *
 */
public abstract class Counter {
	protected FilterLock lock;
	
	public Counter(FilterLock lock){
		this.lock = lock;
	}
	
	public abstract void increment();
	
	public abstract int getValue();
}
