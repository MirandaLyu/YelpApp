package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import yelpBusiness.model.Businesses;
import yelpBusiness.model.CoffeeShops;

public class CoffeeShopsDao extends BusinessesDao {
	
	

	protected ConnectionManager connectionManager;
	private static CoffeeShopsDao instance = null;

	protected CoffeeShopsDao() {
		super();
		connectionManager = new ConnectionManager();
		
	}

	public static CoffeeShopsDao getInstance() {
		if (instance == null) {
			instance = new CoffeeShopsDao();
		}
		return instance;
	}

	
	public CoffeeShops create(CoffeeShops coffeeShop) throws SQLException {
		
		BusinessesDao businessesDao = new BusinessesDao();
		Businesses businesses = new Businesses(coffeeShop.getBusinessId(), coffeeShop.getBusinessName(), coffeeShop.getAddress(), 
				coffeeShop.getCity(), coffeeShop.getState(), coffeeShop.getPostalCode(), coffeeShop.getCategories(), 
				coffeeShop.getLatitude(), coffeeShop.getLongitude(), coffeeShop.getReviewCount(), coffeeShop.isOpen(), coffeeShop.getBusinessOwner());
		businessesDao.create(businesses);
		
		String id = coffeeShop.getBusinessId();
		
		String insertCoffeeShops = "INSERT INTO CoffeeShop(BusinessId, OutDoorSeating) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStml = null;
	    ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStml = connection.prepareStatement(insertCoffeeShops);

			insertStml.setString(1,id);
			insertStml.setBoolean(2, coffeeShop.isOutDoorSeating());


			insertStml.executeUpdate();
			coffeeShop.setBusinessId(id);
			
			return coffeeShop;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public CoffeeShops delete(CoffeeShops coffeeShops) throws SQLException {
	    String deleteCoffeeShops = "DELETE FROM CoffeeShop WHERE BusinessId=?;";
	    Connection connection = null;
	    PreparedStatement deleteStmt = null;
	    try {
	        connection = connectionManager.getConnection();
	        deleteStmt = connection.prepareStatement(deleteCoffeeShops);
	        deleteStmt.setString(1, coffeeShops.getBusinessId());
	        int affectedRows = deleteStmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("No records available to delete for BusinessId=" + coffeeShops.getBusinessId());
	        }

	        super.delete(coffeeShops);

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
