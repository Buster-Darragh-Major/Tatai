package main.java;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.controllers.Context;

public class App extends Application {

	/* THIS IS WHERE THE FUN BEGINS */
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Context.getInstance().newGame();
			Context.getInstance().setGameToEquation();
		
			//Parent root = FXMLLoader.load(getClass().getResource("UserWindow.fxml"));
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/fxml/UserWindow.fxml"));
			Parent root = loader.load(); 
		    
	        Scene scene = new Scene(root, 800, 500);
	       
	        scene.getStylesheets().add(getClass().getResource("/main/resources/view/stylesheets/TataiTheme.css").toExternalForm());	        
	        
	        primaryStage.setTitle("Tatai");
	        primaryStage.setScene(scene);
	        primaryStage.setMinWidth(800);
	        primaryStage.setMinHeight(500);
	        primaryStage.show();
	        	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
