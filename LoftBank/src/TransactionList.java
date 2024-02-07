import java.io.*;
import javax.swing.JOptionPane;

/**
 * Author: Het Patel
 * Date: November 2023
 * Description: Object to hold vehicle information.
 * 
 * Method List:
 * - VehicleRecord() - default constructor
 * - String getMake() - getter
 * - void setMake(String make) - Setter
 * - String getModel() - getter
 * - void setModel(String model) - Setter
 * - int getYear() - getter
 * - void setYear(int year) - Setter
 * - char getType() - getter
 * - void setType(char type) - Setter
 * - void processString(String record) - processes information
 * - String toString() - converts information to a displayable output
 * - static void main(String[] args) - self-testing
 */

public class TransactionList {
	private TransactionRecord[] list;
	private int size;
	private int maxSize;

	// Default constructor
	public TransactionList() {
		this.maxSize = 25;
		this.list = new TransactionRecord[maxSize];
		this.size = 0;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	// Method to add a record into the list
	// checks if the list is below the maxSize and adds information accordingly.
	public boolean add(TransactionRecord record) {
		// if the size is below maxSize
		if (this.size < this.maxSize) {
			this.size++; // increase by 1
			this.list[this.size - 1] = record; // add to the record
			return true;
		} else {
			return false;
		}
	}

	// Returns the transactionRecord list
	public TransactionRecord[] getList() {
		return list;
	}

	// Method to increase the array size and reenter the old data
	public void increaseArraySize(int newSize) {
		TransactionRecord[] newList = new TransactionRecord[newSize];
		for (int i = 0; i < this.size; i++) {
			newList[i] = this.list[i];
		}
		this.list = newList;
		this.maxSize = newSize;
	}

	// Method to delete a record from the list
	public boolean delete(TransactionRecord record) {
		int index = linearSearch(record.toString());
		if (index == -1) {
			return false;
		}
		for (int i = index; i < this.size - 1; i++) {
			this.list[i] = this.list[i + 1];
		}
		this.size--;
		return true;
	}

	// Method to perform a linear search for a transaction
	public int linearSearch(String info) {
		for (int i = 0; i < size; i++) {
			System.out.println(list[i].toString() + "\t" + info);
			if (list[i].toString().equalsIgnoreCase(info)) {
				return i;
			}
		}
		return -1;
	}

	// Method to sort list alphabetically by make, then alphabetically sorts the rest
	public void insertSort() {
		TransactionRecord curr;
		int i;
		for (int top = 0; top < size; top++) {
			curr = this.list[top];
			for (i = top; (i > 0) && curr.getAmount() < this.list[i - 1].getAmount(); i--) {
				this.list[i] = this.list[i - 1];
			}
			this.list[i] = curr;
		}
	}

	// Method to read transaction records from a file
	public boolean readFile(String fileName, int replace) {
		try {
			int infoSize = FileAccessMethods.getFileSize(fileName);
			if (replace == 0) {
				this.size = 0;
				increaseArraySize(infoSize);
			} else {
				increaseArraySize(infoSize + this.maxSize);
			}
			BufferedReader input2 = new BufferedReader(new FileReader(fileName));
			while (true) {
				String s = input2.readLine();
				if ((!s.equalsIgnoreCase("EOF")) && (!s.equalsIgnoreCase(""))) {
					TransactionRecord temp = new TransactionRecord();
					temp.processString(s);
					add(temp);
				} else {
					break;
				}
			}
			input2.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException | IOException e) {
			return false;
		}
	}

	// Method to create a new file with transaction records
	public void newFile(String fileName) {
		try {
			PrintWriter p = new PrintWriter(new FileWriter(fileName));
			for (int i = 0; i < size; i++) {
				p.println(list[i].toString());
			}
			p.print("EOF");
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Method to display all transaction records as a string
	public String toString() {
		String info = "";
		for (int i = 0; i < size; i++) {
			info += "Type: " + this.list[i].getType() + "Account: " + this.list[i].getAmount() + "Start Bal: "
					+ this.list[i].getStartBal() + "End Bal: " + this.list[i].getEndBal() + "\n";
		}
		return info;
	}

	public static void main(String[] args) {
		TransactionList transactionList = new TransactionList();
		while (true) {
			char command = JOptionPane.showInputDialog(null,
					"a - Add Record\n" +
							"d - Delete Record\n" +
							"l - Linear Search\n" +
							"m - Increase Size\n" +
							"i - Insert Sort List\n" +
							"p - Print List\n" +
							"s - Save File\n" +
							"n - New File\n" +
							"q - Quit\n", "a").charAt(0);

			if (command == 'q') {
				break;
			}

			if (command == 'a') {
				String record = JOptionPane.showInputDialog(null, "Enter transaction record details: ");
				TransactionRecord newRecord = new TransactionRecord(record);
				if (transactionList.add(newRecord)) {
					JOptionPane.showMessageDialog(null, "Record added successfully!");
				} else {
					JOptionPane.showMessageDialog(null, "Record could NOT be added. List is full!");
				}
			} else if (command == 'd') {
				String recordToDelete = JOptionPane.showInputDialog(null, "Enter record to delete: ");
				TransactionRecord record = new TransactionRecord(recordToDelete);
				if (transactionList.delete(record)) {
					JOptionPane.showMessageDialog(null, "Record deleted successfully!");
				} else {
					JOptionPane.showMessageDialog(null, "Record could not be found!");
				}
			} else if (command == 'l') {
				String searchInfo = JOptionPane.showInputDialog(null, "Enter the record to search for: ");
				int location = transactionList.linearSearch(searchInfo);
				if (location != -1) {
					JOptionPane.showMessageDialog(null, "Record location: " + location + "\n" + transactionList.getList()[location]);
				} else {
					JOptionPane.showMessageDialog(null, "Record not found!.");
				}
			} else if (command == 'i') {
				transactionList.insertSort();
				JOptionPane.showMessageDialog(null, "List sorted successfully.");
			} else if (command == 'p') {
				JOptionPane.showMessageDialog(null, "Transaction List:\n" + transactionList.toString());
			} else if (command == 's') {
				String fileName = JOptionPane.showInputDialog(null, "Enter the name of file to save", "testing.txt");
				transactionList.newFile(fileName);
				JOptionPane.showMessageDialog(null, fileName + " has been saved!");
			} else if (command == 'n') {
				String[] options = {"Replace", "Add On"};
				String fileName = JOptionPane.showInputDialog(null, "Enter the name of file to save", "testing.txt");
				int replace = JOptionPane.showOptionDialog(null, "Would you like to replace the current data with data "
						+ "\nfrom the file or add on to it?", "Open File", JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, options, 0);

				if (transactionList.readFile(fileName, replace)) {
					JOptionPane.showMessageDialog(null, fileName + " has been opened and read!");
				} else {
					JOptionPane.showMessageDialog(null,
							fileName + " could not be found OR is corrupted!" + "\nCheck your file or the file name you've entered!");
				}
			} else if (command == 'm') {
				int addedSize = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Please Enter the Value you would like to increase the array by:"));
				transactionList.increaseArraySize(addedSize);
				JOptionPane.showMessageDialog(null, "The New List Size has increased to : " + (addedSize));
			}
		}
	}
}