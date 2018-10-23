import java.util.NoSuchElementException;

public class myBST <E extends Comparable<E>>{
	private NodeBST<E> root;
	private int size;
	
	public myBST () {
		this.root = null;
		this.size = 0;
	}
	
	public void insert(E data) {
		NodeBST<E> temp = new NodeBST<>(data);
		NodeBST<E> current = this.root, 
				   prev = this.root;
		
		if(this.root == null) {
			this.root = temp;
		}else {
			while(current != null) {
				prev = current;
				current = data.compareTo(current.getData()) < 0 ? current.getLeft():current.getRight();
			}
			// Current esta en Null y prev está en el nodo que será padre del nodo a insertar.
			if(data.compareTo(prev.getData()) < 0) {
				prev.setLeft(temp);
			}else {
				prev.setRight(temp);
			}
		}
		this.size++;
	}

	private void preOrder (NodeBST<E> current) {
		if(current != null) {
			System.out.print(current.getData() + ",");
			preOrder(current.getLeft());
			preOrder(current.getRight());
		}
	}
	
	public void preOrder () {
		preOrder(this.root);
		System.out.println();
	}
	
	private void inOrder (NodeBST<E> current) {
		if(current != null) {
			inOrder(current.getLeft());
			System.out.print(current.getData() + ",");
			inOrder(current.getRight());
		}
	}
	
	public void inOrder () {
		inOrder(this.root);
		System.out.println();
	}
	
	private void postOrder (NodeBST<E> current) {
		if(current != null) {
			postOrder(current.getLeft());
			postOrder(current.getRight());
			System.out.print(current.getData() + ",");
		}
	}
	
	public void postOrder () {
		postOrder(this.root);
		System.out.println();
	}
	
	public void level() {
		this.privateLevel(this.root);
	}
	
	private void privateLevel (NodeBST<E> root) { //Tarea para el sabado, metodo de recorrido por nivel
		MyQueue<NodeBST> queue, auxQueue;
		NodeBST<E> temp;
		if(root != null) {
			queue = new MyQueue<>();
			auxQueue = new MyQueue<>();
			queue.enqueue(root);
			while(!queue.isEmpty()) {
				temp = queue.dequeue();
				auxQueue.enqueue(temp);
				if(temp.getLeft() != null) {
					queue.enqueue(temp.getLeft());
				}if(temp.getRight() != null) {
					queue.enqueue(temp.getRight());
				}
			}
			int size = auxQueue.size();
			for(int i = 0; i < size; i++) {
				NodeBST c = auxQueue.dequeue();
				System.out.print(c.getData() + ",");
			}
		}
	}
	
	private NodeBST<E> predecesor (NodeBST<E> node){
		NodeBST<E> temp = node.getLeft();
		while(temp.getRight() != null) {
			temp = temp.getRight();
		}
		return temp;
	}
	
	public E delete(E data) throws NoSuchElementException{
		NodeBST<E> current = this.root, 
					parent = this.root;
		
		while(current != null && !data.equals(current.getData())) {
			parent = current;
			current = data.compareTo(current.getData()) < 0 ? current.getLeft():current.getRight();
		}
		if(current == null) {
			throw new NoSuchElementException("This value: " + data + " was not found in the BST.");
		}else {
			E res = current.getData();
			//Caso nodo a borrar es una hoja 
			if(current.getLeft() == null && current.getRight() == null) {
				if(current == this.root) {
					this.root = null;
				}
				if(parent.getLeft() == current) {
					parent.setLeft(null);
				}else {
					parent.setRight(null);
				}
				this.size--;
			}
			//Caso en que el padre solo tiene un hijo izquierdo
			else if (current.getLeft() != null && current.getRight() == null) {
				if(current == this.root) {
					this.root = current.getLeft();
				}else if(parent.getLeft() == current) {
					parent.setLeft(current.getLeft());
				}else {
					parent.setRight(current.getLeft());
				}
				this.size--;
			}
			//Caso en que el padre solo tiene un hijo derecho
			else if(current.getLeft() == null && current.getRight() != null) {
				if(current == this.root) {
					this.root = current.getRight();
				}else if(parent.getLeft() == current) {
					parent.setLeft(current.getRight());
				}else {
					parent.setRight(current.getRight());
				}
				this.size--;
			}else {
				NodeBST<E> pred = predecesor(current);
				current.setData(delete(pred.getData()));
				
			}
			return res;
		}
		
	}
	
	public static void main(String[] args) {
		myBST<Integer> three = new myBST<>();
		three.insert(21);
		three.insert(13);
		three.insert(33);
		three.insert(10);
		three.insert(18);
		three.insert(25);
		three.insert(40);
		three.insert(29);
		three.insert(27);
		three.insert(30);
		three.insert(23);
		three.insert(22);
		three.insert(24);
		three.insert(7);
		three.insert(6);
		three.insert(9);
		
		three.preOrder();
		System.out.println("");
		three.inOrder();
		System.out.println("");
		three.postOrder();
		System.out.println("");
		three.level();
	}
}

class NodeBST<E extends Comparable<E>>{
	private E data;
	private NodeBST<E> left, right;
	
	public NodeBST(E data, NodeBST<E> left, NodeBST<E> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	public NodeBST(E data) {
		this(data, null, null);
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public NodeBST<E> getLeft() {
		return left;
	}
	public void setLeft(NodeBST<E> left) {
		this.left = left;
	}
	public NodeBST<E> getRight() {
		return right;
	}
	public void setRight(NodeBST<E> right) {
		this.right = right;
	}
	
	public String toString() {
		String left = this.left != null ? this.left.data.toString():"null",
				right = this.right != null ? this.right.data.toString():"null";
			return this.data + ":" + left + ":" + right;
	}
	
}
