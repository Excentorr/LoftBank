/**
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 * @author Het Patel
 * Date: December 2023
 * Description: Program is a Registration menu that helps the user register
 * 
 * Method List:
 * - public RegistrationGUI() - constructor for the RegistrationGUI class
 * - public void actionPerformed(ActionEvent e) - ActionListener method
 * - public static void main(String[] args) - main method for testing
 */
public class RegistrationGUI implements ActionListener{

	/**
	 * Instance Data/Attributes
	 */
	private JPanel[] backgroundPanels, registrationPanels;
	private JLabel[] lblRegisterBackgrounds;
	private int width, height;
	private JFrame step1Frame, step2Frame, step3Frame;

	// Step 1 components
	private JLabel lblName, lblPhoneNumber, lblAddress, lblUsername;
	private JTextArea taName, taPhoneNumber, taAddress, taUsername;
	private JButton btnNextStep1, btnBackStep1;

	// Step 2 components
	private JLabel lblPassword, lblConfirmPass;
	private JTextArea taPassword, taConfirmPass;
	private JButton btnNextStep2, btnBackStep2;

	// Step 3 components
	private JLabel lblSavingsBalance, lblChequingsBalance;
	private JTextArea taSavingsBalance, taChequingsBalance;
	private JButton btnRegister, btnBackStep3;

	private String username, password, name, phoneNumber, address, initialSavings, initialChequings;
	
	private Savings savings;
	private Chequing chequing;

	/**
	 * 
	 */
	public RegistrationGUI() {
		// initialize width and height variables
		width = 600;
		height = 420;
		int numberofFrames = 3;

		Font fontLabel = new Font("Arial", Font.PLAIN, 15);
		Font fontTextArea = new Font("Arial", Font.PLAIN, 18);
		Color darkGray = new Color(30, 30, 30);
		
		this.savings = new Savings();
		this.chequing = new Chequing();

		// Initialize the array
		registrationPanels = new JPanel[numberofFrames];
		backgroundPanels = new JPanel[numberofFrames];
		lblRegisterBackgrounds = new JLabel[numberofFrames];

		//>>>>>>>>> JFrames
		step1Frame = new JFrame();
		step1Frame.setSize(width,height);  
		step1Frame.setLocation(100, 100);
		step1Frame.setResizable(false);

		step2Frame = new JFrame();
		step2Frame.setSize(width,height);  
		step2Frame.setLocation(100, 100);
		step2Frame.setResizable(false);

		step3Frame = new JFrame();
		step3Frame.setSize(width,height);  
		step3Frame.setLocation(100, 100);
		step3Frame.setResizable(false);

		//>>>>>>>>> JPanels
		// Loop to create and set up each background panel
		for (int i = 0; i < numberofFrames; i++) {
			backgroundPanels[i] = new JPanel();
			backgroundPanels[i].setLayout(new GridLayout(1, 1));
			backgroundPanels[i].setBounds(0, 0, width, height);

			lblRegisterBackgrounds[i] = new JLabel(new ImageIcon("Images/RegisterBackground.png"));
			lblRegisterBackgrounds[i].setBounds(0, 0, backgroundPanels[i].getWidth(), backgroundPanels[i].getHeight());
			backgroundPanels[i].add(lblRegisterBackgrounds[i]);
		}

		// Loop to create and configure each registration panel
		for (int i = 0; i < numberofFrames; i++) {
			registrationPanels[i] = new JPanel();
			registrationPanels[i].setLayout(null);
			registrationPanels[i].setOpaque(false);
			registrationPanels[i].setBounds(0, 0, width, height);
		}

		/**
		 * Adding components for registrationPanels[0]
		 */
		// JLabels
		lblName = new JLabel("Name: ");
		lblName.setBounds(60, 115, 235, 41);
		lblName.setFont(fontLabel);
		registrationPanels[0].add(lblName);

		lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(60, 160, 235, 41);
		lblUsername.setFont(fontLabel);
		registrationPanels[0].add(lblUsername);

		lblPhoneNumber = new JLabel("Phone Number: ");
		lblPhoneNumber.setBounds(60, 205, 235, 41);
		lblPhoneNumber.setFont(fontLabel);
		registrationPanels[0].add(lblPhoneNumber);

		lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(60, 250, 235, 41);
		lblAddress.setFont(fontLabel);
		registrationPanels[0].add(lblAddress);

		// JTextArea
		taName = new JTextArea();
		taName.setBounds(180, 125, 355, 24);
		taName.setFont(fontTextArea);
		taName.setBorder(new LineBorder(darkGray));
		registrationPanels[0].add(taName);

		taUsername = new JTextArea();
		taUsername.setBounds(180, 170, 355, 24);
		taUsername.setFont(fontTextArea);
		taUsername.setBorder(new LineBorder(darkGray));
		registrationPanels[0].add(taUsername);

		taPhoneNumber = new JTextArea();
		taPhoneNumber.setBounds(180, 215, 355, 24);
		taPhoneNumber.setFont(fontTextArea);
		taPhoneNumber.setBorder(new LineBorder(darkGray));
		registrationPanels[0].add(taPhoneNumber);

		taAddress = new JTextArea();
		taAddress.setBounds(180, 260, 355, 24);
		taAddress.setFont(fontTextArea);
		taAddress.setBorder(new LineBorder(darkGray));
		registrationPanels[0].add(taAddress);

		// JButtons
		btnNextStep1 = new JButton("Next");
		btnNextStep1.setBounds(310, 310, 120, 40); // Adjust the position and size as needed
		btnNextStep1.setFont(fontLabel);
		btnNextStep1.setForeground(Color.BLACK);
		btnNextStep1.setBackground(Color.WHITE);
		btnNextStep1.addActionListener(this);
		registrationPanels[0].add(btnNextStep1);

		btnBackStep1 = new JButton("Back");
		btnBackStep1.setBounds(170, 310, 120, 40); // Adjust the position and size as needed
		btnBackStep1.setFont(fontLabel);
		btnBackStep1.setForeground(Color.BLACK);
		btnBackStep1.setBackground(Color.WHITE);
		btnBackStep1.addActionListener(this);
		registrationPanels[0].add(btnBackStep1);

		// add panels
		registrationPanels[0].add(backgroundPanels[0]); // Add the background panel to the registration panel
		registrationPanels[0].setVisible(true); // Make the registration panel visible

		step1Frame.add(registrationPanels[0]); // Add the registration panel to the frame
		step1Frame.setVisible(true); // Make the frame visible


		/**
		 * Adding components for registrationPanels[1]
		 */
		// JLabels
		lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(60, 115, 235, 41);
		lblPassword.setFont(fontLabel);
		registrationPanels[1].add(lblPassword);

		lblConfirmPass = new JLabel("Confirm Pass: ");
		lblConfirmPass.setBounds(60, 160, 235, 41);
		lblConfirmPass.setFont(fontLabel);
		registrationPanels[1].add(lblConfirmPass);

		// JTextArea
		taPassword = new JTextArea();
		taPassword.setBounds(180, 125, 355, 24);
		taPassword.setFont(fontTextArea);
		taPassword.setBorder(new LineBorder(darkGray));
		registrationPanels[1].add(taPassword);

		taConfirmPass = new JTextArea();
		taConfirmPass.setBounds(180, 170, 355, 24);
		taConfirmPass.setFont(fontTextArea);
		taConfirmPass.setBorder(new LineBorder(darkGray));
		registrationPanels[1].add(taConfirmPass);

		// JButtons
		btnNextStep2 = new JButton("Next");
		btnNextStep2.setBounds(310, 310, 120, 40); // Adjust the position and size as needed
		btnNextStep2.setFont(fontLabel);
		btnNextStep2.setForeground(Color.BLACK);
		btnNextStep2.setBackground(Color.WHITE);
		btnNextStep2.addActionListener(this);
		registrationPanels[1].add(btnNextStep2);

		btnBackStep2 = new JButton("Back");
		btnBackStep2.setBounds(170, 310, 120, 40); // Adjust the position and size as needed
		btnBackStep2.setFont(fontLabel);
		btnBackStep2.setForeground(Color.BLACK);
		btnBackStep2.setBackground(Color.WHITE);
		btnBackStep2.addActionListener(this);
		registrationPanels[1].add(btnBackStep2);

		// add panels
		registrationPanels[1].add(backgroundPanels[1]); // Add the background panel to the registration panel
		registrationPanels[1].setVisible(true); // Make the registration panel visible

		step2Frame.add(registrationPanels[1]); // Add the registration panel to the frame
		step2Frame.setVisible(false); // Make the frame visible

		/**
		 * Adding components for registrationPanels[2]
		 */
		lblSavingsBalance = new JLabel("Initial Savings Balance ($): ");
		lblSavingsBalance.setBounds(50, 115, 235, 41);
		lblSavingsBalance.setFont(fontLabel);
		registrationPanels[2].add(lblSavingsBalance);

		lblChequingsBalance = new JLabel("Initial Chequing Balance ($): ");
		lblChequingsBalance.setBounds(50, 160, 235, 41);
		lblChequingsBalance.setFont(fontLabel);
		registrationPanels[2].add(lblChequingsBalance);

		// JTextArea
		taSavingsBalance = new JTextArea();
		taSavingsBalance.setBounds(240, 125, 315, 24);
		taSavingsBalance.setFont(fontTextArea);
		taSavingsBalance.setBorder(new LineBorder(darkGray));
		registrationPanels[2].add(taSavingsBalance);

		taChequingsBalance = new JTextArea();
		taChequingsBalance.setBounds(240, 170, 315, 24);
		taChequingsBalance.setFont(fontTextArea);
		taChequingsBalance.setBorder(new LineBorder(darkGray));
		registrationPanels[2].add(taChequingsBalance);

		// JButtons
		btnRegister = new JButton("Register");
		btnRegister.setBounds(310, 310, 120, 40); // Adjust the position and size as needed
		btnRegister.setFont(fontLabel);
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.addActionListener(this);
		registrationPanels[2].add(btnRegister);

		btnBackStep3 = new JButton("Back");
		btnBackStep3.setBounds(170, 310, 120, 40); // Adjust the position and size as needed
		btnBackStep3.setFont(fontLabel);
		btnBackStep3.setForeground(Color.BLACK);
		btnBackStep3.setBackground(Color.WHITE);
		btnBackStep3.addActionListener(this);
		registrationPanels[2].add(btnBackStep3);

		// add panels
		registrationPanels[2].add(backgroundPanels[2]); // Add the background panel to the registration panel
		registrationPanels[2].setVisible(true); // Make the registration panel visible

		step3Frame.add(registrationPanels[2]); // Add the registration panel to the frame
		step3Frame.setVisible(false); // Make the frame visible


	}

	/**
	 * ActionListener
	 * 
	 * 	private String username, password, name, phoneNumber, address, initialSavings, initialChequings;
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNextStep1) {

			if (!taUsername.getText().isEmpty() && !taName.getText().isEmpty() &&
					!taPhoneNumber.getText().isEmpty() && !taAddress.getText().isEmpty()) {

				if (FileAccessMethods.check(taUsername.getText(), 0, "CustomerInfo.txt") == -1) {

					username = taUsername.getText();
					name = taName.getText();
					phoneNumber = taPhoneNumber.getText();
					address = taAddress.getText();
					
					savings.getCustomer().setUsername(username);
					savings.getCustomer().setName(name);
					savings.getCustomer().setPhoneNumber(phoneNumber);
					savings.getCustomer().setAddress(address);
					
					chequing.getCustomer().setUsername(username);
					chequing.getCustomer().setName(name);
					chequing.getCustomer().setPhoneNumber(phoneNumber);
					chequing.getCustomer().setAddress(address);

					step1Frame.setVisible(false);
					step2Frame.setVisible(true);

				} else {
					JOptionPane.showMessageDialog(null, "Username Taken.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
				}   

			} else {
				JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == btnBackStep1) {

			step1Frame.dispose();
			step2Frame.dispose();
			step3Frame.dispose();
			new LoginGUI();   	

		} else if (e.getSource() == btnNextStep2) {

			if (taPassword.getText().equals(taConfirmPass.getText())) {
				password = taPassword.getText();
				
				savings.getCustomer().setPassword(password);
				chequing.getCustomer().setPassword(password);
				
				step3Frame.setVisible(true);
				step2Frame.setVisible(false);  
			} else {
				JOptionPane.showMessageDialog(null, "Passwords do not match.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == btnBackStep2) {

			step2Frame.setVisible(false);  
			step1Frame.setVisible(true);

		} else if (e.getSource() == btnRegister) {
			// Check if initialSavingsBalance and initialChequingsBalance are valid numbers greater than 0
			try {
				double savingsBalance = Double.parseDouble(taSavingsBalance.getText());
				double chequingsBalance = Double.parseDouble(taChequingsBalance.getText());

				if (savingsBalance > 0 && chequingsBalance > 0) {
					// If balances are valid, proceed to save them to the variables
					initialSavings = taSavingsBalance.getText();
					initialChequings = taChequingsBalance.getText();
					
					savings.deposit(Double.parseDouble(initialSavings));
					chequing.deposit(Double.parseDouble(initialChequings));

					if (FileAccessMethods.saveUserData(savings, chequing)) {
						// display a successful registration prompt to user
						JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

					}
					else {
						JOptionPane.showMessageDialog(null, "Registration Error", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
					}
					
					step1Frame.dispose();
					step2Frame.dispose();
					step3Frame.dispose();
					
					new LoginGUI();
				} else {
					// Show an error message if balances are not valid
					JOptionPane.showMessageDialog(null, "Please enter valid initial balances greater than 0.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				// Show an error message if balances are not valid numbers
				JOptionPane.showMessageDialog(null, "Please enter valid numeric values for initial balances.", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
			}


		} else if (e.getSource() == btnBackStep3) {
			System.out.println("Back Button in Step 3 clicked");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RegistrationGUI registrationGUI = new RegistrationGUI();
        registrationGUI.step1Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Simulate user input for testing purposes
        String username = JOptionPane.showInputDialog(null, "Enter username for testing:");
        registrationGUI.taUsername.setText(username);

        String name = JOptionPane.showInputDialog(null, "Enter name for testing:");
        registrationGUI.taName.setText(name);

        String phoneNumber = JOptionPane.showInputDialog(null, "Enter phone number for testing:");
        registrationGUI.taPhoneNumber.setText(phoneNumber);

        String address = JOptionPane.showInputDialog(null, "Enter address for testing:");
        registrationGUI.taAddress.setText(address);

        registrationGUI.btnNextStep1.doClick(); // Trigger Next button in Step 1

        // Simulate user input for testing purposes
        String password = JOptionPane.showInputDialog(null, "Enter password for testing:");
        registrationGUI.taPassword.setText(password);

        String confirmPass = JOptionPane.showInputDialog(null, "Confirm password for testing:");
        registrationGUI.taConfirmPass.setText(confirmPass);

        registrationGUI.btnNextStep2.doClick(); // Trigger Next button in Step 2

        // Simulate user input for testing purposes
        String savingsBalance = JOptionPane.showInputDialog(null, "Enter initial savings balance for testing:");
        registrationGUI.taSavingsBalance.setText(savingsBalance);

        String chequingsBalance = JOptionPane.showInputDialog(null, "Enter initial chequings balance for testing:");
        registrationGUI.taChequingsBalance.setText(chequingsBalance);

        registrationGUI.btnRegister.doClick(); // Trigger Register button in Step 3

	}
}
