package tatai.game;

import java.util.ArrayList;
import java.util.List;

import creations.cr.Creation;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import stats.CSVStatsHandler;
import stats.StatisticHandler;
import tatai.creations.Level;
import tatai.creations.TataiCreation;
import tatai.creations.TataiCreationModel;
import tatai.creations.labelgenerator.Level1LabelGenerator;
import tatai.creations.labelgenerator.Level2LabelGenerator;
import tatai.translator.TataiTranslator;
import tatai.translator.Translator;
import users.classroom.ClassRoom;
import users.classroom.TataiClassRoom;

/**
 * Class that deals with the abstract parameters of the game itself, i.e. the
 * current game state. This concerns things like current game difficulty level,
 * any text relevant to a game / game level, amount of questions answered,
 * amount correct, incorrect, references to the CreationModel storing the
 * answered/unanswered creations, etc.
 * 
 * @author Buster Major
 * @author Nathan Cairns
 */
public class TataiGame {
	public static final int TOTAL_NUMBER_OF_QUESTIONS = 10;
	public static final String AVERAGE = "Average Score";
	public static final String TOTAL_PLAYED = "Total Answered";
	public static final String INCORRECT = "Total Incorrect";
	public static final String CORRECT = "Total Correct";
	
	protected Level _level = Level.Level1;
	protected int _questionNo;
	protected TataiCreationModel _creationModel;
	protected Translator _translator;
	protected boolean _hasStarted = false;
	protected int _correct;
	protected int _incorrect;
	protected boolean _firstAttempt;
	protected ArrayList<String> _questionsCorrect = new ArrayList<String>();
	protected StatisticHandler _statsHandler;
	
	protected ClassRoom _classRoom;
	
	protected StatisticHandler _l01statsHandler = new CSVStatsHandler(Level.Level1);
	protected StatisticHandler _l02statsHandler = new CSVStatsHandler(Level.Level2);

	/**
	 * Constructor
	 */
	public TataiGame() {
		_correct = 0;
		_incorrect = 0;
		_questionNo = 0;
		_firstAttempt = false;
		
		_creationModel = new TataiCreationModel();
		_translator = new TataiTranslator();
		
		_classRoom = new TataiClassRoom();
	}
	
	/**
	 * Gets the current class room
	 * @return the current class
	 */
	public ClassRoom getClassRoom() {
		return _classRoom;
	}
	
	public int totalNumberOfQuestions() {
		return TOTAL_NUMBER_OF_QUESTIONS;
	}
	
	/**
	 * Provides a translation of the current question
	 */
	public String translateCurrentQuestion() {
		String label = _creationModel.getCreationLabel(_questionNo);
		
		return _translator.translate(label);
	}

	/**
	 * Sets the current level difficulty for the game object.
	 * 
	 * @param level
	 */
	public void setLevel(Level level) {
		if (!_hasStarted) {
			_level = level;
		} else {
			throw new GameException("Cannot change the level in the middle of a game!");
		}
		
	}

	/**
	 * Gets the current level difficulty for the game object.
	 * 
	 * @returns Level : level
	 */
	public Level currentLevel() {
		return _level;
	}

	/**
	 * Gets the current question number for the game object
	 * 
	 * @return int : Question Number
	 */
	public int currentQuestion() {
		return _questionNo;
	}

	/**
	 * Display the current question
	 */
	public void displayCurrentQuestion(Label label, Pane pane) {
		_creationModel.displayCreation(_questionNo, label, pane);
	}

	/**
	 * i question number for next question in quiz
	 */
	private void nextQuestion() {
		_firstAttempt = false;
		if ((_questionNo < TOTAL_NUMBER_OF_QUESTIONS) && (_questionNo > 0)) {
			_questionNo++;
		} else {
			_questionNo++;
			endGame();
		}
	}

	/**
	 * Returns a header for the current set level difficulty.
	 * 
	 * @return String : header
	 */
	public String getLevelHeader() {
		if (_level == Level.Level1) {
			return ("Level 1");
		} else if (_level == Level.Level2) {
			return ("Level 2");
		}
		return null;
	}

	/**
	 * Returns a description of the current set level difficulty.
	 * 
	 * @return String : description
	 */
	public String getLevelDescription() {
		if (_level == Level.Level1) {
			return ("Ten questions ranging from numbers 1-9");
		} else if (_level == Level.Level2) {
			return ("Ten questions ranging from numbers 1-99");
		}
		return null;
	}

	/**
	 * Returns the game state.
	 * 
	 * @return true if game has started and is active, false otherwise.
	 */
	public boolean isActive() {
		return _hasStarted;
	}

	/**
	 * Start the game
	 */
	public void startGame() {
		if (!_hasStarted) {
			populateModel();
			
			_correct = 0;
			_incorrect = 0;
			_questionNo = 1;
			_hasStarted = true;
			_firstAttempt = false;
			_questionsCorrect = new ArrayList<String>();
			
			if (_level == Level.Level1) {
				_statsHandler = _l01statsHandler;
			} else if (_level == Level.Level2) {
				_statsHandler = _l02statsHandler;
			}
			
		} else {
			throw new GameException("Game has already started");
		}
	}
	
	/**
	 * Restart the game
	 */
	public void restartGame() {
		endGame();
		startGame();
	}
	
	/**
	 * Get amount you got correct
	 */
	public int questionsCorrect() {
		return _correct;
	}
	
	/**
	 * Get amount you got incorrect
	 */
	public int questionIncorrect() {
		return _incorrect;
	}
	
	/**
	 * Answer the current question 
	 * THIS IS A STUB
	 */
	public boolean answerQuestion(boolean answer) {

		if (answer) {
			_questionsCorrect.add("Correct");
			_correct++;
			nextQuestion();	
			return true;
		} else {
			if (_firstAttempt) {
				_questionsCorrect.add("Incorrect");
				_incorrect++;
				nextQuestion();
			} else {
				_firstAttempt = true;
			}
			return false;
		}
	}
	
	/**
	 * Return whether or not the question has already been attempted
	 * 
	 * @return true if already attempted, false otherwise
	 */
	public boolean getAttempted() {
		return _firstAttempt;
	}
	
	/**
	 * Populate the creationModel with a list of creations with
	 * appropriate labels for the current level.s
	 */
	public <T extends Creation> void populateModel() {
		@SuppressWarnings("unchecked")
		Class<T> creationClass = (Class<T>) TataiCreation.class;

		switch (_level) {
		case Level1:
			_creationModel.setLabelingStrategy(new Level1LabelGenerator());
			break;
		case Level2:
			_creationModel.setLabelingStrategy(new Level2LabelGenerator());
			break;
		}
		
		_creationModel.updateModel(creationClass);
	}
	
	/**
	 * End the game
	 */
	public void endGame() {
		if (_hasStarted) {
			_statsHandler.updateStats(_questionNo - 1, _correct, _incorrect);
			_hasStarted = false;
		} else {
			throw new GameException("Game has already ended");
		}
	}
	
	/**
	 * Returns a list of integers as a String in the order they were played 
	 * in the game
	 */
	public ArrayList<String> getQuestionInts() {
		List<Creation> creations = _creationModel.listCreations();
		ArrayList<String> ints = new ArrayList<String>();
		
		for (int i = 0; i < TataiCreationModel.DEFAULT_NUMBER_OF_CREATIONS; i++) {
			ints.add(i, creations.get(i).label());
		}
		
		return ints;
	}
	
	/**
	 * Returns a list of translated integers as a String in the order they
	 * were played in the game
	 */
	public ArrayList<String> getQuestionTrans() {
		ArrayList<String> trans = new ArrayList<String>();
		
		for (int i = 0; i < TataiCreationModel.DEFAULT_NUMBER_OF_CREATIONS; i++) {
			String creation = _creationModel.getCreationLabel(i + 1);
			trans.add(i, _translator.translate(creation));
		}
		
		return trans;
	}
	
	/**
	 * Returns a list of correct correct/incorrect tags in the order the questions
	 * were answered in the game.
	 */
	public ArrayList<String> getQuestionCorrect() {
		return _questionsCorrect;
	}
	
	//// Stats Methods \\\\
	
	public void setStatsHandlerLevel(Level level) {
		if (level == Level.Level1) {
			_statsHandler = _l01statsHandler;
		} else if (level == Level.Level2) {
			_statsHandler = _l02statsHandler;
		}
		
	}
	
	/**
	 * Returns the average as a percentage 
	 * 
	 * return average
	 */
	public String averageAsPercent() {
		if (averageAsDouble() <= 0) {
			return "%" + 0.00;
		}
		
		String per = "" + (averageAsDouble() * 10);
		per = per.substring(0, 4) + "%";
		return per;
	}
		 
	/**
	 * Returns this sessions current average
	 * 
	 * @return The average as a double
	 */
	public double averageAsDouble() {
		return _statsHandler.average();
	}
	
	/**
	 * Total played
	 */
	public int totalPlayed() {
		return _statsHandler.totalPlayed();
	}
	
	/**
	 * correct
	 */
	public int correct() {
		return _statsHandler.totalCorrect();
	}
	
	/**
	 * incorrect
	 */
	public int incorrect() {
		return _statsHandler.totalIncorrect();
	}
}