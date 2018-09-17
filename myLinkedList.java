//Sergio Iván Tostado Nieto A01229274
//10 de Septiembre de 2018
//Estructuras de datos

import java.util.NoSuchElementException;

public class myLinkedList <E>{
	private Node<E> head, tail;
	private int size;
	
	public myLinkedList (){ //Nuestro constructor por default crea una lista enlazada vacia.
		this.head = this.tail = null; //Por lo tanto, tanto head como tail son null. 
		this.size = 0; // Y el tamaño de la lista es cero. 
	}
	
	public myLinkedList(E[] datos) {
		this();
		for(int i = 0; i < datos.length;i++) {
			this.insertAt(i, datos[i]);
		}
	}
	public E head() throws NoSuchElementException {  // Metdodo para conocer el head de la lista enlazada
		try {
			return this.head.getData();
		} catch (NullPointerException e) {
			throw new NoSuchElementException("You can´t access the first element of an empty list");
		}
	}
	public E tail() throws NoSuchElementException{
		try {
			return this.tail.getData(); //Metodo para conocer la cola de la lista enlazada
		} catch (NullPointerException e) {
			throw new NoSuchElementException("You can´t access the last element of an emprty list");
		}
		
	}
	public int size() {
		return this.size; //Metodo para conocer el tamaño de la lista enlazada
	}
	public boolean isEmpty() { //Metodo que expresa si la lista enlazada tiene elementos o esta vacia
		if(this.size() == 0) {
			return true;
		}else {
			return false;
		}
	}
	public void insertAtFirst(E data) { //Metodo que permite ingresar un elemento al principio de la lista
		
		//Creamos un objeto Node, que tiene como referencia de siguiente al nodo 
		//que esta como cabeza actual de la lista.
		Node<E> elementAF = new Node<E>(data, this.head);
		
		//Despues asignamos el nuevo objeto Node (elementAF) como cabeza de nuestra lista
		this.head = elementAF;
		
		//Si nuestra lista estaba vacia, significa que nuestro objeto Node sera cabeza y cola a la vez
		if(this.size == 0) {
			this.tail = this.head;
		}
		this.size++; //Aumentamos el tamaño de la lista para no perder nocion de la longitud en operaciones futuras
	}
	public void insertatTail(E data) {
		if(this.size > 0) { //Primero nos aseguramos que la lista ya tenga elementos
			Node<E> elementAT = new Node<E>(data); // Creamos el nuevo nodo que va a ser el nuevo tail, el ultimo elemento en la lista
			this.tail.setRefNext(elementAT); //Para no perder las referencia, asignamos la vieja tail al nuevo objeto Node creado.
			this.tail = elementAT; //Asignamos el atributo tail al nuevo objeto Node, ya que este será el ultimo objeto en la lista
			this.size++; //Incrementamos el tamaño de la lista
		}else {
			this.insertAtFirst(data); //Si la lista estaba vacia, simplemente utilizamos el metodo que ya teniamos 
		}
	}
	public void insertAt(int pos, E data) throws IndexOutOfBoundsException{ 
		try {
			if(pos == 0){
				this.insertAtFirst(data);
			}else {
				Node<E> temp = new Node<E>(data);
				int cont = 0;
				Node<E> current = this.head;
				while(cont < pos - 1) {
					current = current.getRefNext();
					cont++;
				}
				temp.setRefNext(current.getRefNext());
				current.setRefNext(temp);
				this.size++;
			}
		}catch (NullPointerException e) {
			throw new IndexOutOfBoundsException("You can´t insert a data in a position that does not exist.");
		}
	}
	public E removeAt(int pos) throws IndexOutOfBoundsException{ 
		try {
			if(pos == 0){
				return this.removeFirst();
			}else if(pos == this.size - 1) {
				return this.removeLast();
			}
			
			int cont = 0;
			Node<E> current = this.head;
			while(cont < pos - 1) {
				current = current.getRefNext();
				cont++;
			}
			E dataDeleted = current.getRefNext().getData();
			current.setRefNext(current.getRefNext().getRefNext());
			this.size--;
			return dataDeleted;
		}catch (NullPointerException e) {
			throw new IndexOutOfBoundsException("You can´t remove a data in a position that does not exist.");
		}
	}
	public E removeFirst() throws NoSuchElementException{
		try {
			E temp = this.head.getData();
			this.head = this.head.getRefNext();
			if(this.size == 1) {
				this.tail = null;
			}
			this.size--;
			return temp;
		}catch (NullPointerException e) {
			throw new NoSuchElementException("Yo can´t remove the first element of an empty list");
		}
		
	}
	public E removeLast() throws NoSuchElementException{
		try {
			E temp = this.tail.getData();
			Node<E> current = this.head;
			while(current.getRefNext() != this.tail) {
				current = current.getRefNext();
			}
			this.tail = current;
			current.setRefNext(null);
			if(this.size == 1) {
				this.head = null;
			}
			this.size--;
			return temp;
		}catch (NullPointerException ex) {
			throw new NoSuchElementException("You can´t remove the las element of an empty list");
		}
	}
	public void setAt(int pos, E data) throws IndexOutOfBoundsException{
		try {
			Node<E> current = this.head;
			int cont = 0;
			while(cont < pos - 1) {
				current = current.getRefNext();
				cont++;
			}
			if(pos == 0) {
				this.head.setData(data);
			}else {
				current.getRefNext().setData(data);	
			}
		}catch (NullPointerException e) {
			throw new IndexOutOfBoundsException("You can't modify a data from a position that does not exist.");
		}
	}
	public E getAt(int pos) throws IndexOutOfBoundsException{
		try {
			Node<E> current = this.head;
			int cont = 0;
			while(cont < pos - 1) {
				current = current.getRefNext();
				cont++;
			}
			if(pos == 0) {
				return this.head.getData();
			}else {
				return current.getRefNext().getData();	
			}
		}catch (NullPointerException e) {
			throw new IndexOutOfBoundsException("You can't see the data from a position that does not exist.");
		}
	}
	public String toString() {
		try {
			String res = "";
			Node<E> current = this.head;
			if(this.size == 1) {
				return this.head.getData() + "";
			}
			while(current.getRefNext() != null) {
				res += current.getData() + ", ";
				current = current.getRefNext();
			}
			//res += this.tail();
			return res;
		}catch (NullPointerException e) {
			return "";
		}
		
	}
	public static void main(String[] args) {
		Integer[] nums = {1,2,3,4,5,6};
		myLinkedList n2 = new myLinkedList(nums);
		System.out.println(n2.tail());
		System.out.println(n2.getAt(4));
		System.out.println(n2);
	}
}

class Node <E>{
	private E data;
	private Node<E> refNext;  // El atributo objeto de tipo Node se refiere a la referencia del siguiente nodo en la lista enlazadaa
	
	public Node(E data, Node<E> next) {
//El objeto Node recibido se refiere a la referencia del nodo que le sigue a la instancia en cuestion 
		this.data = data;
		this.refNext = next;
	}
	public Node(E data) {
		this(data, null);
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public Node<E> getRefNext() {
		return refNext;
	}
	public void setRefNext(Node<E> refNext) {
		this.refNext = refNext;
	}
}
