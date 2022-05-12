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
	                   "(username varchar(255) primary key, " +
	                   " password varchar(255), " + 
	                   " address varchar(255), " + 
	                   " email varchar(255), " + 
	                   " name varchar(255)," +
	                   " vip_expire_date Date default null" +
	                   ")";
			
			String create_table_restaurant = "CREATE TABLE restaurants " 
					+ "(username varchar(255) primary key,"
					+ "password varchar(255),"
					+ "email varchar(255),"
					+ "name varchar(255),"
					+ "pos_addr varchar(255),"
					+ "latitude varchar(255),"
					+ "longitude varchar(255),"
					+ "phone varchar(255),"
					+ "store_description varchar(255),"
					+ "order_description varchar(255)"
					+ ")";
			
			String create_table_products = "CREATE TABLE products " +
	                   "( restaurant_name varchar(255),"
	                   + "foreign key(restaurant_name) references restaurants(username),"
	                   + "product_name varchar(255),"
	                   + "price int"
	                   + ")";
		
			String create_table_type_restaurants = "CREATE TABLE type_restaurants " +
	                   "( restaurant_name varchar(255),"
	                   + "foreign key(restaurant_name) references restaurants(username),"
	                   + "type varchar(255)"
	                   + ")";
			
			String create_table_business_times = "CREATE TABLE business_times " +
	                   "( restaurant_name varchar(255),"
	                   + "foreign key(restaurant_name) references restaurants(username),"
	                   + "day int,"
	                   + "which_time varchar(255),"
	                   + "time varchar(255)"
	                   + ")";
			
			String create_table_deliveryman = "CREATE TABLE deliverymen " +
	                   "(username varchar(255) primary key, " +
	                   " password varchar(255), " + 
	                   " address varchar(255), " + 
	                   " email varchar(255), " + 
	                   " name varchar(255) " +
	                   ")";
			
			String create_table_order = "CREATE TABLE orders " +
	                   "(id Int(20), " +			// can be varchar or int
	                   " create_time datetime, " + 
	                   " arrival_time datetime, " + 
	                   " restaurant_name varchar(255), " + 
	                   " foreign key(restaurant_name) references restaurants(username), " +
	                   " member_name varchar(255), " +
	                   " foreign key(member_name) references members(username), " +
	                   " deliveryman_name varchar(255), " +
	                   " foreign key(deliveryman_name) references deliverymen(username)" +
	                   ")";
			
//			String create_table_restaurant_from_json = "CREATE TABLE restaurant_from_json "
//					+ "(id varchar(255) primary key,"
//					+ "password varchar(255),"
//					+ "name varchar(255),"
//					+ "pos_addr varchar(255),"
//					+ "latitude varchar(255),"
//					+ "longitude varchar(255),"
//					+ "phone varchar(255),"
//					+ "store_description varchar(255),"
//					+ "order_description varchar(255)"
//					+ ")";
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(create_table_member);
			stmt.executeUpdate(create_table_restaurant);
			stmt.executeUpdate(create_table_deliveryman);
			stmt.executeUpdate(create_table_products);
			stmt.executeUpdate(create_table_order);
			stmt.executeUpdate(create_table_type_restaurants);
			stmt.executeUpdate(create_table_business_times);
			stmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}


// add restaurant into DB