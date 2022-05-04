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

	public void addRestaurant() {

	}

	public void addDeliveryMen() {

	}

	public boolean checkMemberInWhenRegister(String username, String password) {
		try {
			if (this.DBService.getMember(username, password) != null) {
				return true;
			}
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
