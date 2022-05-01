package sql;

import java.sql.*;

public class DBCreation {
	
	private static Connection conn = null;
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    private static String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASS = "123456";
	
	
	public static Connection createDB() {
		
		try {
			Class.forName(JDBC_DRIVER);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
			String create_database = "CREATE DATABASE fooddelivery";
			stmt.executeUpdate(create_database);
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		DB_URL = "jdbc:mysql://localhost:3306/fooddelivery?"
	    		+ "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		
		try {	
			
			String create_table_member = "CREATE TABLE members " + 
	                   "(username varchar(255), " +
	                   " password varchar(255), " + 
	                   " address varchar(255), " + 
	                   " email varchar(255), " + 
	                   " name varchar(255) primary key," +
	                   " vip Boolean," +
	                   " vip_expire_date Date" +
	                   ")";
			
			String create_table_restaurant = "CREATE TABLE restaurants " +
	                   "(username varchar(255), " +
	                   " password varchar(255), " + 
	                   " address varchar(255), " + 
	                   " email varchar(255), " + 
	                   " name varchar(255) primary key," +
	                   " vip_expire_date Date" +
	                   ")";
			
			String create_table_products = "CREATE TABLE products " +
	                   "( restaurant_name varchar(255),"
	                   + "foreign key(restaurant_name) references restaurants(name),"
	                   + "product_name varchar(255),"
	                   + "price int"
	                   + ")";
			
			String create_table_deliveryman = "CREATE TABLE deliverymen " +
	                   "(username varchar(255), " +
	                   " password varchar(255), " + 
	                   " address varchar(255), " + 
	                   " email varchar(255), " + 
	                   " name varchar(255) primary key" +
	                   ")";
			
			String create_table_order = "CREATE TABLE orders " +
	                   "(id varchar(255), " +			// can be varchar or int
	                   " create_time datetime, " + 
	                   " arrival_time datetime, " + 
	                   " restaurant_name varchar(255), " + 
	                   " foreign key(restaurant_name) references restaurants(name), " +
	                   " member_name varchar(255), " +
	                   " foreign key(member_name) references members(name), " +
	                   " deliveryman_name varchar(255), " +
	                   " foreign key(deliveryman_name) references deliverymen(name)" +
	                   ")";
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(create_table_member);
			stmt.executeUpdate(create_table_restaurant);
			stmt.executeUpdate(create_table_deliveryman);
			stmt.executeUpdate(create_table_products);
			stmt.executeUpdate(create_table_order);
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
