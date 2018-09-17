//Sergio Ivan Tostado Nieto A01229274
//Fiboncci
//15/08/2018

public class Fibonacci {
	public static  long fibonacci(int n) { // Recursividad de grado 2, para resolver un algoritmo se necesitan dos funciones recursivas. Mala implementacion.
		if(n == 0 || n == 1) {
			return 1;
		}else {
			return fibonacci(n - 1) + fibonacci(n -2); // La escencia de esta implementacion esta en la naturaleza de la serie Fibonacci. 
			//La serie de Fibonacci genera un termino por la suma de los dos anteriores. 
		}
	}
	
	private static long fibonacciDinamico(int n, long[] tabla){ //Funcion recursiva.  
		if(tabla[n] == 0) {
			return tabla[n] = fibonacciDinamico(n - 1, tabla) + fibonacciDinamico(n - 2, tabla);
		}else {
			return tabla[n];
		}
	}
	public static long fibonacci2(int n){ // Funcion de preparacion. 
		long[] tabla = new long[n + 1];
		tabla[0] = 1;
		tabla[1] = 1;
		return fibonacciDinamico(n, tabla);
	}
	public static void main(String[] args) {
		System.out.println(fibonacci2(140));
	}
}
