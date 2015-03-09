package assig1;

/**
 * Implements a {@link Savage} that is always hungry.
 * @author Misch
 *
 */
public class AlwaysHungrySavage extends Savage {
	
	
	public AlwaysHungrySavage(Pot pot, int id) {
		super(pot, id);
	}

	/**
	 * This method will be called when a thread is started with this Runnable as a target.
	 * It contains an endless loop and whenever a AlwaysHungrySavage is allowed to eat, that is, all the other AlwaysHungrySavage's have had enough food already, it will eat. 
	 */
	@Override
	public void run() {
		while (true) {
			if (AlwaysHungrySavage.canIEat(this.id)){
				try { eat(); }
				catch (InterruptedException e) { e.printStackTrace(); }
			}
		}
	}
}
