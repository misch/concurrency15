package assig2;

public class Incrementor implements Runnable {

	final static int COUNTER_LIMIT = 300000;
	private Counter counter;
	private int[] counterAccess;
	private int threadID;
	
	public Incrementor(Counter counter, int threadID, int[] counterAccess){
		this.counter = counter;
		this.counterAccess = counterAccess;
		this.threadID = threadID;
	}
	@Override
	public void run() {
		while(true){
			counter.lock.lock();
			if(counter.value < COUNTER_LIMIT){
				counter.increment();
				counterAccess[threadID]++;
			} else{
				break;
			}
			counter.lock.unlock();
		}
	}

}
