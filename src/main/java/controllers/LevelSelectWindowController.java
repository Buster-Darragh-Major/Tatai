package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.java.game.Level;
import main.java.stats.TataiPaths;
import main.java.users.user.User;

public class LevelSelectWindowController extends TataiController implements Initializable {
	/* MACROS */
	public static final String LEVEL2_TOOLTIP = "Score 8 or more\nin Level 1 to unlock";

	/* FIELDS */
	private User _user;

	/* FXML Nodes */
	@FXML
	private Button _level1;
	@FXML
	private Button _level2;
	@FXML
	private Button _practice;
	@FXML
	private Button _customList;
	@FXML
	private FontAwesomeIconView _menuButton;
	@FXML
	private AnchorPane _level2Wrap;
	private Tooltip _tp;

	/**
	 * Constructor
	 */
	public LevelSelectWindowController() {
		_user = _game.getCurrentUser();
		_tp = new Tooltip();
	}

	/**
	 * Handle hovering
	 */
	@FXML
	public void handleHover(MouseEvent e) {
		Node node = (Node) e.getSource();
		Stage stage = (Stage) _level1.getScene().getWindow();
		if (node instanceof AnchorPane) {
			if (node.equals(_level2Wrap) && _level2.isDisabled()) {
				_tp.setText(LEVEL2_TOOLTIP);
				_tp.setAutoHide(true);
				_tp.setStyle("-fx-font-size: 20");
				_tp.show(node, stage.getX() + e.getSceneX(), stage.getY() + e.getSceneY());
			}
		} else {
			_tp.hide();
		}
	}

	/**
	 * Handles user pressing level 1 select
	 */
	@FXML
	public void handleLevel1Click() {
		_game.setLevel(Level.LEVEL1);

		changeWindow(LEVEL_SELECT_CONFIRMATION_FXML, _level1);
	}

	/**
	 * Handles user pressing level 2 select
	 */
	@FXML
	public void handleLevel2Click() {
		_game.setLevel(Level.LEVEL2);

		changeWindow(LEVEL_SELECT_CONFIRMATION_FXML, _level2);
	}

	/**
	 * Handle practice button click
	 */
	@FXML
	public void handlePracticeClick() {
		if (TataiPaths.htkResourcesExists()) {
			changeWindow(PRATICE_FXML, _practice);
		} else {
			showWarningDialog(FILE_NOT_FOUND_DIALOG, FILE_NOT_FOUND_DIALOG_MESSAGE);
		}
	}

	/**
	 * Handles user pressing main menu button
	 */
	@FXML
	public void handleMenuButtonClick() {
		changeWindow(MAIN_FXML, _menuButton);
	}

	/**
	 * Handles user pressing custom list button
	 */
	@FXML
	public void handleCustomListClick() {
		changeWindow(CUSTOM_LIST_SELECTION_FXML, _customList); // Change to CustomListSelectionWindow.fxml
	}

	/**
	 * Handle key binds
	 */
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleMenuButtonClick();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				_level1.requestFocus();
			}
		});
		if (!_user.isUnlocked(Level.LEVEL2)) {
			_level2.setDisable(true);
		} else {
			_level2.setDisable(false);
		}
	}

}
