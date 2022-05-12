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
	
	public Restaurant(String username, String password, String address, String phone, String email, String name) {
		super(username, password, address, phone, email, name);
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
	
	public Restaurant(String Json) {
		// TODO Read the json init
//		do create restaurant and create products
	}


//	fix it after model changed
	public Restaurant getRestaurantInfo() throws SQLException {
		String[] s = dbService.getRestaurant(this.getUserName());
		return null;

		// call output
	}
	
	public HashMap<String, Integer> getProducts() throws SQLException {
		// TODO Return Products list
		return dbService.getProducts(this.getUserName());
	}
	
	public void checkOrders(){
		// TODO Return Order status
	}

}
