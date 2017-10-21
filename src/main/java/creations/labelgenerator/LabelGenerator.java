package main.java.creations.labelgenerator;

/**
 * An interface which allows a user to generate a random number.
 * Implementers should specify how the number is generated.
 * 
 * @author Nathan Cairns
 *
 */
public interface LabelGenerator {
	
	/**
	 * Generate a random number
	 * @return randomly generated number
	 */
	public String generateLabel();
}
