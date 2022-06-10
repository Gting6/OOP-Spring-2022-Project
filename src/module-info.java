module OOP {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires com.google.gson;
	requires javafx.base;
	requires okhttp3;
	requires java.net.http;
	opens application to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
}
