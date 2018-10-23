import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class askForBloodPanel extends JPanel implements ActionListener{
	private JLabel lbBloodType, lbUrgencyLevel, lbPatientId, lbQuantity, lbComments;
	private JTextField tfBloodType, tfPatientId, tfQuantity, tfUrgencyLevel, tfComments;
	private JButton btAskForBlood, btCancel;
	private Logo logoPanel;
	private ProjectWindow window;
	private Functionality functions;
	
	public askForBloodPanel(ProjectWindow window) {
		super();
		this.setBackground(new Color(153, 0, 1));
		this.logoPanel = new Logo(800, 350, 325, 325);
		this.add(this.logoPanel);
		
		this.window = window;
		this.functions = new Functionality();
		
		this.lbBloodType = new JLabel("Tipo de sangre");
		this.lbPatientId = new JLabel("Id del paciente necesitado");
		this.lbQuantity = new JLabel("Cantidad de sangre el Lts");
		this.lbUrgencyLevel = new JLabel("Triage de urgencia");
		this.lbComments = new JLabel("Comentarios");
		
		this.btAskForBlood = new JButton("Solicitar donante");
		this.btCancel = new JButton("Cancelar");
		
		this.tfBloodType = new JTextField(10);
		this.tfPatientId = new JTextField(15);
		this.tfQuantity = new JTextField(10);
		this.tfUrgencyLevel = new JTextField(10);
		this.tfComments = new JTextField(15);
		JLabel[] objects =  {this.lbBloodType, this.lbPatientId, this.lbQuantity, this.lbUrgencyLevel, this.lbComments};
		JButton[] objects2 = {this.btAskForBlood, this.btCancel};
		
		Font lbFont = new Font("Arial Black", Font.PLAIN, 20);
		Font btFont = new Font("Arial Black", Font.PLAIN, 25);
		Color  white = new Color(255, 255, 255);
		Color black = new Color(0, 0, 0);
		
		for(int i = 0; i < objects.length; i++) {
			objects[i].setFont(lbFont);
			objects[i].setForeground(white);
		}
		for(int i = 0; i < objects2.length; i++) {
			objects2[i].setFont(btFont);
			objects2[i].setForeground(black);
			objects2[i].setBackground(white);
		}
		
		this.add(this.lbBloodType);
		this.add(this.tfBloodType);
		this.add(this.lbPatientId);
		this.add(this.tfPatientId);
		this.add(this.lbQuantity);
		this.add(this.tfQuantity);
		this.add(this.lbUrgencyLevel);
		this.add(this.tfUrgencyLevel);
		this.add(this.lbComments);
		this.add(this.tfComments);
		this.add(new JLabel("                                                                                                                                                                                                                                                            "));
		this.add(this.btAskForBlood);
		this.add(this.btCancel);
		this.btCancel.addActionListener(this);
		this.btAskForBlood.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btCancel) {
			this.setVisible(false);
			this.logoPanel.setVisible(false);
			this.window.add(new UserMenu(this.window));
			System.gc();
		}else if(e.getSource() == this.btAskForBlood) {
			try {
				this.functions.sendMessage(this.functions.obtainDonorData(this.window.getDonorsDoc(), this.window.getPath()), this.functions.obtainPatientCurp(this.tfPatientId.getText()), this.functions.obtainAddress(this.window.getLogInDoc(), this.window.getPath()), this.window, this.functions.obtainHospitalName(this.window.getLogInDoc(), this.window.getPath()), this.tfBloodType.getText(), this.tfUrgencyLevel.getText(), this.tfQuantity.getText());
				this.setVisible(false);
				this.logoPanel.setVisible(false);
				this.window.add(new UserMenu(this.window));
				System.gc();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Ha ocurrido un problema en el envio de mensaje. Intelo de nuevo");
			}
			
		}
	}
}
