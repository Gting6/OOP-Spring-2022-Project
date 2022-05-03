package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.LoginView;

public class LoginController extends Controller implements Initializable  {
	private String username;
	private String password;
	private LoginView status;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private TextField usernameTf;

	@FXML
	private TextField passwordTf;
	
	@FXML
	private TextField confirmTf;
	
	@FXML
	private Label confirmLb;
	
	@FXML
	private Label wrongLb;
	
	// Helper function: render by status
	@Override
	protected void render() {
		if (status == LoginView.Login) {
			btn1.setText("Log in");
			btn2.setText("Sign up");
			confirmTf.setVisible(false);
			confirmLb.setVisible(false);
			wrongLb.setVisible(false);
		}
		else if(status == LoginView.SignUp) {
			btn1.setText("Go Back");
			btn2.setText("Confirm");
			confirmTf.setVisible(true);
			confirmLb.setVisible(true);
			wrongLb.setVisible(false);
		}
	}
		
	public void pressBtn1(ActionEvent event) throws IOException {
		if (status == LoginView.Login) {
			username = usernameTf.getText();
			password = passwordTf.getText();

			// switch to main page, please modify the logic here.
			// You may change the login status or store the user name.
			// Here I simply use password as user type.
			switch (password) {
			case "member":
				switchScene(ViewEnum.MEMBER, event, username);
				break;
			case "restaurant":
				switchScene(ViewEnum.RESTAURANT, event, username);
				break;
			case "deliver":
				switchScene(ViewEnum.DELIVER, event, username);
				break;
			default:
				wrongLb.setVisible(true);
			}			
		}
		
		// When the status is SignUp, btn1 becomes "back" button.
		else if (status == LoginView.SignUp) {
			status = LoginView.Login;
			render();
		}
	}
	
	public void pressBtn2(ActionEvent event) throws IOException {
		if (status == LoginView.Login) {
			status = LoginView.SignUp;
			render();
		}
		else if (status == LoginView.SignUp) {
			username = usernameTf.getText();
			password = passwordTf.getText();
			
			// switch to main page, please modify the logic here.
			// You may change the login status or store the user name.
			// Here I simply use password as user type.
			switch (password) {
			case "member":
				switchScene(ViewEnum.MEMBER, event, username);
				break;
			case "restaurant":
				switchScene(ViewEnum.RESTAURANT, event, username);
				break;
			case "deliver":
				switchScene(ViewEnum.DELIVER, event, username);
				break;
			default:
				wrongLb.setVisible(true);
			}			
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = LoginView.Login;
		render();
	}
}
