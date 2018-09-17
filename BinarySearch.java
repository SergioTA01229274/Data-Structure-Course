//Sergio Ivan Tostado Nieto A01229274
//Estructura de Datos
//29 de Agosto de 2018

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch <T extends Number>{
	
	private static <E extends Comparable<E>> int binarySearchRec(E[] elements, E value, int min, int max){ // Funcion Recursiva
		int Med = (min + max)/2;
		if(elements[Med].compareTo(value) == 0) {
			return Med;
		}else if(elements[Med].compareTo(value) >= 0) {
			return binarySearchRec(elements, value, min, Med);
		}else {
			if(min == Med && elements[Med].compareTo(value) != 0){
				return -1;
			}else {
				return binarySearchRec(elements, value, Med, max);
			}
		}
	}
	public static <E extends Comparable<E>> int binarySearch (E[] elements, E value){ //Funcion de preparacion
		return binarySearchRec(elements, value, 0, elements.length);
	}
	public static void main(String[] args) {
		String[] palabras = {"ol", "ola","olaa", "olaaaa"};
		
		System.out.println(binarySearch(palabras, "olaa"));
		
		Integer[] numeros = {1,3,4,6,7,8,9,10,13};
		
		System.out.println(binarySearch(numeros, 3));
		
	} 
}
