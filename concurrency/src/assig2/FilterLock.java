package assig2;

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
	
	public void lock(int threadID){
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
	
	public void unlock(int threadID){
		level.set(threadID, 0);
	}
}
