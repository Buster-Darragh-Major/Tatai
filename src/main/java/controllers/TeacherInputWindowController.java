package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.questionlist.TextQuestionListHandler;

public class TeacherInputWindowController extends TataiController implements Initializable {

	/* MACROS */
	public static final String PLUS = "+";
	public static final String MINUS = "-";
	public static final String DIVISION = "÷";
	public static final String MULTIPLICATION = "x";
	public static final String EQUALS = "=";
	public static final String QUESTION = "Question";
	public static final String QUESTIONS = "Questions";
	
	/* FXML Nodes */
	@FXML private Label _inputLabel;
	@FXML private Label _warningLabel;
	@FXML private Label _questionNoLabel;
	@FXML private Button _0;
	@FXML private Button _1;
	@FXML private Button _2;
	@FXML private Button _3;
	@FXML private Button _4;
	@FXML private Button _5;
	@FXML private Button _6;
	@FXML private Button _7;
	@FXML private Button _8;
	@FXML private Button _9;
	@FXML private Button _plus;
	@FXML private Button _minus;
	@FXML private Button _times;
	@FXML private Button _divide;
	@FXML private Button _enterButton;
	@FXML private Button _clearButton;
	@FXML private Button _submitButton;
	@FXML private FontAwesomeIconView _exitButton;
	
	/* Fields */
	private TextQuestionListHandler _handler;
	
	/**
	 * The following methods handle input from on screen keyboard
	 */
	@FXML
	public void handle0Click() {
		addToLabel(ZERO_TEXT);
	}
	
	@FXML
	public void handle1Click() {
		addToLabel(ONE_TEXT);
	}
	
	@FXML
	public void handle2Click() {
		addToLabel(TWO_TEXT);
	}
	
	@FXML
	public void handle3Click() {
		addToLabel(THREE_TEXT);
	}
	
	@FXML
	public void handle4Click() {
		addToLabel(FOUR_TEXT);
	}
	
	@FXML
	public void handle5Click() {
		addToLabel(FIVE_TEXT);
	}
	
	@FXML
	public void handle6Click() {
		addToLabel(SIX_TEXT);
	}
	
	@FXML
	public void handle7Click() {
		addToLabel(SEVEN_TEXT);
	}
	
	@FXML
	public void handle8Click() {
		addToLabel(EIGHT_TEXT);
	}
	
	@FXML
	public void handle9Click() {
		addToLabel(NINE_TEXT);
	}
	
	@FXML
	public void handlePlusClick() {
		addToLabel(PLUS);
	}
	
	@FXML
	public void handleMinusClick() {
		addToLabel(MINUS);
	}
	
	@FXML
	public void handleTimesClick() {
		addToLabel(MULTIPLICATION);
	}
	
	@FXML
	public void handleDivideClick() {
		addToLabel(DIVISION);
	}
	
	/**
	 * Handles user pressing exit button
	 */
	@FXML
	public void handleExitClick() {
		if (_handler.size() == 0) {
			_handler.delete(); // If user has deleted all questions, delete file.
		}

		changeWindow(TEACHER_INPUT_NAMING_FXML, _exitButton);
	}
	
	/**
	 * Handles user pressing clear button
	 */
	@FXML
	public void handleClearClick() {
		_inputLabel.setText("");
		addToLabel("");
	}
	
	/**
	 * Handles user pressing enter
	 */
	@FXML
	public void handleEnterClick() {
		// Alter buttons and label
		_enterButton.setDisable(true);
		_submitButton.setDisable(false);
		_inputLabel.setText("");
		
		// Write contents of label to custom list file
		_handler.writeToFile(_inputLabel.getText() + EQUALS);
		
		// Update label in lower corner
		if (_handler.size() == 1) {
			_questionNoLabel.setText(_handler.size() + " " + QUESTION);
		} else {
			_questionNoLabel.setText(_handler.size() + " " + QUESTIONS);
		}
	}
	
	/**
	 * Adds the character pressed on the keyboard to the label if syntactically sound
	 * @param character
	 */
	private void addToLabel(String character) {
		String equation = _inputLabel.getText();
		
		if (character.equals(PLUS) || character.equals(MINUS) || character.equals(MULTIPLICATION) || character.equals(DIVISION)) {
			if ((!equation.contains(PLUS)) && (!equation.contains(MINUS)) && 
					(!equation.contains(MULTIPLICATION)) && (!equation.contains(DIVISION)) && (equation.length() > 0)) {
				_inputLabel.setText(_inputLabel.getText() + character);
			} else {
				flashText(_inputLabel);
			}
		} else {
			_inputLabel.setText(_inputLabel.getText() + character);
		}
		
		if (isValid(_inputLabel.getText())) {
			_warningLabel.setVisible(false);
		} else {
			_warningLabel.setVisible(true);
		}
	}
	
	/**
	 * Checks that input from the user is valid for a game of tatai, all answers to questions must
	 * be between 1 - 99 and division questions must evaluate to an integer > 0.
	 * @return
	 */
	private boolean isValid(String equation) {
		if ((!equation.contains(PLUS)) && (!equation.contains(MINUS)) && 
				(!equation.contains(MULTIPLICATION)) && (!equation.contains(DIVISION))) {
			return true;
		}
		
		List<String> nums = new ArrayList<String>();
		
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(equation);
		
		while (m.find()) {
			nums.add(m.group());
		}
		
		if (nums.size() < 2) {
			return true;
		}
		
		int operand1 = Integer.parseInt(nums.get(0));
		int operand2 = Integer.parseInt(nums.get(1));
		
		if (equation.contains(PLUS)) {
			if ((operand1 + operand2 <= 99) && (operand1 + operand2 >= 1)) {
				_enterButton.setDisable(false);
				return true;
			}
		} else if (equation.contains(MINUS)) {
			if ((operand1 - operand2 <= 99) && (operand1 - operand2 >= 1)) {
				_enterButton.setDisable(false);
				return true;
			}
		} else if (equation.contains(MULTIPLICATION)) {
			if ((operand1 * operand2 <= 99) && (operand1 * operand2 >= 1)) {
				_enterButton.setDisable(false);
				return true;
			}
		} else if (equation.contains(DIVISION)) {
			if ((operand1 / operand2 <= 99) && (operand1 / operand2 >= 1) && (operand1 % operand2 == 0)) {
				_enterButton.setDisable(false);
				return true;
			}
		}
		
		_enterButton.setDisable(true);
		return false;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_enterButton.setDisable(true);
		_warningLabel.setVisible(false);
		_submitButton.setDisable(true);
		
		_handler = new TextQuestionListHandler(Context.getInstance().currentQuestionList());
		_handler.makeList();
		
		_questionNoLabel.setText(_handler.size() + " " + QUESTIONS);
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
		} else if (code == KeyCode.X || code == KeyCode.MULTIPLY) {
			handleTimesClick();
		} else if (code == KeyCode.SLASH || code == KeyCode.DIVIDE) {
			handleDivideClick();
		} else if (code == KeyCode.PLUS || code == KeyCode.ADD) {
			handlePlusClick();
		} else if (code == KeyCode.MINUS || code == KeyCode.SUBTRACT) {
			handleMinusClick();
		} else if (code == KeyCode.BACK_SPACE) {
			handleClearClick();
		} else if (code == KeyCode.ENTER) {
			handleEnterClick();
		}
	}
	
}
