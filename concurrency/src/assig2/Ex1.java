package assig2;

import java.util.ArrayList;

public class Ex1 {
	static int nThreads = 20;
	static FilterLock lock = new FilterLock(nThreads);
	static Counter counter = new Counter(lock);
	static int[] counterAccess = new int[nThreads];
	
	public static void main (String[] args) throws InterruptedException{
		ArrayList<Thread> startedThreads = new ArrayList<Thread>();
		for (int i = 0; i < nThreads; i++){
			Thread incThread = new Thread(new Incrementor(counter, i, counterAccess));
			incThread.start();
			startedThreads.add(incThread);
		}
		
		for (Thread thread : startedThreads){
			thread.join();
		}
		
		System.out.println(counter.value);
		printAccesses();
	}
	
	private static void printAccesses(){
		String outStr = "[";
		for (int access : counterAccess){
			outStr += access + "|";
		}
		outStr += "]";
		System.out.println(outStr);
	}
}
