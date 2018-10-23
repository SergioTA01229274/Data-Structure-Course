import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp extends JPanel implements ActionListener{
	private JLabel lbHospitalName, lbHospitalPhoneNumber, lbHospitalAddress, 
					lbHospitalPostalCode, lbUserID, lbPassword;
	private JTextField tfHospitalName, tfHospitalPhoneNumber, tfHospitalAddress, 
						tfHospitalPostalCode, tfUserId, tfPassword;
	private JButton RegisterHospital, Cancel;
	private ProjectWindow window;
	private Logo logoPanel;
	private Functionality funtions;
	
	public SignUp(ProjectWindow window) {
		super();
		this.setBackground(new Color(153, 0, 1));
		this.logoPanel = new Logo(800, 350, 325, 325);
		this.add(this.logoPanel);
		this.window = window;
		
		this.funtions = new Functionality();
		
		this.lbHospitalName = new JLabel("Nombre del hospital");
		this.lbHospitalPhoneNumber = new JLabel("Telefono");
		this.lbHospitalAddress = new JLabel("Direccion");
		this.lbHospitalPostalCode = new JLabel("Codigo Postal");
		this.lbUserID = new JLabel("Usuario");
		this.lbPassword = new JLabel("Contraseña");
		
		Font lbFont = new Font("Arial Black", Font.PLAIN, 20);
		Font lbFont2 = new Font("Arial Black", Font.PLAIN, 25);
		this.lbHospitalName.setFont(lbFont);
		this.lbHospitalPhoneNumber.setFont(lbFont);
		this.lbHospitalAddress.setFont(lbFont);
		this.lbHospitalPostalCode.setFont(lbFont);
		this.lbUserID.setFont(lbFont);
		this.lbPassword.setFont(lbFont);
		
		Color white = new Color(255, 255, 255);
		this.lbHospitalName.setForeground(white);
		this.lbHospitalPhoneNumber.setForeground(white);
		this.lbHospitalAddress.setForeground(white);
		this.lbHospitalPostalCode.setForeground(white);
		this.lbUserID.setForeground(white);
		this.lbPassword.setForeground(white);
		
		this.tfHospitalName = new JTextField(20);
		this.tfHospitalPhoneNumber = new JTextField(20);
		this.tfHospitalAddress = new JTextField(30);
		this.tfHospitalPostalCode = new JTextField(10);
		this.tfUserId = new JTextField(20);
		this.tfPassword = new JTextField(20); 
		
		this.RegisterHospital = new JButton("Registrar institucion");
		this.Cancel = new JButton("Cancel");
		this.RegisterHospital.setForeground(new Color(0, 0, 0));
		this.RegisterHospital.setBackground(white);
		this.RegisterHospital.setFont(lbFont2);
		this.Cancel.setForeground(new Color(0, 0, 0));
		this.Cancel.setBackground(white);
		this.Cancel.setFont(lbFont2);
		
		this.add(this.lbHospitalName);
		this.add(this.tfHospitalName);
		this.add(this.lbHospitalPhoneNumber);
		this.add(this.tfHospitalPhoneNumber);
		this.add(new JLabel("                                                                                                                                                                                                                                                                       "));
		this.add(this.lbHospitalAddress);
		this.add(this.tfHospitalAddress);
		this.add(this.lbHospitalPostalCode);
		this.add(this.tfHospitalPostalCode);
		this.add(new JLabel("                                                                                                                                                                                                                                                                       "));
		this.add(this.lbUserID);
		this.add(this.tfUserId);
		this.add(this.lbPassword);
		this.add(this.tfPassword);
		this.add(new JLabel("                                                                                                                                                                                                                                                                       "));
		
		this.add(this.RegisterHospital);
		this.add(new JLabel("                            "));
		this.add(this.Cancel);
		this.Cancel.addActionListener(this);
		this.RegisterHospital.addActionListener(this);
	}
	
	public String getHName() {
		return this.tfHospitalName.getText();
	}
	public String getHPhoneNumber() {
		return this.tfHospitalPhoneNumber.getText();
	}
	public String getHAddress() {
		return this.tfHospitalAddress.getText();
	}
	public String getHPostalCode() {
		return this.tfHospitalPostalCode.getText();
	}
	public String getHUserId() {
		return this.tfUserId.getText();
	}
	public String getHPassword() {
		return this.tfPassword.getText();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.Cancel) {
			this.setVisible(false);
			this.logoPanel.setVisible(false);
			this.window.add(new StartingScreen(this.window));
			System.gc();
		}else if(e.getSource() == this.RegisterHospital) {
			this.funtions.saveLogInData(this, this.window.getLogInDoc(), this.window.getPath());
			this.setVisible(false);
			this.logoPanel.setVisible(false);
			this.window.add(new StartingScreen(this.window));
			System.gc();
		}
	}
}
