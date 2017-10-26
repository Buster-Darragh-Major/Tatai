package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.HTK.recording.TataiSpeechRecognizer;
import main.java.stats.TataiPaths;
import main.java.translator.TataiTranslator;

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
	private FontAwesomeIconView _exitButton;

	/* Fields */
	private ArrayList<String> _userAnswer;
	private String _trueAnswer;

	/**
	 * The following methods handle corresponding keys in the on screen keyboard
	 * being pressed.
	 */
	@FXML
	public void handle0Click() {
		addToLabel(ZERO_TEXT);
	}

	@FXML
	public void handle1Click() {
		addToLabel(ONE_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle2Click() {
		addToLabel(TWO_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle3Click() {
		addToLabel(THREE_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle4Click() {
		addToLabel(FOUR_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle5Click() {
		addToLabel(FIVE_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle6Click() {
		addToLabel(SIX_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle7Click() {
		addToLabel(SEVEN_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle8Click() {
		addToLabel(EIGHT_TEXT);
		_recordButton.setDisable(false);
	}

	@FXML
	public void handle9Click() {
		addToLabel(NINE_TEXT);
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

	/**
	 * Handles user pressing exit button
	 */
	@FXML
	public void handleExitClick() {
		changeWindow(LEVEL_SELECT_FXML, _exitButton);
	}

	/**
	 * Records user
	 */
	private void record() {
		if (TataiPaths.htkResourcesExists()) {
			TataiSpeechRecognizer speech = new TataiSpeechRecognizer();

			// Change button disabilities
			_recordButton.setDisable(true);
			_recordButton.setStyle("-fx-font: 12 System");
			_recordButton.setText(RECORDING);

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
					// Change button disabilities
					_recordButton.setDisable(false);
					_recordButton.setStyle("-fx-font: 16 System");
					_recordButton.setText("Record");

					// Read outupt
					speech.readFile();
					_userAnswer = speech.getText();
					speech.cleanup();

					// Give visual feedback
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
		} else {
			showWarningDialog(FILE_NOT_FOUND_DIALOG, FILE_NOT_FOUND_DIALOG_MESSAGE);
		}
	}

	/**
	 * Handles visual on screen feedback
	 * 
	 * @param correct
	 */
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

	/**
	 * Translate input speech answer
	 */
	private void translate() {
		TataiTranslator translator = new TataiTranslator();
		_trueAnswer = translator.translate(_inputLabel.getText());
	}

	/**
	 * Add to the label what has been pressed on the keyboard
	 * 
	 * @param num
	 */
	private void addToLabel(String num) {
		if (_inputLabel.getText().length() == 2) {
			flashText(_inputLabel);
		} else if (_inputLabel.getText().equals("0")) {
			_inputLabel.setText(num);
		} else {
			_inputLabel.setText(_inputLabel.getText() + num);
		}
	}

	/**
	 * Compares validity of speech recognized answer with translated answer.
	 * 
	 * @return
	 */
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
	 * 
	 * @param e
	 *            The Key Event
	 */
	@FXML
	public void onKeyPress(KeyEvent e) {
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
