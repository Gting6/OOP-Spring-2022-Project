package sql;

import java.util.HashMap;
import java.sql.SQLException;
import model.Order;
import model.Member;
import model.Restaurant;
import model.DeliveryMan;

public class TestDBService {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBService dbService = new DBService();
		
//		Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
//		long l = now.getTime();
//		long m = 60*60*1000;
//		Timestamp deliver_time = new Timestamp(l+m);
//		Timestamp arrival_time = new Timestamp(l+2*m);
//		
		Member member = new Member("member","123","twn", "09xxxxxxxx","123@abc","yoyo");
//		member.setToDB();
		// cause we read directly to DB so no need to test append restaurant now.
//		Restaurant restaurant = new Restaurant("restaurant");
//		restaurant.setToDB();

		Restaurant restaurant = new Restaurant(dbService.getRestaurant("f3746aef-935f-44cf-9dae-0455d2f7727d"));
		System.out.println(restaurant.getCoupon());
		restaurant.setCoupon("buy_200_get_90_percent_off");
		System.out.println(restaurant.getCoupon());
		restaurant.setOrderStatus("f0a60e9e-ee2f-44cc-89d0-7c180c6b336a");
		DeliveryMan deliveryman = new DeliveryMan(dbService.getDeliveryMan("Deliveryman"));
		deliveryman.setOrderStatus("f0a60e9e-ee2f-44cc-89d0-7c180c6b336a");
		
//		test order function ex: add to cart ...
		HashMap<String, Integer> product1 = new HashMap<>();
		HashMap<String, Integer> all_products = dbService.getProducts("f3746aef-935f-44cf-9dae-0455d2f7727d");
		product1.put("�׺�", 50);
		Order order = new Order("member", "f3746aef-935f-44cf-9dae-0455d2f7727d", product1);
		System.out.println(order.getRestaurantDescription());
		order.addToCart(product1);
		order.addToCart(product1);
		order.addToCart(product1);
		order.addToCart(product1);
		order.establishOrder();
		Order checkorder = member.checkOrderStatus(order.getId());
		System.out.println("Order Fee is " + checkorder.getFee());

//		test assign deliveryman to order
//		DeliveryMan deliveryman = new DeliveryMan("deliveryman","123","khh", "09xxxxxxxx","789@abc","yaya");
//		deliveryman.setToDB();
//		ArrayList<String> all_orders = deliveryman.getNoDeliverymanOrders();
//		deliveryman.pickUpOrder(order.getId());
//		now you can go to DB and see if deliveryman id is added to that order.
		
//		=======================
//		Since each time it puts an random uuid in it, you have to manually change the id
//		=======================
//		String[] bt = dbService.getBusinessTime("copy one restaurant username(uuid) here");
//		for(String t : bt) {
//			System.out.println("bt are" + t);
//		}
//		
//		String[] types = dbService.getTypeRestaurant("copy one restaurant username(uuid) here");
//		for(String t : types) {
//			System.out.println("types are" + t);
//		}

	}

}
