package assig5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnboundedLockBasedQueue implements IQueue {

	Lock 	enqLock = new ReentrantLock(), 
			deqLock = new ReentrantLock();
	
	Node head, tail;
	
	
	public UnboundedLockBasedQueue(){
		Node sentinel = new Node(0);
		head = sentinel;
		tail = sentinel;
	}
	@Override
	public void enq(int value) {
		enqLock.lock();
		try{
			Node newNode = new Node(value);
			tail.next = newNode;
			tail = newNode;
			
		} finally{
			enqLock.unlock();
		}
	}

	@Override
	public int deq() {
		int result = 0;;
		deqLock.lock();
		try{
			if (head.next == null)
				throw new RuntimeException("Queue empty.");
			result = head.next.value;
			head = head.next;
		}finally{
			deqLock.unlock();
		}
		return result;
	}

}
