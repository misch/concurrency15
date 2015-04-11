package assig3;

import java.util.concurrent.locks.Lock;

public class TwoLockMultThreadQueue implements IQueue {
	final int QSIZE;
	volatile int head, tail;
	
	int items [];
	
	Lock headLock = new CCASLock(), tailLock = new CCASLock();
	
	public TwoLockMultThreadQueue(int size){
		this.QSIZE = size;
		this.head = 0;
		this.tail = 0;
		this.items = new int[QSIZE];
	}
	
	@Override
	public void enq( int x) {
		tailLock.lock();
		
		while (tail - head == QSIZE ){}
		items [ tail % QSIZE ] = x; 
		tail ++;
		
		tailLock.unlock();
	}

	@Override
	public int deq() {
		headLock.lock();
		
		while ( tail == head ){}
		int item = items [ head % QSIZE ]; 
		head ++;
		
		headLock.unlock();
		return item;
	}

}
