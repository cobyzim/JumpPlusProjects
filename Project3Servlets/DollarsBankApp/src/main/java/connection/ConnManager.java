package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnManager {
	
	// set connection to null right away
	private static Connection connection = null;
	
	private static final String URL = "jdbc:mysql://localhost:3306/project3?serverTimezone=EST5EDT";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Root@123";
	
	private static void makeConnection() {
		
		// finally, establish connection
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Established connection");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// will establish connection if not already made and return connection
	// object for singleton design
	public static Connection getConnection() {
		
		if (connection == null) {
			makeConnection();
		}
		
		return connection;
	}
	
//	public static void main(String[] args) {
//		Connection conn = ConnManagerWithProperties.getConnection();
//		System.out.println("Made Connection");
//		
//		try {
//			conn.close();
//			System.out.println("Closed connection");
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	
	
	
	
	
}
