package assig3;

public class EnqRunnable implements Runnable{
	static final int N_ENQUEUES = 100000;
	IQueue queue;
	
	public EnqRunnable(IQueue queue){
		this.queue = queue;
	}
	
	public void run(){
		for(int i = 0; i<N_ENQUEUES; i++){
			queue.enq(i%7);
		}
	}
}
