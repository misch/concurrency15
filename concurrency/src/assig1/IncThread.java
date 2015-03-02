package assig1;

public class IncThread extends MyThread {
	
	public IncThread(Counter counter){
		this(counter,SyncOption.NO_SYNC);
	}
	
	public IncThread(Counter counter, SyncOption syncOption){
		super(counter, syncOption);
	}

	@Override
	public void modifyCounter() {
		int counterValue = counter.getValue();
		counterValue++;
		counter.setValue(counterValue);
	}

}
