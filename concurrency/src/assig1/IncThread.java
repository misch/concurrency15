package assig1;

/**
 * This class is an implementation of the abstract class {@link MyThread}.
 * @author Misch
 *
 */
public class IncThread extends MyThread {
	
	public IncThread(Counter counter){
		this(counter,SyncOption.NO_SYNC);
	}
	
	public IncThread(Counter counter, SyncOption syncOption){
		super(counter, syncOption);
	}

	/**
	 * Increase the {@link Counter}.
	 */
	@Override
	public void modifyCounter() {
		int counterValue = counter.getValue();
		counterValue++;
		counter.setValue(counterValue);
	}

}
