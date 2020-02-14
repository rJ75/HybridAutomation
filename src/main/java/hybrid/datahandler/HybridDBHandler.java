package hybrid.datahandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hybrid.HybridException;

public class HybridDBHandler {
	
	public Connection connection = null;
	public ResultSet resultSet = null;
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	
	public void connect(String driverClass, String url, String username, String password) throws HybridException {
		
		try {
			Class.forName(driverClass).newInstance ();
			String connectionString = String.format("%s?user=%s&password=%s", driverClass, url, password);
			connection = DriverManager.getConnection(connectionString);
		} catch (Exception e) {
			throw new HybridException(e, "SQLException : DataBase Connection Failed!");
		}  
	}
	
	public void close() throws HybridException {
		
		 if (statement != null) {
 	        try {
 	        	statement.close();
 	        } catch (SQLException e) {
 				System.err.println("DataBase Connection Failed");
 				System.err.println("SQLException : " + e.getMessage());
 				e.printStackTrace();
 				throw new HybridException("DataBase eror while closing statement!");
 	        }
 	       statement = null;
 	    }
		 if (connection != null) {
             try {
             	connection.close();
                 System.out.println("Database Connection Terminated");
             } catch (Exception e) {
  				throw new HybridException(e, "SQLException : DataBase eror while closing connection!");
             }
             connection = null;
         }
	}
	
	public ResultSet executeQuery(String query) throws HybridException {
		
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setQueryTimeout(30);
			resultSet = statement.executeQuery(query);
			if(resultSet != null) {
				return resultSet;
			} else {
				throw new Exception("Result set is null!");
			}
		} catch (Exception e) {
			throw new HybridException(e, "SQLException : DataBase error while executiong query!");
		} 
	}
	
	public boolean updateQuery(String query) throws HybridException {
		
		boolean result = false;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			result = true;
		} catch (Exception e) {
			throw new HybridException(e, "SQLException : DataBase error in DBHandler.updateQuery() !");
		} 
		return result;
	}

	
}
