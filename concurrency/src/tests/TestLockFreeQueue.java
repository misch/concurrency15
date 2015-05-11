package tests;

import static org.junit.Assert.*;
import assig5.*;

import org.junit.Test;

public class TestLockFreeQueue {

	private IQueue lockFreeQueue = new UnboundedLockFreeQueue();

	@Test(expected = Exception.class)
	public void testQueueIsEmptyWhenInitialized() throws Exception {
		lockFreeQueue.deq();
	}
	
	@Test(expected = Exception.class)
	public void testDequeueOnEmptyQueueThrowsException() throws Exception {
		lockFreeQueue.enq(1);
		assertEquals(1, lockFreeQueue.deq());
		lockFreeQueue.deq();
	}
	
	@Test
	public void testEnqueuedAndDequeuedOrderAgree() throws Exception{
		lockFreeQueue.enq(1);
		lockFreeQueue.enq(3);
		lockFreeQueue.enq(7);
		assertEquals(1, lockFreeQueue.deq());
		assertEquals(3, lockFreeQueue.deq());
		assertEquals(7, lockFreeQueue.deq());
	}
}
