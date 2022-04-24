package model;

import java.util.*;

public class Model {
	
	// Username -> User
	private HashMap<String, User> Users = new HashMap<String, User>(); // one user first
	private HashSet<String> Emails = new HashSet<String>();
	private HashSet<String> TelephoneNumbers = new HashSet<String>();

	public int StringToInt(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e){
			return -1;
			
		}
	}
	
	// given
	public void addUser(User usr) {
		// 可能可以寫個status enum判斷
		// 偷懶用output
		if (!searchUserByUserName(usr.getUserName())) {
			Users.put(usr.getUserName(), usr);
		}
		else {
			System.out.println("What");
		}
	}
	
	public boolean searchUserByUserName(String username) {
		return Users.containsKey(username);		
	}
	
	public boolean checkPassword(String username, String password) {
		return Users.get(username).getPassword().equals(password);
	}
	
	public String searchUserTypeByUserName(String username) {
		return Users.get(username).getClass().getSimpleName();
	}

}
