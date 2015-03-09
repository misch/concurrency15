package assig1;

/**
 * A Cook that will refill an empty {@link Pot} when it's necessary.
 * @author Misch
 *
 */
public class Cook implements Runnable {
	private Pot pot;
	
	public Cook(Pot pot){
		this.pot = pot;
	}
	
	public void run(){
		while(true){
			try {
				refill();
			} catch (InterruptedException e) {
				System.out.println("Cook has been interrupted.");
				break;
			}
		}
	}
	
	/**
	 * Waits for a {@link Pot} to be refilled by acquiring its refillPot-Semaphore.
	 * The pot is refilled by setting the number of available permits of the portionCount-Semaphore to the original capacity of the {@link Pot}.
	 * The signal to refill the pot will usually come from a {@link Savage} who founds that the {@link Pot} is empty.
	 * @throws InterruptedException
	 */
	private void refill() throws InterruptedException{
		pot.refillPot.acquire(); 	// wait until someone (a Savage) gives the signal to refill the pot
		System.out.println("Refilling...");		// refill the pot
		pot.portionCount.release(pot.capacity); 
	}
}
