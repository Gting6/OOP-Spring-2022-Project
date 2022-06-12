package sql;

import java.sql.*;

public class DBConnection {

	private static Connection conn = null;
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/fooddelivery?"
			+ "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "123456";

	private DBConnection() {

	}

	public static Connection getConnection() {

		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			conn = DBCreation.createDB();
		}
		return conn;
	}
}
