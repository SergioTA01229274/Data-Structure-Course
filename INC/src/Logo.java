import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Logo extends JPanel{
	private Image imgLogo;
	private int width, height;
	public Logo(int x, int y, int w, int h) {
		super();
		this.setPreferredSize(new Dimension(x, y));
		this.setBackground(new Color(153, 0, 1));
		this.imgLogo = new ImageIcon("BlooDrop.jpg").getImage();
		this.width = w;
		this.height = h;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.imgLogo, this.getWidth() / 2 - this.width/2, 0, this.width, this.height, this);
	}
}
