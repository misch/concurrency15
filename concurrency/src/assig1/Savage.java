package assig1;

public class Savage implements Runnable {
	private int id;
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

	private void eat() throws InterruptedException {
		pot.eatFromPot.acquire(); 	// wait until the pot is available (noone else
							 		// is eating from the pot)

		if (pot.isEmpty()) {
			pot.refillPot.release(); 	// if the pot is empty, then give signal to
										// refill (the Cook will hear it...)
		}

		pot.portionCount.acquire(); // wait until there's a portion in the pot

		System.out.println("Savage No. " + id + " eating..."); // eat

		pot.eatFromPot.release(); // release the pot again
	}
}
