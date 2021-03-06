package assig2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Implements the generalized Peterson algorithm to lock and unlock shared resources.
 * @author Misch
 *
 */
public class FilterLock implements Lock{
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
	
	@Override
	public void lock(){
		int threadID = (int)Thread.currentThread().getId() % threads;
		
		for (int lvl = 1; lvl < threads; lvl++){
			level.set(threadID, lvl);
			victim.set(lvl, threadID);
			
			boolean iAmTheVictim = true;
			boolean thereIsAHigherLevelThread = true;
			
			while(iAmTheVictim && thereIsAHigherLevelThread){
				iAmTheVictim = (victim.get(lvl) == threadID);
				
				thereIsAHigherLevelThread = false;
				for (int bitch = 0; bitch < level.length(); bitch++){
					if (bitch != threadID && level.get(bitch) >= lvl){
						thereIsAHigherLevelThread = true;
					}
				}
			}
		}
	}
	
	@Override
	public void unlock(){
		int threadID = (int)Thread.currentThread().getId() % threads;
		level.set(threadID, 0);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1)
			throws InterruptedException {
		return false;
	}
}
