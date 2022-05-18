package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.DeliveryMan;
import view.DeliverView;
import view.MemberView;

public class DeliverController extends Controller implements Initializable{
	@FXML
	private Button logoutBtn;
	
	@FXML
	private Label welcomeLb;
	
	@FXML
	private Label displayLb;
	
	@FXML
	private Button orderBtn;
		
	@FXML
	private Button infoBtn;
	
	private String username;
	private DeliverView status;


	public void logout(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	@Override
	protected void setUsernameLb(String s) {
		username = s;
		String tmp = "Hello, Deliver " + s;
		welcomeLb.setText(tmp);
	}
	
	@Override
	protected void render() {
		String tmp;
		switch (status){
		case Info:
			tmp = "Info of " + username;
			displayLb.setText(tmp);
			displayLb.setVisible(true);
			break;			
		case Order:
			tmp = "Order of " + username;
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
		status = DeliverView.Info;
		render();
		DeliveryMan deliveryman = new DeliveryMan(this.username);
		// Maybe can be refactor
		DeliveryMan deliverymanInfo = deliveryman.getDeliveryManInfo();
		if (deliverymanInfo != null) {
			// TODO [FX] handle the info fx.
			System.out.println();
			System.out.println("Username: " + deliverymanInfo.getUserName());
			System.out.println("Address: " + deliverymanInfo.getAddress());
			System.out.println("Phone: " + deliverymanInfo.getPhone());
			System.out.println("Email: " + deliverymanInfo.getEmail());
			System.out.println("Name: " + deliverymanInfo.getName());
		}else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
	}

	public void pressOrderBtn() {
		status = DeliverView.Order;
		render();
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = DeliverView.Default;
		render();
	}

}
