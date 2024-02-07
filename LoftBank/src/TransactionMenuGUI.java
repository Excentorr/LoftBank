/**
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

/**
 * @author Het Patel
 * Date: November 2023
 * Description: Graphical user interface for banking transactions, including deposit, withdrawal, and account information.
 * 
 * Method List:
 * - public TransactionMenuGUI(Savings s, Chequing c) - constructor for the TransactionMenuGUI class
 * - public void actionPerformed(ActionEvent e) - ActionListener method
 * - public static void main(String[] args) - main method for testing
 * - private void updateTransactionDisplay() - method to update transaction list text area
 * 
 */
public class TransactionMenuGUI extends JFrame implements ActionListener{

	/**
	 * Instance Data/Attributes
	 */
	private JPanel menuPanel, backgroundPanel;

	private JLabel lblLoginBackground;
	private JLabel lblName, lblAddress, lblPhoneNum, lblAccountNum;
	private JLabel lblBalance, lblEnterAmount;
	private JTextArea taTransactionData, taEnterAmount;
	private JComboBox<String> cbAccountType;
	private JButton btnDeposit, btnWithdraw, btnLogout, btnDeleteTransaction, btnSort, btnExport, btnImport;

	private JScrollPane spTransactionData = new JScrollPane();

	private int width, height;

	private Savings savings;
	private Chequing chequing;
	private TransactionList transactionList;

	/**
	 * 
	 */
	public TransactionMenuGUI(Savings s, Chequing c) {
		super("Loft Bank");	// calls super class

		// initialize width and height variables
		width = 700;
		height = 700;

		Font fontLabel = new Font("Arial", Font.PLAIN, 15);
		Font fontLabel2 = new Font("Arial", Font.BOLD, 13);
		Color darkGray = new Color(30, 30, 30);

		this.savings = s;
		this.chequing = c;
		this.transactionList = new TransactionList();

		// setting the JFrame to a null layout
		setLayout(null);

		//>>>>>>>>> PANELS
		//set up panel for background
		backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new GridLayout(1, 1));
		backgroundPanel.setBounds(0, 0, width, height);

		//set up panel for main menu
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setOpaque(false);
		menuPanel.setBounds(0, 0, width, height);

		//>>>>>>>>>>>>> Creating Components + adding to Panel

		// JLabels
		lblLoginBackground = new JLabel(new ImageIcon("Images/AccountBackground.png"));
		lblLoginBackground.setBounds(0, 0, width, height);
		backgroundPanel.add(lblLoginBackground);

		lblName = new JLabel(s.getCustomer().getName());
		lblName.setFont(fontLabel2);
		lblName.setBounds(30, 100, 200, 20);
		menuPanel.add(lblName);

		lblAddress = new JLabel(s.getCustomer().getAddress());
		lblAddress.setFont(fontLabel2);
		lblAddress.setBounds(30, 117, 200, 20);
		menuPanel.add(lblAddress);

		lblPhoneNum = new JLabel(s.getCustomer().getPhoneNumber());
		lblPhoneNum.setFont(fontLabel2);
		lblPhoneNum.setBounds(30, 134, 200, 20);
		menuPanel.add(lblPhoneNum);

		lblAccountNum = new JLabel(s.getAccountNumber());
		lblAccountNum.setFont(fontLabel2);
		lblAccountNum.setBounds(width - 135, 100, 200, 20);
		menuPanel.add(lblAccountNum);

		lblBalance = new JLabel("Balance: " + c.getBalance());
		lblBalance.setFont(fontLabel2);
		lblBalance.setBounds(30, 170, 200, 20);
		menuPanel.add(lblBalance);

		lblEnterAmount = new JLabel("Enter amount to withdraw/deposit ($):");
		lblEnterAmount.setFont(fontLabel2);
		lblEnterAmount.setBounds(30, 190, 250, 20);
		menuPanel.add(lblEnterAmount);

		// JTextArea
		taTransactionData = new JTextArea("Not working");
		taTransactionData.setLineWrap(true);
		taTransactionData.setWrapStyleWord(true);
		taTransactionData.setEditable(false);

		taEnterAmount = new JTextArea("");
		taEnterAmount.setFont(fontLabel);
		taEnterAmount.setBorder(new LineBorder(darkGray));
		taEnterAmount.setBounds(30, 210, 300, 22);
		menuPanel.add(taEnterAmount);

		// JScrollPane
		spTransactionData = new JScrollPane(taTransactionData);
		spTransactionData.setBounds(30, 270, 630, 250);
		spTransactionData.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spTransactionData.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		menuPanel.add(spTransactionData);

		// JComboBox
		String[] accountTypes = {"Chequing", "Saving"};
		cbAccountType = new JComboBox(accountTypes);
		cbAccountType.setBounds(560, 170, 100, 25);
		menuPanel.add(cbAccountType);

		// JButton
		btnDeposit = new JButton("Deposit");
		btnDeposit.setBounds(370, 209, 140, 24); // Adjust the position and size as needed
		btnDeposit.setFont(fontLabel);
		btnDeposit.setForeground(Color.BLACK);
		btnDeposit.setBackground(Color.WHITE);
		menuPanel.add(btnDeposit);

		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(520, 209, 140, 24); // Adjust the position and size as needed
		btnWithdraw.setFont(fontLabel);
		btnWithdraw.setForeground(Color.BLACK);
		btnWithdraw.setBackground(Color.WHITE);
		menuPanel.add(btnWithdraw);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(width - 150, height - 80, 120, 30); // Adjust the position and size as needed
		btnLogout.setFont(fontLabel);
		btnLogout.setForeground(Color.BLACK);
		btnLogout.setBackground(Color.WHITE);
		menuPanel.add(btnLogout);

		btnDeleteTransaction = new JButton("Delete");
		btnDeleteTransaction.setBounds(540, 530, 120, 30); // Adjust the position and size as needed
		btnDeleteTransaction.setFont(fontLabel);
		btnDeleteTransaction.setForeground(Color.BLACK);
		btnDeleteTransaction.setBackground(Color.WHITE);
		menuPanel.add(btnDeleteTransaction);

		btnSort = new JButton("Sort");
		btnSort.setBounds(410, 530, 120, 30); // Adjust the position and size as needed
		btnSort.setFont(fontLabel);
		btnSort.setForeground(Color.BLACK);
		btnSort.setBackground(Color.WHITE);
		menuPanel.add(btnSort);

		btnExport = new JButton("Export");
		btnExport.setBounds(280, 530, 120, 30); // Adjust the position and size as needed
		btnExport.setFont(fontLabel);
		btnExport.setForeground(Color.BLACK);
		btnExport.setBackground(Color.WHITE);
		menuPanel.add(btnExport);

		btnImport = new JButton("Import");
		btnImport.setBounds(150, 530, 120, 30); // Adjust the position and size as needed
		btnImport.setFont(fontLabel);
		btnImport.setForeground(Color.BLACK);
		btnImport.setBackground(Color.WHITE);
		menuPanel.add(btnImport);

		// add panels
		add(menuPanel);
		add(backgroundPanel);

		// add actionListeners
		btnDeposit.addActionListener(this);
		btnWithdraw.addActionListener(this);
		btnLogout.addActionListener(this);
		btnDeleteTransaction.addActionListener(this);
		btnSort.addActionListener(this);
		btnExport.addActionListener(this);
		btnImport.addActionListener(this);
		cbAccountType.addActionListener(this);	

		// set size and location of frame
		setSize(width,height);  
		setLocation(100, 100);
		setResizable(false);

		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void updateTransactionDisplay() {
		StringBuilder displayText = new StringBuilder();

		TransactionRecord[] transactionRecords = transactionList.getList();
		DecimalFormat decimalFormat = new DecimalFormat("#.00");

		for (int i = 0; i < transactionList.getSize(); i++) {
			displayText.append("Type: ").append(transactionRecords[i].getType())
			.append(" Account: ").append(transactionRecords[i].getAccount())
			.append(" Amount: ").append(decimalFormat.format(transactionRecords[i].getAmount()))
			.append(" Start Bal: ").append(decimalFormat.format(transactionRecords[i].getStartBal()))
			.append(" End Bal: ").append(decimalFormat.format(transactionRecords[i].getEndBal()))
			.append("\n");
		}

		taTransactionData.setText(displayText.toString());
	}

	/**
	 * Action Listener
	 */
	public void actionPerformed(ActionEvent e) {

		DecimalFormat twoDigits = new DecimalFormat("#.00");

		TransactionRecord newRecord = new TransactionRecord();

		if (e.getSource() == btnDeposit) {
			
			if (!taEnterAmount.getText().equals("")) {
				double amount = Double.parseDouble(taEnterAmount.getText());

				if (cbAccountType.getSelectedItem().equals("Chequing")) {
					chequing.deposit(amount);
					lblBalance.setText("Balance: $" + twoDigits.format(chequing.getBalance()));	
					newRecord.setAccount('c');
				}
				else {
					savings.deposit(amount);
					lblBalance.setText("Balance: $" + twoDigits.format(savings.getBalance()));
					newRecord.setAccount('s');
				}

				// Update transaction list and display
				newRecord.setType("Deposit");
				newRecord.setAmount(amount);
				newRecord.setStartBal(chequing.getBalance() - amount);
				newRecord.setEndBal(chequing.getBalance());

				transactionList.add(newRecord);
				updateTransactionDisplay();

				FileAccessMethods.saveUserData(savings, chequing);

			}

		} else if (e.getSource() == btnWithdraw) {

			if (!taEnterAmount.getText().equals("")) {
				
				double amount = Double.parseDouble(taEnterAmount.getText());

				if (cbAccountType.getSelectedItem().equals("Chequing")) {
					chequing.withdraw(amount);
					lblBalance.setText("Balance: $" + twoDigits.format(chequing.getBalance()));
					newRecord.setAccount('c');
				}
				else {
					savings.withdraw(amount);
					lblBalance.setText("Balance: $" + twoDigits.format(savings.getBalance()));
					newRecord.setAccount('s');
				}

				newRecord.setType("Withdraw");
				newRecord.setAmount(amount);
				newRecord.setStartBal(savings.getBalance() + amount);
				newRecord.setEndBal(savings.getBalance());

				transactionList.add(newRecord);
				updateTransactionDisplay();

				FileAccessMethods.saveUserData(savings, chequing);
				
			}

		} else if (e.getSource() == btnLogout) {
			this.dispose();
			new LoginGUI();

		} else if (e.getSource() == cbAccountType) {

			if (cbAccountType.getSelectedItem().equals("Chequing")) {
				lblBalance.setText("Balance: $" + twoDigits.format(chequing.getBalance()));
			}
			else {
				lblBalance.setText("Balance: $" + twoDigits.format(savings.getBalance()));
			}

		} else if (e.getSource() == btnExport) {
			try {

				String fileToSave = JOptionPane.showInputDialog(null, "Enter a file name to save transaction list");




				// Create a BufferedWriter to write data to the selected file
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));

				// Write transaction data to the file
				TransactionRecord[] transactionRecords = transactionList.getList();
				for (int i = 0; i < transactionList.getSize(); i++) {
					writer.write("Type: " + transactionRecords[i].getType() +
							" Account: " + transactionRecords[i].getAccount() +
							" Amount: " + twoDigits.format(transactionRecords[i].getAmount()) +
							" Start Bal: " + twoDigits.format(transactionRecords[i].getStartBal()) +
							" End Bal: " + twoDigits.format(transactionRecords[i].getEndBal()) +
							"\n");
				}

				// Close the writer
				writer.close();

				// Inform the user that the export was successful
				JOptionPane.showMessageDialog(null, "Export successful. File saved at: " + fileToSave);
			} catch (IOException ex) {
				// Handle exception, e.g., inform the user that export failed
				JOptionPane.showMessageDialog(null, "Export failed. Please try again.");
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {


	}
}
