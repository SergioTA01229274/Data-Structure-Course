
public class nReinas {  
	private int[] reinas; // Indice del objeto es la fila donde esta la reina, numero guardado en el indice es la columna 
	
	public nReinas(int n) {
		this.reinas = new int[n];
		for(int i = 0; i < reinas.length; i++) {
			this.reinas[i] = -1;
		}
	}
	
	private boolean valida(int fila, int columna) {
		for(int i = 0; i < fila; i++) {
			if(reinas[i] == columna) {
				return false;
			}
			if(Math.abs(fila - i) == Math.abs(columna - reinas[i])) { //Preguntar por esta parte
				return false;
			}
		}
		return true;
	}
	private void imprimeSolucion() {
		for(int pos:this.reinas) {
			System.out.println(pos + ",");
		}
		System.out.println();
	}
	
	private void busca(int fila) {
		for(int i = 0; i < this.reinas.length; i++) {
			if(this.valida(fila, i)) {
				this.reinas[fila] = i;
				if(fila < this.reinas.length - 1) {
					//Se ejeuta la recursion
					this.busca(fila + 1);
				}else {
					this.imprimeSolucion();
				}
			}
		}
	}
	public void busca() {
		busca(0);
	}
	public static void main(String[] args) {
		nReinas tablero = new nReinas(4);
		tablero.busca();
	}
}
