package assig1;

/**
 * This class is an implementation of the abstract class {@link MyThread}.
 * @author Misch
 *
 */
public class DecThread extends MyThread {

	public DecThread(Counter counter) {
		super(counter, SyncOption.NO_SYNC);
	}

	public DecThread(Counter counter, SyncOption syncOption) {
		super(counter, syncOption);
	}

	/**
	 * Decrease the {@link Counter}.
	 */
	@Override
	public void modifyCounter() {
		int counterValue = counter.getValue();
		counterValue--;
		counter.setValue(counterValue);
	}
}
