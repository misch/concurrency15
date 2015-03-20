package assig2;

import java.util.ArrayList;

public class Ex1 {
	static int nThreads = 12;
	static FilterLock lock = new FilterLock(nThreads);
	static Counter counter = new Counter(lock);
	static int[] counterAccess = new int[nThreads];
	
	public static void main (String[] args) throws InterruptedException{
		ArrayList<Thread> incThreads = new ArrayList<Thread>();
		
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
		System.out.println("Counter value: " + counter.value);
		System.out.println("#threads: " + nThreads);
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
