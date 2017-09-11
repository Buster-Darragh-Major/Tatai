package tatai.game;

import creations.cr.Creation;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import tatai.creations.Level;
import tatai.creations.TataiCreation;
import tatai.creations.TataiCreationModel;
import tatai.creations.labelgenerator.Level1RandomNumberLabelGenerator;
import tatai.creations.labelgenerator.Level2RandomNumberLabelGenerator;
import tatai.translator.TataiTranslator;
import tatai.translator.Translator;

/**
 * Class that deals with the abstract parameters of the game itself, i.e. the current
 * game state. This concerns things like current game difficulty level, any text 
 * relevant to a game / game level, amount of questions answered, amount correct, 
 * incorrect, references to the CreationModel storing the answered/unanswered 
 * creations, etc.
 * 
 * @author Buster Major
 */
public class TataiGame {
	
	private Level _level = Level.Level1;
	private int _questionNo;
	private TataiCreationModel _creationModel;
	private Translator _translator;
	
	/**
	 * Constructor
	 */
	public TataiGame() {
		_questionNo = 1;
		_creationModel = new TataiCreationModel();
		_translator = new TataiTranslator();
	}
	
	/**
	 * Sets the current level difficulty for the game object.
	 * @param level
	 */
	public void setLevel(Level level) {
		_level = level;
	}
	
	/**
	 * Gets the current level difficulty for the game object.
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
	public void nextQuestion() {
		_questionNo++;
	}
	
	/**
	 * Returns a header for the current set level difficulty.
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
	 * Create a list of creations corresponding to the current level
	 * @param <T>
	 * 
	 * @param level The level to generate creations for.
	 * 
	 * @return The generated list of creations
	 */
	@SuppressWarnings("unchecked")
	public <T extends Creation> void populateModel() {
		Class<T> creationClass = (Class<T>) TataiCreation.class;;
		
		switch (_level) {
		case Level1: _creationModel.setLabelingStrategy(new Level1RandomNumberLabelGenerator());
			break;
		case Level2: _creationModel.setLabelingStrategy(new Level2RandomNumberLabelGenerator());
			break;
		}
		
		_creationModel.updateModel(creationClass);
	}
}
