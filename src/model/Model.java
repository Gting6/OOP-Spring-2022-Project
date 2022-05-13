package model;

import java.sql.SQLException;
import java.util.*;
import sql.DBService;

public class Model {

	// Username -> User
	private DBService DBService;
//	private HashMap<String, User> Users = new HashMap<String, User>(); // one user first

	public Model() {
		this.DBService = new DBService();
	}

	public int StringToInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return -1;

		}
	}

	public void addMember(Member member) {
		try {
			this.DBService.createMember(member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDeliverMan(DeliveryMan deliveryman) {
		try {
			this.DBService.createDeliveryMan(deliveryman);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void addRestaurant() {

	}
	
	// TODO Don't need to pass the password?
	public boolean checkMemberInWhenRegister(String username, String password) {
		try {
			return this.DBService.getMember(username, password) != null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkMemberLoginIn(String username, String password) {

		try {
			return this.DBService.loginDB("members", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkDeliverManWhenRegister(String username) {
		try {
			return this.DBService.getDeliveryMan(username) != null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}

	public boolean checkDeliverManrLoginIn(String username, String password) {

		try {
			return this.DBService.loginDB("deliverymen", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkRestaurantLoginIn(String username, String password) {

		try {
			return this.DBService.loginDB("restaurants", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Restaurant getRestaurant(String username) {
		try {
			return new Restaurant(this.DBService.getRestaurant(username));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//	public void addUser(User usr) {
//		// 可能可以寫個status enum判斷
//		// 偷懶用output
//		if (!searchUserByUserName(usr.getUserName())) {
//			Users.put(usr.getUserName(), usr);
//		}
//		else {
//			System.out.println("What");
//		}
//	}

//	public boolean searchUserByUserName(String username) {
//		return Users.containsKey(username);
//	}
//
//	public boolean checkPassword(String username, String password) {
//		return Users.get(username).getPassword().equals(password);
//	}
//
//	public String searchUserTypeByUserName(String username) {
//		return Users.get(username).getClass().getSimpleName();
//	}

}
