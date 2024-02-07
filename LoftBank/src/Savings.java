/**
 * 
 */

/**
 * @author Het Patel
 * Date: December 2023
 * Description: Savings class representing a savings account with additional instance data for withdrawal fee.
 * 
 * Method List:
 * - public Savings() - default constructor
 * - public Savings(Customer accHolder, double balance, double withdrawalFee) - overloaded constructor
 * - public boolean withdraw(double amount) - overridden method to handle withdrawals with fees
 * - public boolean deposit(double amt) - overridden method to handle deposits
 * - public double getWithdrawalFee() - getter for withdrawalFee
 * - public void setWithdrawalFee(double withdrawalFee) - setter for withdrawalFee
 * - public void processString(String record) - processes a record and updates the account information
 * - public String toString() - returns a string representation of the Savings account
 * - public static void main(String[] args) - self-testing main method
 * 
 */
public class Savings extends Account{

	// Additional instance data for fees
	private double withdrawalFee;
	private double minimumBal;
	


	// Default constructor
	public Savings() { 
		// Calls the parent constructor
		super();
		// Initializes the withdrawal fee
		withdrawalFee = 1.25;
	}

	// Overloaded constructor that takes in a customer object and initializes data
	public Savings(Customer accHolder, double balance, double withdrawalFee) {
		// Calls the parent constructor with customer object and balance
		super(accHolder, balance);
		// Initializes the withdrawal fee
		this.withdrawalFee = withdrawalFee;
	}

	// Overridden withdraw method
	public boolean withdraw(double amount) {
		// Checks if balance is sufficient for the withdrawal
		if (getBalance() >= amount) {

			if (getBalance() < 2000) {
				amount += this.withdrawalFee;
			}
			super.withdraw(amount);

			return true;
		} 
		return false;

	}
	
	public boolean deposit(double amt) {
		// Must call the super deposit to actually change the balance
		super.deposit(amt);
		return true;
	}
	
	/**
	 * @return the withdrawalFee
	 */
	public double getWithdrawalFee() {
		return withdrawalFee;
	}

	/**
	 * @param withdrawalFee the withdrawalFee to set
	 */
	public void setWithdrawalFee(double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
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

	@Override
	public String toString() {
		return "Savings [withdrawalFee=" + withdrawalFee + "]";
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Creating a Customer
		Customer customer = new Customer("Tony", "password123", "Tony Campos", "1234 old people road", "987-987-9876");

	    // Creating a Savings account
	    Savings savingsAccount = new Savings(customer, 1000.0, 1.5);

	    // Displaying account information before operations
	    System.out.println("Initial account information:\n" + savingsAccount.toString());

	    // Testing withdrawal with 0 balance
	    if (savingsAccount.withdraw(100.0)) {
	        System.out.println(savingsAccount.getBalance());
	    } else {
	        System.out.println("ur broke, not enough funds");
	    }

	    // Displaying account information after operations
	    System.out.println(savingsAccount.toString());

	}

}
