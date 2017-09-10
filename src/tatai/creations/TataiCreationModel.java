package tatai.creations;

import java.util.ArrayList;
import java.util.List;

import creations.cr.Creation;
import creations.ml.CreationBuilder;
import creations.ml.CreationModel;
import javafx.scene.paint.Color;
import tatai.creations.labelgenerator.Level1RandomNumberLabelGenerator;
import tatai.creations.labelgenerator.Level2RandomNumberLabelGenerator;
import tatai.creations.labelgenerator.LabelGenerator;

/**
 * Class representing the model of the current set of creations. Holds the list of
 * creations in the model, and allows adding and retrieval of creations, as well as
 * default population based on difficulty settings.
 * 
 * @author Buster Major
 * @author Nathan Cairns
 */
public class TataiCreationModel extends CreationModel{
	/*MACROS*/
	public static final int NUMBER_OF_CREATIONS = 10;
	
	// Approved colors for background - light, non-harsh
	private static final Color[] BACKGROUND_COLORS = new Color[] {
			Color.web("#EE91D8"), // Pastel Pink
			Color.web("#C491EE"), // Pastel Purple
			Color.web("#9198EE"), // Pastel Blue
			Color.web("#91EEE9"), // Pastel Cyan
			Color.web("#EE9191"), // Pastel Red
			Color.web("#EEB791"), // Pastel Orange
			Color.web("#E6EE91"), // Pastel Yellow
			Color.web("#90EE90"), // Pastel Green
			Color.web("#FFF480"), // Stronger Yellow
			Color.web("#FF8585"), // Stronger Red
			Color.web("#85FF8b"), // Stronger Green
			Color.web("#85ABFF"), // Stronger Baby Blue
			Color.web("#BC85FF"), // Stronger Violet
			Color.web("#FF85F9"), // Stronger Pink
			Color.web("#FF85AD")  // Stronger Salmon Pink
	};
	
	// Approved colors for text - darker, higher contrast
	private static final Color[] FONT_COLORS = new Color[] {
			Color.web("#C20A0A"), // Dark Red
			Color.web("#C24A0A"), // Dark Orange
			Color.web("#1B9608"), // Dark Green
			Color.web("#078D67"), // Dark Cyan
			Color.web("#084891"), // Dark Blue
			Color.web("#1A0891"), // Dark Deep Blue
			Color.web("#830726"), // Brown
			Color.web("#05009E"), // Richer Blue
			Color.web("#730099"), // Richer Purple
			Color.web("#009459"), // Richer Bluegreen
			Color.web("#008D94"), // Richer Cyan
			Color.web("#029400"), // Richer Green
			Color.web("#940000"), // Richer Red
			Color.web("#944F00"), // Richer Orange
			Color.web("#910094")  // Richer Violet
	};
	
	private LabelGenerator _labelStrategy;
	private Level _level;
	
	public TataiCreationModel() {
		super();
		
		_labelStrategy = new Level1RandomNumberLabelGenerator();
	}
	
	public void setLabelingStrategy(LabelGenerator lg) {
		_labelStrategy = lg;
	}
	
	/**
	 * Sets the difficulty level of the current population creation model. The difficulty
	 * will effect the model by determining what range of numbers are set to creations.
	 * 
	 * @param level
	 */
	public void setLevel(Level level) {
		_level = level;
	}
	
	/**
	 * Returns the creation needed for the corresponding question number. Returns the
	 * creation placed in the list for a specific question in the quiz.
	 * 
	 * @param questionNo
	 * @return Creation : TataiCreation
	 */
	public Creation getCreation(int questionNo) {
		// Questions go from 1-10, list indexes go from 0-9
		return _creations.get(questionNo - 1);
	}
	
	// TODO move this method to TataiGame and make TataiGame contain a CreationModel
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
		Class<T> creationClass = null;
		
		switch (_level) {
		case Level1: _labelStrategy = new Level1RandomNumberLabelGenerator();
			creationClass = (Class<T>) TataiCreation.class;
			break;
		case Level2: _labelStrategy = new Level2RandomNumberLabelGenerator();
			creationClass = (Class<T>) TataiCreation.class;
			break;
		}
		
		updateModel(creationClass);
	}
	
	/**
	 * Generate a color from the background color list.
	 * 
	 * @return
	 */
	private Color generateBackgroundColor() {
		return BACKGROUND_COLORS[(int) Math.floor((BACKGROUND_COLORS.length * Math.random()))];
	}
	
	/**
	 * Generate a color from the font color list.
	 * 
	 * @return
	 */
	private Color generateFontColor() {
		return FONT_COLORS[(int) Math.floor((FONT_COLORS.length * Math.random()))];
	}
	
	/**l
	 * This method populates the model with the required number of creations.
	 */
	@Override
	public <T extends Creation> void updateModel(Class<T> creationClass) {
		List<Creation> creationList = new ArrayList<Creation>();
		
		for (int i = 0; i < NUMBER_OF_CREATIONS; i++) {
			String label = _labelStrategy.generateLabel();
			Color bgColor = generateBackgroundColor();
			Color fontColor = generateFontColor();
			
			Creation creation = new CreationBuilder().number("" + label)
					.backgroundColor(bgColor)
					.fontColor(fontColor)
					.build(creationClass);
			
			creationList.add(creation);
		}
		
		_creations = creationList;
	}

}