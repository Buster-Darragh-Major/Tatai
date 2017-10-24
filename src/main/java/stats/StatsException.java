package main.java.stats;
/**
 * An exception to deal with stats errors
 * 
 * @author Nathan Cairns
 *
 */
@SuppressWarnings("serial")
public class StatsException extends RuntimeException{
	public StatsException(String msg) {
		super(msg);
	}
}
