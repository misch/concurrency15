package assig3;

import java.util.ArrayList;

public class Ex2 {

	public static void main(String[] args) throws InterruptedException{
		if(!(args.length == 1)){
			System.out.println("Please enter a parameters: nThreads:[int]");
		}
		
		int nThreads = Integer.parseInt(args[0]);

		int queueSize = 20;
		
		IQueue sharedQueue;
//		sharedQueue = new LockFreeTwoThreadQueue(queueSize);
//		sharedQueue = new TwoLockMultThreadQueue(queueSize);
		sharedQueue = new OneLockMultThreadQueue(queueSize);
		
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i<nThreads/2; i++){
			threads.add(new Thread(new EnqRunnable(sharedQueue)));
			threads.add(new Thread(new DeqRunnable(sharedQueue)));
		}
		
		long startTime = System.nanoTime();
		for(Thread thread : threads){
			thread.start();
		}
		
		for(Thread thread : threads){
			thread.join();
		}
		long endTime = System.nanoTime();
		
		System.out.println(util.TimeMeasure.getExecutionTime(startTime, endTime));
	}
}
