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
	public static final File HMMs_DIR = new File(
			MAORI_NUMBERS_DIR.toString() + System.getProperty("file.separator") + "HMMs");
	public static final File HMM15_DIR = new File(HMMs_DIR.toString() + System.getProperty("file.separator") + "hmm15");
	public static final File HMM_USER_DIR = new File(
			MAORI_NUMBERS_DIR.toString() + System.getProperty("file.separator") + "user");
	public static final File DATA_DIR = new File(TATAI_DIR.toString() + System.getProperty("file.separator") + "data");

	/**
	 * Checks whether the maori numbers dir exists and required files are present.
	 * 
	 * @return returns true if all files are present returns false otherwise
	 */
	public static boolean htkResourcesExists() {
		File maoriNumbDir = TataiPaths.MAORI_NUMBERS_DIR;
		File hmmdefs = new File(TataiPaths.HMM15_DIR.toString() + System.getProperty("file.separator") + "hmmdefs");
		File macros = new File(TataiPaths.HMM15_DIR.toString() + System.getProperty("file.separator") + "macros");
		File configLR = new File(
				TataiPaths.HMM_USER_DIR.toString() + System.getProperty("file.separator") + "configLR");
		File dictionaryD = new File(
				TataiPaths.HMM_USER_DIR.toString() + System.getProperty("file.separator") + "dictionaryD");
		File tiedList = new File(
				TataiPaths.HMM_USER_DIR.toString() + System.getProperty("file.separator") + "tiedList");
		File wordNetworkNum = new File(
				TataiPaths.HMM_USER_DIR.toString() + System.getProperty("file.separator") + "wordNetworkNum");

		if (maoriNumbDir.exists() && hmmdefs.exists() && macros.exists() && configLR.exists() && dictionaryD.exists()
				&& tiedList.exists() && wordNetworkNum.exists()) {
			return true;
		}
		return false;
	}
}
