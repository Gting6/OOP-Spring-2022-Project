package model;

import java.sql.SQLException;

import sql.DBService;

public class DeliveryMan extends User {
	
	DBService dbService = new DBService();
	
	public DeliveryMan() {
		
	}
	
	public DeliveryMan(String username, String password, String address, String email, String name) {
		super(username, password, address, email, name);
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
