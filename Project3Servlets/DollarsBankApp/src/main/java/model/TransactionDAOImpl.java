package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnManager;

public class TransactionDAOImpl implements TransactionDAO {
	
	private Connection conn = ConnManager.getConnection();

	@Override
	public boolean insertTransaction(Transaction transaction) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO transactionsTable(custUserId, transaction, transCounter) "
					+ "values(?, ?, ?)");
			
			pstmt.setInt(1, transaction.getCustUserId());
			pstmt.setString(2, transaction.getTransaction());
			pstmt.setInt(3, transaction.getTransCounter());
			
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
	public List<Transaction> getTransactionsById(int userId) {
		List<Transaction> transList = new ArrayList<>();
		Transaction trans = new Transaction();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactionsTable WHERE custUserId = ?");
			
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int custId = rs.getInt("custUserId");
				String transaction = rs.getString("transaction");
				int transCounter = rs.getInt("transCounter");
				
				trans.setCustUserId(userId);
				trans.setTransaction(transaction);
				trans.setTransCounter(transCounter);
				
				transList.add(trans);
			}
			
			return transList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int getCurrentTransactionNumber(int userId) {		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactionsTable WHERE custUserId = ?");
			
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			int highestNumTransaction = 0;
			while (rs.next()) {
				
				int transCounter = rs.getInt("transCounter");
				
				if (transCounter > highestNumTransaction) {
					highestNumTransaction = transCounter;
				}
			}
			
			return highestNumTransaction;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public List<String> getFiveMostRecentTransactions(int userId) {
		List<String> recentFiveTransactionsList = new ArrayList<>();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactionsTable WHERE custUserId = ? "
					+ "ORDER BY transCounter DESC LIMIT 5");
			
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String transaction = rs.getString("transaction");
				recentFiveTransactionsList.add(transaction);
			}
			
			return recentFiveTransactionsList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
