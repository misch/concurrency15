package assig5;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws InterruptedException {
			int nThreads;
			if((args.length == 1)){

				nThreads = Integer.parseInt(args[0]);
			}else{
				System.out.println("No number of threads was specified. Using default of: 2 threads.");
				nThreads = 2;
			}
			
			int nRandomNumbers = 100000;
			int measureNTimes = 5;
			
			int numberOfOperationsPerThread = nRandomNumbers/nThreads;
			
			IQueue queue = new UnboundedLockBasedQueue();
			System.out.println("lock-based queue:");
			runNTimes(measureNTimes, nThreads, queue, numberOfOperationsPerThread);
			

			queue = new UnboundedLockFreeQueue();
			System.out.println("lock-free queue:");
			runNTimes(measureNTimes, nThreads, queue, numberOfOperationsPerThread);
		}
		
		private static void runNTimes(int n, int nThreads, IQueue queue, int nOperationsPerThread) throws InterruptedException{
			double avgRuntime = 0;
			for (int i=0; i<n; i++){
				System.out.print("\n#" + (i+1) + ": \t ");
				double runtime = runThreads(nThreads, queue, nOperationsPerThread);
				avgRuntime += runtime/(float)(n);
			}
			System.out.println("\navg: \t" + avgRuntime + " ms");
		}
		
		private static double runThreads(int nThreads, IQueue queue, int nOperationsPerThread) throws InterruptedException{
			ArrayList<Thread> threads = new ArrayList<Thread>();
			for(int i = 0; i<nThreads/2; i++){
				threads.add(new Thread(new EnqRunnable(queue, nOperationsPerThread)));
			}
			
			for(int i = 0; i<nThreads/2; i++){
				threads.add(new Thread(new DeqRunnable(queue, nOperationsPerThread)));
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
