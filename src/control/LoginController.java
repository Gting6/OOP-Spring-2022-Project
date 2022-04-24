//package control;
//
//import model.Member;
//import model.Restaurant;
//import model.Deliver;
//
//import model.UserType;
//import model.LoginStatus;
//
//public class LoginController {
//	LoginStatus status;
//	String username;
//	private Controller controller;
//
//	public LoginController() {
//
//	}
//
//	public LoginController(LoginStatus login, Controller controller) {
//		this.controller = controller;
//		// Exit
//		if (login == LoginStatus.Exit) {
//			controller.view.bye();
//			status = LoginStatus.Exit;
//		}
//		// Login
//		else if (login == LoginStatus.Login) {
//			status = userLogin();
//		}
//		// Register
//		else if (login == LoginStatus.Register) {
//			// 選擇註冊身份
//			UserType tmp = controller.model.StringToUserType(controller.view.checkUser());
//			if (tmp == UserType.Exit) {
//				status = LoginStatus.Fail;
//			}
//			// 會員註冊
//			else if (tmp == UserType.Member) {
//				status = memberRegister();
//			} else if (tmp == UserType.Restaurant) {
//				// TODO Restaurant Register
//			} else if (tmp == UserType.Deliver) {
//				// TODO Deliver Registar
//
//			} else if (tmp == UserType.Error) {
//				// TODO Error code
//				// Try to do a loop to handle this
//			}
//		} else {
//			controller.view.defaultView("Error");
//			status = LoginStatus.Fail;
//		}
//
//	}
//
//	public LoginController(Member member) {
//
//	}
//
//	public LoginController(Restaurant resturant) {
//
//	}
//
//	public LoginController(Deliver deliver) {
//
//	}
//
//	private LoginStatus userLogin() {
//		// login table?
//		for (int i = 0; i < 5; i++) {
//			controller.view.defaultView("Login Section");
//			String username = "";
//			for (int j = 0; j < 5; j++) {
//				username = controller.view.usernameInput();
//				if (controller.model.searchUserByUserName(username)) {
//					break;
//				} else {
//					if (j == 4) {
//						controller.view.defaultView("LoginFail, bye");
//						return LoginStatus.Exit;
//					}
//					controller.view.defaultView("No user, please retry!");
//				}
//			}
//
//			// 是否限制密碼輸入次數 用時間鎖起來
//			String password = controller.view.passwordInput(0);
//			if (controller.model.checkPassword(username, password)) {
//				controller.view.defaultView("Welcome, " + username + "!");
//				this.username = username;
//				return LoginStatus.Login;
//			} else {
//				controller.view.defaultView("The password is error, please retry");
//			}
//		}
//
//		// 可以優化鎖帳號
//		controller.view.defaultView("You try to type a lot of time");
//		return LoginStatus.Exit;
//	}
//	
//	public LoginStatus memberRegister() {
//		controller.view.defaultView("Register Section");
//		
//		String username = "";
//		for (int i = 0; i < 5; i++) {
//			username = controller.view.usernameInput();
//			if (controller.model.searchUserByUserName(username)) {
//				if (i == 4) {
//					controller.view.defaultView("Register error, bye");
//					return LoginStatus.Exit;
//				}
//				controller.view.defaultView("Username collision, please Retry it");
//			}
//			else {
//				break;
//			}
//		}
//
//		// 這邊要測試是否hash中有出現重複的 username, 用search
//		String password = "";
//		String password2 = "";
//		for (int i = 0; i < 5; i++) {
//			password = controller.view.passwordInput(0);
//			password2 = controller.view.passwordInput(1);
//			
//			if (password.equals(password2)) {
//				break;
//			}
//			else {
//				if (i == 5) {
//					controller.view.defaultView("Register error, bye");
//					return LoginStatus.Exit;
//				}
//				// 懶得寫view 偷吃步
//				controller.view.defaultView("The password error, retry to type them");
//			}
//		}
//		
//		// check number?
//		String telephone = controller.view.telephoneNumberInput();
//		
//		// check email type
//		String email = controller.view.emailInput();
//		
//		// check name type
//		String name = controller.view.nameInput();
//		
//		// save this in database
//		Member member = new Member(username, password, telephone, email, name);
//		
//		controller.model.addUser(member);
//		
//		controller.view.defaultView("註冊成功, 返回頁面");
//		
//		return LoginStatus.Register;
//	}
//
//}
