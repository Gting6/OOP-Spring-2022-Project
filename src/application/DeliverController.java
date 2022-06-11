package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.DeliveryMan;
import model.Order;
import view.DeliverView;
import view.MemberView;

public class DeliverController extends Controller implements Initializable{
	@FXML
	private Button logoutBtn;
	
	@FXML
	private Label welcomeLb;
	
	@FXML
	private VBox displayVb;
	
	@FXML
	private Button orderBtn;
		
	@FXML
	private Button myOrderBtn;

	@FXML
	private Button infoBtn;
	
	private String username;
	private DeliverView status;
	private String tmp;
	private DeliveryMan deliveryman;
	private DeliveryMan deliverymanInfo;
	
	
	public void logout(ActionEvent event) throws IOException {
		switchScene(ViewEnum.LOGIN, event);
	}

	@Override
	protected void setUsernameLb(String s) {
		username = s;
		deliveryman = new DeliveryMan(this.username);
		// Maybe can be refactor
		try {
			deliverymanInfo = deliveryman.getDeliveryManInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		welcomeLb.setText(("Hello, Deliver " + s));
	}
	
	@Override
	protected void render() {
		Label lb;
		switch (status){
		case Info:
			displayVb.getChildren().clear();
			lb = new Label(tmp);
			lb.setFont(new Font("Yu Gothic UI Semibold", 15));
			displayVb.getChildren().add(lb);
			tmp = "";
			break;			
		case Order:
			displayVb.getChildren().clear();
			tmp = "";
			break;			
		case MyOrder:
			displayVb.getChildren().clear();
			tmp = "";
			break;			
		default:
			displayVb.getChildren().clear();
			tmp = "";
			break;
		}
	}
	
	public void pressMyOrderBtn() {
		System.out.println("See all of your order");
		
		ArrayList<String> myorder = deliveryman.getOrdersToSend();
		myorder.forEach(order -> System.out.println(order));
		
		ArrayList<Order> orders = new ArrayList<Order>();
		myorder.forEach(order -> orders.add(deliveryman.getSingleOrder(order)));

		displayVb.getChildren().clear();
	    TableView<Order> tableView = new TableView<Order>();

		TableColumn<Order, String> consumerColumn = new TableColumn<>("Consumer");
		consumerColumn.setCellValueFactory(new PropertyValueFactory<>("consumer_id"));
		tableView.getColumns().add(consumerColumn);
	    
	    TableColumn<Order, String> restaurantColumn = new TableColumn<>("Restaurant");
	    restaurantColumn.setCellValueFactory(new PropertyValueFactory<>("restaurant_name"));
		tableView.getColumns().add(restaurantColumn);

		TableColumn<Order, Timestamp> createTimeColumn = new TableColumn<>("Create Time");
		createTimeColumn.setCellValueFactory(new PropertyValueFactory<>("create_time"));
		tableView.getColumns().add(createTimeColumn);

		TableColumn<Order, String> statusColumn = new TableColumn<>("Status");
	    statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusToString"));
		tableView.getColumns().add(statusColumn);

		TableColumn<Order, String> addressColumn = new TableColumn<>("Consumer Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("consumer_address"));
		tableView.getColumns().add(addressColumn);

		TableColumn<Order, String> addressColumn2 = new TableColumn<>("Restaurant Address");
		addressColumn2.setCellValueFactory(new PropertyValueFactory<>("restaurant_address"));
		tableView.getColumns().add(addressColumn2);

		
	    TableColumn<Order, Void> colBtn2 = new TableColumn("Finish Deliver");
        Callback<TableColumn<Order, Void>, TableCell<Order, Void>> cellFactory2 = new Callback<TableColumn<Order, Void>, TableCell<Order, Void>>() {
            @Override
            public TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                final TableCell<Order, Void> cell = new TableCell<Order, Void>() {

                    private final Button btn = new Button("Finish!");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	Order data = getTableView().getItems().get(getIndex());
                        	System.out.println("Finish!" + data.getId());
                			deliveryman.setOrderStatusDone(data.getId());
                          	pressOrderBtn();
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn2.setCellFactory(cellFactory2);
        tableView.getColumns().add(colBtn2);

		
		// Show out all orderID
		orders.forEach(order->{
			tableView.getItems().add(order);
			System.out.println("-----------order------------");
			System.out.println(order.getId());
		});
		
		displayVb.setPrefWidth(1000);
//		tmpCount2 = 0;
		displayVb.getChildren().add(tableView);	

		// if finish
		try {
//			deliveryman.setOrderStatus(myorder.get(0));
		} catch (Exception e) {
			System.out.println("order expire or not found");
		}
	}
	
	public void pressInfoBtn() throws SQLException {
		status = DeliverView.Info;
		if (deliverymanInfo != null) {
			// TODO [FX] handle the info fx.
			tmp = ("Username: " + deliverymanInfo.getUserName() + "\n");
			tmp += ("Address: " + deliverymanInfo.getAddress() + "\n");
			tmp += ("Phone: " + deliverymanInfo.getPhone() + "\n");
			tmp += ("Email: " + deliverymanInfo.getEmail() + "\n");
			tmp += ("Name: " + deliverymanInfo.getName() + "\n");
			
			System.out.println();
			System.out.println("Username: " + deliverymanInfo.getUserName());
			System.out.println("Address: " + deliverymanInfo.getAddress());
			System.out.println("Phone: " + deliverymanInfo.getPhone());
			System.out.println("Email: " + deliverymanInfo.getEmail());
			System.out.println("Name: " + deliverymanInfo.getName());
		}else {
			System.out.println("some error occur, getting null");
		}
		System.out.println();
		render();
	}

	public void pressOrderBtn() {
		status = DeliverView.Order;
		render();
		displayVb.getChildren().clear();
		System.out.println("ok");
		ArrayList<String> order_ids = deliveryman.getNoDeliverymanOrders();	
		System.out.println("Fuck: " + order_ids.size());
		
		// Show all order_id
		order_ids.forEach(order -> System.out.println(order));
		
		// Get all order object by order_id
		ArrayList<Order> orders = new ArrayList<Order>();
		order_ids.forEach(order -> orders.add(deliveryman.getSingleOrder(order)));
		
		
	    TableView<Order> tableView = new TableView<Order>();

		TableColumn<Order, String> consumerColumn = new TableColumn<>("Consumer");
		consumerColumn.setCellValueFactory(new PropertyValueFactory<>("consumer_id"));
		tableView.getColumns().add(consumerColumn);
	    
	    TableColumn<Order, String> restaurantColumn = new TableColumn<>("Restaurant");
	    restaurantColumn.setCellValueFactory(new PropertyValueFactory<>("restaurant_name"));
		tableView.getColumns().add(restaurantColumn);

		TableColumn<Order, Timestamp> createTimeColumn = new TableColumn<>("Create Time");
		createTimeColumn.setCellValueFactory(new PropertyValueFactory<>("create_time"));
		tableView.getColumns().add(createTimeColumn);

		TableColumn<Order, String> statusColumn = new TableColumn<>("Status");
	    statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusToString"));
		tableView.getColumns().add(statusColumn);

		TableColumn<Order, String> addressColumn = new TableColumn<>("Consumer Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("consumer_address"));
		tableView.getColumns().add(addressColumn);

		TableColumn<Order, String> addressColumn2 = new TableColumn<>("Restaurant Address");
		addressColumn2.setCellValueFactory(new PropertyValueFactory<>("restaurant_address"));
		tableView.getColumns().add(addressColumn2);

		TableColumn<Order, Void> colBtn = new TableColumn("Take Deliver");
        Callback<TableColumn<Order, Void>, TableCell<Order, Void>> cellFactory = new Callback<TableColumn<Order, Void>, TableCell<Order, Void>>() {
            @Override
            public TableCell<Order, Void> call(final TableColumn<Order, Void> param) {
                final TableCell<Order, Void> cell = new TableCell<Order, Void>() {

                    private final Button btn = new Button("Take!");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                        	// [MING] set the take order logic here, you can see "data" is correspond to the item
                        	// You may call pressOrderBtn() after finishing altering database.
                        	Order data = getTableView().getItems().get(getIndex());
                        	System.out.println("Take!" + data.getId());
                			deliveryman.pickUpOrder(data.getId());
                			deliveryman.setOrderStatus(data.getId());
                			pressOrderBtn();
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().add(colBtn);
		
		
		
        
//		for (int i = 0; i < order_ids.size(); i++) {
//			Order order_detail = restaurantInfo.checkOrderDetail(order_ids.get(i));
//			tmpCount2 = max(tmpCount2, 1000+order_detail.getItems().length() * 10);
//			tableView.getItems().add(order_detail);		
//		}
		
		displayVb.setPrefWidth(1000);
//		tmpCount2 = 0;
		displayVb.getChildren().add(tableView);	

		
		
		
		// Show out all orderID
		orders.forEach(order->{
			tableView.getItems().add(order);
			System.out.println(order.getId());
		
		});

		
		
		// want to accept the order
		
		try {

//			deliveryman.pickUpOrder(orders.get(0).getId());
			
		} catch(Exception e) {
			System.out.println("order not found or expire");
		}
		
		
		
		// choose one id you want and show detail
//		try {
//			String orderSearchOne = "89483890-186e-4316-a596-f4688b77855d";
//			Order orderOne = member.checkOrderStatus(orderSearchOne);
//			System.out.println(orderOne.getId());
//			System.out.println(orderOne.getStatus());
//			System.out.println(orderOne.getConsumer_id());
//			System.out.println(orderOne.getDeliveryman_id());
//			System.out.println(orderOne.getRestaurant_id());
//			System.out.println(orderOne.getCreate_time());
//			System.out.println(orderOne.getDeliver_time());
//			System.out.println(orderOne.getArrival_time());
//		} catch(Exception e) {
//			System.out.println("order not found");
//		}

		// show all detail
		orders.forEach(order->{
			System.out.println("-----------order------------");
			System.out.println(order.getId());
			System.out.println(order.getStatus());
			System.out.println(order.getConsumer_id());
			System.out.println(order.getDeliveryman_id());
			System.out.println(order.getRestaurant_id());
			System.out.println(order.getCreate_time());
			System.out.println(order.getDeliver_time());
			System.out.println(order.getArrival_time());
		});
		
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		status = DeliverView.Default;
		render();
	}

}
