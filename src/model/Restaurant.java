package model;

import java.sql.SQLException;
import java.util.HashMap;

import sql.DBService;

public class Restaurant extends User{
	
	
	private String latitude;
	private String longitude;
	private String store_description;
	private String order_despcription;
	private String type;
	
	// TODO coupon list
	// TODO json data
	// TODO products list
	// TODO OrderHandler waiting doing done queue
	
	private HashMap<String, Integer> products;
	DBService dbService = new DBService();
	
	public Restaurant() {
		// TODO Init
	}
	
	public Restaurant(String username) {
		super(username);
		// TODO Init
	}
	
	public Restaurant(String username, String password, String address, String phone, String email, String name) {
		super(username, password, address, phone, email, name);
	}
	
	public Restaurant(String [] s) {
		super(s[0], s[1], s[4], s[7],s[2], s[3]);
		this.latitude = s[5];
		this.longitude = s[6];
		this.store_description = s[8];
		this.order_despcription = s[9];
	}
	
	public void setToDB() {

//		since we have add new stuff in json to fill all the blanks, the restaurant needs to be reset to fulfill the new form of input
//		
//		try {
//			
//		dbService.createRestaurant(this);
//			
		//		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
//	public Restaurant(String Json) {
//		// TODO Read the json init
////		do create restaurant and create products
//	}


////	fix it after model changed
//	public Restaurant getRestaurantInfo() throws SQLException {
//		String[] s = dbService.getRestaurant(this.getUserName());
//		return new Restaurant(s);
//
//		// call output
//	}

	public HashMap<String, Integer> getProducts() throws SQLException {
		// TODO Return Products list
		return dbService.getProducts(this.getUserName());
	}
	
	public void checkOrders(){
		// TODO Return Order status
	}

}
