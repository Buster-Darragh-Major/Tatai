package HTK.recording;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpeechRecognizer {

	public static final File FILEPATH = new File(
			System.getProperty("user.dir") + System.getProperty("file.separator") + "HTK" +
			System.getProperty("file.separator") + "MaoriNumbers");
	
	private ArrayList<String> output;
	
	public void record() {
		String command = "./GoSpeech";
		
		// Build a builder with relevant bash command line command
		ProcessBuilder builder = new ProcessBuilder("bin/bash", "-c", command);
		
		// Change working location of builder to work in the directory containing HTK sript
		builder.directory(FILEPATH);
		
		try {
			Process process = builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getText() {
		return null;
		
	}
	
}
