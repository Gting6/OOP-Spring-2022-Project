package control;

import view.View;
import control.ControlWelcome;

import model.*;

public class Controller {
	ResponseStatus status = ResponseStatus.Null;
	protected View view;
	protected Model model;
	
	public Controller() {
		view = new View();
		model = new Model();
	}
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}
	public void controlMain(){
		// Login Section 仍可封裝Control 但先放著避免錯誤
		ControlWelcome welcome = new ControlWelcome(view, model);
		// 可能用stack處理這邊, viewLogin裡面放有錯和沒錯版本，如果失敗就抽一個補一個，返回就抽一個？
		LoginStatus loginStatus = welcome.welcome();
		
		if (loginStatus == LoginStatus.LoginSuccess) {
			// Jump to 成功
			// Check 誰登入了
			// Jump 管理系統controller
			// 4 ways
		}
		
		if (loginStatus == LoginStatus.LoginFail) {
			// 這要怎麼處理才不會刷掉?
			// 可能在call一次ControlWelcome然後view傳新的進去？
		}
		
		if (loginStatus == LoginStatus.JumpToForgetPassword) {
			
		}
		
		if (loginStatus == LoginStatus.JumpToRegister) {
			
		}
					
	}
}
