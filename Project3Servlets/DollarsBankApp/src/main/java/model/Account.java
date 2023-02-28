package model;

public class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String custName;
	private String custAddress;
	private int custPhone;
	private int custUserId;
	private String custPassword;
	private double custInitialDeposit;
	private double custBalance;
//	private List<Double> transactions;
//	private List<String> transactionTypes;
	
	public Account(String custName, String custAddress, int custPhone, int custUserId, String custPassword,
			double custInitialDeposit, double custBalance) {
		super();
		this.custName = custName;
		this.custAddress = custAddress;
		this.custPhone = custPhone;
		this.custUserId = custUserId;
		this.custPassword = custPassword;
		this.custInitialDeposit = custInitialDeposit;
		this.custBalance = custBalance;
	}
	
	public Account() {
		
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public int getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(int custPhone) {
		this.custPhone = custPhone;
	}

	public int getCustUserId() {
		return custUserId;
	}

	public void setCustUserId(int custUserId) {
		this.custUserId = custUserId;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public double getCustInitialDeposit() {
		return custInitialDeposit;
	}

	public void setCustInitialDeposit(double custInitialDeposit) {
		this.custInitialDeposit = custInitialDeposit;
	}

	public double getCustBalance() {
		return custBalance;
	}

	public void setCustBalance(double custBalance) {
		this.custBalance = custBalance;
	}

	@Override
	public String toString() {
		return "Account [custName=" + custName + ", custAddress=" + custAddress + ", custPhone=" + custPhone
				+ ", custUserId=" + custUserId + ", custPassword=" + custPassword + ", custInitialDeposit="
				+ custInitialDeposit + ", custBalance=" + custBalance + "]";
	}
	

}
