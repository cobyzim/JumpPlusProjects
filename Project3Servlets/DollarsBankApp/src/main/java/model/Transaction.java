package model;

public class Transaction {
	
	private int custUserId;
	private String transaction;
	private int transCounter;
	
	public Transaction(int custUserId, String transaction, int transCounter) {
		super();
		this.custUserId = custUserId;
		this.transaction = transaction;
		this.transCounter = transCounter;
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

	public int getTransCounter() {
		return transCounter;
	}

	public void setTransCounter(int transCounter) {
		this.transCounter = transCounter;
	}
	
}
