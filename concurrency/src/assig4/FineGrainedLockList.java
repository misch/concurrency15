package assig4;

public class FineGrainedLockList implements IList {

	private Node head, tail;
	
	public FineGrainedLockList(){
		this.head = new Node(Integer.MIN_VALUE);
		this.tail = new Node(Integer.MAX_VALUE);
		this.head.next = tail;
	}
	
	@Override
	public boolean add(int key) {
		Node 	pred = null,
				succ = null;
		Node nodeToAdd = new Node(key);
		
		// try to acquire the locks on predecessor and successor
		try{
			pred = this.head;
			pred.lock();
			succ = pred.next;
			succ.lock();
			
			// traverse the list
			while(succ.key <= key){
				// If the key is already in the list, don't add the object (no duplicates)!
				if (key == succ.key){
					return false;
				}
				pred.unlock();
				pred = succ;
				succ = succ.next;
				succ.lock();
			}
			// add the new node
			pred.next = nodeToAdd;
			nodeToAdd.next = succ;
			
		} finally{
			succ.unlock();
			pred.unlock();
		}
		return false;
	}

	@Override
	public boolean contains(int key) {
		Node 	pred = null,
				curr = null;
		
		// try to acquire the locks on predecessor and searched node
		try{
			pred = this.head;
			pred.lock();
			curr = pred.next;
			curr.lock();
			
			// traverse the list
			while(curr.key <= key){
				// if the key is found, return true
				if (key == curr.key){
					return true;
				}
				pred.unlock();
				pred = curr;
				curr = curr.next;
				curr.lock();
			}
		} finally{
			curr.unlock();
			pred.unlock();
		}
		return false;
	}

	@Override
	public boolean remove(int key) {
		Node 	pred = null,
				curr = null;
		
		// try to acquire the locks on predecessor and current node
		try{
			pred = this.head;
			pred.lock();
			curr = pred.next;
			curr.lock();
			
			// traverse the list
			while(curr.key <= key){
				if (key == curr.key){
					pred.next = curr.next;
					return true;
				}
				pred.unlock();
				pred = curr;
				curr = curr.next;
				curr.lock();
			}
		} finally{
			curr.unlock();
			pred.unlock();
		}
		return false;
	}
	
}
