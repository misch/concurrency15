package assig2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Implements the generalized Peterson algorithm to lock and unlock shared resources.
 * @author Misch
 *
 */
public class FilterLock {
	private int threads;
	
	/** holds current level for each thread */
	AtomicIntegerArray level;
	
	/** holds identifier of last thread that entered one level */
	AtomicIntegerArray victim;
	
	public FilterLock(int threads){
		this.threads = threads;
		this.level = new AtomicIntegerArray(threads);
		this.victim = new AtomicIntegerArray(threads);
	}
	
	public void lock(){
		//TODO
	}
	
	public void unlock(){
		//TODO
	}
}
