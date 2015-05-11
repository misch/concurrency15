package assig5;


public class EnqRunnable implements Runnable {
	private int nEnqueues;
	private IQueue queue;
	
	public EnqRunnable(IQueue queue, int nEnqueues){
		this.nEnqueues = nEnqueues;
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for (int i=0; i<nEnqueues; i++){
			queue.enq(i);
		}
	}

}
