package sql;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

import model.Member;
import model.Restaurant;
import model.Order;
import model.DeliveryMan;

public class DBService {
	
	public boolean setVIP(String username) throws SQLException {
		try {
			Connection conn = DBConnection.getConnection();
			
			Calendar c = Calendar.getInstance();
			c.getTime();
			c.add(Calendar.DATE, 30);
			Date dt = new java.sql.Date(c.getTimeInMillis());
			
//			System.out.print(dt.getTime());
			
			PreparedStatement update_VIP = conn.prepareStatement("UPDATE members SET vip_expire_date=? WHERE username=?"); 
			update_VIP.setDate(1, dt);
			update_VIP.setString(2, username);
			
			update_VIP.executeUpdate();

			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}	
	
	public Date getVIPDate(String username) throws SQLException {
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_vip_date = conn.prepareStatement("SELECT * from members WHERE username=?"); 
			query_vip_date.setString(1, username);
			ResultSet res = query_vip_date.executeQuery();
			
			if(res.next()) {
				return res.getDate("vip_expire_date");
			}
			else return null;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	public void createDeliveryMan(DeliveryMan deliveryman) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into deliverymen values(?,?,?,?,?)"); 
			
			stmt.setString(1, deliveryman.getUserName());
			stmt.setString(2, deliveryman.getPassword());
			stmt.setString(3, deliveryman.getAddress());
			stmt.setString(4, deliveryman.getEmail());
			stmt.setString(5, deliveryman.getName());
			
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
			PreparedStatement stmt = conn.prepareStatement("insert into orders values(?,?,?,?,?,?)"); 
			
			stmt.setInt(1, order.getOrder_id_());
			stmt.setObject(2, order.getCreate_time_());		// need to change to date time??
			stmt.setObject(3, order.getArrival_time_());		// no need telephone change to address is better
			stmt.setString(4, order.getRestaurant_name_());
			stmt.setString(5, order.getConsumer_name_());
			stmt.setString(6, order.getDeliveryman_name_());
			
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
	
	
//	we assume to parse the products into a dictionary type
	public void createProducts(Dictionary<String, Integer> products, String restaurant_username) throws SQLException {
	
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into orders values(?,?,?)"); 
			
			Enumeration<String> enu = products.keys();
			while(enu.hasMoreElements()) {
				stmt.setString(1, restaurant_username);
				stmt.setString(2, (String) enu.nextElement());		
				stmt.setString(3, products.get(enu).toString());
				
				int res = stmt.executeUpdate();
				
				if(res == 1) {
					System.out.print("Products Created");
				}
				else {
					System.out.print("User Creation Failed");
				}
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void createRestaurant(Restaurant restaurant) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into restaurants values(?,?,?,?,?)"); 
			
			stmt.setString(1, restaurant.getUserName());
			stmt.setString(2, restaurant.getPassword());
			stmt.setString(3, restaurant.getAddress());		// no need telephone change to address is better
			stmt.setString(4, restaurant.getEmail());
			stmt.setString(5, restaurant.getName());
			
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
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO members values(?,?,?,?,?,?)"); 

			stmt.setString(1, member.getUserName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getAddress());		// no need telephone change to address is better
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getName());
			stmt.setDate(6, member.getVIP_expire_date());
			
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
	
	
	public boolean loginDB(String role, String username, String password) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "+ role + " WHERE username=? AND password=?"); 
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
			
				System.out.print("Login Success");
//				might add cookie or something else??
				return true;
			}
			else {
				System.out.print("Login Failed, Cannot find Account");
				return false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Member getMember(String username, String password) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
//			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND userpassword=?"); 
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM members WHERE username=?"); 
			
			stmt.setString(1, username);
			
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
				System.out.print("Find!");
				return new Member(res.getString("username"), res.getString("password"), res.getString("address"), res.getString("email"), res.getString("name"), res.getDate("vip_expire_date"));		// need recreate or just send these 2 as json file?
			}
			else {
				System.out.print("NotFind!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Restaurant getRestaurant(String restaurant_username) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM restaurants WHERE username=?"); 
			
			stmt.setString(1, restaurant_username);
			
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
			
				System.out.print("Restaurant Found");
				return new Restaurant(res.getString("username"), res.getString("password"),res.getString("address"),res.getString("email"), res.getString("name"));		// need recreate or just send these 2 as json file?
//				might add cookie or something else??
			}
			else {
				System.out.print("Login Failed, Cannot find Account");
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public DeliveryMan getDeliveryMan(String DM_name) throws SQLException {
//		
//		try {
//			Connection conn = DBConnection.getConnection();
////			role stands for his identity
//			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deliverymans WHERE name=?"); 
//			
//			stmt.setString(1, DM_name);
//			
//			ResultSet res = stmt.executeQuery();
//			
//			if(res) {
//			
//				System.out.print("Login Success");
//				return DeliveryMan(res.getString("name"));		// need recreate or just send sth as json file?
////				might add cookie or something else??
//			}
//			else {
//				System.out.print("Cannot find DeliveryMan");
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	public HashMap<String, Integer> getProducts(String restaurant_username) throws SQLException {
	
	try {
		Connection conn = DBConnection.getConnection();
//		role stands for his identity
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM products WHERE restaurant_name=?"); 
		
		stmt.setString(1, restaurant_username);
		
		HashMap<String, Integer> products = new HashMap<String, Integer>();
		ResultSet res = stmt.executeQuery();
		
		while(res.next()) {
			products.put(res.getString("product_name"), res.getInt("price"));
		}
		return products;
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
	
	
	public Order getOrder(int id) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE id=?"); 
			
			stmt.setInt(1, id);
			
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
			
				System.out.println("Order found");
				LocalDateTime c =  (LocalDateTime) res.getObject("create_time");
				LocalDateTime c_bar = (LocalDateTime) res.getObject("arrival_time");
				Timestamp d = Timestamp.valueOf(c);
				Timestamp d_bar = Timestamp.valueOf(c_bar);
				return new Order(res.getInt("id"), d, d_bar, res.getString("member_name"), 
						res.getString("deliveryman_name"), res.getString("restaurant_name"));
//				might add cookie or something else??
			}
			else {
				System.out.println("Cannot find Order");
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
