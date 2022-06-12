package model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import sql.DBService;

public class Restaurant extends User {

	public enum coupon_type {
		buy_200_get_90_percent_off, buy_300_get_80_percent_off, save_20_dollars, save_30_dollars, none;
	};

	private String latitude;
	private String longitude;
	private String store_description;
	private String order_despcription;
	private String[] types;
	private String coupon;

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

	public Restaurant(String[] s) {
		super(s[0], s[1], s[4], s[7], s[2], s[3]);
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
		// } catch (SQLException e) {
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

	public ArrayList<String> checkOrders() {
		// TODO Return Order status
		ArrayList<String> valid_orders = new ArrayList<>();
		try {
			ArrayList<String> all_orders = dbService.getOrdersRestaurant(this.getUserName());
			for (String order_id : all_orders) {
				Order order = dbService.getOrder(order_id);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				System.out.println(">");
				System.out.println(now);
				System.out.println(order.getCreate_time());
				System.out.println(order.getDeliver_time());
				if (order.getCreate_time().compareTo(now) < 0 && order.getDeliver_time().compareTo(now) > 0) {
					valid_orders.add(order_id);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		return valid_orders;
	}

	public void setOrderStatusTakeOrder(String order_id) {
		int status = 1;
		dbService.setOrderStatus(status, order_id);
	}

	public void setOrderStatusFinishOrder(String order_id) {
		int status = 2;
		dbService.setOrderStatus(status, order_id);
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

	public Order checkOrderDetail(String order_id) {
		Order order = null;
		try {
			order = dbService.getOrder(order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	public ArrayList<ArrayList<String>> checkItemValuePerOrder(String order_id) {
		ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
		try {
			items = dbService.getOrderItemsDetail(order_id);
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

	public String getCouponFromDB() {
		String coupon = dbService.getCoupon(this.getUserName());
		switch (coupon) {
		case "buy_200_get_90_percent_off":
			coupon = "滿200九折";
			break;
		case "buy_300_get_80_percent_off":
			coupon = "滿300八折";
			break;
		case "save_20_dollars":
			coupon = "省20";
			break;
		case "save_30_dollars":
			coupon = "省30";
			break;
		default:
			coupon = "";
			break;
		}
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public void setCouponInDB(String coupon) {
		switch (coupon) {
		case "滿200九折":
			this.coupon = "buy_200_get_90_percent_off";
			break;
		case "滿300八折":
			this.coupon = "buy_300_get_80_percent_off";
			break;
		case "省20":
			this.coupon = "save_20_dollars";
			break;
		case "省30":
			this.coupon = "save_30_dollars";
			break;
		default:
			this.coupon = "";
			break;
		}

		dbService.addCoupon(this.getUserName(), this.coupon);
	}

	public void setOrderStatus(String order_id) {
		int status = 1;
		dbService.setOrderStatus(status, order_id);
	}

}
