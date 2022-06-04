package model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import sql.DBService;

public class Order {

	// OrderStatus
	private String id;
	private int status;
	private Timestamp create_time;
	private Timestamp deliver_time;
	private Timestamp arrival_time;
	private String member_id;
	private String deliveryman_id;
	private String restaurant_id; // can be search by call static function
	private HashMap<String, Integer> items;
	// TODO Location?
	private int fee;
	
	DBService dbService = new DBService();
	
	
	// float how to calculate
	public Order() {
		
	}
	
	// An order is created when a customer clicked on the first item to the cart
	// TODO Clear cart when buy things from another store
	public Order(String member_id, String restaurant_id, HashMap<String, Integer> product) {
		this.id = UUID.randomUUID().toString();
		this.member_id = member_id;
		this.restaurant_id = restaurant_id;
		this.deliveryman_id = "";  // it should be designated only when the order is established
		this.deliver_time = null;
		this.items = new HashMap<>();
		addToCart(product);
	}
	
	public Order(String id, int status, Timestamp create_time, Timestamp deliver_time, Timestamp arrival_time, 
			String member_id, String deliveryman_id, String restaurant_id, int fee) {
		this.id = id;
		this.status = 0;  // 0 -> inserted; 1 -> deliveryman_designated
		this.create_time = create_time;
		this.deliver_time = deliver_time;
		this.arrival_time = arrival_time;
		this.member_id = member_id;
		this.deliveryman_id = deliveryman_id;
		this.restaurant_id = restaurant_id;
		this.fee = fee;
	}
	
	public void setToDB() {
		try {
			dbService.createOrder(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// getter and setter
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public String getConsumer_id() {
		return member_id;
	}

	public void setConsumer_id(String member_id) {
		this.member_id = member_id;
	}

	public String getDeliveryman_id() {
		return deliveryman_id;
	}

	public void setDeliveryman_id(String deliveryman_id) {
		this.deliveryman_id = deliveryman_id;
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
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

	public Timestamp getDeliver_time() {
		return deliver_time;
	}

	public void setDeliver_time(Timestamp deliver_time) {
		this.deliver_time = deliver_time;
	}

	public int getFee() {
		return fee;
	}
		
	public void addToCart(HashMap<String, Integer> product) {
		if(this.items == null) {
			System.out.println("HERE");
			this.items.put((String) product.keySet().toArray()[0],1);		}
		else if(this.items.containsKey((String) product.keySet().toArray()[0]))
			this.items.put((String) product.keySet().toArray()[0], this.items.get((String) product.keySet().toArray()[0])+1);
		else
			this.items.put((String) product.keySet().toArray()[0],1);
	}
	
	public void removeFromCart(HashMap<String, Integer> product) {
		if(this.items == null) {
			return;
			}
		else if(this.items.containsKey((String) product.keySet().toArray()[0]) && this.items.get((String) product.keySet().toArray()[0]) > 1)
			this.items.put((String) product.keySet().toArray()[0], this.items.get((String) product.keySet().toArray()[0])-1);
		else if(this.items.containsKey((String) product.keySet().toArray()[0]) && this.items.get((String) product.keySet().toArray()[0]) == 1)
			this.items.remove((String) product.keySet().toArray()[0]);
	}
	
	public void calculateFee() {
		
		String[] rest_info;
		String coupon = "";
		double discount = 1.0;
		int total_price = 0;
		int distance_fee = 0;
		HashMap<String, Integer> products = new HashMap<>();
		
		try {
			Member mem = dbService.getMember(this.member_id);
			rest_info = dbService.getRestaurant(restaurant_id);
			products = dbService.getProducts(restaurant_id);
			coupon = rest_info[10];
			
			if(mem.is_vip) 
				distance_fee = 0;
			else distance_fee = 30; // change to result from google API
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(String key : items.keySet()) {
			System.out.println("key==========");
			System.out.println(key);
			total_price += items.get(key) * products.get(key);
		}
		
		if(coupon != "") {
//			parse the coupon info and change discount
			if(coupon.equals(Restaurant.coupon_type.buy_200_get_90_percent_off.name())) {
				if(total_price >= 200) {
					total_price = (int) (total_price*0.9);
				}
			}
			else if(coupon.equals(Restaurant.coupon_type.buy_300_get_80_percent_off.name())) {
				if(total_price >= 300) {
					total_price = (int) (total_price*0.8);
				}
			}
			else if(coupon.equals(Restaurant.coupon_type.save_20_dollars.name())) {
				total_price = total_price - 20;
			}
			else if(coupon.equals(Restaurant.coupon_type.save_30_dollars.name())) {
				total_price = total_price - 30;
			}
		}
		
		this.fee = (int) ((total_price + distance_fee) * discount);
	}
	
	public void establishOrder() {
		calculateFee();
		Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
		long l = now.getTime();
		Model model = new Model();
		long m = model.CalculateDistanceMemberRest(member_id, restaurant_id);
		// n may be a long type?
//		long m = 30*60*1000;			// m should be calculated by googleMap API
		long n = 15*60*1000;			// n should be calculated by googleMap API
		Timestamp later = new Timestamp(l+m);
		this.status = 0;
		this.deliveryman_id = "";		// should be selected by googleMap API
		this.deliver_time = new Timestamp(l+n);
		this.create_time = now; 
		this.arrival_time = later;
		setToDB();
		setItemsToDB();
		
	}

	private void setItemsToDB() {
		// TODO Auto-generated method stub
		try {
			dbService.createOrderItems(this.items, this.id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}
	
	public void setStatus() {
		this.status += 1;
		dbService.setOrderStatus(status, id);
	}
	
	public String getRestaurantDescription() {
		String s = "";
		try {
			s = dbService.getRestaurant(this.restaurant_id)[8];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
}
