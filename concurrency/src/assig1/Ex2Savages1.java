package assig1;

public class Ex2Savages1 {

	public static void main(String args[]) {
		int portions = 3;
		int savages = 20;
		
		Pot pot = new Pot(portions);
		
		for (int i = 0; i < savages; i++){
			Thread cook = new Thread(new Cook(pot));
			cook.start();
			Thread savage = new Thread(new Savage(pot, i));
			savage.start();
		}
	}
}