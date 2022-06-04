package model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import sql.DBService;

public class Restaurant extends User{
	
	public enum coupon_type {
		buy_200_get_90_percent_off, buy_300_get_80_percent_off, save_20_dollars, save_30_dollars, none; 
	};
	
	
	private String latitude;
	private String longitude;
	private String store_description;
	private String order_despcription;
	private String[] types;
	private String coupon;
	// TODO coupon list
	// TODO Business time
	// TODO json data
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
		this.setLatitude(s[5]);
		this.setLongitude(s[6]);
		this.setStore_description(s[8]);
		this.setOrder_despcription(s[9]);
		this.setCoupon(s[10]);
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

	public Restaurant getRestaurantInfo() throws SQLException {
		// TODO view page
		try {
			Restaurant tmp = new Restaurant(dbService.getRestaurant(this.getUserName()));
			tmp.setTypes(dbService.getTypeRestaurant(this.getUserName()));
			return tmp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Restaurant getRestaurantInfoByName() throws SQLException {
		// TODO view page
		try {
			Restaurant tmp = new Restaurant(dbService.getRestaurantByName(this.getName()));
			tmp.setTypes(dbService.getTypeRestaurant(this.getUserName()));
			return tmp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	
	public ArrayList<String> checkOrders(){
		// TODO Return Order status
		ArrayList<String> valid_orders = new ArrayList<>();
		try {
			ArrayList<String> all_orders = dbService.getOrdersRestaurant(this.getUserName());
			for(String order_id : all_orders) {
				Order order = dbService.getOrder(order_id);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if(order.getCreate_time().compareTo(now) < 0 && order.getDeliver_time().compareTo(now) > 0) {
					valid_orders.add(order_id);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid_orders;
	}
	
	public ArrayList<String> checkItemPerOrder(String order_id) {
		ArrayList<String> items = new ArrayList<>();
		try {
			items = dbService.getOrderItems(order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getStore_description() {
		return store_description;
	}

	public void setStore_description(String store_description) {
		this.store_description = store_description;
	}
	
	public String getOrder_despcription() {
		return order_despcription;
	}

	public void setOrder_despcription(String order_despcription) {
		this.order_despcription = order_despcription;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public String getCoupon() {
		return dbService.getCoupon(this.getUserName());
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
		dbService.addCoupon(this.getUserName(), this.coupon);
	}
	
	public void setOrderStatus(String order_id) {
		int status = 1;
		dbService.setOrderStatus(status, order_id);
	}

}
