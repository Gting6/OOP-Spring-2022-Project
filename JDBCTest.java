package db;

import java.sql.*;

public class JDBCTest {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?"
    		+ "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // register JDBC drive
            Class.forName(JDBC_DRIVER);
        
            // Connect
            System.out.println("Connecting to databases...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // Execute query
            System.out.println(" Example...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            ResultSet rs = stmt.executeQuery(sql);
        
            // SHOW
            while(rs.next()){
                // Search from string
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
    
                // Output
                System.out.print("ID: " + id);
                System.out.print(", Web name: " + name);
                System.out.print(", Web URL: " + url);
                System.out.print("\n");
            }
            // close
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // sql exceptions
            se.printStackTrace();
        }catch(Exception e){
            // Class.forName exceptions
            e.printStackTrace();
        }finally{
            // shutdown
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
