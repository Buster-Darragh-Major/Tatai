package main.java.controllers;

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
import main.java.game.TataiGame;

/**
 * An abstract class intended to be inherited from by any controller for the
 * Tatai application. Controllers are interested in changing views from one
 * .fxml document to another, all controllers inherit a function used for easily
 * transitioning between views.
 * 
 * @author Buster Major
 */
public abstract class TataiController {
	/* Fields */
	protected TataiGame _game;
	
	/* Macros */
	protected static final String INCORRECT_RED = "#f73333";
	protected static final String CORRECT_GREEN = "#00d10a";
	protected static final String WHITE = "#ffffff";
	protected static final String NEXT = "Next";
	protected static final String FXML_LOCATION = "/main/resources/view/fxml/";	
	public static final String CUSTOM_LIST_SELECTION_EDIT_FXML = "CustomListEditView.fxml";
	public static final String CUSTOM_LIST_SELECTION_FXML = "CustomListSelectionWindow.fxml";
	public static final String GAME_FXML = "GameWindow.fxml";
	public static final String LEVEL_SELECT_CONFIRMATION_FXML = "LevelSelectConfirmationWindow.fxml";
	public static final String LEVEL_SELECT_FXML = "LevelSelectWindow.fxml";
	public static final String MAIN_FXML = "MainWindow.fxml";
	public static final String PRATICE_FXML = "PracticeWindow.fxml";
	public static final String RESULTS_FXML = "ResultsWindow.fxml";
	public static final String REVERSE_GAME_FXML = "ReverseGamemodeWindow.fxml";
	public static final String STATS_HELP_FXML = "StatsHelpWindow.fxml";
	public static final String  STATS_FXML = "StatsWindow.fxml";
	public static final String TEACHER_INPUT_NAMING_FXML = "TeacherInputNamingWindow.fxml";
	public static final String TEACHER_INPUT_FXML = "TeacherInputWindow.fxml";
	public static final String TUTORIAL_FXML = "TutorialWindow.fxml";
	public static final String USER_FORM_FXML = "UserFormWindow.fxml";
	public static final String USER_FXML = "UserWindow.fxml";
	
	public TataiController() {
		_game = Context.getInstance().currentGame();
	}
	
	/**
	 * Changes the current view embedded in the desired stage. The fxml String
	 * points to the file desired to be the root of the new view, and the stage
	 * refers to the main stage needing to be changed.
	 */
	protected void changeWindow(String fxmlFile, Stage stage) {
		Parent root = null;
		try {
			// Load root .fxml file for new view
			root = FXMLLoader.load(getClass().getResource(FXML_LOCATION + fxmlFile));
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
