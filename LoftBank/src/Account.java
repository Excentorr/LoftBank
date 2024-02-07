/**
 * 
 */

import java.text.DecimalFormat;

/**
 * @author Het Patel
 * Date: December 2023
 * Description: 
 *    This class represents a bank account with functionality for deposits, withdrawals, and account information.
 * 
 * Method List:
 * - public Account() - default constructor
 * - public Account(Customer theOwner, double balance) - overloaded constructor
 * - public boolean deposit(double amount) - method to deposit funds into the account
 * - public boolean withdraw(double amount) - method to withdraw funds from the account
 * - public String genAccountNumber() - generates a random 8-digit account number
 * - public String getAccountNumber() - getter for the account number
 * - public void setAccountNumber(String accountNumber) - setter for the account number
 * - public Customer getCustomer() - getter for the customer object
 * - public void setCustomer(Customer customer) - setter for the customer object
 * - public double getBalance() - getter for the account balance
 * - @Override public String toString() - overrides the toString method to provide a string representation of the account
 * - public static void main(String[] args) - self-testing main method
 * 
 */
public class Account {

	/**
	 * Instance Data
	 */
	private double balance;
	private String accountNumber;
	private Customer customer;

	// Default constructor
	public Account() {
		// Initializes the balance
		balance = 0;
		// Generates an account number
		accountNumber = genAccountNumber();
		// Initializes Customer object (you might want to add more details like name, address, etc.)
		customer = new Customer();
	}

	// Overloaded Constructor
	public Account(Customer theOwner, double balance) {
		// Initializes balance
		this.balance = balance;
		// Generates an account number
		this.accountNumber = genAccountNumber();
		// Initializes customer object theOwner
		this.customer = theOwner;
	}

	/**
	 * deposit
	 */
	public boolean deposit (double amount)
	{		
		// Deposits amount into balance
		if (amount > 0) {
			balance += amount;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * withdraw
	 */
	public boolean withdraw (double amount)
	{
		// Checks if the amount can be withdrawn
		if (amount > 0 && amount <= balance) {
			// Updates balance
			balance -= amount;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Generates Random 8 Digit Account Number
	 * If the number is less than 8 digits, it should add 0's to the beginning.
	 */
	public String genAccountNumber() {
		DecimalFormat numF = new DecimalFormat("000000000000"); // creates new decimal format

		long number = (long) (Math.random() * 1000000000000L); // Generate a random 12 digit number

		return numF.format(number); // returns generated number with the decimal format
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accountNumber=" + accountNumber + ", customer=" + customer + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create an account
		Account a = new Account();

		// prints data
		System.out.println(a.toString());

		// declare and intiailizes variable
		double depositAmount = 100.0;

		// check if deposit was successful or not
		if (a.deposit(depositAmount)) {
			System.out.println("Deposit Successful");
		} else {
			System.out.println("Deposit Unsuccessful");
		}

		// Display updated account information using toString
		System.out.println(a.toString());

		// declare and intiailizes variable
		double withdrawAmount = 50.0;

		// check if withdraw was successful or not
		if (a.withdraw(withdrawAmount)) {
			System.out.println("Withdraw Successful");
		} else {
			System.out.println("Withdraw Unsuccessful");
		}

		// Display updated account information using toString
		System.out.println(a.toString() + "\n");

		// Creating a Customer
		Customer customer = new Customer("Tony", "password123", "Tony Campos", "1234 old people road", "987-987-9876");
		Customer customer2 = new Customer("nottony", "pass3245", "Not Tony Campos", "1234 young people drive", "nottony@gmail.com");

		// creates new account using overloaded constructor
		Account b = new Account (customer, 500);

		// displays account information
		System.out.println(b.toString());

		// changes Customer information, and sets it to customer2
		b.setCustomer(customer2);

		// displays account information
		System.out.println(b.toString());
	}

}
