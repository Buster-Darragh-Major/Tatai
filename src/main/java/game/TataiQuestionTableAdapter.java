package main.java.game;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class intending to represent the bare minimal information corresponding to a single
 * Tatai Question. The class predominantly serves as an adapter between the game data stored
 * in the TataiGame object, and the PropertyValueFactory used in the ResultsWindowController.
 * The formatting of method and variable names are set up in the method to handle this conversion
 * and are highly volatile if tampered with.
 * 
 * @author Buster Major
 */
public class TataiQuestionTableAdapter {
	
	/* Fields */
	private final SimpleStringProperty qNo;
	private final SimpleStringProperty qInt;
	private final SimpleStringProperty qTranslation;
	private final SimpleStringProperty qCorrect;
	
	/**
	 * Constructor
	 * 
	 * @param questNo :String
	 * @param questInt : String
	 * @param questTranslation : String
	 * @param questCorrect : String
	 */
	public TataiQuestionTableAdapter(String questNo, String questInt, String questTranslation, String questCorrect) {
		qNo = new SimpleStringProperty(questNo);
		qInt = new SimpleStringProperty(questInt);
		qTranslation = new SimpleStringProperty(questTranslation);
		qCorrect = new SimpleStringProperty(questCorrect);
	}
	
	public StringProperty qNoProperty() {
		return qNo;
	}
	
	public String getQNo() {
		return qNo.get();
	}
	
	public void setQNo(String questNo) {
		qNo.set(questNo);
	}
	
	public StringProperty qIntProperty() {
		return qInt;
	}
	
	public String getQInt() {
		return qInt.get();
	}
	
	public void setQInt(String questInt) {
		qInt.set(questInt);
	}
	
	public StringProperty qTranslationProperty() {
		return qTranslation;
	}
	
	public String getQTranslation() {
		return qTranslation.get();
	}
	
	public void setQTranslation(String questTranslation) {
		qTranslation.set(questTranslation);
	}
	
	public StringProperty qCorrectProperty() {
		return qCorrect;
	}
	
	public String getQCorrect() {
		return qCorrect.get();
	}
	
	public void setQCorrect(String questCorrect) {
		qCorrect.set(questCorrect);
	}
}
