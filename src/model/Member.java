package model;

import java.sql.Date;
import java.sql.SQLException;
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
		this.is_vip = false;
		this.vip_expire_date = vip_expire_date;	
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
		return dbService.setVIP(this.getUserName());
	}

	// status->string, �enum撖虫��
	// ��ew 銝��rder by input 鈭� ��振, ParseOrder by UI 撠望隤芾�� 銝西���, 蝣箄����laceOrder�������
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
	public String checkOrderStatus(int no) {
		return new String();
	}

	public boolean getVIP() throws SQLException {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		c.getTime();
		Date today = new java.sql.Date(c.getTimeInMillis());
		
		Date expire_day = dbService.getVIPDate(this.getUserName());
		
		if(today.compareTo(expire_day) >= 0) {
			this.is_vip = false;
		}
		else this.is_vip = true;

		return this.is_vip;
	}

	public Date getVIP_expire_date() throws SQLException {
		// TODO Auto-generated method stub
		return dbService.getVIPDate(this.getUserName());
	}

}
