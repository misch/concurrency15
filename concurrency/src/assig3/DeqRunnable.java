package assig3;

public class DeqRunnable implements Runnable{
	int nDequeues;
	IQueue queue;
	
	public DeqRunnable(IQueue queue, int nDequeues){
		this.queue = queue;
		this.nDequeues = nDequeues;
	}
	
	public DeqRunnable(IQueue queue){
		this(queue, 100000);
	}
	
	public void run(){
		for(int i = 0; i < nDequeues; i++){
			queue.deq();
		}
	}
}
