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
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO transactions(custUserId, transaction) "
					+ "values(?, ?)");
			
			pstmt.setInt(1, transaction.getCustUserId());
			pstmt.setString(2, transaction.getTransaction());
			
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
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactions WHERE custUserId = ?");
			
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int custId = rs.getInt("custUserId");
				String transaction = rs.getString("transaction");
				
				trans.setCustUserId(userId);
				trans.setTransaction(transaction);
				
				transList.add(trans);
			}
			
			return transList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
