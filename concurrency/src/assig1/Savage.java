package assig1;

public class Savage implements Runnable {
	protected static int[] eatingRounds = new int[Ex2Savages2.SAVAGES];
	protected int id;
	private Pot pot;

	public Savage(Pot pot, int i) {
		this.pot = pot;
		this.id = i;
	}

	@Override
	public void run() {
		try {
			eat();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void eat() throws InterruptedException {
		pot.eatFromPot.acquire(); 	// wait until the pot is available (noone else
							 		// is eating from the pot)
		eatingRounds[id]++;
		
		if (pot.isEmpty()) {
			pot.refillPot.release(); 	// if the pot is empty, then give signal to
										// refill (the Cook will hear it...)
		}

		pot.portionCount.acquire(); // wait until there's a portion in the pot

		System.out.println("Savage No. " + id + " eating..."); // eat
		printEatingRounds();

		pot.eatFromPot.release(); // release the pot again
	}
	
	public static void printEatingRounds(){
		String out = "Eating rounds: [ ";
		for (int i = 0; i < eatingRounds.length; i++){
			out += eatingRounds[i] + " ";
		}
		out += "]";
		System.out.println(out);
	}
	
	public static boolean canIEat(int threadID){
		int currentThreadRound = eatingRounds[threadID];
		int minVal = Integer.MAX_VALUE;
		for (int i = 0; i < eatingRounds.length ; i++){
			minVal = eatingRounds[i] < minVal ? eatingRounds[i] : minVal;
		}
		
		return minVal >= currentThreadRound;
	}
}
