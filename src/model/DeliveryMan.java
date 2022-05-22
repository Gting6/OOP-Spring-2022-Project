package model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import sql.DBService;

public class DeliveryMan extends User {
	
	DBService dbService = new DBService();
	
	public DeliveryMan() {
		
	}
	
	public DeliveryMan(String username) {
		super(username);
	}
	
	public DeliveryMan(String username, String password, String address, String phone, String email, String name) {
		super(username, password, address, phone, email, name);
	}
	
	public DeliveryMan(String [] s) {
		super(s[0], s[1], s[2], s[3], s[4], s[5]);
	}
	
	public void setToDB() {
		try {
			dbService.createDeliveryMan(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DeliveryMan getDeliveryManInfo() throws SQLException {
		// TODO view page
		try {
			return new DeliveryMan(dbService.getDeliveryMan(this.getUserName()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
//	get orders with no deliveryman.
	public ArrayList<String> getNoDeliverymanOrders() {
//		add google search distance in the future
		ArrayList<String> orders_can_deliver = new ArrayList<>();
		try {
			orders_can_deliver = dbService.getOrdersNoDeliveryman();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders_can_deliver;
	}
	
//	get orders belong to him and in time to send.
	public ArrayList<String> getOrdersToSend() {
//		add google search distance in the future
		ArrayList<String> orders_to_deliver = new ArrayList<>();
		try {
			ArrayList<String> all_orders = dbService.getOrdersDeliveryman(this.getUserName());
			for(String order_id : all_orders) {
				Order order = dbService.getOrder(order_id);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if(order.getCreate_time().compareTo(now) < 0 && order.getArrival_time().compareTo(now) > 0) {
					orders_to_deliver.add(order_id);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders_to_deliver;
	}
	
	public String getOrderAddress(String order_id) {
		
		String address = "";
		
		try {
			Order order = dbService.getOrder(order_id);
			Member member = dbService.getMember(order.getConsumer_id());
			address = member.getAddress();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return address;
	}
	
	public Order getSingleOrder(String order_id) {
		Order order = null;
		try {
			order = dbService.getOrder(order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	public void pickUpOrder(String order_id) {
		try {
			dbService.assignDeliveryman(this.getUserName(), order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
