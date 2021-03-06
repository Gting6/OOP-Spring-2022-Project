package model;

import java.net.URI;
import java.net.http.*;
//import static RestaurantProcess.deserializeStorejson.data;
import java.net.http.HttpResponse.BodyHandlers;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//import model.ResponseDetail;
//import ResponseDataProcess.ResponseDetail;
import sql.DBService;

public class Model {

	// Username -> User
	private DBService DBService;
//	private HashMap<String, User> Users = new HashMap<String, User>(); // one user first

	public static final String apikey = "AIzaSyACf_DTPbV1hGXeRYiH4cKM7yBSe9g0Sls";

	public Model() {
		this.DBService = new DBService();
	}

	public int StringToInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return -1;

		}
	}

	public void addMember(Member member) {
		try {
			this.DBService.createMember(member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addDeliverMan(DeliveryMan deliveryman) {
		try {
			this.DBService.createDeliveryMan(deliveryman);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addRestaurant() {

	}

	// TODO Don't need to pass the password?
	public boolean checkMemberInWhenRegister(String username, String password) {
		try {
			return this.DBService.getMember(username, password) != null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkMemberLoginIn(String username, String password) {

		try {
			return this.DBService.loginDB("members", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkDeliverManWhenRegister(String username) {
		try {
			return this.DBService.getDeliveryMan(username) != null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkDeliverManrLoginIn(String username, String password) {

		try {
			return this.DBService.loginDB("deliverymen", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkRestaurantLoginIn(String username, String password) {

		try {
			return this.DBService.loginDB("restaurants", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Restaurant getRestaurant(String username) {
		try {
			return new Restaurant(this.DBService.getRestaurant(username));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Integer CalculateDistanceDeliveryRest(String deliveryman, String r_id) {
		try {
			String[] restaurantString = this.DBService.getRestaurant(r_id);
			Restaurant restaurant = new Restaurant(restaurantString);

			// can be refactor
			String[] deliveryString = this.DBService.getDeliveryMan(deliveryman);
			DeliveryMan deliverymanOb = new DeliveryMan(deliveryString);
			String deliverymanAddress = deliverymanOb.getAddress().replace(" ", "%20");

			String findplaceurl = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
					+ deliverymanAddress + "&destinations=" + restaurant.getLatitude() + "," + restaurant.getLongitude()
					+ "&key=" + apikey;

			HttpRequest request = HttpRequest.newBuilder().uri(new URI(findplaceurl)).GET().build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			JsonObject jp = new JsonParser().parse(response.body()).getAsJsonObject();

			if (response.body().contains("ZERO_RESULTS")) {
				return null;
			}

			System.out.println(response.body());
			Integer time = jp.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("elements").get(0)
					.getAsJsonObject().getAsJsonObject("duration").get("value").getAsInt();
			System.out.println(time);
			return time;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Integer CalculateDistanceMemberRest(String username, String r_id) {
		try {
			String[] restaurantString = this.DBService.getRestaurant(r_id);
			Restaurant restaurant = new Restaurant(restaurantString);

			String userAddress = this.DBService.getMember(username).getAddress().replace(" ", "%20");

			String findplaceurl = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + userAddress
					+ "&destinations=" + restaurant.getLatitude() + "," + restaurant.getLongitude() + "&key=" + apikey;

			HttpRequest request = HttpRequest.newBuilder().uri(new URI(findplaceurl)).GET().build();

			HttpClient client = HttpClient.newHttpClient();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			JsonObject jp = new JsonParser().parse(response.body()).getAsJsonObject();

			if (response.body().contains("ZERO_RESULTS")) {
				return null;
			}

			System.out.println(response.body());
			Integer time = jp.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("elements").get(0)
					.getAsJsonObject().getAsJsonObject("duration").get("value").getAsInt();
			System.out.println(time);
			return time;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Map<Restaurant, Integer> SearchRestaurantByDistance(String username) {

		try {
			String[][] ret = this.DBService.getAllRestaurant();

			Restaurant[] restaurants = new Restaurant[29];

			String userAddress = this.DBService.getMember(username).getAddress().replace(" ", "%20");

			for (int i = 0; i < 29; i++) {
				restaurants[i] = new Restaurant(ret[i]);
			}

//			for (Restaurant r : restaurants) {
//				System.out.println(r.getName());
//			}

			String[] findplaceurl = new String[29];

			Map<Restaurant, Integer> map = new HashMap<>();

			for (int i = 0; i < 29; i++) {
//	            findplaceurl[i] = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + latitude + "," + longitude + "&destinations=" + data[i].getlatitude() + "," + data[i].getlongitude() + "&key=" + apikey;
				findplaceurl[i] = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + userAddress
						+ "&destinations=" + restaurants[i].getLatitude() + "," + restaurants[i].getLongitude()
						+ "&key=" + apikey;

				HttpRequest request = HttpRequest.newBuilder().uri(new URI(findplaceurl[i])).GET().build();

				HttpClient client = HttpClient.newHttpClient();
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

				JsonObject jp = new JsonParser().parse(response.body()).getAsJsonObject();

				if (response.body().contains("ZERO_RESULTS")) {
					return null;
				}

				Integer time = jp.getAsJsonArray("rows").get(0).getAsJsonObject().getAsJsonArray("elements").get(0)
						.getAsJsonObject().getAsJsonObject("duration").get("value").getAsInt();

//				System.out.println(time);
				map.put(restaurants[i], time);

			}

//			map.forEach((key, value)->{System.out.println(key.getName() + "time: " + value);});	
			Map<Restaurant, Integer> sortedMap = map.entrySet().stream().sorted(Entry.comparingByValue())
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//			sortedMap.forEach((key, value)->{System.out.println(key.getName() + "time: " + value);});

			return sortedMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
//	public void addUser(User usr) {
//		// ??????????????????status enum??????
//		// ?????????output
//		if (!searchUserByUserName(usr.getUserName())) {
//			Users.put(usr.getUserName(), usr);
//		}
//		else {
//			System.out.println("What");
//		}
//	}

	public ArrayList<Restaurant> SearchRestaurantByName(String input) {
		// TODO Auto-generated method stub
		try {

			ArrayList<String[]> ret = this.DBService.searchRestaurantByName(input);
			if (ret.size() == 0) {
				return null;
			}

			ArrayList<Restaurant> restaurants = new ArrayList();

			for (String[] e : ret) {
				restaurants.add(new Restaurant(e));
			}
			return restaurants;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Restaurant> SearchRestaurantByType(String input) {
		// TODO Auto-generated method stub
		try {

			ArrayList<String[]> ret = this.DBService.searchRestaurantByType(input);
			if (ret.size() == 0) {
				return null;
			}

			ArrayList<Restaurant> restaurants = new ArrayList();

			for (String[] e : ret) {
				restaurants.add(new Restaurant(e));
			}
			return restaurants;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Restaurant> SearchRestaurantByCoupon(String input) {
		// TODO Auto-generated method stub
		try {
			if (input.equals("???200??????"))
				input = "buy_200_get_90_percent_off";
			if (input.equals("???300??????"))
				input = "buy_300_get_80_percent_off";
			if (input.equals("???20"))
				input = "save_20_dollars";
			if (input.equals("???30"))
				input = "save_30_dollars";

			ArrayList<String[]> ret = this.DBService.searchRestaurantByCoupon(input);

			if (ret.size() == 0) {
				return null;
			}

			ArrayList<Restaurant> restaurants = new ArrayList();

			for (String[] e : ret) {
				restaurants.add(new Restaurant(e));
			}
			return restaurants;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//	public boolean searchUserByUserName(String username) {
//		return Users.containsKey(username);
//	}
//
//	public boolean checkPassword(String username, String password) {
//		return Users.get(username).getPassword().equals(password);
//	}
//
//	public String searchUserTypeByUserName(String username) {
//		return Users.get(username).getClass().getSimpleName();
//	}

}
