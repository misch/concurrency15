package assig4;

public class OptimisticFineGrainedLockList implements IList {

	private Node head, tail;
	
	public OptimisticFineGrainedLockList(){
		this.head = new Node(Integer.MIN_VALUE);
		this.tail = new Node(Integer.MAX_VALUE);
		this.head.next = tail;
	}
	
	@Override
	public boolean add(int key) {
		while(true){
			Node nodeToAdd = new Node(key);
			
			// Find the right position in the list
			Node pred = this.head, succ = pred.next;
			while(succ.key <= key){
				pred = succ;
				succ = succ.next;
			}
			
			// Only now acquire locks and check if state from before is still valid
			try{
				pred.lock();
				succ.lock();
				// If valid, go on and add node, otherwise reenter the outer loop and try again from scratch
				if (validate(pred,succ)){
					// If the key is already present, don't add the new node
					if (pred.key == key){
						return false;
					}
					// Otherwise, add it
					nodeToAdd.next = succ;
					pred.next = nodeToAdd;
					return true;
				}
			}finally{
				pred.unlock();
				succ.unlock();
			}
		}
	}

	@Override
	public boolean contains(int key) {
		while (true) {
			Node pred = this.head, curr = pred.next;
			
			boolean found = false;
			while (curr.key <= key) {
				// if the key is found, break
				if (key == curr.key) {
					found = true;
					break;
				}
				pred = curr;
				curr = curr.next;
			}
			
			// If the key was not found, return false.
			if (!found){
				return false;
			}
			
			// This happens, if the key was found: Lock and check if the state is still valid, only then decide if true or false.
			try {
				pred.lock();
				curr.lock();
				if (validate(pred, curr)) {
					if (key == curr.key) {
						return true;
					} else {
						return false;
					}
				}
			} finally {
				pred.unlock();
				curr.unlock();
			}
		}
	}


	@Override
	public boolean remove(int key) {
		while(true){
			
			// Find the right position in the list
			Node pred = this.head, curr = pred.next;
			while(curr.key <= key){
				if (key == curr.key){
					break;
				}
				pred = curr;
				curr = curr.next;
			}
			
			// Only now acquire locks and check if state from before is still valid
			try{
				pred.lock();
				curr.lock();
				// If state is still valid, then remove node, otherwise reenter outer while-loop and try from scratch 
				if (validate(pred,curr)){
					if (curr.key == key){
						pred.next = curr.next;
						return true;
					} else{
						return false;
					}
				}
			} finally{
				pred.unlock();
				curr.unlock();
			}
		}
	}

	private boolean validate(Node pred, Node curr) {
		Node node = this.head;
		
		while(node.key <= pred.key){
			if (node == pred){
				return pred.next == curr;
			}
			node = node.next;
		}
		return false;
	}
	
}
