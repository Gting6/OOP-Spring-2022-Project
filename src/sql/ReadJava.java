package sql;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ReadJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBService dbs = new DBService();
		
		Gson gson = new Gson();

		try {
			Reader reader = Files.newBufferedReader(Paths.get("C:/Users/JS/Desktop/OOP/stores_detail.json"));
		    // convert JSON file to map
		    JsonArray jo = gson.fromJson(reader, JsonArray.class);

		    // print map entries
		    for (JsonElement stores : jo) {
		    	JsonObject store = (JsonObject) stores;
		        System.out.println(store);
		        String name = store.get("name").getAsString();
		        JsonObject pos = store.get("position").getAsJsonObject();
		        String pos_addr = pos.get("address").getAsString();
		        String latitude = pos.get("latitude").getAsString();
		        String longitude = pos.get("longitude").getAsString();
		        String phone = store.get("phone").getAsString();
		        String store_description = store.get("store_description").getAsString();
		        String order_description = store.get("order_description").getAsString();
		        JsonArray types = store.get("type").getAsJsonArray();
		        String[] type = new String[types.size()];
		        int i = 0;
		        for (JsonElement t : types) {
		        	type[i] = t.getAsString();
		        	i++;
		        }
		        JsonArray menus = store.get("menu").getAsJsonArray();
		        HashMap<String, Integer> menu = new HashMap<>();
		        i = 0;
		        for (JsonElement m : menus) {
		        	JsonObject item = (JsonObject) m;
		        	String[] tokens = item.get("price").getAsString().split(",");
		        	for(String t : tokens) {
		        		String[] t_bar = t.split(" ");
		        		if(t_bar.length > 1) {
		        			if(t_bar[0] == "")
		        				menu.put(item.get("name").getAsString()+"("+t_bar[1]+")", Integer.parseInt(t_bar[2]));
		        			else 
		        				menu.put(item.get("name").getAsString()+"("+t_bar[0]+")", Integer.parseInt(t_bar[1]));
		        		}
		        		else
				        	menu.put(item.get("name").getAsString(), Integer.parseInt(item.get("price").getAsString()));
//		        		for(String s : menu.keySet()) {
//		        			System.out.println(s + " = " + menu.get(s));
//		        		}
		        	}
		        }
		        
		        String[] business_time = new String[14];
		        JsonObject b_time = store.get("business_time").getAsJsonObject();
		        i = 0;
		        for(String day : b_time.keySet()) {
		        	JsonObject se = b_time.get(day).getAsJsonObject();
		        	business_time[i] = se.get("start").getAsString();
		        	i++;
		        	business_time[i] = se.get("end").getAsString();
		        	i++;
		        }
		        
		        String uuid = UUID.randomUUID().toString();
		        String password = "0";
		        String email = "test@123";
		        dbs.createRestaurant(uuid, password, email, name, pos_addr, latitude, longitude, phone, store_description, order_description);
		        
		        // type, menu, business time save at diff tables
		        dbs.createTypeRestaurants(type, uuid);
		        dbs.createProducts(menu, uuid);
		        dbs.createBusinessTime(business_time, uuid);
		        
		        
//		        for(int j = 0; j < business_time.length; j++) {
//		        	System.out.print(business_time[j] + ",");
//		        }
//		        System.out.println();
		        
		    }

		    // close reader
		    reader.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
