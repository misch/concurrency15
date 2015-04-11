package assig3;

public class EnqThread implements Runnable{
	static final int N_ENQUEUES = 100000;
	IQueue queue;
	
	public EnqThread(IQueue queue){
		this.queue = queue;
	}
	
	public void run(){
		for(int i = 0; i<N_ENQUEUES; i++){
			queue.enq(i%7);
		}
	}
}
