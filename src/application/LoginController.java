package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
import model.Member;
import model.Model;
import model.UserType;
import view.LoginView;

public class LoginController extends Controller implements Initializable  {
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
			Model model = new Model();
			usernameTf.getText();
			passwordTf.getText();
			// Add check who want to login? or use if to handle
			String pattern = "[0-9a-zA-Z]+";
			
			while (true) {
				if (!Pattern.matches(pattern, usernameTf.getText()) || !Pattern.matches(pattern, passwordTf.getText())) {
					// TODO change the errormsg
					System.out.println("Error pattern in username or password");
					wrongLb.setVisible(true);
					// refresh
					break;
				}
				
				if (model.checkMemberLoginIn(usernameTf.getText(), passwordTf.getText())) {
					switchScene(ViewEnum.MEMBER, event, usernameTf.getText());
					System.out.println("Login success");
				}
				else {
					System.out.println("Login fail");
					wrongLb.setVisible(true);
				}
				
				break;
				
				// TODO [BackEnd] check restaurant and deliver
//				case "restaurant":
//				switchScene(ViewEnum.RESTAURANT, event, username);
//				break;
//				case "deliver":
//				switchScene(ViewEnum.DELIVER, event, username);
//				break;
				
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
			Model model = new Model();
			usernameTf.getText();
			passwordTf.getText();
			confirmTf.getText();
			
			// TODO [FX] need add the Tf of email 
			String email = "abcd@gmail.com";
			
			// TODO [FX] need add the Tf of phoneNumber
			String address = "0922323232";
			
			// TODO [FX] need add the Tf of name
			String name = "";
						
			String pattern = "[0-9a-zA-Z]+";
			
			System.out.println(passwordTf.getText());
			System.out.println(confirmTf.getText());

			while (true) {
				
			
				if (!Pattern.matches(pattern, usernameTf.getText()) || !Pattern.matches(pattern, passwordTf.getText())) {
					// TODO [Fx] The string in wrongLb can be change to the string mentioned below.
					System.out.println("Error pattern in username or password");
					wrongLb.setVisible(true);
					break;
				}
				
				if (!passwordTf.getText().equals(confirmTf.getText())) {
					// TODO [Fx] The string in wrongLb can be change to the string mentioned below.
					System.out.println("Error password and confirm password");
					wrongLb.setVisible(true);
					break;
				}
				
				// TODO [FX] Need a <select> in css to check which type(member, rest, deliver...) the user want to sign-up.
				// usertype = UserType.Member;
				
				Member member = new Member(usernameTf.getText(), passwordTf.getText(), email, address, name);
				if (!model.checkMemberInWhenRegister(member.getUserName(), member.getPassword())) {
					model.addMember(member);
					// TODO [FX] Success, show sign-up success, and give a button to back to login page.
					System.out.println(model.checkMemberInWhenRegister(member.getUserName(), member.getPassword()));
					switchScene(ViewEnum.MEMBER, event, member.getUserName());
				}
				else {
					// TODO [Fx] The string in wrongLb can be change to the string mentioned below.
					System.out.println("fail to register");
					wrongLb.setVisible(true);
				}
				
				break;

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
