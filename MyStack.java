import java.util.NoSuchElementException;

// flush
public class MyStack <E>{
	private myLinkedList<E> list;
	
	public MyStack(){
		this.list = new myLinkedList<>();
	}
	
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	
	public void flush() {
		this.list = new myLinkedList<>();
		System.gc();
	}
	
	public E pop() throws NoSuchElementException{
		try {
			return this.list.removeFirst();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("You can´t remove an element of an empty stack.");
		}
	}
	
	public int size() {
		return this.list.size();
	}
	
	public void push(E data) {
		this.list.insertAtFirst(data);
	}
	
	public E top() throws NoSuchElementException{
		try {
			return this.list.head();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("You can´t consult the top of an empty stack.");
		}
	}
	
	public String toString() {
		return this.list.toString();
	}
	
	public static void main(String[] args) {
		MyStack<String> prueba = new MyStack();
		prueba.push("Serigo");
		prueba.push("Daniel");
		prueba.push("Gus");
		
		while(!prueba.isEmpty()) {
			System.out.println(prueba.pop());
		}
	}
	
}
