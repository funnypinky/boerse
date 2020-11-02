package org.funnypinky.boerse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	private final Scene scene = new Scene(new VBox());
	
	 @Override
	    public void start(Stage stage) throws Exception {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(this.getClass().getResource("view/mainView.fxml"));
	        Parent root = loader.load();
	        this.scene.setRoot(root);
	        stage.setScene(scene);
	        stage.setTitle("Analyse Tool Depot");
	        stage.show();
	    }

	 public static void main(String[] args) {
	        launch(args);
	    }

	
}
