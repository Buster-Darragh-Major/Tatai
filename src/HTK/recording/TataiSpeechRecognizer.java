package HTK.recording;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

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
				
				// Change working location of builder to work in the directory containing HTK sript
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
				System.out.println(line);
			}	
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> getText() {
		return _output;
	}
	
}
