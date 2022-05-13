package model;

import java.sql.SQLException;

import sql.DBService;

public class DeliveryMan extends User {
	
	DBService dbService = new DBService();
	
	public DeliveryMan() {
		
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
	
}
