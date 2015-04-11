package assig3;

public class LockFreeTwoThreadQueue implements IQueue{
	final int QSIZE;
	volatile int head, tail;
	
	int items [];
	
	public LockFreeTwoThreadQueue(int size){
		this.QSIZE = size;
		this.head = 0;
		this.tail = 0;
		this.items = new int[QSIZE];
	}

	@Override
	public void enq( int x) {
		while (tail - head == QSIZE );
		items [ tail % QSIZE ] = x; tail ++;
//		System.out.println(this.toString());
	}

	@Override
	public int deq () {
		while ( tail == head );
		int item = items [ head % QSIZE ]; head ++;
//		System.out.println(this.toString());
		return item ;
	}
	
	@Override
	public String toString(){
		String str = "|";
		for(int item : items){
			str += item + "|";
		}
		return str;
	}
}