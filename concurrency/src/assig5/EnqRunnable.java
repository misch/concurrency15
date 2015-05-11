package assig5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class EnqRunnable implements Runnable {
	private int nEnqueues;
	private IQueue queue;
	private CyclicBarrier barrier;
	
	public EnqRunnable(IQueue queue, int nEnqueues, CyclicBarrier barrier){
		this.nEnqueues = nEnqueues;
		this.queue = queue;
		this.barrier = barrier;
	}
	
	@Override
	public void run() {
		try {
			barrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i=0; i<nEnqueues; i++){
			queue.enq(i);
		}
	}

}
