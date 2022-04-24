package view;

import java.util.Scanner;

import javafx.scene.control.Button;

public class View {
	
	private final Scanner input = new Scanner(System.in);
	
	Button A;
	Button B;
	
	// Can be refactor to other class
	// and handle the input and button, return to handle button
	public String welcome() {
		System.out.println("                  Welcome");
		System.out.println("Username:");
		System.out.println("Password:");
		System.out.println("                  Forget password");
		System.out.println("                  Register");

		System.out.println("                  Submit");
		System.out.println("                  Exit");
		
		// check the button to be click
		
		// The status
		return "Submit";
	}
	
	public void bye() {
		System.out.println("Bye");
	}
	
	public String login() {
		System.out.println("0-Exit, 1-Login, 2-Register");
		return input.nextLine();
	}
	
	public String checkUser() {
		System.out.println("0-back, 1-會員, 2-商家, 3-外送員");
		return input.nextLine();
	}
	
//	public String register() {
//		String out = "";		
//	}
	
	public String usernameInput() {
		System.out.println("Username:");
		return input.nextLine();
	}
	
	public String passwordInput(int a) {
		// 可改特定字串篩選
		// 數字邊界要判定
		if (a == 0) {
			System.out.println("Password(only for Interger)");

		}
		else {
			System.out.println("Retype again Password(only for Interger)");
		}
		
		return input.nextLine();
		
	}
	
	public String telephoneNumberInput() {
		System.out.println("Telephone_number_:");
		return input.nextLine();
	}
	
	public String emailInput() {
		System.out.println("Email:");
		return input.nextLine();
	}
	
	public String nameInput() {
		System.out.println("Name:");
		return input.nextLine();
	}
	
	public void defaultView(String a) {
		System.out.println(a);
	}
}
