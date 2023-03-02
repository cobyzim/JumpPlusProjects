package model;

import java.util.List;

public interface TransactionDAO {
	
	boolean insertTransaction(Transaction transaction);
	List<Transaction> getTransactionsById(int userId);
	int getCurrentTransactionNumber(int userId);

}
