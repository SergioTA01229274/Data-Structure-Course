//Sergio Ivan Tostado Nieto A01229274
//7 de Agosto 2018
//Estructura de datos
//Profesor: Edgar Salinas 
public class ordenamientos {
	public static <E extends Comparable<E>> void bubbleSort(E[] elements) {  //Ordenamiento tipo BubbleSort, implementacion de forma iterativa
		boolean cambio;
		for(int j = 0; j < elements.length - 1; j++) {
			cambio = false;
			for(int i = 0; i < elements.length - 1 - j; i++) {
				if(elements[i].compareTo(elements[i + 1]) > 0) {
					swap(elements, i, i+1);
					cambio = true;
				}
			}
			if(!cambio) {
				break;
			}
		}
	}
	public static <E extends Comparable<E>> void swap(E[] elements, int i, int j) { // cambio de elementos para BubbleSort
		E temp = elements[i];
		elements[i] = elements[j];
		elements[j] = temp;
	}
	public static <E> String imprimeArreglo(E[] elements) {
		String res = "";
		for (E e: elements) {
			res += e + ", ";
		}
		return res;
	}
	
	public static <E extends Comparable<E>>void mergeSort(E[] elements) {  // Ordenamiento tipo Merge Sort, Funcion de preparacion
		mergeSort(elements, 0, elements.length - 1);
	}
	private static <E extends Comparable<E>> void mergeSort(E[] elements, int first, int last) { //Ordenamiento tipo Merge Sort, Funcion Recursiva
		if(first < last) {
			int med = (first + last)/2;
			mergeSort(elements, first, med);
			mergeSort(elements,med + 1, last);
			merge(elements, first, last);
		}
	}
	private static <E extends Comparable<E>> void merge(E[] elements, int first, int last) {
		E[] tempArray = (E[]) new Comparable[elements.length];
		
		for(int i = first; i <= last;i++) {
			tempArray[i] = elements[i];
		}

		int punt1 = first;
		int punt2 = ((first + last)/2) + 1;
		int punt3 = first; 
	
		while(punt1 <= ((first + last)/2) && punt2 <= last) {
			if(tempArray[punt1].compareTo(tempArray[punt2]) <= 0){
				elements[punt3] = tempArray[punt1];
				punt1++;
			}else {
				elements[punt3] = tempArray[punt2];
				punt2++;
			}
			punt3++;
		}
		while(punt1 <= ((first + last)/2)) {
			elements[punt3] = tempArray[punt1];
			punt1++;
			punt3++;
		}
	}
	
	public static <E extends Comparable<E>> void quickSort(E[] elements) {
		int left = 0;
		int right = elements.length - 1;
		quickSort(elements, left, right);
	}
	private static <E extends Comparable<E>> void quickSort(E[] elements, int left, int right) {
		if(left < right) {
			int p = particionar(elements, left, right);
			quickSort(elements, left, p -1);
			quickSort(elements, p + 1, right);
		}
	}
	private static <E extends Comparable<E>> int particionar(E[] elements, int left, int right) {
		E pivot = elements[left];
		int i = left + 1;
		for(int j = i; j <= right; j++) {
			if(elements[j].compareTo(pivot) < 0) {
				swap(elements, j, i);
				i++;
			}
		}
		swap(elements, left, i - 1);
		return i - 1;
	}
	public static void main(String[] args) {
		String[] palabras = "hola que tal espereo que esto vaya a funcionar correctamente".split(" ");
		
		Integer[] nums = {1,4,6,7,2,5,8,9};
		mergeSort(nums);
		//System.out.println(nums);
		for(int i = 0; i < nums.length;i++) {
			System.out.println(nums[i] + ", ");
		}
	}
}
