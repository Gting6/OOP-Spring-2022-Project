package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.DeliveryMan;
import model.Member;
import model.Model;
import model.UserType;
import view.LoginView;

public class SignupSuccessController extends Controller implements Initializable {

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
	private TextField addressTf;

	@FXML
	private ComboBox<String> typeCombo;

	@FXML
	private Label wrongLb;

	@FXML
	private Button backBtn;

	@FXML
	private Button confirmBtn;

	private LoginView status;
	private UserType usertype;

	@Override
	protected void render() {
		if (status == LoginView.SignUp) {
			usernameTf.setVisible(true);
			passwordTf.setVisible(true);
			addressTf.setVisible(true);
			confirmTf.setVisible(true);
			wrongLb.setVisible(false);
			emailTf.setVisible(true);
			phoneTf.setVisible(true);
			nameTf.setVisible(true);
			typeCombo.setVisible(true);
			confirmBtn.setVisible(true);

		} else { // sign up success
			usernameTf.setVisible(false);
			passwordTf.setVisible(false);
			addressTf.setVisible(false);
			confirmTf.setVisible(false);
			wrongLb.setVisible(true);
			emailTf.setVisible(false);
			phoneTf.setVisible(false);
			nameTf.setVisible(false);
			typeCombo.setVisible(false);
			confirmBtn.setVisible(false);
		}
	}

	public void pressBackBtn(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	private void setWrongLb(String s) {
		wrongLb.setText(s);
		wrongLb.setVisible(true);
	}

	public void pressConfirmBtn(ActionEvent event) throws IOException {
		Model model = new Model();
		usernameTf.getText();
		passwordTf.getText();
		confirmTf.getText();

		// TODO [FX] need add the Tf of address
		String address = addressTf.getText();
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

			if (!Pattern.matches(pattern, usernameTf.getText()) || !Pattern.matches(pattern, passwordTf.getText())) {
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
				Member member = new Member(usernameTf.getText(), passwordTf.getText(), address, phone, email, name);
				if (!model.checkMemberInWhenRegister(member.getUserName(), member.getPassword())
						&& !model.checkDeliverManWhenRegister(member.getUserName())) {
					model.addMember(member);
					// TODO (done) [FX] Success, show sign-up success, and give a button to back to
					// login
					// page.
					setWrongLb("Successfully sign up!, press Go back to login!");
					status = LoginView.Success;
					render();
					// switchScene(ViewEnum.LOGIN, event);
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
				DeliveryMan deliveryman = new DeliveryMan(usernameTf.getText(), passwordTf.getText(), address, phone,
						email, name);
				if (!model.checkDeliverManWhenRegister(deliveryman.getUserName())
						&& !model.checkMemberInWhenRegister(deliveryman.getUserName(), deliveryman.getPassword())) {
					model.addDeliverMan(deliveryman);
					setWrongLb("Successfully sign up!, press Go back to login!");
					status = LoginView.Success;
					render();
//					switchScene(ViewEnum.LOGIN, event);
					System.out.println(model.checkDeliverManWhenRegister(deliveryman.getUserName()));
				} else {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = LoginView.SignUp;
		typeCombo.getItems().setAll("Member", "Deliver", "Restaurant"); // set the options
		typeCombo.setValue("Member");
		render();
	}
}
