package model;

import java.sql.SQLException;
import java.sql.Timestamp;

import sql.DBService;

public class Order {

	// OrderStatus
	private int id;
	// status
	private String status; 
	private int price;
	private Timestamp create_time;
	private Timestamp arrival_time;
	private String member_name;
	private String deliveryman_name;
	private String restaurant_name; // can be search by call static function
	// TODO Location?
	private boolean is_vip_;
	private int fee;
	
	DBService dbService = new DBService();
	
	
	// float how to calculate
	public Order() {
		
	}
	
	public Order(int id, int price,Timestamp create_time, Timestamp arrival_time, 
			String member_name, String deliveryman_name, String restaurant_name) {
		this.id = id;
		this.status = "NEW";
		this.price = price;
		this.create_time = create_time;
		this.arrival_time = arrival_time;
		this.member_name = member_name;
		this.deliveryman_name = deliveryman_name;
		this.restaurant_name = restaurant_name;
		
	}
	
	public void setToDB() {
		try {
			dbService.createOrder(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//give the deliveryman after established
//	public Order(Member member, Restaurant restaurant) {
//		this.is_vip_ = member.is_vip;
//		if (!is_vip_) this.fee = 30;
//	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
//	private void calculateCoinDistance(float distance) {
//		
//		this.price_ += distance; // design�ffunction
//		if (!this.is_vip_) {
//			this.price_ += this.fee;
//		}
//		
//		// add food?
//	}
	
	// getter and setter
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Timestamp arrival_time) {
		this.arrival_time = arrival_time;
	}

	public String getConsumer_name() {
		return member_name;
	}

	public void setConsumer_name(String member_name) {
		this.member_name = member_name;
	}

	public String getDeliveryman_name() {
		return deliveryman_name;
	}

	public void setDeliveryman_name(String deliveryman_name) {
		this.deliveryman_name = deliveryman_name;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}


	public Order getOrderInfo() {
		try {
			return dbService.getOrder(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
