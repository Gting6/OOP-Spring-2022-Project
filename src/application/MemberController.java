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
	}
	
	public void pressInfoBtn() {
		status = MemberView.Info;
		render();
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
