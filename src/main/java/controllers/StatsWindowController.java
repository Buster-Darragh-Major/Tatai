package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import main.java.game.Level;
import main.java.game.TataiGame;
import main.java.stats.Stat;
import main.java.stats.StatSkill;
import main.java.users.user.Student;
import main.java.users.user.User;

/**
 * The controller for the stats view
 * 
 * @author Nathan Cairns
 *
 */
public class StatsWindowController extends TataiController implements Initializable {

	/* Macros */
	public final static String SEELEVEL1 = "See Level 1";
	public final static String SEELEVEL2 = "See Level 2";
	public final static String LEVEL1 = "Level 1: ";
	public final static String LEVEL2 = "Level 2: ";

	/* Fields */
	private String _firstName;
	private User _user;

	/* FXML Nodes */
	@FXML
	private Label _statLabel;
	@FXML
	private Label _statTitleLabel;
	@FXML
	private Label _personalBestLabel;
	@FXML
	private Button _averageButton;
	@FXML
	private Button _correctButton;
	@FXML
	private Button _incorrectButton;
	@FXML
	private Button _totalButton;
	@FXML
	private FontAwesomeIconView _exitButton;
	@FXML
	private Button _switchLevelButton;
	@FXML
	private FontAwesomeIconView _averageStar;
	@FXML
	private FontAwesomeIconView _correctStar;
	@FXML
	private FontAwesomeIconView _incorrectStar;
	@FXML
	private FontAwesomeIconView _totalStar;
	@FXML
	private FontAwesomeIconView _personalBestStar;
	@FXML
	private FontAwesomeIconView _helpButton;
	@FXML
	private CheckBox _checkBox;

	Tooltip _tp = new Tooltip();

	/**
	 * Constructor
	 */
	public StatsWindowController() {
		_user = _game.getCurrentUser();
		_firstName = _user.firstName();
	}

	/**
	 * Upon initialization.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_game.setLevel(Level.LEVEL1);
		updateValues();

		if (_user instanceof Student) {
			updateStars();
		} else {
			hideStars();
		}
	}

	/**
	 * Update values shown
	 */
	private void updateValues() {
		String level = null;
		if (_switchLevelButton.getText().equals(SEELEVEL2)) {
			level = LEVEL1;
		} else if (_switchLevelButton.getText().equals(SEELEVEL1)) {
			level = LEVEL2;
		}

		_statLabel.setText(_game.averageAsPercent());
		_statTitleLabel.setText(level + "Average Score for " + _firstName);
		_averageButton.setText(_game.averageAsPercent());
		_correctButton.setText("" + _game.correct());
		_incorrectButton.setText("" + _game.incorrect());
		_totalButton.setText("" + _game.totalPlayed());
		_personalBestLabel.setText("" + _game.personalBest());
	}

	/**
	 * Change the stats label
	 * 
	 * @param text
	 *            the text to change the label to
	 * @param paint
	 *            the color to set the text
	 */
	public void changeLabel(String text, String descripton, Paint paint) {
		String level = null;

		if (_switchLevelButton.getText().equals(SEELEVEL2)) {
			level = LEVEL1;
		} else if (_switchLevelButton.getText().equals(SEELEVEL1)) {
			level = LEVEL2;
		}

		String paintHex = paint.toString();
		paintHex = "#" + paintHex.substring(2, paintHex.length() - 2);

		_statLabel.setText(text);
		_statLabel.setStyle("-fx-border-color: " + paintHex + "; -fx-text-fill: " + paintHex + ";");

		_statTitleLabel.setText(level + descripton + " for " + _firstName);
		_statTitleLabel.setTextFill(paint);
	}

	@FXML
	public void handleCheckBoxClick() {
		if (_checkBox.isSelected()) {
			if (_game.currentLevel() == Level.LEVEL1) {
				_game.setLevel(Level.LEVEL1_REVERSE);
			} else if (_game.currentLevel() == Level.LEVEL2) {
				_game.setLevel(Level.LEVEL2_REVERSE);
			}
		} else {
			if (_game.currentLevel() == Level.LEVEL1_REVERSE) {
				_game.setLevel(Level.LEVEL1);
			} else if (_game.currentLevel() == Level.LEVEL2_REVERSE) {
				_game.setLevel(Level.LEVEL2);
			}
		}
		updateValues();
		handleAverageButtonClick();

		if (_user instanceof Student) {
			updateStars();
		}
	}

	/**
	 * Handles user pressing average
	 */
	@FXML
	public void handleAverageButtonClick() {
		changeLabel("" + _game.averageAsPercent(), TataiGame.AVERAGE, _averageButton.getTextFill());
	}

	/**
	 * Handles user pressing correct
	 */
	@FXML
	public void handleCorrectButtonClick() {
		changeLabel("" + _game.correct(), TataiGame.CORRECT, _correctButton.getTextFill());
	}

	/**
	 * Change the stats label to the incorrect
	 */
	@FXML
	public void handleIncorrectButtonClick() {
		changeLabel("" + _game.incorrect(), TataiGame.INCORRECT, _incorrectButton.getTextFill());
	}

	/**
	 * Change the stats label to the total played/
	 */
	@FXML
	public void handleTotalButtonClick() {
		changeLabel("" + _game.totalPlayed(), TataiGame.TOTAL_PLAYED, _totalButton.getTextFill());
	}

	/**
	 * When clicked return to main menu
	 */
	@FXML
	public void handleExitButtonClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		changeWindow("MainWindow.fxml", stage);
	}

	@FXML
	public void switchLevel() {
		_checkBox.setSelected(false);
		if (_switchLevelButton.getText().equals(SEELEVEL2)) {
			_game.setLevel(Level.LEVEL2);
			_switchLevelButton.setText(SEELEVEL1);
		} else if (_switchLevelButton.getText().equals(SEELEVEL1)) {
			_game.setLevel(Level.LEVEL1);
			_switchLevelButton.setText(SEELEVEL2);
		}

		updateValues();
		handleAverageButtonClick();

		if (_user instanceof Student) {
			updateStars();
		}
	}

	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleExitButtonClick();
		} else if (e.getCode() == KeyCode.F1) {
			handleHelpClick();
		}
	}

	@FXML
	public void handleHelpClick() {
		Stage stage = (Stage) _averageButton.getScene().getWindow();
		changeWindow("StatsHelpWindow.fxml", stage);
	}

	@FXML
	public void handleIconHover(MouseEvent e) {
		Node node = (Node) e.getSource();
		Stage stage = (Stage) _averageButton.getScene().getWindow();

		if (node instanceof FontAwesomeIconView) {
			Student student = (Student) _user;
			if (node.equals(_averageStar)) {
				_tp.setText("Average: " + getSkillText(student.getStatSkill(Stat.AVERAGE, _game.currentLevel())));
			} else if (node.equals(_correctStar)) {
				_tp.setText("Correct: " + getSkillText(student.getStatSkill(Stat.TOTAL_CORRECT, _game.currentLevel())));
			} else if (node.equals(_incorrectStar)) {
				_tp.setText(
						"Incorrect: " + getSkillText(student.getStatSkill(Stat.TOTAL_INCORRECT, _game.currentLevel())));
			} else if (node.equals(_totalStar)) {
				_tp.setText("Total: " + getSkillText(student.getStatSkill(Stat.TOTAL_PLAYED, _game.currentLevel())));
			} else if (node.equals(_personalBestStar) || node.equals(_personalBestLabel)) {
				_tp.setText("Personal Best: "
						+ getSkillText(student.getStatSkill(Stat.PERSONAL_BEST, _game.currentLevel())));
			}
			_tp.setAutoHide(true);
			_tp.setStyle("-fx-font-size: 20");
			_tp.show(node, stage.getX() + e.getSceneX(), stage.getY() + e.getSceneY());
		} else {
			_tp.hide();
		}
	}
	/**/

	/**
	 * Displays relevant text for a specific skill level
	 * 
	 * @param skill
	 *            the skill
	 * @return relevant text
	 */
	private String getSkillText(StatSkill skill) {
		String skillText = null;

		switch (skill) {
		case BRONZE:
			skillText = "Bronze";
			break;
		case GOLD:
			skillText = "Gold";
			break;
		case NONE:
			skillText = "No";
			break;
		case PLATINUM:
			skillText = "Platinum";
			break;
		case SILVER:
			skillText = "Silver";
			break;
		default:
			break;
		}

		return skillText + " Level";
	}

	/**
	 * Hides achievements
	 */
	private void hideStars() {
		hideFontAwesomeIcon(_averageStar);
		hideFontAwesomeIcon(_correctStar);
		hideFontAwesomeIcon(_incorrectStar);
		hideFontAwesomeIcon(_totalStar);
		hideFontAwesomeIcon(_personalBestStar);
		_personalBestLabel.setVisible(false);
	}

	private void hideFontAwesomeIcon(FontAwesomeIconView fa) {
		fa.setVisible(false);
	}

	/**
	 * Updates the stars colours
	 */
	private void updateStars() {
		updateStar(Stat.AVERAGE, _averageStar);
		updateStar(Stat.TOTAL_CORRECT, _correctStar);
		updateStar(Stat.TOTAL_INCORRECT, _incorrectStar);
		updateStar(Stat.TOTAL_PLAYED, _totalStar);
		updateStar(Stat.PERSONAL_BEST, _personalBestStar);
	}

	/**
	 * update the colour of a specific star
	 * 
	 * @param stat
	 * @param fa
	 */
	private void updateStar(Stat stat, FontAwesomeIconView fa) {
		String styleClass = getStatSkillCss(stat, _game.currentLevel());
		changeStyleClass(fa, styleClass);
	}

	/**
	 * Takes the stat and level you wish to enquire and returns a string
	 * representing the style class to use.
	 * 
	 * @param stat
	 *            the stat you wish to enquire about.
	 * @param level
	 *            the level of the stat you wish to enquire about
	 * @return the style sheet to assign to the star
	 */
	private String getStatSkillCss(Stat stat, Level level) {
		Student student = (Student) _game.getCurrentUser();
		StatSkill skill = student.getStatSkill(stat, level);

		switch (skill) {
		case PLATINUM:
			return "icon-platnium";
		case GOLD:
			return "icon-gold";
		case SILVER:
			return "icon-silver";
		case BRONZE:
			return "icon-bronze";
		default:
			return "icon-none";
		}
	}

	/**
	 * Change the style class of the icons appropriately
	 * 
	 * @param fa
	 *            the star to change
	 * @param styleClass
	 *            the class to assign
	 */
	private void changeStyleClass(FontAwesomeIconView fa, String styleClass) {
		if (fa.getStyleClass() != null) {
			fa.getStyleClass().clear();
		}
		fa.getStyleClass().add(styleClass);
	}

}
