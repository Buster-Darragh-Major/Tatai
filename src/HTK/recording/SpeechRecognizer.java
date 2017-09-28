package HTK.recording;

import java.util.ArrayList;

/**
 * Classes that implement this interface are intended to have the ability of recognizing speech
 * through a recording process. They have the ability to record, read the speech audio file into
 * test as well as play back the audio.
 * 
 * @author Buster Major
 */
public interface SpeechRecognizer {
	
	/**
	 * Records the speech necessary to read off for the object
	 */
	public void record();
	
	/**
	 * Returns the speech in the form of plain text as a series of strings stored in a list
	 * @return textList : ArrayList<String>
	 */
	public ArrayList<String> getText();
	
	/**
	 * Plays back the current speech audio file
	 */
	public void playback();
}
