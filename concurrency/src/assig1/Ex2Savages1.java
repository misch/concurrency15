package assig1;

import java.util.ArrayList;

public class Ex2Savages1 {

	public static final int SAVAGES = 20;
	private static final int POT_CAPACITY = 3;
	
	public static void main(String args[]) throws InterruptedException {
		Pot pot = new Pot(POT_CAPACITY);
		ArrayList<Thread> startedSavages = new ArrayList<Thread>();
		
		Cook cookRunnable = new Cook(pot);
		Thread cook = new Thread(cookRunnable);
		cook.start();
		
		for (int i = 0; i < SAVAGES; i++){
			Thread savage = new Thread(new Savage(pot, i));
			startedSavages.add(savage);
			savage.start();
		}
		
		for (Thread savage : startedSavages){
			savage.join();
		}
		
		System.out.println("All savages ate. Thanks, cook. You can die now.");

		cook.interrupt();
		
	}
	
}