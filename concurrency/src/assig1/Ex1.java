package assig1;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import assig1.MyThread.SyncOption;

public class Ex1 {
	public static void main(String args[]) throws InterruptedException, FileNotFoundException{

		Counter sharedCounter;
		for (int nThreads = 1; nThreads <= 8; nThreads*=2){
			System.out.println("#threads: " + nThreads);
			
			sharedCounter = new Counter(0);
			System.out.println("Without synchronization \n***********************");
			runThreads(nThreads, nThreads, sharedCounter, SyncOption.NO_SYNC);
			System.out.println("\t Counter value: " + sharedCounter.getValue() + "\n");
			
			sharedCounter = new Counter(0);
			System.out.println("With synchronization \n*********************");
			runThreads(nThreads, nThreads, sharedCounter, SyncOption.SYNC);
			System.out.println(" \t Counter value: " + sharedCounter.getValue() + "\n");
			
			sharedCounter = new Counter(0);
			System.out.println("With reentered lock \n********************");
			runThreads(nThreads, nThreads, sharedCounter, SyncOption.REENTERED_LOCK);
			System.out.println("\t Counter value: " + sharedCounter.getValue() + "\n");
		}
	}
	
	private static void runThreads(int incThreads, int decThreads, Counter sharedCounter, SyncOption syncOption) throws InterruptedException{
		ArrayList<Thread> startedThreads = new ArrayList<Thread>();
		
		long startTime = System.nanoTime();
		for (int i = 0; i < incThreads; i++){			
			IncThread inc = new IncThread(sharedCounter, syncOption);
			Thread newIncThread = new Thread(inc);
			newIncThread.start();
			startedThreads.add(newIncThread);
		}
		
		for (int i = 0; i < decThreads; i++){			
			DecThread dec = new DecThread(sharedCounter, syncOption);
			Thread newDecThread = new Thread(dec);
			newDecThread.start();
			startedThreads.add(newDecThread);
		}
		
		for(Thread thread : startedThreads){
			thread.join();
		}
		
		long endTime = System.nanoTime();
		System.out.println("\t Execution time: " + (endTime-startTime)*0.000001 + "ms");
	}
}
