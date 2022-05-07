package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.LoginView;

public class SignupSuccessController extends Controller implements Initializable {
	@FXML
	private Button backBtn;

	@FXML
	private Label welcomeLb;

	public void pressBtn(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	@Override
	protected void setUsernameLb(String s) {
		String tmp = "Welcome, new user " + s;
		welcomeLb.setText(tmp);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
}
