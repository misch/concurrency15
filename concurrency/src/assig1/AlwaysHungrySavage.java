package assig1;

public class AlwaysHungrySavage extends Savage {
	
	
	public AlwaysHungrySavage(Pot pot, int id) {
		super(pot, id);
	}

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
