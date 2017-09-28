package HTK.recording;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.concurrent.Task;

public class TataiSpeechRecognizer implements SpeechRecognizer{

	public static final File FILEPATH = new File(
			System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
			System.getProperty("file.separator") + "HTK" +
			System.getProperty("file.separator") + "MaoriNumbers");
	
	private ArrayList<String> _output = new ArrayList<String>();
	
	public void record() {
		String command = "./GoSpeech";
		
		// Build a builder with relevant bash command line command
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
		
		// Change working location of builder to work in the directory containing HTK script
		builder.directory(FILEPATH);
		
		try {
			Process process = builder.start();
			try {
				process.waitFor(); // Ensure thread waits for process to complete
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {
		}		
	}
	
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILEPATH + 
					System.getProperty("file.separator") + "recout.mlf"));
			String line;
			while ((line = reader.readLine()) != null) {
				_output.add(line);
			}	
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		extractWords();
	}
	
	private void extractWords() {
		for (int i = _output.size() - 1; i >= 0; i--) {
			if (_output.get(i).equals("sil") || _output.get(i).equals(".") || 
					_output.get(i).equals("#!MLF!#") || _output.get(i).equals("\"*/foo.rec\"")) {
				_output.remove(i);
			}
		}
	}
	
	public void playback() {
		String command = "aplay foo.wav";
		
		// Build a builder with relevant Bash line command
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
		
		// Change working location directory of builder to be correct directory
		builder.directory(FILEPATH);
		
		Process process;
		try {
			process = builder.start();
			try {
				process.waitFor(); // Ensure thread waits for process to complete
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {
		}		
				
	}
	
	public void cleanup() {
		String command = "rm foo.wav";
		
		// Build a builder with relevant Bash line command
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
		
		// Change working location directory of builder to be correct directory
		builder.directory(FILEPATH);
		
		Process process;
		try {
			process = builder.start();
			try {
				process.waitFor(); // Ensure thread waits for process to complete
			} catch (InterruptedException e) {
			} 
		} catch (IOException e) {
		}		
				
	}

	public ArrayList<String> getText() {
		return _output;
	}
	
}
