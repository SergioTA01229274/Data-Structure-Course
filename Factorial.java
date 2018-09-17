
public class Factorial {
	public static long factorial(int numero) { // funcion recursiva
		if(numero == 1 || numero == 0) { //Caso base 
			return 1;
		}else {
			return numero*factorial(numero - 1); // Aproximacion del numero dado al caso base, obligamos a que el numero tienda a 0
		}
	}
	public static void main(String[] args) {
		System.out.println(factorial(10));
	}
}
