package application;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Member;
import model.Model;
import model.Restaurant;
import view.MemberView;

public class MemberController extends Controller implements Initializable {
	@FXML
	private Button logoutBtn;

	@FXML
	private Label welcomeLb;

	@FXML
	private Button trackBtn;

	@FXML
	private Button infoBtn;

	@FXML
	private Button searchBtn;

	@FXML
	private Button distanceBtn;

	@FXML
	private Button nameBtn;

	@FXML
	private Button typeBtn;

	@FXML
	private TextField searchTf;

	@FXML
	private Button searchTfBtn;

	@FXML
	private Button orderBtn;

	@FXML
	private VBox displayVb;

	private String username;
	private MemberView status;
	private String tmp = "";
	private int searchBy = 1;
	private String selectedRestaurant;
	
	public void logout(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}
	

//	public EventHandler<ActionEvent> setSelectedRestaurant(String s) {
//		selectedRestaurant = s;
//		
//		return null;
//	}
	
	@Override
	protected void setUsernameLb(String s) {
		username = s;
		String tmp = "Hello, Member " + s + "\nWelcome to Footopia!";
		welcomeLb.setText(tmp);
	}

	@Override
	protected void render() {
		switch (status) {
		case Info:
			nameBtn.setDisable(true);
			typeBtn.setDisable(true);
			distanceBtn.setDisable(true);
			searchTf.setDisable(true);
			searchTfBtn.setDisable(true);
			nameBtn.setStyle("-fx-background-color: #ffffff");
			typeBtn.setStyle("-fx-background-color: #ffffff");
			distanceBtn.setStyle("-fx-background-color: #ffffff");

			displayVb.getChildren().clear();
			Label label13 = new Label(tmp);
			label13.setFont(new Font("Agency FB", 24));
			displayVb.getChildren().add(label13);
			searchBy = 1;
			break;
		case Order:
			nameBtn.setDisable(true);
			typeBtn.setDisable(true);
			distanceBtn.setDisable(true);
			searchTf.setDisable(true);
			searchTfBtn.setDisable(true);
			nameBtn.setStyle("-fx-background-color: #ffffff");
			typeBtn.setStyle("-fx-background-color: #ffffff");
			distanceBtn.setStyle("-fx-background-color: #ffffff");
			searchBy = 1;
			tmp = "Order of " + username;
			break;
		case Track:
			nameBtn.setDisable(true);
			typeBtn.setDisable(true);
			distanceBtn.setDisable(true);
			searchTfBtn.setDisable(true);
			displayVb.getChildren().clear();
			searchTf.setDisable(true);
			searchBy = 1;
			nameBtn.setStyle("-fx-background-color: #ffffff");
			typeBtn.setStyle("-fx-background-color: #ffffff");
			distanceBtn.setStyle("-fx-background-color: #ffffff");
			tmp = "Track of " + username;
			break;
		case Search:
			nameBtn.setDisable(false);
			typeBtn.setDisable(false);
			distanceBtn.setDisable(false);
			searchTfBtn.setDisable(false);
			searchTf.setDisable(false);
			displayVb.getChildren().clear();
			distanceBtn.setVisible(true);
			nameBtn.setVisible(true);
			typeBtn.setVisible(true);
		
			break;
		default:
			break;
		}
	}

	public void pressDistanceBtn() throws SQLException {
		this.searchBy = 0;
		distanceBtn.setStyle("-fx-background-color: #7ec6ed");
		nameBtn.setStyle("-fx-background-color: #ffffff");
		typeBtn.setStyle("-fx-background-color: #ffffff");
		pressSearchBtn();
	}

	public void pressNameBtn() throws SQLException {
		this.searchBy = 1;
		nameBtn.setStyle("-fx-background-color: #7ec6ed");
		distanceBtn.setStyle("-fx-background-color: #ffffff");
		typeBtn.setStyle("-fx-background-color: #ffffff");
		pressSearchBtn();
	}

	public void pressTypeBtn() throws SQLException {
		this.searchBy = 2;
		typeBtn.setStyle("-fx-background-color: #7ec6ed");
		distanceBtn.setStyle("-fx-background-color: #ffffff");
		nameBtn.setStyle("-fx-background-color: #ffffff");
		pressSearchBtn();
	}

	private void searchHelper(int searchBy, Model model, String input) {
		ArrayList<Restaurant> result;
		switch (searchBy){
		case 1:
			result = model.SearchRestaurantByName(input);
			break;
		case 2:
			result = model.SearchRestaurantByType(input);
			break;
		default:
			result = model.SearchRestaurantByName(input);
			break;			
		}
	
		displayVb.getChildren().clear();
		if (result != null)
			result.forEach((rest) -> {
				Button label13 = new Button(rest.getName());
				label13.setFont(new Font("Yu Gothic UI Semibold", 18));
				label13.setPrefWidth(270);
				label13.setId(rest.getName().replaceAll("\\s+","%"));
			    label13.setStyle("-fx-background-color: #f5e1a9; -fx-cursor: hand;");
			    label13.setOnAction(e -> {
			    	Button t;
			    	if (selectedRestaurant != null) {
				    	t = (Button) label13.getScene().lookup("#" + selectedRestaurant);
				    	if (t!=null) {
					    	t.setStyle("-fx-background-color: #f5e1a9; -fx-cursor: hand;");			    	
			
				    	}
			    	}
			    	selectedRestaurant = ((Button) (e.getSource())).getId();
			    	
			    	t = ((Button) e.getSource());
			    	t.setStyle("-fx-background-color: #7ec6ed; -fx-cursor: hand;");;
			    	
			    	System.out.println(((Button) (e.getSource())).getId());
			    });
				
				displayVb.getChildren().add(label13);

				System.out.println(rest.getName());
			});
		else {
			displayVb.getChildren().clear();
			Label label13 = new Label("No Result!");
			label13.setFont(new Font("Yu Gothic UI Semibold", 18));
			displayVb.getChildren().add(label13);
		}

	}
	
	public void pressSearchTfBtn() throws SQLException {
		Model model = new Model();
		String input = searchTf.getText();
		if (this.searchBy == 0) {
			// Search By Distance given location in Eng, maybe test chinese
			Map<Restaurant, Integer> result = model.SearchRestaurantByDistance(username);
			// min second or Hr
			if (result != null) {
				result.forEach((key, value) -> {
					System.out.println(key.getName() + "time: " + value / 60 + " (min)");
				});
			}
		} else {
			searchHelper(this.searchBy, model, input);
		}
	}

	public void pressSearchBtn() throws SQLException {
		status = MemberView.Search;
//		tmp = "";
//		String tmp2 = "Hello, Member " + "\nWelcome to Footopia!";
//		welcomeLb.setText(tmp2);
		render();
	}

	public void pressInfoBtn() throws SQLException {
		status = MemberView.Info;
		Member member = new Member(this.username);
		// Maybe can be refactor
		Member memberInfo = member.getMemberInfo();
		if (memberInfo != null) {
			// TODO [FX] handle the info fx.
			tmp = "Username: " + memberInfo.getUserName() + "\n";
			tmp += "Address: " + memberInfo.getAddress() + "\n";
			tmp += "Phone: " + memberInfo.getPhone() + "\n";
			tmp += "Email: " + memberInfo.getEmail() + "\n";
			tmp += "Name: " + memberInfo.getName() + "\n";

			System.out.println("Username: " + memberInfo.getUserName());
			System.out.println("Address: " + memberInfo.getAddress());
			System.out.println("Phone: " + memberInfo.getPhone());
			System.out.println("Email: " + memberInfo.getEmail());
			System.out.println("Name: " + memberInfo.getName());
			if (memberInfo.getVIP_expire_date() == null) {
				// Maybe think a good expression for vip date.
				System.out.println("Vip Expired date: ----");
			} else {
				System.out.println("Vip Expired date: " + memberInfo.getVIP_expire_date());
			}
		} else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
		render();
	}

	public void pressOrderBtn() {
		status = MemberView.Order;
		Label label13 = new Label("");
		if (selectedRestaurant != null) {
			label13 = new Label(selectedRestaurant.replaceAll("%", " ") + "\nMenu: \n");
		} else {
			label13 = new Label("Please search restaurant first !");
		}
		displayVb.getChildren().clear();
		label13.setFont(new Font("Yu Gothic UI Semibold", 18));
		displayVb.getChildren().add(label13);			
    	

		System.out.println(selectedRestaurant);	
		render();
	}

	public void pressTrackBtn() {
		status = MemberView.Track;
		render();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = MemberView.Default;
		searchBy = 1;
		displayVb.setSpacing(5);
		render();
	}
}
