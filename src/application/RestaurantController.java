package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Order;
import model.Restaurant;
import view.MemberView;
import view.RestaurantView;

public class RestaurantController extends Controller implements Initializable {
	@FXML
	private Button logoutBtn;

	@FXML
	private Label welcomeLb;

	@FXML
	private Button orderBtn;

	@FXML
	private Button couponBtn;

	@FXML
	private Button infoBtn;

	@FXML
	private VBox displayVb;

	@FXML
	private Button productBtn;
	
	private ComboBox<String> couponCombo = new ComboBox<String>();

	private String tmp;
	private Restaurant restaurant;
	private Restaurant restaurantInfo;
	private Button confirmBtn = new Button("Confirm");
	
//	private String tmp = "";

	private String username;
	private RestaurantView status;
//	private Restaurant restaurant;

	public void logout(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	@Override
	protected void setUsernameLb(String s) {
		username = s;
		restaurant = new Restaurant(username);
		try {
			restaurantInfo = restaurant.getRestaurantInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String tmp = "Hello " + restaurantInfo.getName();
		welcomeLb.setText(tmp);
	}

	@Override
	protected void render() {
		Label lb;
		switch (status) {
		case Coupon:
			Restaurant restaurant = new Restaurant(this.username);
			tmp = (restaurant.getCoupon().equals(""))? "You can set coupon for your restaurant here\nCoupon now: None": "You can set coupon for your restaurant here\nCoupon now: \n" + restaurant.getCoupon() + "\n";
			Label l = new Label(tmp);
			displayVb.getChildren().clear();
			couponCombo.setValue("");
			couponCombo.getItems().setAll("", "滿200九折", "滿300八折", "省20", "省30"); // set the options
			displayVb.getChildren().add(l);
			displayVb.getChildren().add(couponCombo);
			displayVb.getChildren().add(confirmBtn);
			confirmBtn.setOnAction(e -> {
				// [MING] Please set coupon here.
				String t = couponCombo.getValue();
				System.out.println("Set: " + t);
				try {
					restaurant.setCouponInDB(t);
				} catch (Exception eCoupon) {
					System.out.println("Some error occur");
				}
			});
			break;
		case Order:
			displayVb.getChildren().clear();
			tmp = "";
//			tmp = "Order of " + username;
			break;
		case Info:
			displayVb.getChildren().clear();
			lb = new Label(tmp);
			lb.setFont(new Font("Yu Gothic UI Semibold", 15));
			displayVb.getChildren().add(lb);
			tmp = "";

//			tmp = "Info of " + username;
			break;
		case Product:
			displayVb.getChildren().clear();
			lb = new Label(tmp);
			lb.setFont(new Font("Yu Gothic UI Semibold", 15));
			displayVb.getChildren().add(lb);
			tmp = "";
//			tmp = "Product of " + username;
			break;

		default:
			displayVb.getChildren().clear();
			tmp = "";
			break;
		}
	}

	public void pressInfoBtn() throws SQLException {
		status = RestaurantView.Info;
		// Maybe can be refactor
		if (restaurantInfo != null) {
			// TODO [FX] handle the info fx.
		
			tmp = "Username: " + restaurantInfo.getUserName() + "\n";
			if (restaurantInfo.getAddress().length() > 14) {
				tmp += "Address: " + restaurantInfo.getAddress().substring(0, 14) + "\n";
				tmp += restaurantInfo.getAddress().substring(14	, restaurantInfo.getAddress().length()) + "\n";
			} else {
				tmp += "Address: " + restaurantInfo.getAddress() + "\n";
			}

			tmp += "Phone: " + restaurantInfo.getPhone() + "\n";
			tmp += "Email: " + restaurantInfo.getEmail() + "\n";
			tmp += "Name: " + restaurantInfo.getName() + "\n";
			tmp += "Type: ";
						
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

			String[] types = restaurantInfo.getTypes();
			System.out.println("Type: ");

			for (String type : types) {
				tmp += " ";
				tmp += type;
				System.out.println(type);
			}
			tmp += "\n";

		} else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
		render();
	}

	public void pressOrderBtn() throws SQLException {
		status = RestaurantView.Order;
		render();
		
		try {
		System.out.println("Order in "+this.username);
		
		
		ArrayList<String> order_ids = restaurantInfo.checkOrders();
		
		// List all valid order
		order_ids.forEach(order -> System.out.println(order));
		
		
		// choose one order
		
		Order order_detail = restaurantInfo.checkOrderDetail(order_ids.get(0));
		ArrayList<ArrayList<String>>  order_items = restaurantInfo.checkItemValuePerOrder(order_ids.get(0));
		
		System.out.println(order_detail.getId());
		order_items.forEach(orderitem -> System.out.println(orderitem.get(0) + " * " + orderitem.get(1)));
		
		// if the restaurant want to accept the order
		restaurantInfo.setOrderStatus(order_ids.get(0));
		
		} catch (Exception e) {
			System.out.println("order not found or expire");
		}
		
		
		
		
		

	}

	public void pressCouponBtn() throws SQLException {
		status = RestaurantView.Coupon;
		render();

//		// TODO maybe setting coupon in DB later and show out the result
		System.out.println(this.username);
		Restaurant restaurant = new Restaurant(this.username);
		Restaurant restaurantInfo = restaurant.getRestaurantInfo();
		if (restaurantInfo != null) {
			System.out.println("Coupon: " + restaurantInfo.getCoupon());
		} else {
			System.out.println("some error occur, getting null");
		}

		System.out.println();
	}

	public void pressProductBtn() throws SQLException {
		status = RestaurantView.Product;
		// Maybe Store Description and order Description can be handle in this?
		HashMap<String, Integer> restaurantProduct = restaurant.getProducts();

		if (restaurantProduct != null) {
			// TODO [FX] handle the info fx.
			System.out.println("Products List: ");

			for (String key : restaurantProduct.keySet()) {
				System.out.println(key + ": $" + restaurantProduct.get(key));
				tmp += (key + ": $" + restaurantProduct.get(key) + "\n");
			}
				

		} else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();

		render();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = RestaurantView.Default;
		render();

		System.out.println(this.username);
//		restaurant = 

	}
}
