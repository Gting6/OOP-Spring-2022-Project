package model;

// User is Member, Deliver or Restaurant.
public abstract class User {
	private String username;
	private String password;
	private String address;
	private String phone;
	private String email;
	private String name;

	public User() {

	}

	public User(String username) {
		this.username = username;

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String address, String phone, String email, String name) {
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.name = name;
	}

	public User(User b) {
		this.username = b.username;
		this.password = b.password;
		this.address = b.address;
		this.phone = b.phone;
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

	public String getAddress() {
		return this.address;
	}

	public String getPhone() {
		return this.phone;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

}
