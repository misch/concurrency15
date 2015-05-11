package assig3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class CCASLock implements Lock {

	/**
	 * Defines if the lock is acquired (state = 1) or not (state = 0).
	 */
	AtomicInteger state = new AtomicInteger(0);
	
	@Override
	public void lock() {
		
		while(true){
			while(state.get() == 1){}
		
			if(state.compareAndSet(0, 1)){
				return;
			}
		}

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {}

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

	@Override
	public void unlock() {
		state.set(0);

	}

}
