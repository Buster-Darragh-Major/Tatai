package stats;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TataiQuestion {
	
	private final SimpleStringProperty qNo;
	private final SimpleStringProperty qInt;
	private final SimpleStringProperty qTranslation;
	private final SimpleStringProperty qCorrect;
	
	public TataiQuestion(String questNo, String questInt, String questTranslation, String questCorrect) {
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
