package model;

public class Member extends User {
	// 做會員info輸出系統
	boolean is_vip;
	int vip_expire_date;
	
	// TODO OrderHandler
	
	public Member() {
		
	}
	
	public Member(String username, String password, String telephone_number, String email, String name) {
		super(username, password, telephone_number, email, name);
		this.is_vip = false;
		this.vip_expire_date = -1;
	}
	
	public Member(User usr) {
		super(usr);
		this.is_vip = false;
		this.vip_expire_date = -1;
	}
	
	public void getMemberInfo() {
		// TODO view page
	}	
	
	public void searchMemberDetail() {
		
		
	}
	
	public void becomeVIP() {
		// TODO
		// 經過一串手續 才能call become vip或是修改值
	}
	
	// status->string, 用enum實作
	// 先new 一個order by input 人 商家, ParseOrder by UI 就是說要點啥 並計算, 確認後再丟placeOrder回傳狀態
	public void placeOrder(Order order) {
		// push order in order list 
		//		Order newOrder = new Order(this.is_vip_);
		// new一單
		// 讓單子吃距離 計算外送金額回傳
		// check是否付費會員 加總金額 存進單子內
		
		//		return newOrder.getStatus();
	}
	
	// 應該要回傳status
	// user.callSearch
	// 要想一下怎麼存怎麼查再遇到修改刪除時方便
	public Restaurant searchRestaurant(String restaurant_name) {
		// 要在model內生成 name->Restaurant
		// 每註冊一個就要新增一個
		// 問要怎麼查 存enum
		// 判定查詢方式
		// call 查詢方式
		// 判斷是否查詢成功 輸出UI並回傳狀態？
		return null;
	}
	
	public Restaurant searchRestaurant(Restaurant restaurant, String type) {
		return restaurant;
	}
	
	// view出去 告訴我是啥單
	public String checkOrderStatus(int no) {
		return new String();
	}
	


}
