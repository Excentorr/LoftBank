/**
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Het Patel
 * Date: November 2023
 * Description: Graphical user interface for banking transactions, including deposit, withdrawal, and account information.
 * 
 * Method List:
 * - public TransactionMenuGUI(Savings s, Chequing c) - constructor for the TransactionMenuGUI class
 * - public void actionPerformed(ActionEvent e) - ActionListener method
 * - public static void main(String[] args) - main method for testing
 * 
 */
public class FileAccessMethods {

	/**
	 * Gets file size
	 * @param filename
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static int getFileSize(String filename) throws IOException {
		int size = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null && !line.equals("EOF")) {
				size++;
			}
		}

		return size;
	}

	/**
	 * checks if a string value is found in a file
	 */
	public static int check(String check, int index, String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			int lineIndex = 0;

			while (true) {
				String line = reader.readLine();

				if (line == null || line.equals("EOF")) {
					break; // Exit the loop when reaching the end of the file
				}

				String[] info = line.split("/");
				if (info.length > index && check.equals(info[index])) {
					return lineIndex;
				}

				lineIndex++;
			}

			return -1;
		} catch (IOException e) {
			return -1;
		}
	}

	public static String readPassword(int userIndex, String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));

			// Read lines until the specified user index
			for (int i = 0; i < userIndex; i++) {
				reader.readLine();
			}

			// Read the line for the specified user index and split it to get the password
			String line = reader.readLine();
			String[] userInfo = line.split("/");
			if (userInfo.length > 1) {
				return userInfo[1]; // Assuming password is stored at index 1
			} else {
				return null; // Handle the case where password is not found
			}

		} catch (IOException e) {
			return null; // Handle IO exception
		}
	}

	public static void getDataFromAccount(Savings savings, Chequing chequing, Customer c, int index) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader("CustomerInfo.txt"));

			for (int i = 0; i < index; i++) {
				reader.readLine();
			}

			c.processString(reader.readLine());

			reader.close();

			reader = new BufferedReader(new FileReader("BalanceInfo.txt"));


			for (int i = 0; i < index; i++) {
				reader.readLine();
				reader.readLine();
			}

			chequing.processString(reader.readLine());
			savings.processString(reader.readLine());

			chequing.setCustomer(c);
			savings.setCustomer(c);

			reader.close();
		}
		catch (IOException e) {

		}

	}

	/**
	 * Saves all the user's data to the file.
	 */
	public static boolean saveUserData(Savings savings, Chequing chequing) {
		try {
			// Read the original lines without the last line "EOF" from CustomerInfo.txt
			String[] originalCustomerLines = readFileToArray("CustomerInfo.txt");

			// Find the line number for the specified username
			int customerLineNumber = check(savings.getCustomer().getUsername(), 0, "CustomerInfo.txt");

			if (customerLineNumber != -1) {
				// Update the customer information in the array
				originalCustomerLines[customerLineNumber] = String.format("%s/%s/%s/%s/%s",
						savings.getCustomer().getUsername(), savings.getCustomer().getPassword(), savings.getCustomer().getName(), savings.getCustomer().getAddress(), savings.getCustomer().getPhoneNumber());

				// Write the modified customer lines back to CustomerInfo.txt
				writeArrayToFile(originalCustomerLines, "CustomerInfo.txt");
			} else {
				// Append new customer information at the bottom
				String newCustomerLine = String.format("%s/%s/%s/%s/%s",
						savings.getCustomer().getUsername(), savings.getCustomer().getPassword(), savings.getCustomer().getName(), savings.getCustomer().getAddress(), savings.getCustomer().getPhoneNumber());
				insert(newCustomerLine, "CustomerInfo.txt");
			}

			// Read the original lines without the last line "EOF" from BalanceInfo.txt
			String[] originalBalanceLines = readFileToArray("BalanceInfo.txt");

			// Find the line numbers for the specified account numbers
			int savingsLineNumber = check(savings.getAccountNumber(), 0, "BalanceInfo.txt");
			int chequingLineNumber = check(chequing.getAccountNumber(), 0, "BalanceInfo.txt");

			if (savingsLineNumber != -1 && chequingLineNumber != -1) {
				// Update the savings and chequing balances in the array
				originalBalanceLines[savingsLineNumber] = String.format("%s/%s",
						savings.getAccountNumber(), savings.getBalance());
				originalBalanceLines[chequingLineNumber] = String.format("%s/%s",
						chequing.getAccountNumber(), chequing.getBalance());

				// Write the modified balance lines back to BalanceInfo.txt
				writeArrayToFile(originalBalanceLines, "BalanceInfo.txt");
			} else {
				// Append new balance information at the bottom
				chequing.setAccountNumber(savings.getAccountNumber());

				String newSavingsLine = String.format("%s/%s", savings.getAccountNumber(), savings.getBalance());
				String newChequingLine = String.format("%s/%s", chequing.getAccountNumber(), chequing.getBalance());
				insert(newSavingsLine, "BalanceInfo.txt");
				insert(newChequingLine, "BalanceInfo.txt");
			}

			return true;

		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Inserts a new record into the file, rewriting the file with "EOF" at the last line
	 */
	public static boolean insert(String record, String filename) {
		try {
			// Read the original lines without the last line "EOF"
			String[] originalLines = readFileToArray(filename);

			// Append the new record to the end of the lines
			String[] newLines = new String[originalLines.length + 1];
			System.arraycopy(originalLines, 0, newLines, 0, originalLines.length);
			newLines[originalLines.length] = record;

			// Write the modified lines back to the file with "EOF" at the end
			writeArrayToFile(newLines, filename);

			return true;

		} catch (IOException e) {
			return false;
		}
	}

	public static void readAccountInfo(int index, Customer customer, Savings savings, Chequing chequing) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("CustomerList.txt"));
			for (int i = 0; i < index; i++) {
				reader.readLine();
			}

			customer.processString(reader.readLine());

			reader.close();

			reader = new BufferedReader(new FileReader("BalanceInfo.txt"));

			for (int i = 0; i < index; i++) {
				reader.readLine();
				reader.readLine();
			}

			chequing.processString(reader.readLine());
			savings.processString(reader.readLine());
			reader.close();

		} catch (IOException e) {

		}
	}

	/**
	 * Method to make each line in the file into a different index within an array
	 */
	private static String[] readFileToArray(String filename) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			int size = getFileSize(filename);
			String[] lines = new String[size];

			for (int i = 0; i < size; i++) {
				lines[i] = reader.readLine();
			}
			return lines;
		}
	}

	/**
	 * Method to write each index within an array into a new line inside a file.
	 */
	private static void writeArrayToFile(String[] lines, String filename) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			for (int i = 0; i < lines.length; i++) {
				writer.write(lines[i]);
				writer.newLine();
			}
			writer.write("EOF");
		}
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
