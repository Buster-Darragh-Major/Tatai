package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	// TODO: Select a proper consistent font that allows a macron

	@Override
	public void start(Stage primaryStage) {
		try {
			Context.getInstance().newGame();
			Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		    
	        Scene scene = new Scene(root, 800, 500);
	       
	        scene.getStylesheets().add(getClass().getResource("TataiTheme.css").toExternalForm());
	        
	        primaryStage.setTitle("Tatai");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
