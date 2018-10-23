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

public class RegisterDonorPanel extends JPanel implements ActionListener{
	
	private Logo logoPanel;
	private ProjectWindow window;
	private UserMenu userMenu;
	private JLabel lbDonorId, lbDate, lbSex, lbCity, lbPhoneNumber, lbBloodType;
	private JTextField tfDonorId, tfDate, tfCity, tfPhoneNumber;
	private JComboBox<String> cbSex, cbBloodType;
	private JButton registerDonor, cancel;
	private Functionality functions;
	String[] bloodTypes = {"A+", "AB+", "A-", "AB-", "B+", "B-", "O+", "O-"};
	String[] sex = {"H", "M"};
	
	public RegisterDonorPanel(ProjectWindow window, UserMenu userMenu) {
		super();
		
		this.setBackground(new Color(152, 0, 1));
		this.logoPanel = new Logo(800, 350, 325, 325);
		this.add(this.logoPanel);
		this.window = window;
		this.userMenu = userMenu;
		this.functions = new Functionality();
		
		this.tfDonorId = new JTextField("XXXXXXXXXXXXMA+",20);
		this.tfDate = new JTextField(20);
		this.cbSex = new JComboBox(sex);
		this.cbBloodType = new JComboBox(bloodTypes);
		this.tfPhoneNumber = new JTextField(20);
		this.tfCity = new JTextField(20); //generar memeoria de datos para poder llenar esto
		
		this.registerDonor = new JButton("Registrar Donante");
		this.cancel = new JButton("Canelar");
		
		Font font = new Font("Arial Black", Font.PLAIN, 20);
		Font font2 = new Font("Arial Black", Font.PLAIN, 25);
		Color white = new Color(255, 255, 255);
		
		this.registerDonor.setFont(font2);
		this.cancel.setFont(font2);
		this.registerDonor.setBackground(white);
		this.cancel.setBackground(white);
		this.registerDonor.setForeground(new Color(0, 0, 0));
		this.cancel.setForeground(new Color(0, 0, 0));
		
		this.lbDonorId = new JLabel("Id del donante");
		this.lbDate = new JLabel("Fecha");
		this.lbSex = new JLabel("Sexo");
		this.lbCity = new JLabel("Ciudad");
		this.lbPhoneNumber = new JLabel("Telefono");
		this.lbBloodType = new JLabel("Tipo de sangre");
		
		this.lbDonorId.setFont(font);
		this.lbDate.setFont(font);
		this.lbSex.setFont(font);
		this.lbCity.setFont(font);
		this.lbPhoneNumber.setFont(font);
		this.lbBloodType.setFont(font);
		
		this.lbDonorId.setForeground(white);
		this.lbDate.setForeground(white);
		this.lbSex.setForeground(white);
		this.lbCity.setForeground(white);
		this.lbPhoneNumber.setForeground(white);
		this.lbBloodType.setForeground(white);
		
		this.tfDonorId.setEditable(false);
		this.tfCity.setEditable(false);
		
		this.add(this.lbDonorId);
		this.add(this.tfDonorId);
		this.add(new JLabel("                                "));
		this.add(this.lbDate);
		this.add(this.tfDate);
		this.add(new JLabel("                                                                                                                                                                                                                                                                       "));
		
		this.add(this.lbSex);
		this.add(this.cbSex);
		this.add(new JLabel("                                                                                                                     "));
		this.add(this.lbCity);
		this.add(this.tfCity);
		this.add(new JLabel("                                                                                                                                                                                                                                                                        "));
		
		this.add(this.lbPhoneNumber);
		this.add(this.tfPhoneNumber);
		this.add(new JLabel("                                                                   "));
		
		this.add(this.lbBloodType);
		this.add(this.cbBloodType);
		this.add(new JLabel("                                                                                                                                                                                                                                                                        "));
		
		this.add(this.registerDonor);
		//this.add(new JLabel("                                "));
		
		this.add(this.cancel);
		this.cancel.addActionListener(this);
		this.registerDonor.addActionListener(this);
	}
	public String getPhoneNumber() {
		return this.tfPhoneNumber.getText();
	}
	public String getSex() {
		return this.sex[this.cbSex.getSelectedIndex()];
	}
	public String getBloodType() {
		return this.bloodTypes[this.cbBloodType.getSelectedIndex()];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.cancel) {
			this.setVisible(false);
			this.logoPanel.setVisible(false);
			this.userMenu.getUserMenuLogo().setVisible(true);
			this.window.add(new UserMenu(this.window));
			System.gc();
		}else if(e.getSource() == this.registerDonor) {
			try {
				this.functions.savePerson2(this.window.getDonorsDoc(), this.window.getPath(), this.getPhoneNumber(), this.getSex(), this.getBloodType());
				this.setVisible(false);
				this.logoPanel.setVisible(false);
				this.userMenu.getUserMenuLogo().setVisible(true);
				this.window.add(new UserMenu(this.window));
				System.gc();
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null, "No ha sido posible registrar al donante. Intentelo de nuevo.");
			}
			
		}
		
	}

}
