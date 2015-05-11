package assig5;


public class DeqRunnable implements Runnable {
	private int nDequeues;
	private IQueue queue;
	
	public DeqRunnable(IQueue queue, int nDequeues){
		this.nDequeues = nDequeues;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < nDequeues; i++){
			queue.deq();
		}
	}

}
