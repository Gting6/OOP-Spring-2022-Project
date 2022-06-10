package sql;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
			conn.close();
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
				Date vip_expire_date = res.getDate("vip_expire_date");
				conn.close();
				return vip_expire_date;
			}
			else {
				conn.close();
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	public void createDeliveryMan(DeliveryMan deliveryman) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into deliverymen values(?,?,?,?,?,?)"); 
			
			stmt.setString(1, deliveryman.getUserName());
			stmt.setString(2, deliveryman.getPassword());
			stmt.setString(3, deliveryman.getAddress());
			stmt.setString(4, deliveryman.getPhone());
			stmt.setString(5, deliveryman.getEmail());
			stmt.setString(6, deliveryman.getName());
			
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("DeliveryMan Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("User Creation Failed");
			}
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void createOrder(Order order) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into orders values(?,?,?,?,?,?,?,?,?)"); 
			
			stmt.setString(1, order.getId());
			stmt.setInt(2, order.getStatus());
			stmt.setObject(3, order.getCreate_time());		// need to change to date time??
			stmt.setObject(4, order.getDeliver_time());
			stmt.setObject(5, order.getArrival_time());		// no need telephone change to address is better
			stmt.setObject(6, order.getFee());
			stmt.setString(7, order.getConsumer_id());
			stmt.setString(8, order.getRestaurant_id());
			stmt.setString(9, order.getDeliveryman_id());
			
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("Order Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("Order Creation Failed");
			}
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//	we assume to parse the products into a dictionary type

	public void createProducts(HashMap<String, Integer> products, String restaurant_username) throws SQLException {
	
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into products values(?,?,?)"); 
			
			for(String product_name : products.keySet()) {
				stmt.setString(1, restaurant_username);
				stmt.setString(2, product_name);		
				stmt.setInt(3, products.get(product_name));
				
				int res = stmt.executeUpdate();
				
				if(res == 1) {
					System.out.print("Products Created");
				}
				else {
          
					System.out.print("Products Creation Failed");

				}
			}	
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createOrderItems(HashMap<String, Integer> products, String id) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into order_items values(?,?,?)"); 
			
			for(String product_name : products.keySet()) {
				stmt.setString(1, id);
				stmt.setString(2, product_name);
				stmt.setInt(3, products.get(product_name));
				
				int res = stmt.executeUpdate();
				
				if(res == 1) {
					System.out.print("OrderItems Created");
				}
				else {
          
					System.out.print("OrderItems Creation Failed");
				}
			}	
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public void createMember(Member member) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO members values(?,?,?,?,?,?,?)"); 

			stmt.setString(1, member.getUserName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getAddress());		// no need telephone change to address is better
			stmt.setString(4, member.getPhone());
			stmt.setString(5, member.getEmail());
			stmt.setString(6, member.getName());
			stmt.setDate(7, member.getVIP_expire_date());
			
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("Member Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("Member Creation Failed");
			}
			conn.close();
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
				conn.close();
				return true;
			}
			else {
				System.out.print("Login Failed, Cannot find Account");
				conn.close();
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
				Member member = new Member(res.getString("username"), res.getString("password"), res.getString("address"), res.getString("phone"), res.getString("email"), res.getString("name"), res.getDate("vip_expire_date"));		// need recreate or just send these 2 as json file?
				conn.close();
				return member;
			}
			else {
				System.out.print("NotFind!");
				conn.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Member getMember(String username) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
//			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND userpassword=?"); 
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM members WHERE username=?"); 
			
			stmt.setString(1, username);
			
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
				System.out.print("Find!");
				Member member = new Member(res.getString("username"), res.getString("password"), res.getString("address"), res.getString("phone"), res.getString("email"), res.getString("name"), res.getDate("vip_expire_date"));		// need recreate or just send these 2 as json file?
				conn.close();
				return member;
			}
			else {
				System.out.print("NotFind!");
				conn.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public String[] getRestaurant(String restaurant_username) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM restaurants WHERE username=?"); 
			
			stmt.setString(1, restaurant_username);
			
			ResultSet res = stmt.executeQuery();

			String[] s = new String[11];
			
			if(res.next()) {
				
				s[0] = res.getString("username");
				s[1] = res.getString("password");
				s[2] = res.getString("email");
				s[3] = res.getString("name");
				s[4] = res.getString("pos_addr");
				s[5] = res.getString("latitude");
				s[6] = res.getString("longitude");
				s[7] = res.getString("phone");
				s[8] = res.getString("store_description");
				s[9] = res.getString("order_description");
				s[10] = res.getString("coupon");
				
			}
			conn.close();
			return s;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getRestaurantByName(String name) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM restaurants WHERE name=?"); 
			
			stmt.setString(1, name);
			
			ResultSet res = stmt.executeQuery();

			String[] s = new String[11];
			
			if(res.next()) {
				
				s[0] = res.getString("username");
				s[1] = res.getString("password");
				s[2] = res.getString("email");
				s[3] = res.getString("name");
				s[4] = res.getString("pos_addr");
				s[5] = res.getString("latitude");
				s[6] = res.getString("longitude");
				s[7] = res.getString("phone");
				s[8] = res.getString("store_description");
				s[9] = res.getString("order_description");
				s[10] = res.getString("coupon");
				
			}
			conn.close();
			return s;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[][] getAllRestaurant() throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM restaurants"); 
						
			ResultSet res = stmt.executeQuery();
			
			// Maybe can be more space
			String[][] s = new String[29][11];
			int count = 0;
			while(res.next()) {
//				System.out.println(res.getString("name"));
				s[count][0] = res.getString("username");
				s[count][1] = res.getString("password");
				s[count][2] = res.getString("email");
				s[count][3] = res.getString("name");
				s[count][4] = res.getString("pos_addr");
				s[count][5] = res.getString("latitude");
				s[count][6] = res.getString("longitude");
				s[count][7] = res.getString("phone");
				s[count][8] = res.getString("store_description");
				s[count][9] = res.getString("order_description");
				s[count][10] = res.getString("coupon");
				count++;
			}
			conn.close();
			return s;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String[]> searchRestaurantByName(String name) throws SQLException{
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * from restaurants WHERE (lower(name) LIKE '%" + name + "%')");

			ResultSet res = stmt.executeQuery();
			
			// Maybe can be more space
			ArrayList<String[]> al = new ArrayList<String[]>();
			
			while(res.next()) {
				String[] s = new String[11];
				s[0] = res.getString("username");
				s[1] = res.getString("password");
				s[2] = res.getString("email");
				s[3] = res.getString("name");
				s[4] = res.getString("pos_addr");
				s[5] = res.getString("latitude");
				s[6] = res.getString("longitude");
				s[7] = res.getString("phone");
				s[8] = res.getString("store_description");
				s[9] = res.getString("order_description");
				s[10] = res.getString("coupon");
				al.add(s);
			}
			conn.close();
			return al;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public ArrayList<String[]> searchRestaurantByType(String type) throws SQLException{
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("select * from restaurants where username in (select restaurant_name from type_restaurants WHERE type = \"" + type + "\")");

			ResultSet res = stmt.executeQuery();
			
			// Maybe can be more space
			ArrayList<String[]> al = new ArrayList<String[]>();
			
			while(res.next()) {
				String[] s = new String[11];
				s[0] = res.getString("username");
				s[1] = res.getString("password");
				s[2] = res.getString("email");
				s[3] = res.getString("name");
				s[4] = res.getString("pos_addr");
				s[5] = res.getString("latitude");
				s[6] = res.getString("longitude");
				s[7] = res.getString("phone");
				s[8] = res.getString("store_description");
				s[9] = res.getString("order_description");
				s[10] = res.getString("coupon");
				al.add(s);
			}
			conn.close();
			return al;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public ArrayList<String[]> searchRestaurantByCoupon(String coupon) throws SQLException{
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("select * from restaurants where coupon = ?");
			
			stmt.setString(1, coupon);

			ResultSet res = stmt.executeQuery();
			
			// Maybe can be more space
			ArrayList<String[]> al = new ArrayList<String[]>();
			
			while(res.next()) {
				String[] s = new String[11];
				s[0] = res.getString("username");
				s[1] = res.getString("password");
				s[2] = res.getString("email");
				s[3] = res.getString("name");
				s[4] = res.getString("pos_addr");
				s[5] = res.getString("latitude");
				s[6] = res.getString("longitude");
				s[7] = res.getString("phone");
				s[8] = res.getString("store_description");
				s[9] = res.getString("order_description");
				s[10] = res.getString("coupon");
				al.add(s);
			}
			conn.close();
			return al;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public String[] getDeliveryMan(String DM_name) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM deliverymen WHERE username=?"); 
			
			stmt.setString(1, DM_name);
			
			ResultSet res = stmt.executeQuery();
			String[] s = new String[6];
			if(res.next()) {
			
				System.out.print("DeliveryMan Find!");
				System.out.println(res.getString("username"));
				s[0] = res.getString("username");
				s[1] = res.getString("password");
				s[2] = res.getString("address");
				s[3] = res.getString("phone");
				s[4] = res.getString("email");
				s[5] = res.getString("name");
				conn.close();
				return s;
//				return DeliveryMan(res.getString("name"));		// need recreate or just send sth as json file?
//				might add cookie or something else??
			}
			else {
				System.out.print("Cannot find DeliveryMan");
				conn.close();
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
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
		conn.close();
		return products;
	}
		catch (Exception e) {
			e.printStackTrace();
	}
		return null;
	}
	
	public ArrayList<String> getOrderItems(String order_id) throws SQLException {
		
	try {
		Connection conn = DBConnection.getConnection();
//		role stands for his identity
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM order_items WHERE id=?"); 
		
		stmt.setString(1, order_id);
		
		ArrayList<String> items = new ArrayList<>();
		ResultSet res = stmt.executeQuery();
		
		while(res.next()) {
			items.add(res.getString("item"));
		}
		conn.close();
		return items;
	}catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}

	public ArrayList<ArrayList<String>> getOrderItemsDetail(String order_id) throws SQLException {
		
	try {
		Connection conn = DBConnection.getConnection();
//		role stands for his identity
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM order_items WHERE id=?"); 
		
		stmt.setString(1, order_id);
		
		ResultSet res = stmt.executeQuery();
		ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
		
		while(res.next()) {
			ArrayList<String> item = new ArrayList<>();
			item.add(res.getString("item"));
			item.add(res.getString("number"));
			items.add(item);
		}
		conn.close();
		return items;
	}catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
	
	
	public Order getOrder(String id) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders WHERE id=?"); 
			
			stmt.setString(1, id);
			
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
			
				System.out.println("Order found");
				LocalDateTime c =  (LocalDateTime) res.getObject("create_time");
				LocalDateTime dt =  (LocalDateTime) res.getObject("deliver_time");
				LocalDateTime c_bar = (LocalDateTime) res.getObject("arrival_time");
				Timestamp d = Timestamp.valueOf(c);
				Timestamp d_bar = Timestamp.valueOf(c_bar);
				Timestamp deliver_time = Timestamp.valueOf(dt);
				Order order = new Order(res.getString("id"), res.getInt("status"), d, deliver_time, d_bar, res.getString("member_name"), 
						res.getString("deliveryman_name"), res.getString("restaurant_name"), res.getInt("fee"));
				conn.close();
				return order;
//				might add cookie or something else??
			}
			else {
				System.out.println("Cannot find Order");
				conn.close();
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public void createRestaurant(String uuid, String password, String email, String name, String pos_addr,
			String latitude, String longitude, String phone, String store_description, String order_description,
			String coupon) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into restaurants values(?,?,?,?,?,?,?,?,?,?,?)"); 
			
			stmt.setString(1, uuid);
			stmt.setString(2, password);
			stmt.setString(3, email);
			stmt.setString(4, name);
			stmt.setString(5, pos_addr);
			stmt.setString(6, latitude);
			stmt.setString(7, longitude);
			stmt.setString(8, phone);
			stmt.setString(9, store_description);
			stmt.setString(10, order_description);
			stmt.setString(11, coupon);
			int res = stmt.executeUpdate();
			
			if(res == 1) {
			
				System.out.print("Restaurant From Json Created");
//				might add cookie or something else??
			}
			else {
				System.out.print("Restaurant Creation From Json Failed");
			}
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTypeRestaurants(String[] type, String uuid) {
		// TODO Auto-generated method stub
		try{
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into type_restaurants values(?,?)"); 
			
			for(String t : type) {
				stmt.setString(1, uuid);
				stmt.setString(2, t);
				stmt.executeUpdate();
			}
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String[] getTypeRestaurant(String id) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM type_restaurants WHERE restaurant_name=?"); 
			
			stmt.setString(1, id);
			
			ResultSet res = stmt.executeQuery();
			
			int index = 0;
			while(res.next()) {
				index ++;
			}
			String[] s = new String[index];
			System.out.println(index);
			index = 0;
			res = stmt.executeQuery();
			while(res.next()) {
				s[index] = res.getString("type");
				index ++;
			}
			conn.close();
			return s;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createBusinessTime(String[] business_time, String uuid) {
		// TODO Auto-generated method stub
		try{
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into business_times values(?,?,?,?)"); 
			
			int date = 2;
			for(String bt : business_time) {
				stmt.setString(1, uuid);
				stmt.setInt(2, date/2);
				if(date % 2 == 0)
					stmt.setString(3, "start_time");
				else
					stmt.setString(3, "end_time");
				stmt.setString(4, bt);
				stmt.executeUpdate();
				date ++;
			}
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public String[] getBusinessTime(String id) throws SQLException {
		
		try {
			Connection conn = DBConnection.getConnection();
//			role stands for his identity
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM business_times WHERE restaurant_name=?"); 
			stmt.setString(1, id);
			
			ResultSet res = stmt.executeQuery();
		
			String[] s = new String[14];
			
			int index = 0;
			while(res.next()) {
				
				s[index] = res.getString("time");
				index ++;

			}
			conn.close();
			return s;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getOrdersRestaurant(String restaurant_name) throws SQLException {
		
		ArrayList<String> orders = new ArrayList<>();
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from orders WHERE restaurant_name=?"); 
			query_orders.setString(1, restaurant_name);
			ResultSet res = query_orders.executeQuery();
			while(res.next()) {
				orders.add(res.getString("id"));
			}
			conn.close();
			return orders;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}	
	
	public ArrayList<String> getOrdersNoDeliveryman() throws SQLException {
		
		ArrayList<String> orders = new ArrayList<>();
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from orders WHERE deliveryman_name=? and status=1"); 
			query_orders.setString(1, "");
			ResultSet res = query_orders.executeQuery();
			while(res.next()) {
				orders.add(res.getString("id"));
			}
			conn.close();
			return orders;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}	
	
	public ArrayList<String> getOrdersDeliveryman(String Deliveryman_id) throws SQLException {
		
		ArrayList<String> orders = new ArrayList<>();
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from orders WHERE deliveryman_name=?"); 
			query_orders.setString(1, Deliveryman_id);
			ResultSet res = query_orders.executeQuery();
			while(res.next()) {
				orders.add(res.getString("id"));
			}
			conn.close();
			return orders;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public ArrayList<String> getOrdersMember(String member_id) throws SQLException {
		
		ArrayList<String> orders = new ArrayList<>();
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from orders WHERE member_name=?"); 
			query_orders.setString(1, member_id);
			ResultSet res = query_orders.executeQuery();
			while(res.next()) {
				orders.add(res.getString("id"));
			}
			conn.close();
			return orders;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public void assignDeliveryman(String deliveryman_id, String order_id) throws SQLException {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement update_orders = conn.prepareStatement("UPDATE orders SET deliveryman_name=? WHERE id=?"); 
			update_orders.setString(1, deliveryman_id);
			update_orders.setString(2, order_id);
			update_orders.executeUpdate();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addCoupon(String restaurant_id, String coupon) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement update_orders = conn.prepareStatement("UPDATE restaurants SET coupon=? WHERE username=?"); 
			update_orders.setString(1, coupon);
			update_orders.setString(2, restaurant_id);
			update_orders.executeUpdate();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public String getCoupon(String restaurant_id) {
		// TODO Auto-generated method stub
		
		String coupon = "";
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from restaurants WHERE username=?"); 
			query_orders.setString(1, restaurant_id);
			ResultSet res = query_orders.executeQuery();
			while(res.next()) {
				coupon = res.getString("coupon");
			}
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return coupon;
	}
	
	public void setOrderStatus(int status, String order_id) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement update_orders = conn.prepareStatement("UPDATE orders SET status=? WHERE id=?"); 
			update_orders.setInt(1, status);
			update_orders.setString(2, order_id);
			update_orders.executeUpdate();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getOrderStatus(String order_id) {
		
		int status = -1;
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from orders WHERE id=?"); 
			query_orders.setString(1, order_id);
			ResultSet res = query_orders.executeQuery();
			status = res.getInt(status);
			conn.close();
			return status;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public String getThisOrderRestaurantName(String restaurant_id) {
		
		String restaurant_name = "";
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from restaurants WHERE username=?"); 
			query_orders.setString(1, restaurant_id);
			ResultSet res = query_orders.executeQuery();
			if(res.next()) {
				restaurant_name = res.getString("name");
			}
			conn.close();
			return restaurant_name;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return restaurant_name;
	}	
	
	public String getThisOrderDeliverymanName(String deliveryman_id) {
		
		String deliveryman = "";
		
		try {
			Connection conn = DBConnection.getConnection();
			
			PreparedStatement query_orders = conn.prepareStatement("SELECT * from deliverymen WHERE username=?"); 
			query_orders.setString(1, deliveryman_id);
			ResultSet res = query_orders.executeQuery();
			if(res.next()) {
				deliveryman = res.getString("name");
			}
			conn.close();
			return deliveryman;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return deliveryman;
	}	
	
	public HashMap<String, String> getAllOrderItems(String order_id) throws SQLException {
		
	try {
		Connection conn = DBConnection.getConnection();
//		role stands for his identity
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM order_items WHERE id=?"); 
		
		stmt.setString(1, order_id);
		
		ResultSet res = stmt.executeQuery();
		HashMap<String, String> items = new HashMap<>();
		
		while(res.next()) {
			items.put(res.getString("item"), res.getString("number"));
		}
		conn.close();
		return items;
	}catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}
}
