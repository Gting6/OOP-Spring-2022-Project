package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Member;
import model.Model;
import model.Order;
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
	private Button goBtn;
	
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
	
//	@FXML
//	private Button orderBtn;

	@FXML
	private VBox displayVb;

	private String username;
	private MemberView status;
	private String tmp = "";
	private SearchBy searchBy = SearchBy.NAME;
	private String selectedRestaurant;
	private Integer count = 0;
	private int tmpCount = 0; // for dynamically alter displayVb's height
	private int tmpCount2 = 0; // for dynamically alter displayVb's width
	
	
	
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
			displayVb.setPrefHeight(250);
			displayVb.setPrefWidth(275);
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
			goBtn.setVisible(false);
			break;
		case Order:
			displayVb.setPrefHeight(250);
			displayVb.setPrefWidth(275);
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
			goBtn.setVisible(false);

			
			vipBtn.setVisible(false);
			vipConfirmBtn.setVisible(false);
			tmp = "Order of " + username;
			break;
		case Track:
			displayVb.setPrefHeight(250);
			displayVb.setPrefWidth(800);
			goBtn.setVisible(false);
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
			displayVb.setPrefHeight(250);
			displayVb.setPrefWidth(275);
			goBtn.setVisible(false);
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
			displayVb.setPrefHeight(250);
			displayVb.setPrefWidth(275);
			vipBtn.setVisible(false);
			goBtn.setVisible(false);
			vipConfirmBtn.setVisible(false);
//			searchCombo.getItems().setAll("", "Member", "Deliver", "Restaurant"); // set the options
			searchCombo.setValue("");
			searchCombo.setDisable(true);
			break;
		}
	}

	public void pressAddToCartBtn(Scene scene) {
		try {
			Restaurant restaurant = new Restaurant();
			restaurant.setName(selectedRestaurant.replaceAll("%", " "));
			restaurant = restaurant.getRestaurantInfoByName();
			String restaurantName = restaurant.getUserName();
			Order order = new Order(this.username, restaurantName);
			
			HashMap<String, Integer> restaurantProduct = restaurant.getProducts();
			tmp = "Your order: \n";
			restaurantProduct.forEach((rest, value) -> {
				ComboBox<Integer> tmpCom = (ComboBox<Integer>) scene.lookup("#" + rest);
				System.out.println("Add to Cart " + rest + " x " + tmpCom.getValue());		
				count += tmpCom.getValue() * value;
				if (tmpCom.getValue() > 0) {
					tmp += rest + " x " + tmpCom.getValue().toString() + "\n";
					tmpCount += 1;
				}
				// [MING] please modify the cart logic here, thx.
				
				System.out.println(rest);
				System.out.println(tmpCom.getValue());
				System.out.println(value);
				for (int i = 0; i < tmpCom.getValue(); i++) {
					HashMap<String, Integer> tmp = new HashMap<String, Integer>();
					tmp.put(rest, value);
					order.addToCart(tmp);
				}
				
			});
			
			order.showOrder();
			System.out.println("Order with Fee is " + order.getFee());
			if (count == 0) {
				System.out.println("You should at least select one item!");
				displayVb.getChildren().clear();
				Label tmp = new Label("You should at least select one item");
				tmp.setFont(new Font("Yu Gothic UI Semibold", 16));
				displayVb.getChildren().add(tmp);
			} else {
				System.out.println("order without fee is" + count.toString());
				System.out.println("Success! you need to pay $" + order.getFee());

				displayVb.getChildren().clear();
				tmp += "----------------------------------\n";
				tmp += "$:" + count.toString() + "\n";
				Label tmpLb = new Label(tmp);
				tmp = "";
				tmpLb.setFont(new Font("Yu Gothic UI Semibold", 16));
				HBox hb = new HBox(8);
				Button btn1 = new Button("Pay");
				btn1.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 0.2 0.2 0.2 0.2; -fx-effect: dropshadow( three-pass-box, rgba(0, 0, 0, 0.6), 5, 0.0, 0, 1); -fx-cursor: hand");
				btn1.setOnAction(e -> {
					displayVb.getChildren().clear();
					order.establishOrder();
					Member member = new Member(this.username);
					Order checkorder = member.checkOrderStatus(order.getId());
					System.out.println("You have pay for " + checkorder.getFee());

					tmp = "Success, please go to \"track\" to \ncheck your order!";
					Label tmpLb2 = new Label(tmp);
					tmp = "";
					tmpLb2.setFont(new Font("Yu Gothic UI Semibold", 16));
					displayVb.getChildren().add(tmpLb2);
				});

				Button btn2 = new Button("Cancel");
				btn2.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 0.2 0.2 0.2 0.2; -fx-effect: dropshadow( three-pass-box, rgba(0, 0, 0, 0.6), 5, 0.0, 0, 1); -fx-cursor: hand");
				btn2.setOnAction(e -> {
					displayVb.getChildren().clear();
					tmp = "Order canceled, please search \nrestaurant again!";
					Label tmpLb3 = new Label(tmp);
					tmp = "";
					tmpLb3.setFont(new Font("Yu Gothic UI Semibold", 16));
					displayVb.getChildren().add(tmpLb3);
				});
				hb.getChildren().addAll(btn1, btn2);
				displayVb.setPrefHeight(150 + tmpCount * 30);
				tmpCount = 0;
				displayVb.getChildren().add(tmpLb);
				displayVb.getChildren().add(hb);
			}
			count = 0;
		} catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	public void pressGoBtn() throws SQLException {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(selectedRestaurant.replaceAll("%", " "));
		System.out.println("Go!" + selectedRestaurant.replaceAll("%", "\\s+"));
		// Maybe can be refactor
		restaurant = restaurant.getRestaurantInfoByName();
		HashMap<String, Integer> restaurantProduct = null;
		//		System.out.println(restaurantInfo.getName());
		if (restaurant != null) {

			restaurantProduct = restaurant.getProducts();

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

		
		if (restaurantProduct != null) {
			displayVb.getChildren().clear();
			restaurantProduct.forEach((rest, value) -> {
				System.out.println(rest + " " + value);
			
//				String qq = rest.getName() + ": " + Integer.toString(time) + " min";
//				System.out.println(qq);
				HBox hbox = new HBox(8);
				hbox.setPrefWidth(275);
				Label label14 = new Label();
				label14.setText(rest + " $" + value);
				label14.setFont(new Font("Yu Gothic UI Semibold", 18));
				try {
					if (rest.getBytes("UTF-8").length < 35) {
						label14.setPrefWidth(220);
					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				label14.setStyle("-fx-background-color: #e3d5ca; -fx-text-alignment: center;");
				ComboBox<Integer> numberCombo = new ComboBox<Integer> ();
				numberCombo.getItems().setAll(0, 1, 2, 3, 4, 5); // set the options
				numberCombo.setValue(0);
				numberCombo.setMaxWidth(10);
				numberCombo.setMaxHeight(3);
				numberCombo.setId(rest);
				System.out.println("Add id " + "#" + rest);
				numberCombo.setStyle("-fx-background-color: rgba(0,0,0,0);  -fx-border-color: #e3d5ca; -fx-border-width: 0.5 0.5 0.5 0.5;-fx-text-color: black; ");
				hbox.getChildren().addAll(label14, numberCombo);
				displayVb.getChildren().add(hbox);
			});
			Button b = new Button("Add to Cart!");
			b.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 0.2 0.2 0.2 0.2; -fx-effect: dropshadow( three-pass-box, rgba(0, 0, 0, 0.6), 5, 0.0, 0, 1); -fx-cursor: hand");
			b.setOnAction(e -> {
				pressAddToCartBtn(b.getScene());
			});
			displayVb.getChildren().add(b);
			
		} else {
			Label label13;
			System.out.println("Something Wrong! No Product!");
			displayVb.getChildren().clear();
			label13 = new Label("No Result!");
			label13.setFont(new Font("Yu Gothic UI Semibold", 18));
			displayVb.getChildren().add(label13);
		
		}
		goBtn.setVisible(false);
	}
	
	public void pressCouponBtn() throws SQLException {
		// TODO: Coupon Logic
		this.searchBy = SearchBy.COUPON;
		couponBtn.setStyle("-fx-background-color: #7ec6ed");
		nameBtn.setStyle("-fx-background-color: #ffffff");
		distanceBtn.setStyle("-fx-background-color: #ffffff");
		typeBtn.setStyle("-fx-background-color: #ffffff");
//		searchCombo.getItems().setAll("九折", "八折", "七折"); // set the options
//		searchCombo.setValue("九折");
		searchCombo.getItems().setAll("滿200九折", "滿300八折", "省20", "省30"); // set the options
		searchCombo.setValue("滿200九折");
		searchCombo.setDisable(false);
		searchTf.setDisable(true);
		pressSearchBtn();
	}

	public void pressVipConfirmBtn() throws SQLException {
		// TODO: VIP Logic
		vipBtn.setVisible(true);
		vipConfirmBtn.setVisible(false);
		vipBtn.setDisable(true);
		Member member = new Member(this.username);
		try {
			member.becomeVIP();
			System.out.println("Congrats! You become VIP");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fail to become Vip");
		}
		render();
		pressInfoBtn();
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
		case COUPON:
			result = model.SearchRestaurantByCoupon(input);
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
							
							goBtn.setVisible(true);
							System.out.println(((Button) (e.getSource())).getId());
						});
						displayVb.getChildren().add(label14);
						System.out.println(rest.getName());
						System.out.println(rest.getName() + "time: " + value / 60 + " (min)");
					} else {
						displayVb.getChildren().clear();
						Label tmp = new Label("No Result!");
						tmp.setFont(new Font("Yu Gothic UI Semibold", 18));
						displayVb.getChildren().add(tmp);
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
						goBtn.setVisible(true);
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

//	public void pressOrderBtn() throws SQLException {
//		status = MemberView.Order;
//		Label label13 = new Label("");
//		if (selectedRestaurant != null) {
//			label13 = new Label(selectedRestaurant.replaceAll("%", " ") + "\nMenu: \n");
//		} else {
//			label13 = new Label("Please search restaurant first !");
//		}
//		displayVb.getChildren().clear();
//		label13.setFont(new Font("Yu Gothic UI Semibold", 18));
//		displayVb.getChildren().add(label13);
//		
//		render();
//	}

	public void pressTrackBtn() {
		status = MemberView.Track;
		Member member = new Member(this.username);
		// Maybe can be refactor
		render();
		displayVb.getChildren().clear();
		displayVb.setPrefWidth(1000);
		tmpCount2 = 0;
	    TableView<Order> tableView = new TableView<Order>();

		// Get all order_id
		ArrayList<String> order_ids = member.checkAllOrders();
		
		
		// Show all order_id
		order_ids.forEach(order -> System.out.println(order));
		
		// Get all order object by order_id
		ArrayList<Order> orders = new ArrayList<Order>();
		order_ids.forEach(order -> orders.add(member.checkOrderStatus(order)));
				
	    TableColumn<Order, String> restaurantColumn = new TableColumn<>("Restaurant");
	    restaurantColumn.setCellValueFactory(new PropertyValueFactory<>("restaurant_name"));
		tableView.getColumns().add(restaurantColumn);
		
		TableColumn<Order, String> deliveryManColumn = new TableColumn<>("Delivery");
	    deliveryManColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryman_id"));
		tableView.getColumns().add(deliveryManColumn);
	    
		TableColumn<Order, Timestamp> createTimeColumn = new TableColumn<>("Create Time");
		createTimeColumn.setCellValueFactory(new PropertyValueFactory<>("create_time"));
		tableView.getColumns().add(createTimeColumn);


		TableColumn<Order, Timestamp> deliverTimeColumn = new TableColumn<>("Deliver Time");
		deliverTimeColumn.setCellValueFactory(new PropertyValueFactory<>("deliver_time"));
		tableView.getColumns().add(deliverTimeColumn);
		
		TableColumn<Order, Timestamp> arrivalTimeColumn = new TableColumn<>("Arrival Time");
		arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
		tableView.getColumns().add(arrivalTimeColumn);

		TableColumn<Order, String> statusColumn = new TableColumn<>("Status");
	    statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusToString"));
		tableView.getColumns().add(statusColumn);
		
		TableColumn<Order, String> detailColumn = new TableColumn<>("Details");
		detailColumn.setCellValueFactory(new PropertyValueFactory<>("items"));
		tableView.getColumns().add(detailColumn);
		
		// show all detail
		orders.forEach(order->{
			tableView.getItems().add(order);
			System.out.println("---------------------");
			tmpCount2 = max(tmpCount2, 1000+order.getItems().length() * 10);
			System.out.println(order.getId());
			System.out.println(order.getStatus());
			System.out.println(order.getConsumer_id());
			System.out.println(order.getDeliveryman_id());
			System.out.println(order.getRestaurant_id());
			System.out.println(order.getCreate_time());
			System.out.println(order.getDeliver_time());
			System.out.println(order.getArrival_time());
		});
		displayVb.setPrefWidth(tmpCount2);
		displayVb.getChildren().add(tableView);

		
	}

	private int max(int tmpCount22, int i) {
		// TODO Auto-generated method stub
		return (tmpCount22 > i)? tmpCount22: i;
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
