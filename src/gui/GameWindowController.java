package gui;

import java.net.URL;
import java.util.ResourceBundle;

import creations.tatai.TataiCreationModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class GameWindowController extends TataiController implements Initializable{
	
	@FXML
	private Label _intLabel;
	
	TataiCreationModel _model;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_intLabel.setAlignment(Pos.CENTER);
	}
}
