package main.java.creations.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.paint.Color;
import main.java.creations.creation.Creation;
import main.java.creations.creation.CreationException;

/**
 * This class deals with generating creations, implements a builder design pattern
 * 
 * @author Nathan Cairns
 * @author Buster Major
 *
 */
public class CreationBuilder {
	/* MACROS */
	public static final String DEFAULT_LABEL = "NO LABEL SET";
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.web("#9198EE"); // pastel blue
	public static final Color DEFAULT_FONT_COLOR = Color.web("#910094"); // Richer Violet

	/* Fields */
	private String _label;
	private Color _bgColor;
	private Color _fontColor;

	/* Default Constructor */
	public CreationBuilder() {
		_label = DEFAULT_LABEL;
		_bgColor = DEFAULT_BACKGROUND_COLOR;
		_fontColor = DEFAULT_FONT_COLOR;
	}

	/**
	 * Sets background hex color value for creation.
	 * @param color
	 * @return
	 */
	public CreationBuilder backgroundColor(Color color) {
		_bgColor = color;

		return this;
	}

	/**
	 * Sets font hex color value for creation.
	 * @param color
	 * @return
	 */
	public CreationBuilder fontColor(Color color) {
		_fontColor = color;

		return this;
	}

	/**
	 * Sets number int value for creation.
	 * @param label
	 * @return
	 */
	public CreationBuilder number(String label) {
		_label = label;

		return this;
	}

	/**
	 * Builds creation with current set parameters
	 * @param creationClass
	 * @return
	 */
	public <T extends Creation> T build(Class<T> creationClass) {
		T builtCreation = null;

		try {
			Constructor<T> constructor = creationClass.getConstructor(java.lang.String.class,
					javafx.scene.paint.Color.class, javafx.scene.paint.Color.class);

			builtCreation = (T) constructor.newInstance(_label, _bgColor, _fontColor);
		} catch (CreationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return builtCreation;
	}

}
