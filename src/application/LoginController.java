package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Member;
import model.DeliveryMan;
import model.Model;
import model.Restaurant;
import model.UserType;
import view.LoginView;

public class LoginController extends Controller implements Initializable {
	private LoginView status;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Label confirmLb;

	@FXML
	private Label wrongLb;

	@FXML
	private Label emailLb;

	@FXML
	private Label phoneLb;

	@FXML
	private Label nameLb;

	@FXML
	private TextField usernameTf;

	@FXML
	private TextField passwordTf;

	@FXML
	private TextField confirmTf;

	@FXML
	private TextField nameTf;

	@FXML
	private TextField phoneTf;

	@FXML
	private TextField emailTf;

	@FXML
	private ComboBox<String> typeCombo;

	private UserType usertype;

	// To set the error message for wrongLb
	// Can be extend to input label.
	private void setWrongLb(String s) {
		wrongLb.setText(s);
	}

	// Helper function: render by status
	@Override
	protected void render() {
		if (status == LoginView.Login) {
			btn1.setText("Log in");
			btn2.setText("Sign up");
			confirmTf.setVisible(false);
			confirmLb.setVisible(false);
			wrongLb.setVisible(false);
			emailTf.setVisible(false);
			phoneTf.setVisible(false);
			nameTf.setVisible(false);
			emailLb.setVisible(false);
			phoneLb.setVisible(false);
			nameLb.setVisible(false);
			typeCombo.setVisible(false);
		} else if (status == LoginView.SignUp) {
			btn1.setText("Go Back");
			btn2.setText("Confirm");
			confirmTf.setVisible(true);
			confirmLb.setVisible(true);
			wrongLb.setVisible(false);
			emailTf.setVisible(true);
			phoneTf.setVisible(true);
			nameTf.setVisible(true);
			emailLb.setVisible(true);
			phoneLb.setVisible(true);
			nameLb.setVisible(true);
			typeCombo.setVisible(true);
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
				} 
				else if (model.checkDeliverManrLoginIn(usernameTf.getText(), passwordTf.getText())) {
					switchScene(ViewEnum.DELIVER, event, usernameTf.getText());
					System.out.println("Login success as Deliver");
				}
				else if (model.checkRestaurantLoginIn(usernameTf.getText(), passwordTf.getText())) {
					switchScene(ViewEnum.RESTAURANT, event, model.getRestaurant(usernameTf.getText()).getName());
					System.out.println("Login success as Restaurant");
				}
				else {
					setWrongLb("Login Fail");
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
		} else if (status == LoginView.SignUp) {
			Model model = new Model();
			usernameTf.getText();
			passwordTf.getText();
			confirmTf.getText();

			// TODO [FX] need add the Tf of address
			String address = "abc";
			// TODO [FX](done) need add the Tf of phoneNumber
			String phone = phoneTf.getText();
			// TODO [FX](done) need add the Tf of email
			String email = emailTf.getText();

			// TODO [FX](done) need add the Tf of name
			String name = nameTf.getText();

			String pattern = "[0-9a-zA-Z]+";
			
			String phonePattern = "09[0-9]{8}";
			String emailPattern = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";

			System.out.println(passwordTf.getText());
			System.out.println(confirmTf.getText());

			// TODO (done) [FX] Need a <select> in css to check which type(member, rest,
			// deliver...) the user want to sign-up.
			// usertype = UserType.Member;
			switch (typeCombo.getValue()) {
			case "Member":
				usertype = UserType.Member;
				break;
			case "Deliver":
				usertype = UserType.Deliver;
				break;
			case "Restaurant":
				usertype = UserType.Restaurant;
				break;
			default:
				usertype = UserType.Error;
				break;
			}

			while (true) {

				if (!Pattern.matches(pattern, usernameTf.getText())
						|| !Pattern.matches(pattern, passwordTf.getText())) {
					// TODO [Fx](done) The string in wrongLb can be change to the string mentioned
					// below.
					setWrongLb("Error pattern in username or password");
					wrongLb.setVisible(true);
					break;
				}
				
				if (!Pattern.matches(phonePattern, phone)) {
					setWrongLb("Error pattern in phone");
					wrongLb.setVisible(true);
					break;
				}
				
				if (!Pattern.matches(emailPattern, email)) {
					setWrongLb("Error pattern in email");
					wrongLb.setVisible(true);
					break;
				}

				if (!passwordTf.getText().equals(confirmTf.getText())) {
					// TODO [Fx](done) The string in wrongLb can be change to the string mentioned
					// below.
					setWrongLb("Error password and confirm password");
					wrongLb.setVisible(true);
					break;
				}
				if (usertype == UserType.Member) {
					Member member = new Member(usernameTf.getText(), passwordTf.getText(),address, phone, email, name);
					if (!model.checkMemberInWhenRegister(member.getUserName(), member.getPassword()) && !model.checkDeliverManWhenRegister(member.getUserName())) {
						model.addMember(member);
						// TODO (done) [FX] Success, show sign-up success, and give a button to back to
						// login
						// page.
						switchScene(ViewEnum.SIGNUPSUCCESS, event, member.getUserName());
						System.out.println(model.checkMemberInWhenRegister(member.getUserName(), member.getPassword()));
						// switchScene(ViewEnum.MEMBER, event, member.getUserName());
					} else {
						// TODO (done) [Fx] The string in wrongLb can be change to the string mentioned
						// below.
						setWrongLb("Fail to register member");
						wrongLb.setVisible(true);
					}
					break;
				}
				
				if (usertype == UserType.Deliver) {
					DeliveryMan deliveryman= new DeliveryMan(usernameTf.getText(), passwordTf.getText(),address, phone, email, name);
					if (!model.checkDeliverManWhenRegister(deliveryman.getUserName()) && !model.checkMemberInWhenRegister(deliveryman.getUserName(), deliveryman.getPassword())) {
						model.addDeliverMan(deliveryman);
						switchScene(ViewEnum.SIGNUPSUCCESS, event, deliveryman.getUserName());
						System.out.println(model.checkDeliverManWhenRegister(deliveryman.getUserName()));
					}
					else {
						setWrongLb("Fail to register deliveryman");
						wrongLb.setVisible(true);
					}
					break;
				}
				
				if (usertype == UserType.Restaurant) {
					setWrongLb("Not allow Restaurant to register now");
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
		typeCombo.getItems().setAll("Member", "Deliver", "Restaurant"); // set the options
		typeCombo.setValue("Member");
		render();
	}
}
