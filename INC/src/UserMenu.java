import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserMenu extends JPanel implements ActionListener{
	
	private Logo logoPanel;
	private JButton registerPatient, registerDonor, askForBlood, consultPatient, consultDonor;
	private ProjectWindow window;
	public UserMenu(ProjectWindow window) {
		super();
		this.setPreferredSize(new Dimension(800, 650));
		this.setBackground(new Color(153, 0, 1));
		this.logoPanel = new Logo(800, 350, 325, 325);
		this.add(this.logoPanel);
		this.window = window;
		
		this.registerPatient = new JButton("Registar paciente");
		this.registerDonor = new JButton("Registar donante");
		this.consultDonor = new JButton("Consultar donantes");
		this.consultPatient = new JButton("Consultar pacientes");
		this.askForBlood = new JButton("Pedir donantes de sangre");
		Font btFont = new Font("Arial Black", Font.PLAIN, 25);
		
		this.registerPatient.setFont(btFont);
		this.registerPatient.setForeground(new Color(0, 0, 0));
		this.registerPatient.setBackground(new Color(255, 255, 255));
		
		this.registerDonor.setFont(btFont);
		this.registerDonor.setForeground(new Color(0, 0, 0));
		this.registerDonor.setBackground(new Color(255, 255, 255));
		
		this.consultPatient.setFont(btFont);
		this.consultPatient.setForeground(new Color(0, 0, 0));
		this.consultPatient.setBackground(new Color(255, 255, 255));
		
		this.consultDonor.setFont(btFont);
		this.consultDonor.setForeground(new Color(0, 0, 0));
		this.consultDonor.setBackground(new Color(255, 255, 255));
		
		this.askForBlood.setFont(btFont);
		this.askForBlood.setForeground(new Color(255, 0, 0));
		this.askForBlood.setBackground(new Color(255, 255, 255));
		
		this.add(this.registerPatient);
		this.add(this.registerDonor);
		this.add(new JLabel("                                                                                                                                                                                                               "));
		this.add(this.consultPatient);
		this.add(this.consultDonor);
		this.add(new JLabel("                                                                                                                                                                "));
		this.add(this.askForBlood);
		
		this.registerPatient.addActionListener(this);
		this.registerDonor.addActionListener(this);
		this.askForBlood.addActionListener(this);
	}
	
	public Logo getUserMenuLogo() {
		return this.logoPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.registerPatient) {
			this.logoPanel.setVisible(false);
			this.setVisible(false);
			this.window.add(new RegisterPatientPanel(this.window, this));
			System.gc();
		}else if(e.getSource() == this.registerDonor) {
			this.logoPanel.setVisible(false);
			this.setVisible(false);
			this.window.add(new RegisterDonorPanel(this.window, this));
			System.gc();
		}else if(e.getSource() == this.askForBlood) {
			this.logoPanel.setVisible(false);
			this.setVisible(false);
			this.window.add(new askForBloodPanel(this.window));
			System.gc();
		}
	}
}
