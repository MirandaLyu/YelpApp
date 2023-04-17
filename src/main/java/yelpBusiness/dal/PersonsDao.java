package yelpBusiness.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import yelpBusiness.model.Persons;

public class PersonsDao {

	protected ConnectionManager connectionManager;
	private static PersonsDao instance = null;

	protected PersonsDao() {
		connectionManager = new ConnectionManager();
	}

	public static PersonsDao getInstance() {
		if (instance == null) {
			instance = new PersonsDao();
		}
		return instance;
	}

	public Persons create(Persons person) throws SQLException {

		String insertPerson = "INSERT INTO Persons(UserId,FirstName,ReviewCount, YelpingSince, UsefulCount, FunnyCount, CoolCount) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;

		PreparedStatement insertStml = null;
		try {
			connection = connectionManager.getConnection();
			insertStml = connection.prepareStatement(insertPerson);

			insertStml.setString(1, person.getUserId());
			insertStml.setString(2, person.getPersonName());
			insertStml.setInt(3, person.getReviewCount());
			insertStml.setTimestamp(4, new Timestamp(person.getYelpingSince().getTime()));
			insertStml.setInt(5, person.getUsefulCount());
			insertStml.setInt(6, person.getFunnyCount());
			insertStml.setInt(7, person.getCoolCount());
			insertStml.executeUpdate();

			return person;
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

	public Persons delete(Persons person) throws SQLException {
		String deletePerson = "DELETE FROM Persons WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePerson);
			deleteStmt.setString(1, person.getUserId());
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