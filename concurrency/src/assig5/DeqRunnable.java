package assig5;

import java.util.concurrent.CyclicBarrier;


public class DeqRunnable implements Runnable {
	private int nDequeues;
	private IQueue queue;
	private CyclicBarrier barrier;
	
	public DeqRunnable(IQueue queue, int nDequeues, CyclicBarrier barrier){
		this.nDequeues = nDequeues;
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
		for(int i = 0; i < nDequeues; i++){
			queue.deq();
		}
	}

}
