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
		// Create new thread for recording to occur in, recording takes time so allow
		// GUI to be responsive by assigning separate thread.
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				String command = "./GoSpeech";
				
				// Build a builder with relevant bash command line command
				ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
				
				// Change working location of builder to work in the directory containing HTK script
				builder.directory(FILEPATH);
				
				try {
					Process process = builder.start();
					process.waitFor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return null;
			}
		};
		
		// Start thread
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		
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
		// Create a new thread for the playback of the audio file to occur in, allows
		// GUI to remain responsive in this time
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				String command = "aplay foo.wav";
				
				// Build a builder with relevant Bash line command
				ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
				
				// Change working location directory of builder to be correct directory
				builder.directory(FILEPATH);
				
				Process process = builder.start();
				
				return null;
			}
		};
		
		// Start the thread
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}
	
	public void cleanup() {
		// Create a new thread for the removal of the audio file to occur in, allows
		// GUI to remain responsive in this time
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				String command = "rm foo.wav";
				
				// Build a builder with relevant Bash line command
				ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
				
				// Change working location directory of builder to be correct directory
				builder.directory(FILEPATH);
				
				Process process = builder.start();
				
				return null;
			}
		};
				
		// Start the thread
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	@Override
	public ArrayList<String> getText() {
		return _output;
	}
	
}
