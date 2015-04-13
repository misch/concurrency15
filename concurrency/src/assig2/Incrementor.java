package assig2;

public class Incrementor implements Runnable {

	final static int COUNTER_LIMIT = 300000;
	private Counter counter;
	private int[] counterAccess;
	
	public Incrementor(Counter counter, int[] counterAccess){
		this.counter = counter;
		this.counterAccess = counterAccess;
	}
	@Override
	public void run() {
		while(true){
			int threadID = (int)Thread.currentThread().getId() % counterAccess.length;
			counter.lock.lock();
			if(counter.getValue() < COUNTER_LIMIT){
				counter.increment();
				counterAccess[threadID]++;
			} else{
				counter.lock.unlock();
				break;
			}
			counter.lock.unlock();
		}
	}

}
