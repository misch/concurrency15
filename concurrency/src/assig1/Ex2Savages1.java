package assig1;

import java.util.ArrayList;

public class Ex2Savages1 {

	public static void main(String args[]) throws InterruptedException {
		int portions = 3;
		int savages = 20;
		
		Pot pot = new Pot(portions);
		ArrayList<Thread> startedSavages = new ArrayList<Thread>();
		
		Cook cookRunnable = new Cook(pot);
		Thread cook = new Thread(cookRunnable);
		cook.start();
		
		for (int i = 0; i < savages; i++){
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