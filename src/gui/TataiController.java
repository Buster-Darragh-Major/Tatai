package gui;

import java.io.IOException;
import java.util.Optional;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * An abstract class intended to be inherited from by any controller for the
 * Tatai application. Controllers are interested in changing views from one
 * .fxml document to another, all controllers inherit a function used for easily
 * transitioning between views.
 * 
 * @author Buster Major
 */
public abstract class TataiController {

	/* Macros */
	protected static final String INCORRECT_RED = "#f73333";
	protected static final String CORRECT_GREEN = "#00d10a";
	protected static final String WHITE = "#ffffff";
	protected static final String NEXT = "Next";

	/**
	 * Changes the current view embedded in the desired stage. The fxml String
	 * points to the file desired to be the root of the new view, and the stage
	 * refers to the main stage needing to be changed.
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
		scene.getRoot().requestFocus();
		stage.setScene(scene); // Set current stage to show new view scene
		stage.show();
		stage.setWidth(width);
		stage.setHeight(height);
	}

	protected void flashText(Label label) {
		label.setStyle("-fx-text-fill: " + INCORRECT_RED + ";");

		// Create thread for flashing process
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Thread.sleep(100);
				return null;
			}
		};

		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				label.setStyle("-fx-text-fill: white;");
			}
		});

		Thread th = new Thread(task);
		th.start();
	}

	/**
	 * Starts a background thread
	 * 
	 * @param task:
	 *            the task to be performed in the background thread
	 */
	protected void startBackgroundThread(@SuppressWarnings("rawtypes") Task task) {
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	/**
	 * Shows a warning dialog which asks for confirmation
	 * 
	 * @param title
	 * @param message
	 * @return
	 */
	public boolean showWarningDialogConfirmation(String title, String message) {
		// Create alert
		Label l = new Label(message);
		l.setWrapText(true);
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.getDialogPane().setContent(l);

		// Create yes + no buttons
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("no", ButtonData.NO);
		alert.getButtonTypes().setAll(yesButton, noButton);

		// Handle the response
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == yesButton) {
			return true;
		} else {
			return false;
		}
	}
	
	public void showWarningDialog(String title, String message) {
		Label l = new Label(message);
		l.setWrapText(true);
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.getDialogPane().setContent(l);
		
		alert.show();
	}
}
