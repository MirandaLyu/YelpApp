package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yelpBusiness.model.*;

public class OpenTimeDao {

	protected ConnectionManager connectionManager;

	private static OpenTimeDao instance = null;
	protected OpenTimeDao() {
		connectionManager = new ConnectionManager();
	}
	public static OpenTimeDao getInstance() {
		if(instance == null) {
			instance = new OpenTimeDao();
		}
		return instance;
	}
	
	
	public OpenTime create(OpenTime openTime) throws SQLException {
	    String insertCheckIns =
	        "INSERT INTO OpenTime(BusinessId, Hours) " +
	        "VALUES(?,?);";
	    Connection connection = null;
	    PreparedStatement insertStmt = null;
	    ResultSet resultKey = null;
	    try {
	        connection = connectionManager.getConnection();

	        insertStmt = connection.prepareStatement(insertCheckIns);
	        insertStmt.setString(1, openTime.getBusiness().getBusinessId());
	        insertStmt.setString(2, openTime.getOpenHours());

	        insertStmt.executeUpdate();

	        return openTime;
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
	
	public OpenTime delete(OpenTime openTime) throws SQLException {
	    String deleteOpenTime = "DELETE FROM OpenTime WHERE BusinessId=?;";
	    Connection connection = null;
	    PreparedStatement deleteStmt = null;
	    try {
	        connection = connectionManager.getConnection();
	        deleteStmt = connection.prepareStatement(deleteOpenTime);
	        deleteStmt.setString(1, openTime.getBusiness().getBusinessId());
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
