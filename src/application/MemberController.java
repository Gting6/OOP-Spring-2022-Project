package application;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Member;
import model.Model;
import model.Restaurant;
import view.MemberView;

public class MemberController extends Controller implements Initializable{
	@FXML
	private Button logoutBtn;
	
	@FXML
	private Label welcomeLb;
	
	@FXML
	private Label displayLb;
	
	@FXML
	private Button orderBtn;
	
	@FXML
	private Button trackBtn;
	
	@FXML
	private Button infoBtn;
	
	@FXML
	private Button searchBtn;
	
	private String username;
	private MemberView status;


	public void logout(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	@Override
	protected void setUsernameLb(String s) {
		username = s;
		String tmp = "Hello, Member " + s;
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
		case Track:
			tmp = "Track of " + username;
			displayLb.setText(tmp);
			displayLb.setVisible(true);
			break;						
		case Search:
			tmp = "Search of " + username;
			displayLb.setText(tmp);
			displayLb.setVisible(true);
			break;						
		default:
			displayLb.setVisible(false);
			displayLb.setText("");
			break;
		}
	}
	
	public void pressSearchBtn() {
		status = MemberView.Search;
		render();
		Model model = new Model();
		
		// Search By Distance given location in Eng, maybe test chinese
		Map <Restaurant, Integer> result = model.SearchRestaurantByDistance(username);
		// min second or Hr
		result.forEach((key, value)->{System.out.println(key.getName() + "time: " + value/60 +" (min)");});
		
		
	}
	
	public void pressInfoBtn() throws SQLException {
		status = MemberView.Info;
		render();
		Member member = new Member(this.username);
		// Maybe can be refactor
		Member memberInfo = member.getMemberInfo();
		if (memberInfo != null) {
			// TODO [FX] handle the info fx.
			System.out.println();
			System.out.println("Username: " + memberInfo.getUserName());
			System.out.println("Address: " + memberInfo.getAddress());
			System.out.println("Phone: " + memberInfo.getPhone());
			System.out.println("Email: " + memberInfo.getEmail());
			System.out.println("Name: " + memberInfo.getName());
			if (memberInfo.getVIP_expire_date() == null) {
				// Maybe think a good expression for vip date.
				System.out.println("Vip Expired date: ----");
			}
			else {
				System.out.println("Vip Expired date: " + memberInfo.getVIP_expire_date());
			}
		}else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
	}

	public void pressOrderBtn() {
		status = MemberView.Order;
		render();
	}
	
	public void pressTrackBtn() {
		status = MemberView.Track;
		render();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = MemberView.Default;
		render();
	}
}
