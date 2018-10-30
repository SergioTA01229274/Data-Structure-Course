
public class MyHeap <E extends Comparable<E>>{
	private E[] values;
	private int size;
	
	public MyHeap(int length) {
		this.values = (E[]) new Comparable[length];
		this.size = 0;
	}
	
	public MyHeap() {
		this(10);
	}
	
	public void insert(E data) {
		if(this.size >= this.values.length) {
			E[] temp = (E[]) new Comparable[this.values.length*2];
			System.arraycopy(this.values, 0, temp, 0, this.values.length);
			this.values = temp;
			System.gc();
			this.insert(data);
		}else {
			this.values[this.size] = data;
			int parentIndex = (this.size - 1) / 2;
			int k = this.size;
			while(this.values[parentIndex].compareTo(this.values[k]) < 0) {
				E temp = this.values[k];
				this.values[k] = this.values[parentIndex];
				this.values[parentIndex] = temp;
				k = parentIndex;
				parentIndex = (parentIndex - 1) / 2;
			}
			this.size ++;
		}
	}
	
	public E remove() {
		E res = this.values[0];
		this.values[0] = this.values[this.size - 1];
		this.values[this.size - 1] = null;
		
		this.size--;
		return res; 
	}
	
	public String toString() {
		String res = "";
		for (int i = 0; i < this.size; i++) {
			res += this.values[i] + ",";
		}
		return res;
	}
	public static void main(String[] args) {
		MyHeap<Integer> heap = new MyHeap<>();
		heap.insert(85);
		heap.insert(70);
		heap.insert(80);
		heap.insert(50);
		heap.insert(40);
		heap.insert(75);
		heap.insert(30);
		heap.insert(20);
		heap.insert(10);
		heap.insert(35);
		heap.insert(15);
		heap.insert(62);
		heap.insert(58);
		System.out.println(heap);
		
		
	}
}
