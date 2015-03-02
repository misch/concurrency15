package assig1;

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
	
	private void runNoSync(){
		for (int i = 0; i < 100000; i++){
			modifyCounter();
		}
	}
	
	private void runSync(){
		for (int i = 0; i < 100000; i++){
			synchronized(counter){
				modifyCounter();
			}
		}
	}
	
	private void runReenteredLock(){
		for (int i = 0; i < 100000; i++){
			counter.lock.lock();
			modifyCounter();
			counter.lock.unlock();
		}
	}
	
	public abstract void modifyCounter();
}
