package assig1;

import java.util.ArrayList;

/**
 * Contains the main method to start some AlwaysHungrySavages and a Cook thread.
 * @author Misch
 *
 */
public class Ex2Savages2 {

	public static final int SAVAGES = 20;
	private static final int POT_CAPACITY = 4;
	
	public static void main(String args[]) throws InterruptedException {
		Pot pot = new Pot(POT_CAPACITY);
		ArrayList<Thread> startedSavages = new ArrayList<Thread>();
		
		Cook cookRunnable = new Cook(pot);
		Thread cook = new Thread(cookRunnable);
		cook.start();
		
		for (int i = 0; i < SAVAGES; i++){
			Thread savage = new Thread(new AlwaysHungrySavage(pot, i));
			startedSavages.add(savage);
			savage.start();
		}
	}
}