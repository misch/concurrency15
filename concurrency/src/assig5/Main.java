package assig5;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

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
			System.out.println("\nlock-free queue:");
			runNTimes(measureNTimes, nThreads, queue, numberOfOperationsPerThread);
		}
		
		private static void runNTimes(int n, int nThreads, IQueue queue, int nOperationsPerThread) throws InterruptedException{
			double avgRuntime = 0;
			for (int i=0; i<n; i++){
				System.out.print("\n#" + (i+1) + ": \t ");
				double runtime = runThreads(nThreads, queue, nOperationsPerThread);
				avgRuntime = (i > 0) ? avgRuntime+runtime/(float)(n-1) : 0; // leave out first run when calculating average time
			}
			System.out.println("\navg: \t" + avgRuntime + " ms");
		}
		
		private static double runThreads(int nThreads, IQueue queue, int nOperationsPerThread) throws InterruptedException{
			ArrayList<Thread> threads = new ArrayList<Thread>();
			final Timer timer = new Timer();
			
			// define a barrier - the timer will only be started after all the threads are ready.
			CyclicBarrier barrier = new CyclicBarrier(nThreads,new Runnable(){
				@Override
				public void run() {
					timer.startTime = System.nanoTime();
				}
			});

			
			for(int i = 0; i<nThreads/2; i++){
				threads.add(new Thread(new EnqRunnable(queue, nOperationsPerThread, barrier)));
			}	
			
			for(int i = 0; i<nThreads/2; i++){
			threads.add(new Thread(new DeqRunnable(queue, nOperationsPerThread, barrier)));
			}
			
			for(Thread thread : threads){
				thread.start();
			}
			
			for(Thread thread : threads){
				thread.join();
			}
			
			timer.endTime = System.nanoTime();
			
			double runTime = util.TimeMeasure.getExecutionTime(timer.startTime, timer.endTime);
			System.out.print(runTime + " ms \t (" + nThreads + " threads)");
			return runTime;
		}
}
