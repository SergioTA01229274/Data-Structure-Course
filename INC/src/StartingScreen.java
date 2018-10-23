import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StartingScreen extends JPanel{
	
	private Logo logoPanel;
	private StartingScreenButtons SSButtonsPanel;
	private ProjectWindow window;
	public StartingScreen(ProjectWindow window) {
		super();
		this.setPreferredSize(new Dimension(800, 650));
		this.setBackground(new Color(153, 0, 1));
		
		this.window = window;
		this.logoPanel = new Logo(800, 350, 390, 390);
		this.SSButtonsPanel = new StartingScreenButtons(this.logoPanel, this, this.window);
		this.add(this.logoPanel);
		this.add(this.SSButtonsPanel);
	}
	
}
