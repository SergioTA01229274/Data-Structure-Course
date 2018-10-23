import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterPatientPanel extends JPanel implements ActionListener{
	private JLabel lbCurp, lbBloodType, lbUrgencyLevel;
	private JTextField tfCurp;
	private JComboBox<String> cbBloodType, cbUrgenccyLevel;
	private JButton btRegister, btCancel;
	private Logo logoPanel;
	private ProjectWindow window;
	private Functionality functions;
	private UserMenu userMenu;
	public String[] bloodTypes = {"A+", "AB+", "A-", "AB-", "B+", "B-", "O+", "O-"};
	public String[] urgencyLevels = {"1", "2","3","4","5"};
	
	public RegisterPatientPanel (ProjectWindow window, UserMenu userMenu) {
		super();
		this.setBackground(new Color(153, 0, 1));
		this.logoPanel = new Logo(800, 350, 325, 325);
		this.add(this.logoPanel);
		
		this.window = window;
		this.userMenu = userMenu;
		this.functions = new Functionality();
		this.lbCurp = new JLabel("CURP");
		this.lbBloodType = new JLabel("Tipo de sangre");
		this.lbUrgencyLevel = new JLabel("Nivel de urgencia");
		Font font = new Font("Arial Black", Font.PLAIN, 25);
		
		this.btRegister = new JButton("Registar paciente");
		this.btRegister.setFont(font);
		this.btRegister.setForeground(new Color(0, 0, 0));
		this.btRegister.setBackground(new Color(255, 255, 255));
		this.btCancel = new JButton("Cancelar");
		this.btCancel.setFont(font);
		this.btCancel.setForeground(new Color(0, 0, 0));
		this.btCancel.setBackground(new Color(255, 255, 255));
		
		this.lbCurp.setFont(font);
		this.lbCurp.setForeground(new Color(255,255,255));
		this.lbBloodType.setFont(font);
		this.lbBloodType.setForeground(new Color(255,255,255));
		this.lbUrgencyLevel.setFont(font);
		this.lbUrgencyLevel.setForeground(new Color(255,255,255));
		
		this.tfCurp = new JTextField(50);
		this.cbUrgenccyLevel = new JComboBox<String>(urgencyLevels);
		this.cbBloodType = new JComboBox<String>(bloodTypes);
		
		this.add(this.lbCurp);
		this.add(this.tfCurp);
		this.add(new JLabel("                                                                                                                                                                                                               "));
		this.add(this.lbBloodType);
		this.add(this.cbBloodType);
		this.add(this.lbUrgencyLevel);
		this.add(this.cbUrgenccyLevel);
		this.add(new JLabel("                                                                                                                                                                                                               "));
		this.add(new JLabel("                                                                                                                                                                                                               "));
		
		this.add(this.btRegister);
		this.add(new JLabel("                                                              "));
		this.add(this.btCancel);
		this.btCancel.addActionListener(this);
		this.btRegister.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btCancel) {
			this.setVisible(false);
			this.logoPanel.setVisible(false);
			this.userMenu.getUserMenuLogo().setVisible(true);
			this.window.add(new UserMenu(this.window));
			System.gc();
		}else if(e.getSource() == this.btRegister) {
			try {
				this.functions.savePerson(this.window.getPatientsDoc(), this.window.getPath(), this.getTfCurp(), this.getCbUrgenccyLevel(), this.getCbBloodType());
				this.setVisible(false);
				this.logoPanel.setVisible(false);
				this.userMenu.getUserMenuLogo().setVisible(true);
				this.window.add(new UserMenu(this.window));
				System.gc();
			}catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "No ha sido posible registrar al paciente. Intentelo de nuevo.");
			}
			
		}
	}

	public String getTfCurp() {
		return tfCurp.getText();
	}

	public String getCbBloodType() {
		return bloodTypes[this.cbBloodType.getSelectedIndex()];
	}

	public String getCbUrgenccyLevel() {
		return urgencyLevels[this.cbUrgenccyLevel.getSelectedIndex()];
	}
}
