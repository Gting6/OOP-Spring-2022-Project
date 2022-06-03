package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
	private Button couponBtn;

	@FXML
	private Button nameBtn;

	@FXML
	private ComboBox<String> searchCombo;

	@FXML
	private Button typeBtn;

	@FXML
	private TextField searchTf;

	@FXML
	private Button searchTfBtn;

	@FXML
	private Button vipBtn;

	@FXML
	private Button vipConfirmBtn;
	
	@FXML
	private Button orderBtn;

	@FXML
	private VBox displayVb;

	private String username;
	private MemberView status;
	private String tmp = "";
	private SearchBy searchBy = SearchBy.NAME;
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
			couponBtn.setDisable(true);
			searchTf.setText("");
			searchTf.setDisable(true);
			searchTfBtn.setDisable(true);
			nameBtn.setStyle("-fx-background-color: #ffffff");
			couponBtn.setStyle("-fx-background-color: #ffffff");
			typeBtn.setStyle("-fx-background-color: #ffffff");
			distanceBtn.setStyle("-fx-background-color: #ffffff");

//			searchCombo.getItems().setAll("", "Member", "Deliver", "Restaurant"); // set the options
			searchCombo.setValue("");
			searchCombo.setDisable(true);

			vipBtn.setVisible(true);
			vipConfirmBtn.setVisible(false);
			
			displayVb.getChildren().clear();
			Label label13 = new Label(tmp);
			label13.setFont(new Font("Yu Gothic UI Semibold", 15));
			displayVb.getChildren().add(label13);
			searchBy = SearchBy.NAME;
			break;
		case Order:
			nameBtn.setDisable(true);
			typeBtn.setDisable(true);
			couponBtn.setDisable(true);
			distanceBtn.setDisable(true);
			searchTf.setText("");
			searchTf.setDisable(true);
			searchTfBtn.setDisable(true);
			couponBtn.setStyle("-fx-background-color: #ffffff");
			nameBtn.setStyle("-fx-background-color: #ffffff");
			typeBtn.setStyle("-fx-background-color: #ffffff");
			distanceBtn.setStyle("-fx-background-color: #ffffff");
			searchBy = SearchBy.NAME;
//			searchCombo.getItems().setAll("", "Member", "Deliver", "Restaurant"); // set the options			
			searchCombo.setValue("");
			searchCombo.setDisable(true);
			
			
			vipBtn.setVisible(false);
			vipConfirmBtn.setVisible(false);
			tmp = "Order of " + username;
			break;
		case Track:
			nameBtn.setDisable(true);
			typeBtn.setDisable(true);
			couponBtn.setDisable(true);
			distanceBtn.setDisable(true);
			searchTf.setText("");
			searchTfBtn.setDisable(true);
			displayVb.getChildren().clear();
			searchTf.setDisable(true);
			vipBtn.setVisible(false);
			vipConfirmBtn.setVisible(false);
//			searchCombo.getItems().setAll("", "Member", "Deliver", "Restaurant"); // set the options
			searchCombo.setValue("");
			searchCombo.setDisable(true);
			searchBy = SearchBy.NAME;
			couponBtn.setStyle("-fx-background-color: #ffffff");
			nameBtn.setStyle("-fx-background-color: #ffffff");
			typeBtn.setStyle("-fx-background-color: #ffffff");
			distanceBtn.setStyle("-fx-background-color: #ffffff");
			tmp = "Track of " + username;
			break;
		case Search:
			nameBtn.setDisable(false);
			couponBtn.setDisable(false);
			typeBtn.setDisable(false);
			distanceBtn.setDisable(false);
			searchTf.setText("");
			searchTfBtn.setDisable(false);
			searchTf.setDisable(false);
			displayVb.getChildren().clear();
			distanceBtn.setVisible(true);
			nameBtn.setVisible(true);
			typeBtn.setVisible(true);
			vipBtn.setVisible(false);
			vipConfirmBtn.setVisible(false);
//			searchCombo.getItems().setAll("", "Member", "Deliver", "Restaurant"); // set the options
			if (searchBy == SearchBy.COUPON || searchBy == SearchBy.TYPE) {
				searchTf.setDisable(true);
				searchCombo.setDisable(false);				
			}
			else {
				searchCombo.setValue("");
				searchCombo.setDisable(true);				
				searchTf.setDisable(false);
			}

			break;
		default:
			vipBtn.setVisible(false);
			vipConfirmBtn.setVisible(false);
//			searchCombo.getItems().setAll("", "Member", "Deliver", "Restaurant"); // set the options
			searchCombo.setValue("");
			searchCombo.setDisable(true);
			break;
		}
	}

	
	public void pressCouponBtn() throws SQLException {
		// TODO: Coupon Logic
		this.searchBy = SearchBy.COUPON;
		couponBtn.setStyle("-fx-background-color: #7ec6ed");
		nameBtn.setStyle("-fx-background-color: #ffffff");
		distanceBtn.setStyle("-fx-background-color: #ffffff");
		typeBtn.setStyle("-fx-background-color: #ffffff");
		searchCombo.getItems().setAll("九折", "八折", "七折"); // set the options
		searchCombo.setValue("九折");
		searchCombo.setDisable(false);
		searchTf.setDisable(true);
		pressSearchBtn();
	}

	public void pressVipConfirmBtn() {
		// TODO: VIP Logic
		vipBtn.setVisible(true);
		vipConfirmBtn.setVisible(false);
		vipBtn.setDisable(true);
		System.out.println("Congrats! You become VIP");
		render();
	}

	
	public void pressVipBtn() {
		// TODO: VIP Logic
		vipBtn.setVisible(false);
		vipConfirmBtn.setVisible(true);
		
	}

	public void pressDistanceBtn() throws SQLException {
		this.searchBy = SearchBy.DISTANCE;
		distanceBtn.setStyle("-fx-background-color: #7ec6ed");
		nameBtn.setStyle("-fx-background-color: #ffffff");
		couponBtn.setStyle("-fx-background-color: #ffffff");
		typeBtn.setStyle("-fx-background-color: #ffffff");
		searchCombo.setValue("");
		searchCombo.setDisable(true);
		searchTf.setDisable(false);
		pressSearchBtn();
	}

	public void pressNameBtn() throws SQLException {
		this.searchBy = SearchBy.NAME;
		nameBtn.setStyle("-fx-background-color: #7ec6ed");
		couponBtn.setStyle("-fx-background-color: #ffffff");
		distanceBtn.setStyle("-fx-background-color: #ffffff");
		typeBtn.setStyle("-fx-background-color: #ffffff");
		searchCombo.setValue("");
		searchCombo.setDisable(true);
		searchTf.setDisable(false);
		pressSearchBtn();
	}

	public void pressTypeBtn() throws SQLException {
		this.searchBy = SearchBy.TYPE;
		typeBtn.setStyle("-fx-background-color: #7ec6ed");
		distanceBtn.setStyle("-fx-background-color: #ffffff");
		couponBtn.setStyle("-fx-background-color: #ffffff");
		nameBtn.setStyle("-fx-background-color: #ffffff");
		searchCombo.getItems().setAll("日式", "中式", "西式","麵食","便當","甜點", "小吃","餅類","飲料", "其他"); // set the options
		searchCombo.setValue("日式");
		searchCombo.setDisable(false);
		searchTf.setDisable(true);
		pressSearchBtn();
	}

	private void searchHelper(SearchBy searchBy, Model model, String input) {
		ArrayList<Restaurant> result = null;
		Map<Restaurant, Integer> result2 = null;
		Label label13;
		int limit = 10000;

		switch (searchBy) {
		case NAME:
			result = model.SearchRestaurantByName(input);
			break;
		case TYPE:
			result = model.SearchRestaurantByType(input);
			break;
		case DISTANCE:
			result2 = model.SearchRestaurantByDistance(username);
			break;
		default:
			result = model.SearchRestaurantByName(input);
			break;
		}

		if (searchBy == SearchBy.DISTANCE) {
			if (input == null || input.equals("")) {
				limit = 10000;
			} else {
				try {
					limit = Integer.parseInt(input);
//		            System.out.println(limit); // output = 25
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}

			}
			final int tmpLimit = limit; // Since for each require comparison with final argument
			if (result2 != null) {
				displayVb.getChildren().clear();
				result2.forEach((rest, value) -> {
					int time = value / 60;
					if (time < tmpLimit) {
						String qq = rest.getName() + ": " + Integer.toString(time) + " min";
						System.out.println(qq);
						Button label14 = new Button();
						label14.setText(qq);
						label14.setFont(new Font("Yu Gothic UI Semibold", 18));
						try {
							if (qq.getBytes("UTF-8").length < 35) {
								label14.setPrefWidth(275);
//								System.out.println("Setting "+qq);
//								label14.setTextAlignment(TextAlignment.LEFT);
							}
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						label14.setTextAlignment(TextAlignment.CENTER);
						label14.setId(rest.getName().replaceAll("\\s+", "%"));
						label14.setStyle("-fx-background-color: #f5e1a9; -fx-cursor: hand;");
						label14.setOnAction(e -> {
							Button t;
							if (selectedRestaurant != null) {
								t = (Button) label14.getScene().lookup("#" + selectedRestaurant);
								if (t != null) {
									t.setStyle("-fx-background-color: #f5e1a9; -fx-cursor: hand;");

								}
							}
							selectedRestaurant = ((Button) (e.getSource())).getId();

							t = ((Button) e.getSource());
							t.setStyle("-fx-background-color: #7ec6ed; -fx-cursor: hand;");
							;

							System.out.println(((Button) (e.getSource())).getId());
						});
						displayVb.getChildren().add(label14);
						System.out.println(rest.getName());
						System.out.println(rest.getName() + "time: " + value / 60 + " (min)");
					}
//					System.out.println(time);
				});
			} else {
				displayVb.getChildren().clear();
				label13 = new Label("No Result!");
				label13.setFont(new Font("Yu Gothic UI Semibold", 18));
				displayVb.getChildren().add(label13);
			}
		} else {
			displayVb.getChildren().clear();
			if (result != null)
				result.forEach((rest) -> {
					Button label14 = new Button(rest.getName());
					label14.setFont(new Font("Yu Gothic UI Semibold", 18));
					label14.setPrefWidth(270);
					label14.setId(rest.getName().replaceAll("\\s+", "%"));
					label14.setStyle("-fx-background-color: #f5e1a9; -fx-cursor: hand;");
					label14.setOnAction(e -> {
						Button t;
						if (selectedRestaurant != null) {
							t = (Button) label14.getScene().lookup("#" + selectedRestaurant);
							if (t != null) {
								t.setStyle("-fx-background-color: #f5e1a9; -fx-cursor: hand;");

							}
						}
						selectedRestaurant = ((Button) (e.getSource())).getId();

						t = ((Button) e.getSource());
						t.setStyle("-fx-background-color: #7ec6ed; -fx-cursor: hand;");
						;

						System.out.println(((Button) (e.getSource())).getId());
					});

					displayVb.getChildren().add(label14);

					System.out.println(rest.getName());
				});
			else {
				displayVb.getChildren().clear();
				label13 = new Label("No Result!");
				label13.setFont(new Font("Yu Gothic UI Semibold", 18));
				displayVb.getChildren().add(label13);
			}
		}

	}

	public void pressSearchTfBtn() throws SQLException {
		Model model = new Model();
		String input = "";
		if ((searchBy == SearchBy.NAME) || (searchBy == SearchBy.DISTANCE)) {
			input = searchTf.getText();			
		}else {
			input = searchCombo.getValue();
		}
		searchHelper(this.searchBy, model, input);
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
			if (memberInfo.getAddress().length() > 14) {
				tmp += "Address: " + memberInfo.getAddress().substring(0, 14) + "\n";
				tmp += memberInfo.getAddress().substring(14	, memberInfo.getAddress().length()) + "\n";
			} else {
				tmp += "Address: " + memberInfo.getAddress() + "\n";
			}

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
				tmp += "Vip Expired date: ----";
				System.out.println("Vip Expired date: ----");
				vipBtn.setDisable(false);
			} else {
				tmp += "Vip Expired date: " + memberInfo.getVIP_expire_date();
				System.out.println("Vip Expired date: " + memberInfo.getVIP_expire_date());
				vipBtn.setDisable(true);
			}
		} else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
		render();
	}

	public void pressOrderBtn() throws SQLException {
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

		Restaurant restaurant = new Restaurant();
		restaurant.setName(selectedRestaurant);
		// Maybe can be refactor
		restaurant = restaurant.getRestaurantInfoByName();
//		System.out.println(restaurantInfo.getName());
		if (restaurant != null) {

			HashMap<String, Integer> restaurantProduct = restaurant.getProducts();

			if (restaurantProduct != null) {
				// TODO [FX] handle the info fx.
				System.out.println("Products List: ");

				for (String key : restaurantProduct.keySet())
					System.out.println(key + ": $" + restaurantProduct.get(key));

			} else {
				System.out.println("some error occur, getting null");
			}
			System.out.println();
		}

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
		searchBy = SearchBy.NAME;
		displayVb.setSpacing(5);
//		searchCombo.getItems().setAll("", "Member", "Deliver", "Restaurant"); // set the options
		searchCombo.setValue("");
		searchCombo.setDisable(true);
		render();
	}

	private enum SearchBy {
		DISTANCE, NAME, TYPE, COUPON
	}

}
