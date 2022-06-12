package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Model;

public class LoginController extends Controller implements Initializable {

	@FXML
	private Button loginBtn;

	@FXML
	private Button signupBtn;

	@FXML
	private Label wrongLb;

	@FXML
	private TextField usernameTf;

	@FXML
	private TextField passwordTf;

	// To set the error message for wrongLb
	// Can be extend to input label.
	private void setWrongLb(String s) {
		wrongLb.setText(s);
	}

	// Helper function: render by status
	// There is only 1 status at login -> No need to render here
	@Override
	protected void render() {

	}
	
	/*
	 * Handle the login logic here.
	 */
	public void pressLoginBtn(ActionEvent event) throws IOException {
		Model model = new Model();
		usernameTf.getText();
		passwordTf.getText();
		// Add check who want to login? or use if to handle
		String pattern = "[0-9a-zA-Z]+";

		while (true) {
			if (!Pattern.matches(pattern, passwordTf.getText())) {
				// TODO (done) change the errormsg
				setWrongLb("Error pattern in username or password");
				wrongLb.setVisible(true);
				// refresh
				break;
			}

			if (model.checkMemberLoginIn(usernameTf.getText(), passwordTf.getText())) {
				switchScene(ViewEnum.MEMBER, event, usernameTf.getText());
				System.out.println("Login success as member");
			} else if (model.checkDeliverManrLoginIn(usernameTf.getText(), passwordTf.getText())) {
				switchScene(ViewEnum.DELIVER, event, usernameTf.getText());
				System.out.println("Login success as Deliver");
			} else if (model.checkRestaurantLoginIn(usernameTf.getText(), passwordTf.getText())) {
				switchScene(ViewEnum.RESTAURANT, event, usernameTf.getText());
				System.out.println("Login success as Restaurant");
			} else {
				setWrongLb("Login Fail");
				wrongLb.setVisible(true);
			}

			break;
		}
	}

	public void pressSignupBtn(ActionEvent event) throws IOException {
		switchScene(ViewEnum.SIGNUPSUCCESS, event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setWrongLb(null);
	}
}
