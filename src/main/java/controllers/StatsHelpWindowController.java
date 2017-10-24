package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StatsHelpWindowController extends TataiController implements Initializable {
	
	/* FXML Nodes */
	@FXML
	private FontAwesomeIconView _backButton;
	
	/**
	 * Handles user pressing back
	 */
	@FXML
	public void handleBackClick() {
		changeWindow(STATS_FXML, _backButton);
	}
	
	/**
	 * Handles key binding
	 * @param e : KeyEvent
	 */
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleBackClick();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
