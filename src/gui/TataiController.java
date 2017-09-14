package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An abstract class intended to be inherited from by any controller for the Tatai
 * application. Controllers are interested in changing views from one .fxml document
 * to another, all controllers inherit a function used for easily transitioning 
 * between views.
 * 
 * @author Buster Major
 */
public abstract class TataiController {

	/* Macros */
	protected static final String INCORRECT_RED = "#f73333";
	protected static final String CORRECT_GREEN = "#00d10a";
	protected static final String WHITE = "#ffffff";
	
	/**
	 * Changes the current view embedded in the desired stage. The fxml String points
	 * to the file desired to be the root of the new view, and the stage refers to 
	 * the main stage needing to be changed.
	 */
	protected void changeWindow(String fxmlFile, Stage stage) {
		Parent root = null;
		try {
			// Load root .fxml file for new view
			root = FXMLLoader.load(getClass().getResource(fxmlFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		double width = stage.getWidth();
		double height = stage.getHeight();
		
		Scene scene = new Scene(root, width, height); // Create new scene based off new view root
		stage.setScene(scene); // Set current stage to show new view scene
		stage.show();
		stage.setWidth(width);
		stage.setHeight(height);
	}
	
}
