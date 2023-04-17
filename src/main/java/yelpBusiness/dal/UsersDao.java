package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import yelpBusiness.model.Persons;
import yelpBusiness.model.Users;

public class UsersDao extends PersonsDao {

	protected ConnectionManager connectionManager;
	private static UsersDao instance = null;

	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}

	public static UsersDao getInstance() {
		if (instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {

//		public Persons(String userId, String personName, int reviewCount, int usefulCount, int funnyCount, int coolCount,
//				Date yelpingSince) {
//			this.userId = userId;
//			this.personName = personName;
//			this.reviewCount = reviewCount;
//			this.usefulCount = usefulCount;
//			this.funnyCount = funnyCount;
//			this.coolCount = coolCount;
//			this.yelpingSince = yelpingSince;
//		}
		create(new Persons(user.getUserId(), user.getPersonName(), user.getReviewCount(), user.getUsefulCount(),
				user.getFunnyCount(), user.getCoolCount(), user.getYelpingSince()));
		String insertUser = "INSERT INTO Users(UserId,FirstName) VALUES(?,?);";
		Connection connection = null;

		PreparedStatement insertStml = null;
		try {
			connection = connectionManager.getConnection();
			insertStml = connection.prepareStatement(insertUser);

			insertStml.setString(1, user.getUserId());
			insertStml.setString(2, user.getPersonName());
			insertStml.executeUpdate();

			return user;
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
	
	public Users getUserByUserId(String userId) throws SQLException {
		String selectUser = "SELECT Users.UserId,Users.FirstName,ReviewCount,YelpingSince,UsefulCount,FunnyCount,CoolCount " +
							"FROM Users INNER JOIN Persons " +
							"ON Users.UserId=Persons.UserId " +
							"WHERE Users.UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String resultUserId = results.getString("UserId");
				String resultFirstName = results.getString("FirstName");
				int resultReviewCount = results.getInt("ReviewCount");
				Date resultYelpingSince =  new Date(results.getTimestamp("YelpingSince").getTime());
				int resultUsefulCount = results.getInt("UsefulCount");
				int resultFunnyCount = results.getInt("FunnyCount");
				int resultCoolCount = results.getInt("CoolCount");
				
				Users user = new Users(resultUserId, resultFirstName, resultReviewCount, resultUsefulCount, resultFunnyCount, resultCoolCount,resultYelpingSince);
				return user;
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

	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserId());
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