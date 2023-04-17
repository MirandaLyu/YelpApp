package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import yelpBusiness.model.Tips;

public class TipsDao {

	protected ConnectionManager connectionManager;
	private static TipsDao instance = null;

	protected TipsDao() {
		connectionManager = new ConnectionManager();
	}

	public static TipsDao getInstance() {
		if (instance == null) {
			instance = new TipsDao();
		}
		return instance;
	}

	public Tips create(Tips tip) throws SQLException {
		String insertTip = "INSERT INTO Tips(UserId,BusinessId, Content, Date, ComplimentCount) VALUES(?,?,?,?,?);";
		Connection connection = null;

		PreparedStatement insertStml = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStml = connection.prepareStatement(insertTip, Statement.RETURN_GENERATED_KEYS);

			insertStml.setString(1, tip.getUser().getUserId());
			insertStml.setString(2, tip.getBusiness().getBusinessId());
			insertStml.setString(3, tip.getContent());
			insertStml.setTimestamp(4, new Timestamp(tip.getTipDate().getTime()));
			insertStml.setInt(5, tip.getComplimentCount());
			insertStml.executeUpdate();
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStml.getGeneratedKeys();
			int tipId = -1;
			if (resultKey.next()) {
				tipId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			tip.setTipsId(tipId);
			return tip;
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

	public Tips delete(Tips tip) throws SQLException {
		String deleteTip = "DELETE FROM Tips WHERE TipsId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTip);
			deleteStmt.setInt(1, tip.getTipsId());
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