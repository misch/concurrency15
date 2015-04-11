package assig3;

public class DeqThread implements Runnable{
	static final int N_DEQUEUES = 100000;
	IQueue queue;
	
	public DeqThread(IQueue queue){
		this.queue = queue;
	}
	
	public void run(){
		for(int i = 0; i < N_DEQUEUES; i++){
			queue.deq();
		}
	}
}
