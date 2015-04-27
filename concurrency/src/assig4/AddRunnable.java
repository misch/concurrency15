package assig4;

public class AddRunnable implements Runnable {
	private int[] keysToAdd;
	private IList sharedList;
	
	public AddRunnable(IList sharedList, int[] keysToAdd){
		this.keysToAdd = keysToAdd;
		this.sharedList = sharedList;
	}
	@Override
	public void run() {
		for (int key : keysToAdd){
			sharedList.add(key);
		}
	}

}
