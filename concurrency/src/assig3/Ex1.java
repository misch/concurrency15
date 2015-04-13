package assig3;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import assig2.*;

public class Ex1 {
	static int[] counterAccess;
	
	public static void main (String[] args) throws InterruptedException{
		
		if (args.length != 3){
			System.out.println("Please enter 3 program arguments: #threads [int] volatile [true/false] solarisAffinity [true/false]");
			System.exit(0);
		}
		
		int nThreads = Integer.parseInt(args[0]);
		boolean volatileCounter = Boolean.parseBoolean(args[1]);
		boolean solarisAffinity = Boolean.parseBoolean(args[2]);
		Lock locks[] = {new FilterLock(nThreads), new CASLock(), new CCASLock()};
		
		for (Lock lock : locks){
			runThreads(nThreads, volatileCounter, solarisAffinity, lock);
			System.out.println("\n");
		}
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

	public static void setSolarisAffinity(){
		try {
			// retrieve process id
			String pid_name = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
			String[] pid_array = pid_name.split("@");
			int pid = Integer.parseInt(pid_array[0]);
			// random processor
			int processor = new java.util.Random().nextInt(32);
			// Set process affinity to one processor ( on Solaris )
			Process p = Runtime.getRuntime().exec("/usr/sbin/pbind -b" + processor +" "+ pid );
			p.waitFor();
		} catch ( Exception err ) {
			err.printStackTrace();
		}
	}
	
	private static void runThreads(int nThreads, boolean volatileCounter, boolean solarisAffinity, Lock lock) throws InterruptedException{
		ArrayList<Thread> incThreads = new ArrayList<Thread>();
		counterAccess = new int[nThreads];
		
		Counter counter;
		if (volatileCounter){
			counter = new VolatileCounter(lock);
		} else{
			counter = new NonVolatileCounter(lock);
		}
		
		if(solarisAffinity){
			setSolarisAffinity();
		}

		// create threads
		for (int i = 0; i < nThreads; i++){
			Thread incThread = new Thread(new Incrementor(counter, counterAccess));
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
		System.out.println("Lock: " + lock.getClass());
		System.out.println("Case: " + exerciseCase );
		System.out.println("Counter value: " + counter.getValue());
		System.out.println("#threads: " + nThreads);
		System.out.println("\n** MEASUREMENTS **");
		System.out.println("Accesses: " + accessesToString());
		System.out.println("Lowest number of accesses: " + getMinAccesses());
		System.out.println("Highest number of accesses: " + getMaxAccesses());
		System.out.println("Execution time: " + util.TimeMeasure.getExecutionTime(startTime, endTime) + " ms");
	}
}
