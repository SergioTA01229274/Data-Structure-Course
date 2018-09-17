//Sergio Ivan Tostado Nieto A01229274
//Fecha: 19 de Agosto de 2018
//Estructura de Datos

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class yaMeHiceBolas extends JFrame{
	private int Largo, nivel;
	private Point coordenada;
	public yaMeHiceBolas() {
		super("Ya me hice bolas");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.coordenada = new Point(25, 35);
		this.Largo = 650;
		this.nivel = Integer.parseInt(JOptionPane.showInputDialog("Escribe el nivel a dibujar: "));
		this.setVisible(true);
	}
	
	public void pintaC(Graphics g, int x, int y, int L, int n) {
		g.drawOval(x, y, L, L);
	}
	
	public void pintaCirculos(Graphics g, int nivel, int x1, int y1, int largo) { // Funcion recursiva
		if(nivel == 0) {
			this.pintaC(g, x1, y1, largo, nivel); // revisar esta linea
		}else {
			Point izq = new Point(this.getWidth() - (largo + x1), (this.getHeight()/2) - largo/(4));
			Point der = new Point((int)izq.getX() + (largo/2), (int)izq.getY());
			
			this.pintaCirculos(g, nivel - 1, (int)izq.getX(), (int)izq.getY(), largo/2);
			this.pintaCirculos(g, nivel - 1, (int)der.getX(), (int)der.getY(), largo/2);
			this.pintaCirculos(g, nivel - 1, x1, y1, largo);
		} 
	}
	public void paint(Graphics g) {
		super.paint(g);
		this.pintaCirculos(g, this.nivel, (int)this.coordenada.getX(), (int)this.coordenada.getY(), this.Largo);
	}
	
	
	public static void main(String[] args) {
		yaMeHiceBolas E1 = new yaMeHiceBolas();
	}
}
