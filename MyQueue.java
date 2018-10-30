import java.util.NoSuchElementException;

public class MyQueue <E>{
	private myLinkedList<E> list;
	public MyQueue(){
		this.list = new myLinkedList<>();
	}
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	public void enqueue(E data) {  
		//Ingresamos un elemento a la cola
		//Se ingresan los datos por la tail de la lista enlazada
		//de esta manera es mas facil acceder al head de la lista enlazada (primer elemento ingresado)
		this.list.insertatTail(data);
	}
	
	public E dequeue() throws NoSuchElementException{  
		//Eliminamos un elemento de la cola
		try {
			//Se elimina el primer elemento de la cola (que fue el primer ingresado)
			return this.list.removeFirst();
		} catch (NoSuchElementException e) { 
			//Traducimos la excepcion que pueda lanzar el metodo utilizado
			throw new NoSuchElementException("It's not possible to do a dequeue in an empty queue.");
		}
		
	}
	
	public E peek() throws NoSuchElementException{ 
		//Visualizamos el primer elemento de la cola (el primer dato ingresado, bottom)
		try {
			return this.list.head();
		} catch (NoSuchElementException e) { 
			//Traducimos la excepcion que pueda lanzar el metodo utilizado
			throw new NoSuchElementException("You can´t do a peek in an empty queue");
		}
		
	}
	
	public int size() {
		return this.list.size();
	}
	
	public void flush() {
		// Creamos una nueva lista enlazada de manera que este vacia
		this.list = new myLinkedList<>(); 
		System.gc(); //Llamamos al garbage collector para que se lleve la lista anterior.
	}
	
	public String toString() {
		return this.list.toString();
	}
	
	public static void main(String[] args) {
		MyQueue<String> nombres = new MyQueue();
		nombres.enqueue("Sergio");
		nombres.enqueue("Edgar");
		nombres.enqueue("Christopher");
		nombres.enqueue("Alex");
		nombres.enqueue("Daniel");
		
		System.out.println(nombres);
		while(!nombres.isEmpty()) {
			System.out.println(nombres.dequeue());
		}
	}
}
