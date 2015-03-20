package assig2;

import java.util.ArrayList;

public class Ex1 {
	static int[] counterAccess;
	
	public static void main (String[] args) throws InterruptedException{
		if (args.length != 2){
			System.out.println("Please enter 2 program arguments: #threads [int] volatile [true/false]");
		}
		ArrayList<Thread> incThreads = new ArrayList<Thread>();
		int nThreads = Integer.parseInt(args[0]);
		FilterLock lock = new FilterLock(nThreads);
		
		counterAccess = new int[nThreads];
		boolean volatileCounter = Boolean.parseBoolean(args[1]);
		
		Counter counter;
		if (volatileCounter){
			counter = new VolatileCounter(lock);
		} else{
			counter = new NonVolatileCounter(lock);
		}
		
		// create threads
		for (int i = 0; i < nThreads; i++){
			Thread incThread = new Thread(new Incrementor(counter, i, counterAccess));
			incThreads.add(incThread);
		}
		
		// start threads and time measurement
		long startTime = System.nanoTime();
		for (Thread thread : incThreads){
			thread.start();
		}
		
		// wait till all threads are done, stop time measurement
		for (Thread thread : incThreads){
			thread.join();
		}
		long endTime = System.nanoTime();
		
		// print statistics
		System.out.println("** SETTINGS **");
		String exerciseCase = (volatileCounter ? "volatile " : "non-volatile ") + "counter"; 
		System.out.println("Case: " + exerciseCase );
		System.out.println("Counter value: " + counter.getValue());
		System.out.println("#threads: " + nThreads);
		System.out.println("\n** MEASUREMENTS **");
		System.out.println("Accesses: " + accessesToString());
		System.out.println("Lowest number of accesses: " + getMinAccesses());
		System.out.println("Highest number of accesses: " + getMaxAccesses());
		System.out.println("Execution time: " + util.TimeMeasure.getExecutionTime(startTime, endTime) + " ms");
	}
	
	private static String accessesToString(){
		String outStr = "[";
		for (int access : counterAccess){
			outStr += access + "|";
		}
		outStr += "]";
		return outStr;
	}
	
	private static int getMinAccesses(){
		int min = Integer.MAX_VALUE;
		for (int i : counterAccess){
			min = (i < min ? i : min);
		}
		
		return min;
	}
	
	private static int getMaxAccesses(){
		int max = 0;
		for (int i : counterAccess){
			max = (i > max ? i : max);
		}
		return max;
	}
}
