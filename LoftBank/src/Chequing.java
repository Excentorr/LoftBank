/**
 * 
 */

/**
 * @author Het Patel
 * Date: December 2023
 * Description: Chequing class representing a type of bank account with withdrawal and deposit fees.
 * 
 * Method List:
 * - public Chequing() - default constructor
 * - public Chequing(Customer customer, double balance, double withdrawFee, double depositFee) - overloaded constructor
 * - public boolean withdraw(double amt) - withdraws money from the account with withdrawal fee
 * - public boolean deposit(double amt) - deposits money into the account with deposit fee
 * - public String toString() - returns a string representation of the Chequing account
 * - public double getWithdrawFee() - getter for withdrawal fee
 * - public void setWithdrawFee(double withdrawFee) - setter for withdrawal fee
 * - public double getDepositFee() - getter for deposit fee
 * - public void setDepositFee(double depositFee) - setter for deposit fee
 * - public void processString(String record) - processes a record and updates the account information
 * - public static void main(String[] args) - self-testing main method
 * 
 */
public class Chequing extends Account{

	/**
	 * Instance Data
	 */
	private double withdrawFee;
	private double depositFee;


	/**
	 * Default Constructor
	 */
	public Chequing() {
		super();
		this.withdrawFee = 0.5/100;
		this.depositFee = 1.50;
	}


	// Overloaded constructor that takes in a customer object and initializes data
	public Chequing(Customer customer, double balance, double withdrawFee, double depositFee) {
		super(customer, balance);
		this.withdrawFee = withdrawFee;
		this.depositFee = depositFee;
	}

	public boolean withdraw(double amt) {
		// Checks if balance is sufficient
		if (getBalance() >= amt) {
			// Charges a fee to withdraw
			double feeAmount = amt * withdrawFee;
			// Must call the super withdraw to actually change the balance
			super.withdraw(amt + feeAmount);
			return true;
		} else {
			return false;
		}
	}

	public boolean deposit(double amt) {
		// Charges a fee to deposit
		double feeAmount = depositFee;
		// Must call the super deposit to actually change the balance
		super.deposit(amt - feeAmount);
		return true;
	}

	public String toString()
	{
		return "Chequing Account - Balance: " + getBalance() + ", Withdrawal Fee: " +
				withdrawFee * 100 + "%, Deposit Fee: $" + depositFee;
	}

	/**
	 *  Getters and setters
	 */
	public double getWithdrawFee() {
		return withdrawFee;
	}

	public void setWithdrawFee(double withdrawFee) {
		this.withdrawFee = withdrawFee;
	}

	public double getDepositFee() {
		return depositFee;
	}

	public void setDepositFee(double depositFee) {
		this.depositFee = depositFee;
	}
	
	/**
	 * Method to process my record
	 */
	public void processString(String record) {
		String words[];
		words = record.split("/");
		
		super.setAccountNumber(words[0]);
		super.deposit(Double.parseDouble(words[1]));
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Creating a Customer
		Customer customer = new Customer("Tony", "password123", "Tony Campos", "1234 old people road", "987-987-9876");

		// Creating a Chequing account
		Chequing chequingAccount = new Chequing(customer, 0, 0.005, 1.50);

		// Displaying account information before operations
		System.out.println(chequingAccount.toString() + "\n");

		// Testing withdrawal with 0 balance
		if (chequingAccount.withdraw(100.0)) {
			System.out.println("Withdrawal successful. New balance: " + chequingAccount.getBalance());
		} else {
			System.out.println("Insufficient funds for withdrawal.");
		}

		// Displaying account information after operations
		System.out.println(chequingAccount.toString() + "\n");

		// Testing deposit
		if (chequingAccount.deposit(200.0)) {
			System.out.println("Deposit successful. New balance: " + chequingAccount.getBalance());
		} else {
			System.out.println("Error occurred during deposit.");
		}

		// Displaying account information after operations
		System.out.println(chequingAccount.toString() + "\n");

		// testing withdrawal with 200 deposited
		if (chequingAccount.withdraw(100.0)) {
			System.out.println("Withdrawal successful. New balance: " + chequingAccount.getBalance());
		} else {
			System.out.println("Insufficient funds for withdrawal.");
		}

		// Displaying account information after operations
		System.out.println(chequingAccount.toString() + "\n");
		
		
		/**
         * Setters
         */
		chequingAccount.setWithdrawFee(0.1);
		chequingAccount.setDepositFee(0.5);
        
        /**
         * Getters
         */
        System.out.println("Withdraw fee: " + chequingAccount.getWithdrawFee() + "%");
        System.out.println("Deposit fee: " + chequingAccount.getDepositFee() + "%");
	}

}
