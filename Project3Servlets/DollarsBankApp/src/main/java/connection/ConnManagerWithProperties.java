package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnManagerWithProperties {
	
	// set connection to null right away
	private static Connection connection = null;
	
	private static void makeConnection() {
		
		// Properties will be used to access props file and read its values
		Properties props = new Properties();
		
		// use the FileInputStream to load in the values from the file to props
		try {
			props.load(new FileInputStream("resources/config.properties"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		// save the values to the variables, use the same name as what is written
		// in the file to get the values
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
		// finally, establish connection
		try {
			connection = DriverManager.getConnection(url, username, password);
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
