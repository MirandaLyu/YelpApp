package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import yelpBusiness.model.BusinessOwners;
import yelpBusiness.model.Businesses;

public class BusinessesDao {

	protected ConnectionManager connectionManager;
	private static BusinessesDao instance = null;

	protected BusinessesDao() {
		connectionManager = new ConnectionManager();
	}

	public static BusinessesDao getInstance() {
		if (instance == null) {
			instance = new BusinessesDao();
		}
		return instance;
	}

	public Businesses create(Businesses business) throws SQLException {

		String insertBusiness = "INSERT INTO Business(BusinessId, UserId,BusinessName, Address, City, State, PostalCode, "
				+ "Latitude, Longtitude, ReviewCount, IsOpen, Categories) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;

		PreparedStatement insertStml = null;
		try {
			connection = connectionManager.getConnection();
			insertStml = connection.prepareStatement(insertBusiness);

			insertStml.setString(1, business.getBusinessId());
			insertStml.setString(2, business.getBusinessOwner().getUserId());
			insertStml.setString(3, business.getBusinessName());
			insertStml.setString(4, business.getAddress());
			insertStml.setString(5, business.getCity());
			insertStml.setString(6, business.getState());
			insertStml.setString(7, business.getPostalCode());
			insertStml.setDouble(8, business.getLatitude());
			insertStml.setDouble(9, business.getLongitude());
			insertStml.setInt(10, business.getReviewCount());
			insertStml.setBoolean(11, business.isOpen());
			insertStml.setString(12, business.getCategories());

			insertStml.executeUpdate();

			return business;
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
	
	public List<Businesses> getMostReviewedBusiness() throws SQLException {
	    List<Businesses> businesses = new ArrayList<>();
	    String selectBusiness =
	        "SELECT * " +
	        "FROM Business " +
	        "Order by ReviewCount DESC Limit 5;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	    	connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBusiness);
			results = selectStmt.executeQuery();
	        
			BusinessOwnersDao businessOnwersDao = BusinessOwnersDao.getInstance();
			
	        while(results.next()) {
	        	String resultBusinessId = results.getString("BusinessId");
				String resultUserId = results.getString("UserId");
				String resultBusinessName = results.getString("BusinessName");
				String resultAddress = results.getString("Address");
				String resultCity = results.getString("City");
				String resultState = results.getString("State");
				String resultPostalCode = results.getString("PostalCode");
				double resultLatitude = results.getDouble("Latitude");
				double resultLongtitude = results.getDouble("Longtitude");
				int resultReviewCount = results.getInt("ReviewCount");
				String resultCategories = results.getString("Categories");
				boolean resultIsOpen = results.getBoolean("IsOpen");
				
				BusinessOwners businessOwner = businessOnwersDao.getBusinessOwnerById(resultUserId);
				
				Businesses business = new Businesses(resultBusinessId,resultBusinessName,resultAddress,
						resultCity,resultState,resultPostalCode,resultCategories,resultLatitude,resultLongtitude,
						resultReviewCount,resultIsOpen,businessOwner);
				businesses.add(business);
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
	    return businesses;
	}
	
	public Businesses getBusinessById(String businessId) throws SQLException {
		//
		String selectBusiness =
				"SELECT BusinessId,UserId,BusinessName,Address,City,State,PostalCode,Latitude,Longtitude,ReviewCount,IsOpen,Categories " +
				"FROM Business " +
				"WHERE BusinessId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBusiness);
			selectStmt.setString(1, businessId);
			results = selectStmt.executeQuery();
			
			BusinessOwnersDao businessOnwersDao = BusinessOwnersDao.getInstance();
			
			if(results.next()) {
				
				String resultBusinessId = results.getString("BusinessId");
				String resultUserId = results.getString("UserId");
				String resultBusinessName = results.getString("BusinessName");
				String resultAddress = results.getString("Address");
				String resultCity = results.getString("City");
				String resultState = results.getString("State");
				String resultPostalCode = results.getString("PostalCode");
				double resultLatitude = results.getDouble("Latitude");
				double resultLongtitude = results.getDouble("Longtitude");
				int resultReviewCount = results.getInt("ReviewCount");
				String resultCategories = results.getString("Categories");
				boolean resultIsOpen = results.getBoolean("IsOpen");
				
				BusinessOwners businessOwner = businessOnwersDao.getBusinessOwnerById(resultUserId);
				
				Businesses business = new Businesses(resultBusinessId,resultBusinessName,resultAddress,
						resultCity,resultState,resultPostalCode,resultCategories,resultLatitude,resultLongtitude,
						resultReviewCount,resultIsOpen,businessOwner);
				
				return business;
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

	public Businesses delete(Businesses business) throws SQLException {
		String deleteBusiness = "DELETE FROM Business WHERE BusinessId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBusiness);
			deleteStmt.setString(1, business.getBusinessId());
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