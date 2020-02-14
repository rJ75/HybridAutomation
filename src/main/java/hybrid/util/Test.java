package hybrid.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class Test {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ebay_data?" +
            	                                   "user=HybridAutomation&password=hybridautomation");
        } catch (Exception ex) {
            // handle the error
        	System.out.println("hello");
        	 System.out.println("SQLException: " + ex.getMessage());
        	    System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
        	    System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
        }
    }
}