package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Restaurant;
import view.MemberView;
import view.RestaurantView;

public class RestaurantController extends Controller implements Initializable{
	@FXML
	private Button logoutBtn;
	
	@FXML
	private Label welcomeLb;
	
	@FXML
	private Label displayLb;
	
	@FXML
	private Button orderBtn;
	
	@FXML
	private Button discountBtn;
	
	@FXML
	private Button infoBtn;
		
	private String username;
	private RestaurantView status;


	public void logout(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	@Override
	protected void setUsernameLb(String s) {
		username = s;
		String tmp = "Hello, Restaurant " + s;
		welcomeLb.setText(tmp);
	}
	
	@Override
	protected void render() {
		String tmp;
		switch (status){
		case Discount:
			tmp = "Discount of " + username;
			displayLb.setText(tmp);
			displayLb.setVisible(true);
			break;			
		case Order:
			tmp = "Order of " + username;
			displayLb.setText(tmp);
			displayLb.setVisible(true);
			break;			
		case Info:
			tmp = "Info of " + username;
			displayLb.setText(tmp);
			displayLb.setVisible(true);
			break;						
		default:
			displayLb.setVisible(false);
			displayLb.setText("");
			break;
		}
	}
		
	public void pressInfoBtn() throws SQLException {
		status = RestaurantView.Info;
		render();
		
		System.out.println(this.username);
		Restaurant restaurant = new Restaurant(this.username);
		// Maybe can be refactor
		Restaurant restaurantInfo = restaurant.getRestaurantInfo();
		if (restaurantInfo != null) {
			// TODO [FX] handle the info fx.
			System.out.println();
			System.out.println("Username: " + restaurantInfo.getUserName());
			System.out.println("Address: " + restaurantInfo.getAddress());
			System.out.println("Phone: " + restaurantInfo.getPhone());
			System.out.println("Email: " + restaurantInfo.getEmail());
			System.out.println("Name: " + restaurantInfo.getName());
			System.out.println("Latitude: " + restaurantInfo.getLatitude());
			System.out.println("Longitude: " + restaurantInfo.getLongitude());
			System.out.println("Store Description: " + restaurantInfo.getStore_description());
			System.out.println("Order Description: " + restaurantInfo.getOrder_despcription());
			System.out.println("Coupon: " + restaurantInfo.getCoupon());

			String [] types = restaurantInfo.getTypes();
			System.out.println("Type: ");

			for (String type : types) {
				System.out.println(type);
			}

		}else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
	}

	public void pressOrderBtn() throws SQLException {
		status = RestaurantView.Order;
		render();
		System.out.println(this.username);
		Restaurant restaurant = new Restaurant(this.username);
		// Maybe Store Description and order Description can be handle in this?
		HashMap<String, Integer> restaurantProduct = restaurant.getProducts();
		
		if (restaurantProduct != null) {
			// TODO [FX] handle the info fx.
			System.out.println("Products List: ");

			for (String key : restaurantProduct.keySet()) 
				System.out.println(key + ": $" + restaurantProduct.get(key));

		}else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
		
	}
	
	public void pressDiscountBtn() throws SQLException {
		status = RestaurantView.Discount;
		render();
		
		// TODO maybe setting coupon in DB later and show out the result
		System.out.println(this.username);
		Restaurant restaurant = new Restaurant(this.username);
		Restaurant restaurantInfo = restaurant.getRestaurantInfo();
		if (restaurantInfo != null) {
			System.out.println("Coupon: " + restaurantInfo.getCoupon());
		}else {
			System.out.println("some error occur, getting null");
		}
		
		System.out.println();
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = RestaurantView.Default;
		render();
	}
}
