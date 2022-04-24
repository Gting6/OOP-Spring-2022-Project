package model;
// User is Member, Deliver or Restaurant.
public abstract class User {
	// 可能做user 輸出系統
	private String username;
	private String password;
	private String telephone_number;
	private String email;
	private String name;
	
	public User() {
		
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String telephone_number, String email, String name) {
		this.username = username;
		this.password = password;
		this.telephone_number = telephone_number;
		this.email = email;
		this.name = name;
	}
	
	public User(User b) {
		this.username = b.username;
		this.password = b.password;
		this.telephone_number = b.telephone_number;
		this.email = b.email;
		this.name = b.name;
	}
	
	// Getter part
	public String getUserName() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getTelephoneNumber() {
		return this.telephone_number;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getName() {
		return this.name;
	}
	
	// Setter part
	public void setUserName(String name) {
		this.username = name;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public void setTelephoneNumber(String telephone_number) {
		this.telephone_number = telephone_number;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
