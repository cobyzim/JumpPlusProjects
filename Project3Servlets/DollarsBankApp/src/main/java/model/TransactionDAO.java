package model;

public interface TransactionDAO {
	
	boolean insertTransaction(Transaction transaction);
	boolean getTransactionsById(int userId);
	

}
