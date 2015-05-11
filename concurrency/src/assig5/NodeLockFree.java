package assig5;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This class defines a Node that can be enqueued and dequeued to the unbounded queues implemented in {@link assig5}.
 * @author Misch
 */
public class NodeLockFree {
	public int value;
	public AtomicReference<NodeLockFree> next;

	public NodeLockFree(int value) {
		this.value = value;
		next = new AtomicReference<NodeLockFree>(null);
	}
}
