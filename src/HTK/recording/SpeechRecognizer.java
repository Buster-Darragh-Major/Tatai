package HTK.recording;

import java.util.ArrayList;

public interface SpeechRecognizer {
	
	public void record();
	
	public ArrayList<String> getText();
}
