package main.java.stats;

import java.io.File;

/**
 * This interface contains global constants which refer to files used by
 * multiple classes.
 * 
 * @author Nathan Cairns.
 * @author Buster Major.
 *
 */
public interface TataiPaths {

	public static final File TATAI_DIR = new File(
			System.getProperty("user.dir") + System.getProperty("file.separator") + "TataiData");
	public static final File MAORI_NUMBERS_DIR = new File(
			TATAI_DIR.toString() + System.getProperty("file.separator") + "MaoriNumbers");
	public static final File DATA_DIR = new File(TATAI_DIR.toString() + System.getProperty("file.separator") + "data");

}
