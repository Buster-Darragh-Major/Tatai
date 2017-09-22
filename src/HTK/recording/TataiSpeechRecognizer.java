package HTK.recording;

import java.io.File;
import java.util.ArrayList;

public class TataiSpeechRecognizer implements SpeechRecognizer{

	public static final File FILEPATH = new File(
			System.getProperty("user.dir") + System.getProperty("file.separator") + "HTK" +
			System.getProperty("file.separator") + "MaoriNumbers");
	
	public void record() {
		
	}
	
	public ArrayList<String> getText() {
		return null;
		
	}
	
}
