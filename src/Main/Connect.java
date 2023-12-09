package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {

	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String HOST = "localhost:3306";
	private final String DATABASE = "njuice";
	private final String URL = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private Connection connection;
	private Statement statement;
	public PreparedStatement preparedStatement;
	private ResultSet rs;


	public Connect() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PreparedStatement setPreparedStatement(String query) {
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return preparedStatement;
	}

	public ResultSet executeQuery() {
		try {
			rs = preparedStatement.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}

	public void executeUpdate() {
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet runQuery(String query) {
		try {
			rs = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public void runUpdate(String query) {
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}