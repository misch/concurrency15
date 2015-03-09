package assig1;

public class AlwaysHungrySavage extends Savage {
	
	public AlwaysHungrySavage(Pot pot, int id) {
		super(pot, id);
	}

	@Override
	public void run() {
		while (true) {
			try { eat(); }
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
