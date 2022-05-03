package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
		
	public void pressInfoBtn() {
		status = RestaurantView.Info;
		render();
	}

	public void pressOrderBtn() {
		status = RestaurantView.Order;
		render();
	}
	
	public void pressDiscountBtn() {
		status = RestaurantView.Discount;
		render();
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = RestaurantView.Default;
		render();
	}
}
