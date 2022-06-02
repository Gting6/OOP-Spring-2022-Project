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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.DeliveryMan;
import view.DeliverView;
import view.MemberView;

public class DeliverController extends Controller implements Initializable{
	@FXML
	private Button logoutBtn;
	
	@FXML
	private Label welcomeLb;
	
	@FXML
	private VBox displayVb;
	
	@FXML
	private Button orderBtn;
		
	@FXML
	private Button infoBtn;
	
	private String username;
	private DeliverView status;
	private String tmp;
	private DeliveryMan deliveryman;
	private DeliveryMan deliverymanInfo;
	
	
	public void logout(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	@Override
	protected void setUsernameLb(String s) {
		username = s;
		deliveryman = new DeliveryMan(this.username);
		// Maybe can be refactor
		try {
			deliverymanInfo = deliveryman.getDeliveryManInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		welcomeLb.setText(("Hello, Deliver " + s));
	}
	
	@Override
	protected void render() {
		Label lb;
		switch (status){
		case Info:
			displayVb.getChildren().clear();
			lb = new Label(tmp);
			lb.setFont(new Font("Yu Gothic UI Semibold", 15));
			displayVb.getChildren().add(lb);
			tmp = "";
			break;			
		case Order:
			displayVb.getChildren().clear();
			tmp = "";
			break;			
		default:
			displayVb.getChildren().clear();
			tmp = "";
			break;
		}
	}
		
	public void pressInfoBtn() throws SQLException {
		status = DeliverView.Info;
		if (deliverymanInfo != null) {
			// TODO [FX] handle the info fx.
			tmp = ("Username: " + deliverymanInfo.getUserName() + "\n");
			tmp += ("Address: " + deliverymanInfo.getAddress() + "\n");
			tmp += ("Phone: " + deliverymanInfo.getPhone() + "\n");
			tmp += ("Email: " + deliverymanInfo.getEmail() + "\n");
			tmp += ("Name: " + deliverymanInfo.getName() + "\n");
			
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
		render();
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
