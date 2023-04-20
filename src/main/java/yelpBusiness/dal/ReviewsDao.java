package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yelpBusiness.dal.ReviewsDao;
import yelpBusiness.model.Reviews;
import yelpBusiness.model.Businesses;
import yelpBusiness.model.Users;

public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(ReviewId,UserId,BusinessId,Stars,UsefulCount,FunnyCount,CoolCount,ReviewContent,ReviewDate) " +
			"VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			
			insertStmt.setString(1, review.getReviewId());
			insertStmt.setString(2, review.getUser().getUserId());
			insertStmt.setString(3, review.getBusiness().getBusinessId());
			insertStmt.setDouble(4, review.getStars());
			insertStmt.setInt(5, review.getUsefulCount());
			insertStmt.setInt(6, review.getFunnyCount());
			insertStmt.setInt(7, review.getCoolCount());
			insertStmt.setString(8, review.getReviewContent());
			insertStmt.setTimestamp(9, new Timestamp(review.getReviewDate().getTime()));
			
			insertStmt.executeUpdate();
			
			return review;
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
	
	public Reviews UpdateReviewContent(Reviews review, String newContent) throws SQLException{
		String updateReview = "UPDATE Reviews SET ReviewContent=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setString(1, newContent);
			updateStmt.setString(2, review.getReviewId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			review.setReviewContent(newContent);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Reviews getReviewByReviewId(String reviewId) throws SQLException {
		String selectReview =
			"SELECT ReviewId,UserId,BusinessId,Stars,UsefulCount,FunnyCount,CoolCount,ReviewContent,ReviewDate " +
			"FROM Reviews " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setString(1, reviewId);
			results = selectStmt.executeQuery();
			
			
			UsersDao usersDao = UsersDao.getInstance();
			BusinessesDao businessesDao = BusinessesDao.getInstance();
			
			if(results.next()) {
				String resultReviewId = results.getString("ReviewId");
				String resultUserId = results.getString("UserId");
				String resultBusinessId = results.getString("BusinessId");
				
				double resultStars = results.getDouble("Stars");
				int resultUserfulCount = results.getInt("UsefulCount");
				int resultFunnyCount = results.getInt("FunnyCount");
				int resultCoolCount = results.getInt("CoolCount");
				String resultReviewContent = results.getString("ReviewContent");
				
				Date resultReviewDate =  new Date(results.getTimestamp("ReviewDate").getTime());
				
				Users user = usersDao.getUserByUserId(resultUserId);
				Businesses business = businessesDao.getBusinessById(resultBusinessId);
				
				Reviews review = new Reviews(resultReviewId,resultStars,resultUserfulCount,resultFunnyCount,resultCoolCount,resultReviewContent,
						resultReviewDate,business,user);
				
				return review;
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
	
	public List<Reviews> getReviewsByBusinessName(String businessName) throws SQLException {
	    List<Reviews> reviews = new ArrayList<>();
	    String selectReviews =
	        "SELECT * " +
	        "FROM Reviews Inner Join Business " +
	        "ON Reviews.businessId = Business.businessId " +
	        "Where Business.BusinessName = ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectReviews);
	        selectStmt.setString(1, businessName);
	        results = selectStmt.executeQuery();
	        
	        UsersDao usersDao = UsersDao.getInstance();
			BusinessesDao businessesDao = BusinessesDao.getInstance();
			
	        while(results.next()) {
	            String reviewId = results.getString("ReviewId");
	            String resultUserId = results.getString("UserId");
				String resultBusinessId = results.getString("BusinessId");
	            double starts = results.getDouble("Stars");
	            int usefulCount = results.getInt("UsefulCount");
	            int funnyCount = results.getInt("FunnyCount");
	            int coolCount = results.getInt("CoolCount");
	            String content = results.getString("ReviewContent");
	            Date resultReviewDate =  new Date(results.getTimestamp("ReviewDate").getTime());
				
				Users user = usersDao.getUserByUserId(resultUserId);
				Businesses business = businessesDao.getBusinessById(resultBusinessId);
				
	            
	            Reviews review = new Reviews(reviewId,starts,usefulCount,funnyCount,coolCount,content,
						resultReviewDate,business,user);
	            reviews.add(review);
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
	    return reviews;
	}
	
	public List<Reviews> getUsefulReviewsByBusinessName(String businessName) throws SQLException {
	    List<Reviews> reviews = new ArrayList<>();
	    String selectReviews =
	        "SELECT * " +
	        "FROM Reviews Inner Join Business " +
	        "ON Reviews.businessId = Business.businessId " +
	        "Where Business.BusinessName = ? Order by UsefulCount DESC Limit 5;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	        connection = connectionManager.getConnection();
	        selectStmt = connection.prepareStatement(selectReviews);
	        selectStmt.setString(1, businessName);
	        results = selectStmt.executeQuery();
	        
	        UsersDao usersDao = UsersDao.getInstance();
			BusinessesDao businessesDao = BusinessesDao.getInstance();
			
	        while(results.next()) {
	            String reviewId = results.getString("ReviewId");
	            String resultUserId = results.getString("UserId");
				String resultBusinessId = results.getString("BusinessId");
	            double starts = results.getDouble("Stars");
	            int usefulCount = results.getInt("UsefulCount");
	            int funnyCount = results.getInt("FunnyCount");
	            int coolCount = results.getInt("CoolCount");
	            String content = results.getString("ReviewContent");
	            Date resultReviewDate =  new Date(results.getTimestamp("ReviewDate").getTime());
				
				Users user = usersDao.getUserByUserId(resultUserId);
				Businesses business = businessesDao.getBusinessById(resultBusinessId);
				
	            
	            Reviews review = new Reviews(reviewId,starts,usefulCount,funnyCount,coolCount,content,
						resultReviewDate,business,user);
	            reviews.add(review);
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
	    return reviews;
	}
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setString(1, review.getReviewId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Review instance.
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
