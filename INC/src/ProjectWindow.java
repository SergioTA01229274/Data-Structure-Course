import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;

public class ProjectWindow extends JFrame{
	private StartingScreen SSPanel;
	private UserMenu userMenuPanel;
	private RegisterPatientPanel registerPatientPanel;
	private String logIn = "LogInData.txt";
	private String Patients = "PatientsRegistration.txt";
	private String Donors = "DonorsRegistration.txt";
	private String path = "C:\\Users\\sergio\\Desktop\\prueba";
	
	public ProjectWindow() {
		super("BlooDrop");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		File directory = new File(this.path);
		directory.mkdir();
		
		this.SSPanel = new StartingScreen(this);
		this.userMenuPanel = new UserMenu(this);
		this.registerPatientPanel = new RegisterPatientPanel(this, this.userMenuPanel);
		this.add(this.SSPanel);
		
		this.pack();
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public String getLogInDoc() {
		return this.logIn;
	}
	public String getPatientsDoc() {
		return this.Patients;
	}
	public String getPath() {
		return this.path;
	}
	public String getDonorsDoc() {
		return this.Donors;
	}
	public static void main(String[] args) {
		ProjectWindow myNewProjectWindow = new ProjectWindow();
	}
}
