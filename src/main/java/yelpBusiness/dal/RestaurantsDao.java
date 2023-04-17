package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yelpBusiness.model.Restaurants;
import yelpBusiness.model.BusinessOwners;
import yelpBusiness.model.Businesses;

public class RestaurantsDao extends BusinessesDao{
	protected ConnectionManager connectionManager;

	private static RestaurantsDao instance = null;
	
	protected RestaurantsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static RestaurantsDao getInstance() {
		if(instance == null) {
			instance = new RestaurantsDao();
		}
		return instance;
	}
	
	public Restaurants create(Restaurants restaurant) throws SQLException {
		create(new Businesses(restaurant.getBusinessId(),restaurant.getBusinessName(),restaurant.getAddress(),
				restaurant.getCity(),restaurant.getState(),restaurant.getPostalCode(),restaurant.getCategories(),
				restaurant.getLatitude(),restaurant.getLongitude(),restaurant.getReviewCount(),restaurant.isOpen(),
				restaurant.getBusinessOwner()));
		
		
		String insertRestaurant =
			"INSERT INTO Restaurants(BusinessId,RestaurantsTakeOut) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Restaurants has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertRestaurant);
			insertStmt.setString(1, restaurant.getBusinessId());
			insertStmt.setBoolean(2, restaurant.isCanTakeOut());
			
			insertStmt.executeUpdate();
			
			return restaurant;
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
	
	public Restaurants getRestaurantById(String businessId) throws SQLException {
		String selectRestaurant =
			"SELECT Restaurants.BusinessId,Restaurants.RestaurantsTakeOut,UserId,BusinessName,Address,City,State,PostalCode,Latitude,Longtitude,ReviewCount,IsOpen,Categories " +
			"FROM Restaurants INNER JOIN Business " +
			"ON Restaurants.BusinessId = Business.BusinessId " +
			"WHERE Restaurants.BusinessId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setString(1, businessId);
			results = selectStmt.executeQuery();
			
			BusinessOwnersDao businessOnwersDao = BusinessOwnersDao.getInstance();
			
			if(results.next()) {
				
				String resultBusinessId = results.getString("BusinessId");
				String resultUserId = results.getString("UserId");
				boolean resultRestaurantsTakeOut = results.getBoolean("RestaurantsTakeOut");
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
				
				Restaurants restaurant = new Restaurants(resultBusinessId,resultBusinessName,resultAddress,
						resultCity,resultState,resultPostalCode,resultCategories,resultLatitude,resultLongtitude,
						resultReviewCount,resultIsOpen,resultRestaurantsTakeOut,businessOwner);
				
				return restaurant;
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
	
	public Restaurants delete(Restaurants restaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM Restaurants WHERE BusinessId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setString(1, restaurant.getBusinessId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for restaurantId=" + restaurant.getBusinessId());
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}