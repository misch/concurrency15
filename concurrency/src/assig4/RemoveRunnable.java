package assig4;

public class RemoveRunnable implements Runnable {
	private int[] keysToRemove;
	private IList sharedList;
	
	public RemoveRunnable(IList sharedList, int[] keysToRemove){
		this.keysToRemove = keysToRemove;
		this.sharedList = sharedList;
	}
	@Override
	public void run() {
		for(int key : keysToRemove){
			sharedList.remove(key);
		}
	}

}
