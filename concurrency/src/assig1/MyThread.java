package assig1;

/**
 * An abstract class to run threads in different synchronization modes: no synchronization (NO_SYNC), java "synchronize" (SYNC), reentered lock (REENTERED_LOCK).
 * Every child class has to implement the abstract method "modifyCounter()".
 * @author Misch
 *
 */
public abstract class MyThread implements Runnable {
	public enum SyncOption{ NO_SYNC, SYNC, REENTERED_LOCK }
	
	protected Counter counter;
	protected SyncOption syncOption;
	
	public MyThread(Counter counter, SyncOption syncOption){
		this.counter = counter;
		this.syncOption = syncOption;
	}
	
	public MyThread(Counter counter){
		this(counter, SyncOption.NO_SYNC);
	}
	
	@Override
	public void run() {
		switch(syncOption){
		case NO_SYNC:
			runNoSync();
			break;
		case SYNC:
			runSync();
			break;
		case REENTERED_LOCK:
			runReenteredLock();
			break;
		}

	}
	
	/**
	 * Modify the {@link Counter} without locking it.
	 */
	private void runNoSync(){
		for (int i = 0; i < 100000; i++){
			modifyCounter();
		}
	}
	
	/**
	 * Lock the {@link Counter} using Java "synchronized" before it's modified.
	 */
	private void runSync(){
		for (int i = 0; i < 100000; i++){
			synchronized(counter){
				modifyCounter();
			}
		}
	}
	
	/**
	 * Lock the {@link Counter} using a reentered lock before it's modified.
	 */
	private void runReenteredLock(){
		for (int i = 0; i < 100000; i++){
			counter.lock.lock();
			modifyCounter();
			counter.lock.unlock();
		}
	}
	
	public abstract void modifyCounter();
}
