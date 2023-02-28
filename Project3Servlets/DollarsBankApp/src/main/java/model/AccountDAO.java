package model;

public interface AccountDAO {
	
	public int insertAccount();
	public Account getAccount(String userId, String password);
	

}
