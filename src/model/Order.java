package model;

public class Order {
	// 做訂單輸出系統
	// OrderStatus
	private int order_no_;
	// status
	private String status_; 
	private int total_spend_;
	private int create_time_;
	private int deliver_time_;
	private String locate_;
	private String consumer_name_;
	private String deliver_name_;
	private String restaurant_name_; // can be search by call static function
	private boolean is_vip_;
	private int fee;
	
	
	// float 可能換計算方法？
	public Order() {
		
	}
	
	// 單子成立後才給外送員
	public Order(Member member, Restaurant restaurant) {
		this.is_vip_ = member.is_vip;
		if (!is_vip_) this.fee = 30;
	}
	
	public String getStatus() {
		return this.status_;
	}
	
	private void calculateCoinDistance(float distance) {
		
		this.total_spend_ += distance; // 設計function
		if (!this.is_vip_) {
			this.total_spend_ += this.fee;
		}
		
		// 如加上食物怎辦？
	}
	
	// getter
	
	// setter

	public void getOrderInfo() {
		
	}
	
}
