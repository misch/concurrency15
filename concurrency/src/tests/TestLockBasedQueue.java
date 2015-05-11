package tests;

import static org.junit.Assert.*;
import assig5.*;

import org.junit.Test;

public class TestLockBasedQueue {

	private IQueue lockBasedQueue = new UnboundedLockBasedQueue();

	@Test(expected = Exception.class)
	public void testQueueIsEmptyWhenInitialized() throws Exception {
		lockBasedQueue.deq();
	}
	
	@Test(expected = Exception.class)
	public void testDequeueOnEmptyQueueThrowsException() throws Exception {
		lockBasedQueue.enq(1);
		assertEquals(1, lockBasedQueue.deq());
		lockBasedQueue.deq();
	}
	
	@Test
	public void testEnqueuedAndDequeuedOrderAgree() throws Exception{
		lockBasedQueue.enq(1);
		lockBasedQueue.enq(3);
		lockBasedQueue.enq(7);
		assertEquals(1, lockBasedQueue.deq());
		assertEquals(3, lockBasedQueue.deq());
		assertEquals(7, lockBasedQueue.deq());
	}
}
