package model;

public class Restaurant extends User{
	
	// TODO coupon list
	// TODO json data
	// TODO products list
	// TODO OrderHandler waiting doing done queue
	
	public Restaurant() {
		// TODO Init
	}
	
	public Restaurant(String username, String password, String telephone_number, String email, String name) {
		super(username, password, telephone_number, email, name);
	}
	
	public Restaurant(String Json) {
		// TODO Read the json init
	}

	public void getRestaurantInfo() {
		// call output
	}
	
	public void getProducts() {
		// TODO Return Products list
	}
	
	public void checkOrders(){
		// TODO Return Order status
	}

}
