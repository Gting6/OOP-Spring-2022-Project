package model;

import java.sql.SQLException;
import java.sql.Timestamp;

import sql.DBService;

public class Order {

	// OrderStatus
	private int order_id_;
	// status
	private String status_; 
	private int price_;
	private Timestamp create_time_;
	private Timestamp deliver_time_;
	private Timestamp arrival_time_;
	private String consumer_name_;
	private String deliveryman_name_;
	private String restaurant_name_; // can be search by call static function
	private boolean is_vip_;
	private int fee;
	
	DBService dbService = new DBService();
	
	
	// float how to calculate
	public Order() {
		
	}
	
	public Order(int id, Timestamp create_time, Timestamp arrival_time, 
			String consumer_name, String deliveryman_name, String restaurant_name) {
		this.order_id_ = id;
		this.create_time_ = create_time;
		this.arrival_time_ = arrival_time;
		this.consumer_name_ = consumer_name;
		this.deliveryman_name_ = deliveryman_name;
		this.restaurant_name_ = restaurant_name;	
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
	public Order(Member member, Restaurant restaurant) {
		this.is_vip_ = member.is_vip;
		if (!is_vip_) this.fee = 30;
	}
	
	public String getStatus() {
		return this.status_;
	}
	
	private void calculateCoinDistance(float distance) {
		
		this.price_ += distance; // designˆffunction
		if (!this.is_vip_) {
			this.price_ += this.fee;
		}
		
		// add food?
	}
	
	// getter and setter
	public int getOrder_id_() {
		return order_id_;
	}

	public void setOrder_id_(int order_id_) {
		this.order_id_ = order_id_;
	}

	public Timestamp getCreate_time_() {
		return create_time_;
	}

	public void setCreate_time_(Timestamp create_time_) {
		this.create_time_ = create_time_;
	}

	public Timestamp getArrival_time_() {
		return arrival_time_;
	}

	public void setArrival_time_(Timestamp arrival_time_) {
		this.arrival_time_ = arrival_time_;
	}

	public String getConsumer_name_() {
		return consumer_name_;
	}

	public void setConsumer_name_(String consumer_name_) {
		this.consumer_name_ = consumer_name_;
	}

	public String getDeliveryman_name_() {
		return deliveryman_name_;
	}

	public void setDeliveryman_name_(String deliveryman_name_) {
		this.deliveryman_name_ = deliveryman_name_;
	}

	public String getRestaurant_name_() {
		return restaurant_name_;
	}

	public void setRestaurant_name_(String restaurant_name_) {
		this.restaurant_name_ = restaurant_name_;
	}


	public Order getOrderInfo() {
		try {
			return dbService.getOrder(order_id_);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
