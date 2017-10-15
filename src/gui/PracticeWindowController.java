package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import HTK.recording.TataiSpeechRecognizer;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tatai.translator.TataiTranslator;

public class PracticeWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML
	private Label _inputLabel;
	@FXML
	private Label _feedbackLabel;
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
	private Button _exitButton;
	
	/* Fields */
	private ArrayList<String> _userAnswer;
	private String _trueAnswer;
	
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
		translate();
		record();
	}
	
	@FXML
	public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get Current stage
		changeWindow("LevelSelectWindow.fxml", stage);// Change to StatsWindow.fxml view
	}
	
	
	
	private void record() {
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
				
				speech.readFile();
				_userAnswer = speech.getText();
				speech.cleanup();
				
				if (compareAnswers()) {
					giveFeedback(true);
				} else {
					giveFeedback(false);
				}
			}
		});
		
		// Run thread
		Thread th = new Thread(task);
		th.start();		
	}
	
	
	private void giveFeedback(boolean correct) {
		if (correct) {
			_inputLabel.setStyle("-fx-background-color: " + CORRECT_GREEN);
		} else {
			_inputLabel.setStyle("-fx-background-color: " + INCORRECT_RED);
		}
		
		// Create thread for flashing process
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Thread.sleep(3000);
				return null;
			}
		};
		
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				_inputLabel.setStyle("-fx-background-color: " + "null");
			}
		});
		
		Thread th = new Thread(task);
		th.start();
	}
	
	
	
	private void translate() {
		TataiTranslator translator = new TataiTranslator();
		_trueAnswer = translator.translate(_inputLabel.getText());
	}
	
	
	
	private void addToLabel(String num) {
		if (_inputLabel.getText().length() == 2) {
			flashText(_inputLabel);
		} else if (_inputLabel.getText().equals("0")) {
			_inputLabel.setText(num);
		} else {
			_inputLabel.setText(_inputLabel.getText() + num);
		}
	}
	
	
	
	private boolean compareAnswers() {
		_trueAnswer = _trueAnswer.replace("mā", "maa");
		_trueAnswer = _trueAnswer.replace("whā", "whaa");
		_trueAnswer = _trueAnswer.replace("ā", "a");
		List<String> answerList = Arrays.asList(_trueAnswer.split(" "));
		
		ArrayList<String> concatList = new ArrayList<String>();
		
		for (int i = 0; i < answerList.size(); i++) {
			for (int j = 0; j < _userAnswer.size(); j++) {
				if (_userAnswer.get(j).equals(answerList.get(i))) {
					concatList.add(_userAnswer.get(j));
					break;
				}
			}
		}
		
		if (concatList.equals(answerList)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Handles key presses
	 * @param e The Key Event
	 */
	@FXML public void onKeyPress(KeyEvent e) {
		KeyCode code = e.getCode();
		if (code == KeyCode.ESCAPE) {
			handleExitClick();
		} else if (code == KeyCode.DIGIT0 || code == KeyCode.NUMPAD0) {
			handle0Click();
		} else if (code == KeyCode.DIGIT1 || code == KeyCode.NUMPAD1) {
			handle1Click();
		} else if (code == KeyCode.DIGIT2 || code == KeyCode.NUMPAD2) {
			handle2Click();
		} else if (code == KeyCode.DIGIT3 || code == KeyCode.NUMPAD3) {
			handle3Click();
		} else if (code == KeyCode.DIGIT4 || code == KeyCode.NUMPAD4) {
			handle4Click();
		} else if (code == KeyCode.DIGIT5 || code == KeyCode.NUMPAD5) {
			handle5Click();
		} else if (code == KeyCode.DIGIT6 || code == KeyCode.NUMPAD6) {
			handle6Click();
		} else if (code == KeyCode.DIGIT7 || code == KeyCode.NUMPAD7) {
			handle7Click();
		} else if (code == KeyCode.DIGIT8 || code == KeyCode.NUMPAD8) {
			handle8Click();
		} else if (code == KeyCode.DIGIT9 || code == KeyCode.NUMPAD9) {
			handle9Click();
		} else if (code == KeyCode.BACK_SPACE) {
			handleDeleteClick();
		} else if (code == KeyCode.ENTER && !_recordButton.isDisable()) {
			handleRecordClick();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_recordButton.setDisable(true);
	}
}
