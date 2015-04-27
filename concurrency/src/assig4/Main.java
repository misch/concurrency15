package assig4;

import java.util.ArrayList;
import java.util.Random;

import assig3.DeqRunnable;
import assig3.EnqRunnable;
import assig3.IQueue;
import assig3.LockFreeTwoThreadQueue;
import assig3.OneLockMultThreadQueue;
import assig3.TwoLockMultThreadQueue;

public class Main {
	private static int nIntegers = 100000;

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
			
			int[] keysToAdd = new int[nRandomNumbers/nThreads];
			int[] keysToRemove = new int[nRandomNumbers/nThreads];
			
			IList list = new FineGrainedLockList();
			System.out.println("Fine-grained lock:");
			runNTimes(measureNTimes, nThreads, list, keysToAdd, keysToRemove);
			

//			queue = new OneLockMultThreadQueue(queueSize);
//			System.out.println("\nOne lock:");
//			runNTimes(measureNTimes, nThreads, queue);
//			
//			System.out.println("\nLock-free (2 threads only):");
//			queue = new LockFreeTwoThreadQueue(queueSize);
//			runNTimes(measureNTimes, 2, queue);
		}
		
		private static void runNTimes(int n, int nThreads, IList list, int[] keysToAdd, int[] keysToRemove) throws InterruptedException{
			double avgRuntime = 0;
			for (int i=0; i<n; i++){
				System.out.print("\n#" + (i+1) + ": \t ");
				double runtime = runThreads(nThreads, list, keysToAdd, keysToRemove);
				avgRuntime += runtime/(float)(n);
			}
			System.out.println("\navg: \t" + avgRuntime + " ms");
		}
		
		private static double runThreads(int nThreads, IList sharedList, int[] keysToAdd, int[] keysToRemove) throws InterruptedException{
			ArrayList<Thread> threads = new ArrayList<Thread>();
			for(int i = 0; i<nThreads/2; i++){
				threads.add(new Thread(new AddRunnable(sharedList, keysToAdd)));
				threads.add(new Thread(new RemoveRunnable(sharedList, keysToRemove)));
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
