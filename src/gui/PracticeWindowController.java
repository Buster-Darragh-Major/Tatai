package gui;

import java.net.URL;
import java.util.ResourceBundle;

import HTK.recording.TataiSpeechRecognizer;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PracticeWindowController extends TataiController implements Initializable {

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
	private Button _recordButton;
	
	@FXML
	public void handle0Click() {
		addToLabel("0");
	}
	
	@FXML
	public void handle1Click() {
		addToLabel("1");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle2Click() {
		addToLabel("2");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle3Click() {
		addToLabel("3");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle4Click() {
		addToLabel("4");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle5Click() {
		addToLabel("5");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle6Click() {
		addToLabel("6");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle7Click() {
		addToLabel("7");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle8Click() {
		addToLabel("8");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handle9Click() {
		addToLabel("9");
		_recordButton.setDisable(false);

	}
	
	@FXML
	public void handleDeleteClick() {
		_inputLabel.setText("");
		_recordButton.setDisable(true);

		
	}
	
	@FXML
	public void handleRecordClick() {
		TataiSpeechRecognizer speech = new TataiSpeechRecognizer();
		
		_recordButton.setDisable(true);
		_recordButton.setStyle("-fx-font: 12 System");
		_recordButton.setText("Recording...");
		
		// Create thread for recording process
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// Begin recording process
				speech.record();
				
				return null;
			}
		};
		
		// Once recording task is complete reconfigure GUI
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				_recordButton.setDisable(false);
				_recordButton.setStyle("-fx-font: 16 System");
				_recordButton.setText("Record");
			}
		});
		
		// Run thread
		Thread th = new Thread(task);
		th.start();
	}
	
	private void addToLabel(String num) {
		if (_inputLabel.getText().length() == 2) {
			flashText();
		} else if (_inputLabel.getText().equals("0")) {
			_inputLabel.setText(num);
		} else {
			_inputLabel.setText(_inputLabel.getText() + num);
		}
	}
	
	private void flashText() {
		_inputLabel.setStyle("-fx-text-fill: red;");
		
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
				_inputLabel.setStyle("-fx-text-fill: white;");
			}
		});
		
		Thread th = new Thread(task);
		th.start();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_recordButton.setDisable(true);
		
	}
}
