package creations.cr;

import java.util.ArrayList;
import java.util.List;

import creations.labelgenerator.LabelGenerator;
import creations.labelgenerator.Level1LabelGenerator;
import creations.ml.CreationBuilder;
import creations.ml.CreationModel;
import javafx.scene.paint.Color;

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
	public static final int DEFAULT_NUMBER_OF_CREATIONS = 10;
	
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
	
	/* Fields */
	private LabelGenerator _labelStrategy;
	private int _numberOfCreations;
	
	/**
	 * Constructor
	 */
	public TataiCreationModel() {
		this(new Level1LabelGenerator(), DEFAULT_NUMBER_OF_CREATIONS);
	}
	
	/**
	 * Constructor
	 * @param numberOfCreations : int
	 */
	public TataiCreationModel(int numberOfCreations) {
		this (new Level1LabelGenerator(), numberOfCreations);
	}
	
	/**
	 * Constructor
	 * @param labelGenerator : LabelGenerator
	 */
	public TataiCreationModel(LabelGenerator labelGenerator) {
		this (labelGenerator, DEFAULT_NUMBER_OF_CREATIONS);
	}
	
	/**
	 * Constructor
	 * @param labelGenerator : LabelGenerator
	 * @param numberOfCreations : int
	 */
	public TataiCreationModel(LabelGenerator labelGenerator, int numberOfCreations) {
		super();
		_labelStrategy = labelGenerator;
		_numberOfCreations = numberOfCreations;
	}
	
	/**
	 * Set the way creations are labeled.
	 * 
	 * @param lg label generator to use.
	 */
	public void setLabelingStrategy(LabelGenerator lg) {
		_labelStrategy = lg;
	}
	
	/**
	 * Set the number of creations generated when calling updateModel().
	 * s
	 * @param numberOfCreations
	 */
	public void setNumberOfCreations(int numberOfCreations) {
		_numberOfCreations = numberOfCreations;
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
	
	/**
	 * This method populates the model with the required number of creations. The number of questions
	 * depends on the game type, for equation game types it is statically set to 10, whereas for the custom
	 * game mode it depends on the length of the custom list that is read off.
	 */
	@Override
	public <T extends Creation> void updateModel(Class<T> creationClass) {
		List<Creation> creationList = new ArrayList<Creation>();
		
		for (int i = 0; i < _numberOfCreations; i++) {
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
