package assig1;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
	int counter;
	ReentrantLock lock = new ReentrantLock();
	
	public Counter(int initValue){
		this.counter = initValue;
	}
	
	public int getValue(){
		return this.counter;
	}
	
	public void setValue(int newCounter){
		this.counter = newCounter;
	}
}