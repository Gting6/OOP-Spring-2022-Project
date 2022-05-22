package sql;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
		
		Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
		long l = now.getTime();
		long m = 60*60*1000;
		Timestamp deliver_time = new Timestamp(l+m);
		Timestamp arrival_time = new Timestamp(l+2*m);
		
		Member member = new Member("member","123","twn", "09xxxxxxxx","123@abc","yoyo");
		member.setToDB();
		// cause we read directly to DB so no need to test append restaurant now.
//		Restaurant restaurant = new Restaurant("restaurant");
//		restaurant.setToDB();

		
//		test order function ex: add to cart ...
		HashMap<String, Integer> product1 = new HashMap<>();
//		HashMap<String, Integer> all_products = dbService.getProducts("f3746aef-935f-44cf-9dae-0455d2f7727d");
		product1.put("¦×ºê", 50);
		Order order = new Order("member", "f3746aef-935f-44cf-9dae-0455d2f7727d", product1);
		order.establishOrder();
		Order checkorder = member.checkOrderStatus(order.getId());
		System.out.println("Order Fee is " + checkorder.getFee());

//		test assign deliveryman to order
		DeliveryMan deliveryman = new DeliveryMan("deliveryman","123","khh", "09xxxxxxxx","789@abc","yaya");
//		deliveryman.setToDB();
		ArrayList<String> all_orders = deliveryman.getOrders();
		deliveryman.pickUpOrder(order.getId());
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
