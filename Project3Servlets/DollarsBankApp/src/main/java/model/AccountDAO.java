package model;

public interface AccountDAO {
	
	boolean insertAccount(Account account);
	Account getAccount(int userId, String password);
	boolean doesAccountExist(int userId);
	Account getAccountById(int userId);
	boolean updateBalance(double total, int custId);
	

}
