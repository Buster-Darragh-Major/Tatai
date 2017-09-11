package tatai.game;

import creations.cr.Creation;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import stats.CSVStatsHandler;
import stats.StatisticHandler;
import tatai.creations.Level;
import tatai.creations.TataiCreation;
import tatai.creations.TataiCreationModel;
import tatai.creations.labelgenerator.Level1RandomNumberLabelGenerator;
import tatai.creations.labelgenerator.Level2RandomNumberLabelGenerator;
import tatai.translator.TataiTranslator;
import tatai.translator.Translator;

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
	
	private Level _level = Level.Level1;
	private int _questionNo;
	private TataiCreationModel _creationModel;
	private Translator _translator;
	private boolean _hasStarted = false;
	private StatisticHandler _statsHandler;
	private int _correct;
	private int _incorrect;
	private boolean _firstAttempt;

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
		_statsHandler = new CSVStatsHandler();
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
	 * Increments question number for next question in quiz
	 */
	private void nextQuestion() {
		_firstAttempt = false;
		if (_questionNo < TOTAL_NUMBER_OF_QUESTIONS && _questionNo > 0) {
			_questionNo++;
		} else {
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
			return ("Ten questions ranging from numbers 1-10");
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
		boolean wasCorrect;
		if (answer) {
			_correct++;
			nextQuestion();
			wasCorrect = true;
		} else {
			wasCorrect = false;
		}
		
		if (_firstAttempt) {
			_incorrect++;
			nextQuestion();
		} else {
			_firstAttempt = true;
		}
		
		return wasCorrect;
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
	private <T extends Creation> void populateModel() {
		@SuppressWarnings("unchecked")
		Class<T> creationClass = (Class<T>) TataiCreation.class;

		switch (_level) {
		case Level1:
			_creationModel.setLabelingStrategy(new Level1RandomNumberLabelGenerator());
			break;
		case Level2:
			_creationModel.setLabelingStrategy(new Level2RandomNumberLabelGenerator());
			break;
		}
		
		_creationModel.updateModel(creationClass);
	}
	
	/**
	 * End the game
	 */
	public void endGame() {
		if (_hasStarted) {
			_statsHandler.updateStats(_questionNo, _correct, _incorrect);

			_hasStarted = false;
		} else {
			throw new GameException("Game has already ended");
		}
	}
}