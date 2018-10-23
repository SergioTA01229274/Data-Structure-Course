import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StartingScreenButtons extends JPanel implements ActionListener{
	private JTextField tfUserID, pfPassword;
	private JButton btLogIn, btSignUp;
	private JLabel lbUserId, lbPassword;
	private Logo logo;
	private StartingScreen sScreen;
	private ProjectWindow window;
	private Functionality functions;
	
	public StartingScreenButtons (Logo logo, StartingScreen ss, ProjectWindow window) {
		super();
		this.setPreferredSize(new Dimension(200, 200));
		this.setBackground(new Color(153, 0, 1));
		Font lbFont = new Font("Arial Black", Font.PLAIN, 17);
		Font lbFont2 = new Font("Arial Black", Font.PLAIN, 12);
		this.lbUserId = new JLabel("USUARIO");
		this.lbPassword = new JLabel("CONTRASEÑA");
		
		this.logo = logo;
		this.sScreen =  ss;
		this.window = window;
		this.functions = new Functionality();
		
		this.lbUserId.setFont(lbFont);
		this.lbUserId.setForeground(new Color(255, 255, 255));
		this.lbPassword.setFont(lbFont);
		this.lbPassword.setForeground(new Color(255, 255, 255));
		
		this.tfUserID = new JTextField(14);
		this.pfPassword = new JPasswordField(14);
		this.btLogIn = new JButton("Ingresar");
		this.btSignUp = new JButton("Registrar");
		
		this.add(this.lbUserId);
		this.add(this.tfUserID);
		this.add(this.lbPassword);
		this.add(this.pfPassword);
		this.add(new JLabel("                                              "));
		
		this.btLogIn.setFont(lbFont2);
		this.btSignUp.setFont(lbFont2);
		this.btLogIn.setBackground(new Color(255, 255, 255));
		this.btSignUp.setBackground(new Color(255, 255, 255));
		this.btLogIn.setForeground(new Color(0, 0, 0));
		this.btSignUp.setForeground(new Color(0, 0, 0));
		this.add(this.btLogIn);
		this.add(this.btSignUp);
		this.btLogIn.addActionListener(this);
		this.btSignUp.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btLogIn) {
			try {
				if(this.functions.verifyAccount(this.window.getLogInDoc(), this.window.getPath(), this.tfUserID.getText(), this.pfPassword.getText())) {
					this.sScreen.setVisible(false);
					this.window.add(new UserMenu(this.window));
					System.gc();
				}else {
					JOptionPane.showMessageDialog(null, "Su usuario o contraseña no coinciden con la cuenta.");
				}
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null, "Ocurrio un problema al verificar su usuario. Intentelo de nuevo.");
			}
			
		}else if(e.getSource() == this.btSignUp) {
			try {
				if(!this.functions.verifyDocument(this.window.getLogInDoc(), this.window.getPath())) {
					this.sScreen.setVisible(false);
					this.window.add(new SignUp(this.window));
					System.gc();
				}else {
					JOptionPane.showMessageDialog(null, "Ya se registro previamente en la plataforma");
				}
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(null, "Hubo un error. Intentelo de nuevo");
			}
		}
	}

}
