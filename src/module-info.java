module OOP {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires com.google.gson;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
