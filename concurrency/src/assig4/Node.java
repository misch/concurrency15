package assig4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {
	public int key;
	public Node next;
	private Lock lock;
	
	
	
	public Node(int key) {
		this.key = key;
		this.lock = new ReentrantLock();
	}

	public void lock(){
		this.lock.lock();
	}
	
	public void unlock(){
		this.lock.unlock();
	}
}
