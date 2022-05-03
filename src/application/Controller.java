package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {
	private Stage stage;
	private Scene scene;
	private Parent root;

	protected void setUsernameLb(String username) {
		
	}
	
	protected void render() {
		
	}
	
	
	//Helper function to switch
	// Version 1: Without user info (For logout)
	protected void switchScene(final ViewEnum p, ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(p.getFxmlFile()));
		root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	// Version 2: With user info (For login)
	protected void switchScene(final ViewEnum p, ActionEvent event, String username) throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource(p.getFxmlFile()));
		root = loader.load();
		Controller controller = loader.getController();
		controller.setUsernameLb(username); // Use this to set the username to view. Note that you should set it to database or model or somewhere else. 	
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


}
