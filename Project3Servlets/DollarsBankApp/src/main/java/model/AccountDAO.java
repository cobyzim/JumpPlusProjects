package model;

public interface AccountDAO {
	
	boolean insertAccount(Account account);
	Account getAccount(int userId, String password);
	

}
