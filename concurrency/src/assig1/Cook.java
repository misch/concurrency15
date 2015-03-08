package assig1;

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
			}
		}
	}
	
	private void refill() throws InterruptedException{
		pot.refillPot.acquire(); 	// wait until someone (a Savage) gives the signal to refill the pot
		System.out.println("Refilling...");		// refill the pot
		pot.portionCount.release(pot.capacity); 
	}
}
