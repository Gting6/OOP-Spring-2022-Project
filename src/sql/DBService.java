package sql;

import java.sql.*;
import model.Member;
import model.Restaurant;
import model.Order;

public class DBService {
	
	
	
	public void createDeliveryMan(DeliveryMan deliveryman) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into deliverymans values(?,?,?,?,?,?)"); 
			
			stmt.setString(1, DeliveryMan.getUserName());
			stmt.setString(2, DeliveryMan.getPassword());
			stmt.setString(4, DeliveryMan.getEmail());
			stmt.setString(5, DeliveryMan.getName());
			stmt.setArray(6, null);								// for orders
			
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("Member Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("User Creation Failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void createOrder(Order order) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into orders values(?,?,?,?,?)"); 
			
			stmt.setString(1, order.getOrderNO());
			stmt.setString(2, order.getCreateTime());		// need to change to date time??
			stmt.setString(3, order.getDeliverTime());		// no need telephone change to address is better
			stmt.setString(4, order.getRestaurantName());
			stmt.setArray(5, order.getUserName());
			
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("Member Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("User Creation Failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void createRestaurant(Restaurant restaurant) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into restaurants values(?,?,?,?,?,?,?)"); 
			
			stmt.setString(1, restaurant.getUserName());
			stmt.setString(2, restaurant.getPassword());
			stmt.setString(3, restaurant.getAddress());		// no need telephone change to address is better
			stmt.setString(4, restaurant.getEmail());
			stmt.setString(5, restaurant.getName());
			stmt.setArray(6, restaurant.getProducts());
			stmt.setArray(7, null);								// for orders
			
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("Restaurant Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("Restaurant Creation Failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void createMember(Member member) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into members values(?,?,?,?,?,?.?,?)"); 
			
			stmt.setString(1, member.getUserName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getAddress());		// no need telephone change to address is better
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getName());
			stmt.setString(6, member.getVIP());
			stmt.setDate(7, member.getVIP_expire_date());
			stmt.setArray(8, null);								// for orders
			
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("Member Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("Member Creation Failed");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void loginDB(String role, String username, String password) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "+ role + " WHERE username=? AND userpassword=?"); 
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
			
				System.out.print("Login Success");
//				might add cookie or something else??
			}
			else {
				System.out.print("Login Failed, Cannot find Account");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member getMember(String username, String password) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND userpassword=?"); 
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet res = stmt.executeQuery();
			
			if(res) {
			
				System.out.print("Login Success");
				return Member(res.getString("username"), res.getString("address"));		// need recreate or just send these 2 as json file?
//				might add cookie or something else??
			}
			else {
				System.out.print("Login Failed, Cannot find Account");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Restaurant getRestaurant(String restaurant_name) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM restaurants WHERE name=?"); 
			
			stmt.setString(1, restaurant_name);
			
			ResultSet res = stmt.executeQuery();
			
			if(res) {
			
				System.out.print("Login Success");
				return Restaurant(res.getString("name"), res.getString("address"),res.getArray("products"));		// need recreate or just send these 2 as json file?
//				might add cookie or something else??
			}
			else {
				System.out.print("Login Failed, Cannot find Account");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DeliveryMan getDeliveryMan(String DM_name) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deliverymans WHERE name=?"); 
			
			stmt.setString(1, DM_name);
			
			ResultSet res = stmt.executeQuery();
			
			if(res) {
			
				System.out.print("Login Success");
				return DeliveryMan(res.getString("name"));		// need recreate or just send sth as json file?
//				might add cookie or something else??
			}
			else {
				System.out.print("Cannot find DeliveryMan");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Order getOrder(String orderNO) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE orderNO=?"); 
			
			stmt.setString(1, orderNO);
			
			ResultSet res = stmt.executeQuery();
			
			if(res) {
			
				System.out.print("Login Success");
				return Order(res.getString("OrderNO"), res.getString("restaurant_name"), res.getString("deliveryman_name"),
						res.getString("member_name"), res.getDate("order_time"), res.getDate("arrival_time"));		// need recreate or just send sth as json file?
//				might add cookie or something else??
			}
			else {
				System.out.print("Cannot find Order");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
