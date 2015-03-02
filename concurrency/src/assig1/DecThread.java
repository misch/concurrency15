package assig1;

public class DecThread extends MyThread {

	public DecThread(Counter counter) {
		super(counter, SyncOption.NO_SYNC);
	}

	public DecThread(Counter counter, SyncOption syncOption) {
		super(counter, syncOption);
	}

	@Override
	public void modifyCounter() {
		int counterValue = counter.getValue();
		counterValue--;
		counter.setValue(counterValue);
	}
}
