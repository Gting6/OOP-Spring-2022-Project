package model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import sql.DBService;

public class Member extends User {
	// ���info頛詨蝟餌絞
	boolean is_vip;
	Date vip_expire_date;
	DBService dbService = new DBService();

	// TODO OrderHandler

	public Member() {

	}
	
	public Member(String username) {
		super(username);
	}

	public Member(String username, String password, String address, String phone, String email, String name) {
		super(username, password, address, phone, email, name);
		this.is_vip = false;
		this.vip_expire_date = null;	
	}
	
	public Member(String username, String password, String address, String phone,String email, String name, Date vip_expire_date) {
		super(username, password, address, phone, email, name);
		this.vip_expire_date = vip_expire_date;	
		this.is_vip = getVIP();
	}
	
	public void setToDB() {
		try {
			dbService.createMember(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Member(User usr) {
		super(usr);
		this.is_vip = false;
		this.vip_expire_date = null;
	}

	public Member getMemberInfo() throws SQLException {
		// TODO view page
		try {
			return dbService.getMember(this.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void searchMemberDetail() {
		// Seems to be the same as above??
	}

	public boolean becomeVIP() throws SQLException {
		// TODO
		dbService.setVIP(this.getUserName());
		this.is_vip = getVIP();
		return this.is_vip;
	}

	// i think this function is no use now
	public void placeOrder(Order order) {
		// push order in order list
		// Order newOrder = new Order(this.is_vip_);
		try {
			dbService.createOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return newOrder.getStatus();
	}

	// ��府閬�status
	// user.callSearch
	// 閬銝�銝�獐摮�獐����靽格����靘�
	public Restaurant searchRestaurant(String restaurant_name) {
		// 閬model����� name->Restaurant
		// 瘥酉����停閬憓���
		// ����獐� 摮num
		// �摰閰Ｘ撘�
		// call �閰Ｘ撘�
		// �����閰Ｘ��� 頛詨UI銝血�������
		return null;
	}

	public Restaurant searchRestaurant(Restaurant restaurant, String type) {
		return restaurant;
	}

	// view�� ��迄����
	public Order checkOrderStatus(String order_id) {
		Order order = null;
		try {
			order = dbService.getOrder(order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	public ArrayList<String> checkAllOrders() {
		ArrayList<String> all_orders = new ArrayList<>();
		try {
			all_orders = dbService.getOrdersMember(this.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all_orders;
	}
	
	public ArrayList<String> checkAllOrdersNotExpired() {
		
		ArrayList<String> not_expired_orders = new ArrayList<>();
		
		ArrayList<String> all_orders = checkAllOrders();
		try {
			for(String order_id : all_orders) {
				Order order = dbService.getOrder(order_id);
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if(order.getCreate_time().compareTo(now) < 0 && order.getDeliver_time().compareTo(now) > 0) {
					not_expired_orders.add(order_id);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return not_expired_orders;
	}

	public boolean getVIP() {
		
		boolean vip_check = false;
		
		Calendar c = Calendar.getInstance();
		c.getTime();
		Date today = new java.sql.Date(c.getTimeInMillis());
		Date expire_day;
		try {
			expire_day = dbService.getVIPDate(this.getUserName());
			if(expire_day == null)
				vip_check = false;
			else if(today.compareTo(expire_day) >= 0) {
				vip_check = false;
			}
			else vip_check = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vip_check;
	}

	public Date getVIP_expire_date() throws SQLException {
		// TODO Auto-generated method stub
		return dbService.getVIPDate(this.getUserName());
	}

}
