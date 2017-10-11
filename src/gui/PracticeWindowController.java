package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PracticeWindowController extends TataiController {

	@FXML
	private Label _inputLabel;
	@FXML
	private Button _0;
	@FXML
	private Button _1;
	@FXML
	private Button _2;
	@FXML
	private Button _3;
	@FXML
	private Button _4;
	@FXML
	private Button _5;
	@FXML
	private Button _6;
	@FXML
	private Button _7;
	@FXML
	private Button _8;
	@FXML
	private Button _9;
	
	@FXML
	public void handle0Click() {
		addToLabel("0");
	}
	
	@FXML
	public void handle1Click() {
		addToLabel("1");
	}
	
	@FXML
	public void handle2Click() {
		addToLabel("2");
	}
	
	@FXML
	public void handle3Click() {
		addToLabel("3");
	}
	
	@FXML
	public void handle4Click() {
		addToLabel("4");
	}
	
	@FXML
	public void handle5Click() {
		addToLabel("5");
	}
	
	@FXML
	public void handle6Click() {
		addToLabel("6");
	}
	
	@FXML
	public void handle7Click() {
		addToLabel("7");
	}
	
	@FXML
	public void handle8Click() {
		addToLabel("8");
	}
	
	@FXML
	public void handle9Click() {
		addToLabel("9");
	}
	
	@FXML
	public void handleDeleteClick() {
		_inputLabel.setText("");
	}
	
	@FXML
	public void handleRecordClick() {
		
	}
	
	private void addToLabel(String num) {
		_inputLabel.setText(_inputLabel.getText() + num);
	}
}
