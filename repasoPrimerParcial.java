
public class repasoPrimerParcial {
	
	public static void insertionSort(int[] elements) {
		for(int i = 0; i < elements.length - 1; i++) {
			if(elements[i] > elements[i + 1]) {
				for(int j = i; j >= 0; j--) {
					if(elements[j] > elements[j + 1]) {
						int temp = elements[j];
						elements[j] = elements[j + 1];
						elements[j + 1] = temp;
					}
				}
			}
		}
		for(int i = 0; i < elements.length;i++) {
			System.out.println(elements[i] + " ");
		}
	}
	
	public static boolean palindromo(String palabra) {
		String[] letras = palabra.split("");
		int f = 0;
		int l = letras.length - 1;
		return palindromo(f,l,letras);
	}
	
	public static boolean palindromo(int f, int l, String[] letras) {
		if(f >= l) {
			return true;
		}else if(letras[f].compareTo(letras[l]) != 0) {
			return false;
		}else {
			return palindromo(++f,--l,letras);
		}
	}
	public static void main(String[] args) {
		int[] nums = {4,1,8,2,9};
		insertionSort(nums);
		String p = "anitalavakatina";
		System.out.println(palindromo(p));
	}
}
