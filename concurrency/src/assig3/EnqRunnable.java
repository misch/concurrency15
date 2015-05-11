package assig3;

public class EnqRunnable implements Runnable{
	int nEnqueues;
	IQueue queue;
	
	public EnqRunnable(IQueue queue, int nEnqueues){
		this.queue = queue;
		this.nEnqueues = nEnqueues;
	}
	public EnqRunnable(IQueue queue){
		this(queue, 100000);
	}
	
	public void run(){
		for(int i = 0; i<nEnqueues; i++){
			queue.enq(i%7);
		}
	}
}
