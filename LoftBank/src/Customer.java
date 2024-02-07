/**
 * 
 */

/**
 * @author Het Patel
 * Date: December 2023
 * Description: Customer class representing a bank customer with username, password, name, address, and phone number.
 * 
 * Method List:
 * - public Customer() - default constructor
 * - public Customer(String username, String password, String name, String address, String phone) - overloaded constructor
 * - public String getName() - getter for name
 * - public void setName(String name) - setter for name
 * - public String getAddress() - getter for address
 * - public void setAddress(String address) - setter for address
 * - public String getPhoneNumber() - getter for phone number
 * - public void setPhoneNumber(String phoneNumber) - setter for phone number
 * - public String getUsername() - getter for username
 * - public void setUsername(String username) - setter for username
 * - public String getPassword() - getter for password
 * - public void setPassword(String password) - setter for password
 * - public void processString(String record) - processes a record and updates the customer information
 * - public String toString() - returns a string representation of the Customer
 * - public static void main(String[] args) - self-testing main method
 * 
 */
class Customer {
	
	/**
	 * Instance Data
	 */
	private String username, password, name, address, phoneNumber;
	
	/**
	 * Constructor
	 */
	public Customer() {
		this.username = "";
		this.password = "";
		this.name = "";
		this.address = "";
		this.phoneNumber = "";
	}
	
	/**
	 * Overloaded Constructor
	 */
	public Customer(String username, String password, String name, String address, String phone) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phoneNumber = phone;
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method to process my record
	 */
	public void processString(String record) {
		String words[];
		words = record.split("/");
		
		this.username = words[0];
		this.password = words[1];
		this.name = words[2];
		this.address = words[3];
		this.phoneNumber = words[4];
		
	}
	

	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    // creates 2 customers
	    Customer customer = new Customer();
	    Customer customerOverloaded = new Customer("Tony", "password123", "Tony Campos", "1234 old people road", "987-987-9876");

	    System.out.println(customer.toString());
	    System.out.println("Overloaded Customer = " + customerOverloaded.toString());

	    // Data to process
	    String customerData = "Tony/123 young road/123-123-1233";
	    customer.processString(customerData);

	    System.out.println(customer.toString() + "\n");

	    /**
	     * Setters
	     */
	    customer.setName("Not Tony");
	    customer.setAddress("4567 old road");
	    customer.setPhoneNumber("905-123-1234");
	    customer.setUsername("asdfasdf");
	    customer.setPassword("asdasdf32453523534fasdf");

	    /**
	     * Getters
	     */
	    System.out.println("Name: " + customer.getName());
	    System.out.println("Address: " + customer.getAddress());
	    System.out.println("Phone Number: " + customer.getPhoneNumber());
	    System.out.println("Username: " + customer.getUsername());
	    System.out.println("Password: " + customer.getPassword());

        
	}

}
