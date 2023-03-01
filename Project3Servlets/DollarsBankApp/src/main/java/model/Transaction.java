package model;

public class Transaction {
	
	private int custUserId;
	private String transaction;
	
	public Transaction(int custUserId, String transaction) {
		super();
		this.custUserId = custUserId;
		this.transaction = transaction;
	}
	
	public Transaction() {
		
	}

	public int getCustUserId() {
		return custUserId;
	}

	public void setCustUserId(int custUserId) {
		this.custUserId = custUserId;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	
}
