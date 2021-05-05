package TestPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
		System.out.println("JDBC Test");
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String db = "testdb";
		String driver = "com.mysql.cj.jdbc.Driver";
		String username = "root";
		String password = "password";
		//String f1,f2;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+db,username,password);
			if (!conn.isClosed())
				System.out.println("\nSuccess!!!\n");
			
			
			
			
		}
		catch(Exception e){
			System.out.println("\nNot connected\n");
		}
		finally {
			try {
				if (conn!=null)
				{
					conn.close();
				}
			}
			catch(SQLException e) {}
		}
	}
}
