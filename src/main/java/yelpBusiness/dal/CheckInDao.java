package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import yelpBusiness.model.*;


public class CheckInDao {

	protected ConnectionManager connectionManager;

	private static CheckInDao instance = null;
	protected CheckInDao() {
		connectionManager = new ConnectionManager();
	}
	public static CheckInDao getInstance() {
		if(instance == null) {
			instance = new CheckInDao();
		}
		return instance;
	}
	
	
	public CheckIns create(CheckIns checkIns) throws SQLException {
	    String insertCheckIns =
	        "INSERT INTO CheckIn(BusinessId, CheckInTime) " +
	        "VALUES(?,?);";
	    Connection connection = null;
	    PreparedStatement insertStmt = null;
	    ResultSet resultKey = null;
	    try {
	        connection = connectionManager.getConnection();
	        insertStmt = connection.prepareStatement(insertCheckIns, Statement.RETURN_GENERATED_KEYS);
	        insertStmt.setString(1, checkIns.getBusiness().getBusinessId());
	        insertStmt.setString(2, checkIns.getCheckInTime());
	        insertStmt.executeUpdate();
	        
	        resultKey = insertStmt.getGeneratedKeys();
	        int checkInId = -1;
	        if(resultKey.next()) {
	        	checkInId = resultKey.getInt(1);
	        } else {
	 
	            throw new SQLException("Unable to retrieve auto-generated key.");
	        }
	        checkIns.setCheckInId(checkInId);
	        return checkIns;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if(connection != null) {
	            connection.close();
	        }
	        if(insertStmt != null) {
	            insertStmt.close();
	        }
	        if(resultKey != null) {
	            resultKey.close();
	        }
	    }
	}
	
	public CheckIns delete(CheckIns checkIns) throws SQLException {
	    String deleteCheckIns = "DELETE FROM CheckIn WHERE CheckInId=?;";
	    Connection connection = null;
	    PreparedStatement deleteStmt = null;
	    try {
	        connection = connectionManager.getConnection();
	        deleteStmt = connection.prepareStatement(deleteCheckIns);
	        deleteStmt.setInt(1, checkIns.getCheckInId());
	        deleteStmt.executeUpdate();

	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        if (connection != null) {
	            connection.close();
	        }
	        if (deleteStmt != null) {
	            deleteStmt.close();
	        }
	    }
	}

}
