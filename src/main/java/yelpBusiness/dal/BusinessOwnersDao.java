package yelpBusiness.dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import yelpBusiness.model.BusinessOwners;
import yelpBusiness.model.Persons;

public class BusinessOwnersDao extends PersonsDao {

	protected ConnectionManager connectionManager;
	private static BusinessOwnersDao instance = null;

	protected BusinessOwnersDao() {
		connectionManager = new ConnectionManager();
	}

	public static BusinessOwnersDao getInstance() {
		if (instance == null) {
			instance = new BusinessOwnersDao();
		}
		return instance;
	}

	public BusinessOwners create(BusinessOwners businessOwner) throws SQLException {

		create(new Persons(businessOwner.getUserId(), businessOwner.getPersonName(), businessOwner.getReviewCount(),
				businessOwner.getUsefulCount(), businessOwner.getFunnyCount(), businessOwner.getCoolCount(),
				businessOwner.getYelpingSince()));

		String insertBusinessOwner = "INSERT INTO BusinessOwners(UserId) VALUES(?);";
		Connection connection = null;

		PreparedStatement insertStml = null;
		try {
			connection = connectionManager.getConnection();
			insertStml = connection.prepareStatement(insertBusinessOwner);

			insertStml.setString(1, businessOwner.getUserId());
			insertStml.executeUpdate();

			return businessOwner;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStml != null) {
				insertStml.close();
			}
		}
	}
	
	public BusinessOwners getBusinessOwnerById(String userId) throws SQLException{
		String selectOwner = "SELECT BusinessOwners.UserId,FirstName,ReviewCount,YelpingSince,UsefulCount,FunnyCount,CoolCount " +
	                         "FROM BusinessOwners INNER JOIN Persons " +
	                         "ON BusinessOwners.UserId=Persons.UserId " +
	                         "WHERE BusinessOwners.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOwner);
			selectStmt.setString(1, userId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				
				String resultUserId = results.getString("UserId");
				String resultFirstName = results.getString("FirstName");
				int resultReviewCount = results.getInt("ReviewCount");
				int resultUserfulCount = results.getInt("UsefulCount");
				int resultFunnyCount = results.getInt("FunnyCount");
				int resultCoolCount = results.getInt("CoolCount");
				Date resultYelpingSince =  new Date(results.getTimestamp("YelpingSince").getTime());
				
				BusinessOwners businessOwner = new BusinessOwners(resultUserId,resultFirstName,
						resultReviewCount,resultUserfulCount,resultFunnyCount,resultCoolCount,resultYelpingSince);
				
				return businessOwner;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public BusinessOwners delete(BusinessOwners businessOwner) throws SQLException {
		String deleteBusinessOwner = "DELETE FROM BusinessOwners WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBusinessOwner);
			deleteStmt.setString(1, businessOwner.getUserId());
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