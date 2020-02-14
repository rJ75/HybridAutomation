package hybrid.datahandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hybrid.HybridException;

public class DBHandler {
	
	public static Connection connection = null;
	public static ResultSet resultset = null;
	public static Statement stmt = null;
	
	
	public static void connect(String driverClass, String connectionString) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebay_data","root","automationserver");
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	public static void connect() throws HybridException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance ();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/ebay_data?" + "user=HybridAutomation&password=hybridautomation");
			if(connection != null) {
				System.out.println("Database connection Successful!");
			}
			stmt=connection.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from object_manager");  
			while(rs.next())  {
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
			}
		}  catch (Exception e) {
            System.err.println("DataBase Connection Failed");

            System.err.println("SQLException : " + e.getMessage());
            e.printStackTrace();
            throw new HybridException("DataBase Connection Failed");
        } finally {
            if (connection != null) {
                try {
                	connection.close();
                    System.out.println("Database Connection Terminated");
                } catch (Exception e) {}
            }
            if (stmt != null) {
    	        try {
    	            stmt.close();
    	        } catch (SQLException sqlEx) { } // ignore

    	        stmt = null;
    	    }
        }
	}
	
	 
}
