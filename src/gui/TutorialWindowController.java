package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TutorialWindowController  extends TataiController implements Initializable {
	/* MACROS */
	public final static String FILEPATH_START = "res/imgs/tutorial/";

	/* FXML Nodes */
	@FXML private Button _nextButton;
	@FXML private Button _backButton;
	@FXML private Button _exitButton;
	@FXML private Circle _progressIcon1;
	@FXML private Circle _progressIcon2;
	@FXML private Circle _progressIcon3;
	@FXML private Circle _progressIcon4;
	@FXML private Circle _progressIcon5;
	@FXML private Circle _progressIcon6;
	@FXML private Circle _progressIcon7;
	@FXML private ImageView _imageView;
	@FXML private TextArea _textArea;
	@FXML private Label _titleLabel;
	
	/* Fields */
	private int _page = 1;
	
	@FXML public void handleNextClick() {
		_page++;
		if (_page == 2) {
			_progressIcon2.setFill(Color.GREEN);
			updateSlide(FILEPATH_START + "LevelSelect.png", "Pick your level! \n\nThere will be a short description of the level before you begin.");
		} else if (_page == 3) {
			_progressIcon3.setFill(Color.GREEN);
			updateSlide(FILEPATH_START + "LevelStart.png", "Read the level description and get ready play!");
		} else if (_page == 4) {
			_progressIcon4.setFill(Color.GREEN);
			updateSlide(FILEPATH_START + "record.png", "Record your answer! \n\n The label describes what you need to pronounce. \nPress the Record button to "
					+ "record your attempt \nor click the exit button at any time to quits");
		} else if (_page == 5) {
			_progressIcon5.setFill(Color.GREEN);
			updateSlide(FILEPATH_START + "submit.png", "Review your attempt. \n\nFrom here you can relisten to your audio and submit you answer to be tested.");
		} else if (_page == 6) {
			_progressIcon6.setFill(Color.GREEN);
			updateSlide(FILEPATH_START + "incorrect.png", "If you got it wrong \n\nYou will see this screen.  \nHere you have the option to try again (a maximum of "
					+ "two attempts is allowed) or skip to the next question. If you skip the question it will be counted as incorrect");
		} else if (_page == 7) {
			_progressIcon7.setFill(Color.GREEN);
			updateSlide(FILEPATH_START + "Right.png", "If you got it right \n\nYou will see this screen. \nCongrats! you will be able to goto the next question.");
		} else if (_page == 8) {
			clearSlide();
			_titleLabel.setText("YOU ARE READY \nTO PLAY!");
		} else {
			Stage stage = (Stage) _nextButton.getScene().getWindow();
			changeWindow("MainWindow.fxml", stage);
		}
	}
	
	@FXML public void handleBackClick() {
		_page--;
		
		if(_page == 1) {
			_progressIcon2.setFill(Color.TRANSPARENT);
			updateSlide(FILEPATH_START + "MainWindow.png", "Welcome To Tatai! \n\nThis is the main menu. \n1.Press the play button to start "
					+ "playing. \n2.Press the stats button for some stats.");
		} else if (_page == 2) {
			_progressIcon3.setFill(Color.TRANSPARENT);
			updateSlide(FILEPATH_START + "LevelSelect.png", "Pick your level! \n\nThere will be a short description of the level before you begin.");
		} else if (_page == 3) {
			_progressIcon4.setFill(Color.TRANSPARENT);
			updateSlide(FILEPATH_START + "LevelStart.png", "Read the level description and get ready play!");
		} else if (_page == 4) {
			_progressIcon5.setFill(Color.TRANSPARENT);
			updateSlide(FILEPATH_START + "record.png", "Record your answer! \n\n The label describes what you need to pronounce. \nPress the Record button to "
					+ "record your attempt \nor click the exit button at any time to quits");
		} else if (_page == 5) {
			_progressIcon6.setFill(Color.TRANSPARENT);
			updateSlide(FILEPATH_START + "submit.png", "Review your attempt. \n\nFrom here you can relisten to your audio and submit you answer to be tested.");
		} else if (_page == 6) {
			_progressIcon7.setFill(Color.TRANSPARENT);
			updateSlide(FILEPATH_START + "incorrect.png", "If you got it wrong \n\nYou will see this screen.  \nHere you have the option to try again (a maximum of "
					+ "two attempts is allowed) or skip to the next question. If you skip the question it will be counted as incorrect");
		} else if (_page == 7) {
			_progressIcon7.setFill(Color.GREEN);
			updateSlide(FILEPATH_START + "Right.png", "If you got it right \n\nYou will see this screen. \nCongrats! you will be able to goto the next question.");
			_titleLabel.setText("");
		} else if (_page == 8) {
			clearSlide();
			_titleLabel.setText("YOU ARE READY \nTO PLAY!");
		} else {
			Stage stage = (Stage) _nextButton.getScene().getWindow();
			changeWindow("MainWindow.fxml", stage);
		}
	}
	
	private void updateSlide(String imageFilepath, String description) {
		Image img = new Image(imageFilepath);
		_imageView.setImage(img);
		_textArea.setText(description);
	}
	
	private void clearSlide() {
		_imageView.setImage(null);
		_textArea.clear();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_progressIcon1.setFill(Color.GREEN);
		_textArea.setWrapText(true);
		updateSlide(FILEPATH_START + "MainWindow.png", "Welcome To Tatai! \n\nThis is the main menu. \n1.Press the play button to start "
				+ "playing. \n2.Press the stats button for some stats.");
		_nextButton.requestFocus();
	}
	
	@FXML public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		changeWindow("MainWindow.fxml", stage);
	}

}
