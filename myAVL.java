
public class myAVL <E extends Comparable<E>>{
	private NodeAVL<E> root;
	private int size;
	
	public myAVL() {
		this.root = null;
		this.size = 0;
	}
	
	public int getHeight (NodeAVL<E> root) {
		if(root == null) {
			return 0;
		}else {
			return 1 + Math.max(getHeight(root.getRefLeft()), getHeight(root.getRefRight()));
		}
	}
	
	public void balanceFactor(NodeAVL<E> root) {
		root.setBalanceFactor(getHeight(root.getRefRight()) - getHeight(root.getRefLeft()));
	}
	
	public void simpleRightRotation(NodeAVL<E> problematicNode, NodeAVL<E> fatherRoot) {
		if(problematicNode == this.root) {
			NodeAVL<E> temp = this.root;
			this.root = problematicNode.getRefLeft();
			try {
				temp.setRefLeft(this.root.getRefRight());
			} catch (NullPointerException e) {
				temp.setRefLeft(null);
			}
			this.root.setRefRight(temp);
			this.root.setBalanceFactor(0);
			this.root.getRefRight().setBalanceFactor(0);
		}else {
			NodeAVL<E> temp = problematicNode;
			fatherRoot.setRefLeft(problematicNode.getRefLeft());
			try {
				temp.setRefLeft(fatherRoot.getRefLeft().getRefRight());
			} catch (NullPointerException e) {
				temp.setRefLeft(null);
			}
			fatherRoot.getRefLeft().setRefRight(temp);
			fatherRoot.getRefLeft().setBalanceFactor(0);
			fatherRoot.getRefLeft().getRefRight().setBalanceFactor(0);
		}
	}
	
	public void simpleLeftRotation(NodeAVL<E> problematicNode, NodeAVL<E> fatherRoot) {
		if(problematicNode == this.root) {
			NodeAVL<E> temp = this.root;
			this.root = problematicNode.getRefRight();
			try {
				temp.setRefRight(this.root.getRefLeft());
			} catch (NullPointerException e) {
				temp.setRefRight(null);
			}
			this.root.setRefLeft(temp);
			this.root.setBalanceFactor(0);
			this.root.getRefLeft().setBalanceFactor(0);
		}else {
			NodeAVL<E> temp = problematicNode;
			fatherRoot.setRefRight(problematicNode.getRefRight());
			try {
				temp.setRefRight(fatherRoot.getRefRight().getRefLeft());
			} catch (NullPointerException e) {
				temp.setRefRight(null);
			}
			fatherRoot.getRefRight().setRefLeft(temp);
			fatherRoot.getRefRight().setBalanceFactor(0);
			fatherRoot.getRefRight().getRefLeft().setBalanceFactor(0);
		}
	}

	private void insert(E data, NodeAVL<E> prev, NodeAVL<E> current) {
		if(this.root != null) {
			if(current == null) {
				NodeAVL<E> temp = new NodeAVL<E>(data);
				if(data.compareTo(prev.getData()) > 0) {
					prev.setRefRight(temp);
				}else {
					prev.setRefLeft(temp);
				}
				this.size++;
			}else {
				NodeAVL<E> father = prev;
				prev = current;
				current = data.compareTo(current.getData()) > 0 ? current.getRefRight():current.getRefLeft();
				insert(data, prev, current);
				balanceFactor(prev);
				if(prev.getBalanceFactor() < -1) {
					simpleRightRotation(prev, father);
				}else if(prev.getBalanceFactor() > 1) {
					simpleLeftRotation(prev, father);
				}
			}
		}else {
			this.root = new NodeAVL<E>(data);
		}
		
	}
	
	public void insert(E data) {
		this.insert(data, this.root, this.root);
	}
	
	private void inOrder (NodeAVL<E> current) {
		if(current != null) {
			inOrder(current.getRefLeft());
			System.out.println(current + ":" + current.getBalanceFactor());
			inOrder(current.getRefRight());
		}
	}
	
	public void inOrder () {
		inOrder(this.root);
		System.out.println();
	}
	
	public static void main(String[] args) {
		myAVL<Integer> three = new myAVL<>();
		three.insert(9);
		three.insert(13);
		three.insert(21);
		three.insert(24);
		three.insert(25);
		three.insert(26);
		three.insert(8);
		three.insert(7);
		three.insert(23);
		three.inOrder();
	}
}



class NodeAVL<E extends Comparable<E>>{
	private E data;
	private NodeAVL<E> refLeft, refRight;
	private int balanceFactor;
	
	public NodeAVL(E data, NodeAVL<E> left, NodeAVL<E> right) {
		this.data = data;
		this.refLeft = left;
		this.refRight = right;
		this.balanceFactor = 0;
	}
	public String toString() {
		String left = this.refLeft != null ? this.refLeft.data.toString():"null",
				right = this.refRight != null ? this.refRight.data.toString():"null";
		return this.data + ":" + left + ":" + right;
	}
	public NodeAVL(E data) {
		this(data, null, null);
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public NodeAVL<E> getRefLeft() {
		return refLeft;
	}
	public void setRefLeft(NodeAVL<E> refLeft) {
		this.refLeft = refLeft;
	}
	public NodeAVL<E> getRefRight() {
		return refRight;
	}
	public void setRefRight(NodeAVL<E> refRight) {
		this.refRight = refRight;
	}
	public int getBalanceFactor() {
		return balanceFactor;
	}
	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}
}