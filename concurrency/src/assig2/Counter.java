package assig2;

/**
 * Implements a simple counter that can be locked using the generalized Peterson algorithm (filter lock).
 * @author Misch
 * 
 */
public class Counter {
	int value;
	FilterLock lock;
	
	public Counter(FilterLock lock){
		this.lock = lock;
	}
	
	public void increment(){
		value++;
	}
}