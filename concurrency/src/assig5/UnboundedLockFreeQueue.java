package assig5;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnboundedLockFreeQueue implements IQueue {

	AtomicReference<NodeLockFree> 	head = new AtomicReference<NodeLockFree>(), 
									tail = new AtomicReference<NodeLockFree>();
	
	public UnboundedLockFreeQueue(){
		NodeLockFree sentinel = new NodeLockFree(0);
		head.set(sentinel);
		tail.set(sentinel);
	}
	
	@Override
	public void enq(int value) {
		NodeLockFree newNode = new NodeLockFree(value);
		while(true){
			NodeLockFree last = tail.get();
			NodeLockFree next = last.next.get();
			
			if (last == tail.get()){
				if(next == null){
					if (last.next.compareAndSet(next, newNode)){
						tail.compareAndSet(last, newNode);
						return;
					}
				} else{
					tail.compareAndSet(last, next);
				}
			}
		}
	}

	@Override
	public int deq() {
		while (true){
			NodeLockFree first = head.get();
			NodeLockFree last = tail.get();
			NodeLockFree next = first.next.get();
			
			if (first == head.get()){
				if(first == last){
					if (next == null){
						throw new RuntimeException("Queue empty.");
					}
					tail.compareAndSet(last, next);
				} else{
					int value = next.value;
					if(head.compareAndSet(first, next)){
						return value;
					}
				}
			}
		}
	}

}
