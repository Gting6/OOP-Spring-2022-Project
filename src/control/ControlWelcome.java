package control;

import javafx.scene.control.Button;
import model.LoginStatus;
import model.Model;
import view.View;

public class ControlWelcome extends Controller{	
	public String username;
	
	
	public ControlWelcome(View view, Model model) {
		super(view, model);
	}
	
	public LoginStatus welcome() {
		
		String state = view.welcome(); // view out the login page
		
		
		if (state == "Submit") {
			// check the input of username and password
			boolean check = false;
			
			if (!check) {
				return LoginStatus.LoginFail;
			}
			return LoginStatus.LoginSuccess;
		}
		
		
		if (state == "Forget") {
			return LoginStatus.JumpToForgetPassword;
		}
		
		if (state == "Register") {
			return LoginStatus.JumpToRegister;
		}
		
		if (state == "Exit") {
			return LoginStatus.Exit;

		}
		
		return LoginStatus.Error;
		
		
	}
	
	
}
