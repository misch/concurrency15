package assig3;

import java.util.ArrayList;

public class Ex2 {

	public static void main(String[] args) throws InterruptedException{
		if(!(args.length == 1)){
			System.out.println("Please enter a parameters: nThreads:[int]");
		}
		
		int nThreads = Integer.parseInt(args[0]);

		int queueSize = 20;
		int measureNTimes = 3;
		
		IQueue queue = new TwoLockMultThreadQueue(queueSize);
		System.out.println("Two locks:");
		runNTimes(measureNTimes, nThreads, queue);
		

		queue = new OneLockMultThreadQueue(queueSize);
		System.out.println("\nOne lock:");
		runNTimes(measureNTimes, nThreads, queue);
		
		System.out.println("\nLock-free (2 threads only):");
		queue = new LockFreeTwoThreadQueue(queueSize);
		runNTimes(measureNTimes, 2, queue);
	}
	
	private static void runNTimes(int n, int nThreads, IQueue queue) throws InterruptedException{
		double avgRuntime = 0;
		for (int i=0; i<n; i++){
			System.out.print("\n#" + (i+1) + ": \t ");
			double runtime = runThreads(nThreads, queue);
			avgRuntime += runtime/(float)(n);
		}
		System.out.println("\navg: \t" + avgRuntime + " ms");
	}
	
	private static double runThreads(int nThreads, IQueue sharedQueue) throws InterruptedException{
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
		
		double runTime = util.TimeMeasure.getExecutionTime(startTime, endTime);
		System.out.print(runTime + " ms \t (" + nThreads + " threads)");
		return runTime;
	}
}
