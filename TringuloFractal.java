import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TringuloFractal extends JFrame{
	private Point a, b, c;
	private int nivel;
	
	public TringuloFractal(){
		super("Fractal SierpinskiGasket");
		this.setSize(640, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.a = new Point(320, 50);
		this.b = new Point(30, 350);
		this.c = new Point(610, 350);
		this.nivel = Integer.parseInt(JOptionPane.showInputDialog("Escribe el nivel a dibujar: "));
		this.setVisible(true);
	}
	
	public Point puntoMedio(Point a, Point b) {
		return new Point((b.x + a.x)/2, (b.y + a.y)/2);
	}
	public void pintaLineas(Graphics g, Point a, Point b) {
		g.drawLine(a.x, a.y, b.x, b.y);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		this.pintaTriangulos(g, this.nivel, this.a, this.b, this.c);
	}
	
	public void pintaTriangulos(Graphics g, int n , Point a, Point b, Point c) {
		if(n == 0) {
			this.pintaLineas(g, a, b);
			this.pintaLineas(g, b, c);
			this.pintaLineas(g, c, a);
		}else {
			Point pmAB = puntoMedio(a, b), 
				  pmBC = puntoMedio(b, c), 
				  pmCA = puntoMedio(c, a);
			
			this.pintaTriangulos(g, n - 1, a, pmAB, pmCA);
			this.pintaTriangulos(g, n - 1, pmAB, b, pmBC);
			this.pintaTriangulos(g, n - 1, pmCA, pmBC, c);
		}
	}
	public static void main(String[] args) {
		TringuloFractal T1 = new TringuloFractal();
	}
}