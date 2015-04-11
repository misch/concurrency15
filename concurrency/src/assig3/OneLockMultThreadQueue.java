package assig3;

import java.util.concurrent.locks.Lock;

public class OneLockMultThreadQueue implements IQueue {
	Lock lock = new CCASLock();
	
	final int QSIZE;
	volatile int head, tail;
	
	int items [];
	
	public OneLockMultThreadQueue(int size){
		this.QSIZE = size;
		this.head = 0;
		this.tail = 0;
		this.items = new int[QSIZE];
	}

	@Override
	public void enq( int x) {
		lock.lock();
		while (tail - head == QSIZE ){
			// Give another (hopefully dequeuer-) thread the chance to get the lock and change the head
			lock.unlock();
			lock.lock();
		};
		
		items [ tail % QSIZE ] = x; 
		
		tail ++;
		lock.unlock();
	}

	@Override
	public int deq() {
		lock.lock();
		while ( tail == head ) {
			// Give another (hopefully enqueuer-) thread the chance to get the lock and change the tail
			lock.unlock();
			lock.lock();
		}
		int item = items [ head % QSIZE ]; 
		head ++;
		lock.unlock();
		return item;
	}
}
