package assig5;

/**
 * This class defines a Node that can be enqueued and dequeued to the unbounded queues implemented in {@link assig5}.
 * @author Misch
 */
public class Node {
	public int value;
	public Node next;

	public Node(int value) {
		this.value = value;
	}
}
