package assig4;

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
			
			int numberOfKeysPerThread = nRandomNumbers/nThreads;
			
			IList list = new FineGrainedLockList();
			System.out.println("Fine-grained lock:");
			runNTimes(measureNTimes, nThreads, list, numberOfKeysPerThread);
			

			list = new OptimisticFineGrainedLockList();
			System.out.println("Optimistic Fine-grained lock:");
			runNTimes(measureNTimes, nThreads, list, numberOfKeysPerThread);
		}
		
		private static void runNTimes(int n, int nThreads, IList list, int nKeysPerThread) throws InterruptedException{
			double avgRuntime = 0;
			for (int i=0; i<n; i++){
				System.out.print("\n#" + (i+1) + ": \t ");
				double runtime = runThreads(nThreads, list, nKeysPerThread);
				avgRuntime += runtime/(float)(n);
			}
			System.out.println("\navg: \t" + avgRuntime + " ms");
		}
		
		private static double runThreads(int nThreads, IList sharedList, int nKeysPerThread) throws InterruptedException{
			ArrayList<Thread> threads = new ArrayList<Thread>();
			for(int i = 0; i<nThreads/2; i++){
				
				// Generate random keys
				int[] keysToAdd = new int[nKeysPerThread];
				int[] keysToRemove = new int[nKeysPerThread];
				
				Random rndGenerator = new Random();
				for (int j = 1; j < keysToAdd.length; j++){
					keysToAdd[j] = rndGenerator.nextInt(101);
					keysToRemove[j] = rndGenerator.nextInt(101);
				}
				
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
