package sql;

import java.sql.Timestamp;
import java.util.Calendar;
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
		Timestamp later = new Timestamp(l+m);
		
		Member member = new Member("member","123","twn","123@abc","yoyo");
		member.setToDB();
		Restaurant restaurant = new Restaurant("restaurant","123","tpe","456@abc","yuyu");
		restaurant.setToDB();
		DeliveryMan deliveryman = new DeliveryMan("deliveryman","123","khh","789@abc","yaya");
		deliveryman.setToDB();
		Order oor = new Order(123,now,later,"member","deliveryman","restaurant");
		oor.setToDB();
		Restaurant rr = restaurant.getRestaurantInfo();
		System.out.println(rr.getUserName());
		Member member_test = member.getMemberInfo();
		System.out.println(member_test.getEmail());
		Order order_print = oor.getOrderInfo();
		System.out.println(order_print.getArrival_time_());
//		restaurant.products.put
	}

}
