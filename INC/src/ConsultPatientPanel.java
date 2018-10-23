import java.awt.Color;

import javax.swing.JPanel;

public class ConsultPatientPanel extends JPanel{
	private ProjectWindow window;
	private Logo logoPanel;
	private Functionality functions;
	
	public ConsultPatientPanel(ProjectWindow window) {
		super();
		this.setBackground(new Color(153, 0, 1));
		this.logoPanel = new Logo(800, 350, 325, 325);
		this.add(this.logoPanel);
		this.window = window;
		this.functions =  new Functionality();
	}
	
	public void generatePatients() {
		
	}
}
