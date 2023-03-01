package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnManager;

public class AccountDAOImpl implements AccountDAO {
	
	private Connection conn = ConnManager.getConnection();

	@Override
	public boolean insertAccount(Account account) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO account(custName, custAddress, "
					+ "custPhone, custUserId, custPassword, custInitialDeposit, custBalance) values(?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setString(1, account.getCustName());
			pstmt.setString(2, account.getCustAddress());
			pstmt.setInt(3, account.getCustPhone());
			pstmt.setInt(4, account.getCustUserId());
			pstmt.setString(5, account.getCustPassword());
			pstmt.setDouble(6, account.getCustInitialDeposit());
			pstmt.setDouble(7, account.getCustBalance());
			
			int i = pstmt.executeUpdate();
			
			if (i > 0) {
				return true;
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Account getAccount(int userId, String password) {
		Account account = new Account();
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM account WHERE custUserId=? AND custPassword=?");
			pstmt.setInt(1, userId);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String custName = rs.getString("custName");
				String custAddress = rs.getString("custAddress");
				int custPhone = rs.getInt("custPhone");
				int custId = rs.getInt("custUserId");
				String custPassword = rs.getString("custPassword");
				double custInitialBalance = rs.getDouble("custInitialDeposit");
				double custBalance = rs.getDouble("custBalance");
				
				account.setCustName(custName);
				account.setCustAddress(custAddress);
				account.setCustPhone(custPhone);
				account.setCustUserId(custId);
				account.setCustPassword(custPassword);
				account.setCustInitialDeposit(custInitialBalance);
				account.setCustBalance(custBalance);
			}
			
			return account;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public boolean doesAccountExist(int userId) {
		Account account = new Account();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM account WHERE custUserId = ?");
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String custName = rs.getString("custName");
				String custAddress = rs.getString("custAddress");
				int custPhone = rs.getInt("custPhone");
				int custId = rs.getInt("custUserId");
				String custPassword = rs.getString("custPassword");
				double custInitialBalance = rs.getDouble("custInitialDeposit");
				double custBalance = rs.getDouble("custBalance");
				
				account.setCustName(custName);
				account.setCustAddress(custAddress);
				account.setCustPhone(custPhone);
				account.setCustUserId(custId);
				account.setCustPassword(custPassword);
				account.setCustInitialDeposit(custInitialBalance);
				account.setCustBalance(custBalance);
			}
			
			// Account with this id already present
			if (account.getCustUserId() != 0) {
				return true;
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

}
