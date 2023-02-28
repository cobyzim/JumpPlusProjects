package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import connection.ConnManagerWithProperties;

public class AccountDAOClass {
	
	private Connection conn = ConnManagerWithProperties.getConnection();

	public List<Account> getAllAccounts() {
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM account");
			
			List<Account> accountList = new ArrayList<Account>();
			
			while (rs.next()) {
				String custName = rs.getString("custName");
				String custAddress = rs.getString("custAddress");
				int custPhone = rs.getInt("custPhone");
				int custUserId = rs.getInt("custUserId");
				String custPassword = rs.getString("custPassword");
				double custInitialBalance = rs.getDouble("custInitialDeposit");
				double custBalance = rs.getDouble("custBalance");
				
				Account account = new Account(custName, custAddress, custPhone, custUserId,
						custPassword, custInitialBalance, custBalance);
				accountList.add(account);
			}
			
			return accountList;
		}
		catch(SQLException e) {
			System.out.println("Couldn't retrieve list of accounts from DB");
		}

		// return null if not found
		return null;
	}

	public Account getAccountById(int custUserId) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM account WHERE custUserId = ?");
			pstmt.setInt(1, custUserId);
			
			ResultSet rs = pstmt.executeQuery();
			
			Account account = new Account();
			
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
			System.out.println("Account with id = " + custUserId + "not found.");
			
		}
		
		
		return null;
	}

	public boolean addAccount(Account account) {
		
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

}
