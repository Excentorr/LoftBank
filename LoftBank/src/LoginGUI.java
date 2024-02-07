/**
 * 
 */
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author Het Patel
 * Date: November 2023
 * Description: A graphical user interface for user login and registration.
 * 
 * Method List:
 * - public LoginGUI() - constructor for the LoginGUI class
 * - public void actionPerformed(ActionEvent e) - ActionListener method
 * - public static void main(String[] args) - main method for testing
 * 
 */
public class LoginGUI extends JFrame implements ActionListener{

	/**
	 * Instance Data/Attributes
	 */
	private JPanel loginPanel, backgroundPanel;

	private JLabel lblLoginBackground;
	private JLabel lblEnterUsername, lblEnterPassword;
	private JTextArea taUsername, taPassword;
	private JButton btnLogin, btnRegister;

	private Customer customer;
	private Savings savings;
	private Chequing chequing;

	private int width, height;

	/**
	 * 
	 */
	public LoginGUI() {
		super("Loftbank Login");	// calls super class

		// initialize width and height variables
		width = 400;
		height = 350;

		Font f1 = new Font("Arial", Font.PLAIN, 16);
		Font f2 = new Font("Arial", Font.PLAIN, 18);

		this.customer = new Customer();
		this.savings = new Savings();
		this.chequing = new Chequing();

		// setting the JFrame to a null layout
		setLayout(null);

		//>>>>>>>>> PANELS
		//set up panel for background
		backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new GridLayout(1, 1));
		backgroundPanel.setBounds(0, 0, width, height);

		//set up panel for main menu
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		loginPanel.setOpaque(false);
		loginPanel.setBounds(0, 0, width, height);

		//>>>>>>>>>>>>> Creating Components + adding to Panel

		// JLabels
		lblLoginBackground = new JLabel(new ImageIcon("Images/LoginBackground.png"));
		lblLoginBackground.setBounds(0, 0, width, height);
		backgroundPanel.add(lblLoginBackground);

		lblEnterUsername = new JLabel("Username or Access Code");
		lblEnterUsername.setBounds(40, 70, 235, 41);
		lblEnterUsername.setFont(f1);
		loginPanel.add(lblEnterUsername);

		lblEnterPassword = new JLabel("Password");
		lblEnterPassword.setBounds(40, 140, 235, 41);
		lblEnterPassword.setFont(f1);
		loginPanel.add(lblEnterPassword);

		// JTextArea
		taUsername = new JTextArea();
		taUsername.setBounds(40, 110, 320, 24);
		taUsername.setFont(f2);
		taUsername.setBorder(new LineBorder(new Color(32, 32, 32)));
		loginPanel.add(taUsername);

		taPassword = new JTextArea();
		taPassword.setBounds(40, 180, 320, 24);
		taPassword.setFont(f2);
		taPassword.setBorder(new LineBorder(new Color(32, 32, 32)));
		loginPanel.add(taPassword);

		// JButton
		btnLogin = new JButton("Login");
		btnLogin.setBounds(40, 240, 155, 36);
		btnLogin.setFont(f1);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.WHITE);
		loginPanel.add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(205, 240, 155, 36);
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setFont(f1);
		loginPanel.add(btnRegister);



		// add panels
		add(loginPanel);
		add(backgroundPanel);

		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);

		// set size and location of frame
		setSize(width,height);  
		setLocation(100, 100);
		setResizable(false);

		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegister) {
			this.dispose();
			new RegistrationGUI();

		} else if (e.getSource() == btnLogin) {

			if (!(taUsername.getText().isEmpty()) && !(taPassword.getText().isEmpty())) {

				int userIndex = FileAccessMethods.check(taUsername.getText(), 0, "CustomerInfo.txt");

				if (userIndex != -1) {
					String storedPassword = FileAccessMethods.readPassword(userIndex, "CustomerInfo.txt");

					if (taPassword.getText().equals(storedPassword)) {
						this.dispose();

						// Successful login
						int index = FileAccessMethods.check(taUsername.getText(), 0, "CustomerInfo.txt");
						FileAccessMethods.getDataFromAccount(savings, chequing, customer, index);

						new TransactionMenuGUI(savings, chequing);
					} else {
						JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.", 
								"Login Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username not found. Please check your username and try again.", 
							"Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoginGUI loginMenu = new LoginGUI();



	}
}
