package HTK.recording;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * An implementation of the SpeechRecognizer interface intended to read speech for the Tatai
 * application format. This uses a provided bash script "./GoSpeech" for recording and analyzing
 * audio, outputting the data to a file recout.mlf. Playing capabilities come from playing off
 * a temporary foo.wax file storing the recording.
 * 
 * @author Buster Major
 */
public class TataiSpeechRecognizer implements SpeechRecognizer{

	/* Macros */
	public static final File FILEPATH = new File(
			System.getProperty("user.dir") + System.getProperty("file.separator") + "src" +
			System.getProperty("file.separator") + "HTK" +
			System.getProperty("file.separator") + "MaoriNumbers");
	
	/* Fields */
	private ArrayList<String> _output = new ArrayList<String>();
	
	/**
	 * Reads from the "./GoSpeech" script provided in the HTK.MaoriNumbers package
	 */
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
	
	/**
	 * Read from the recout.mlf that stores the interpreted output of the speech after
	 * "./GoSpeech" has been called.
	 */
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
	
	/**
	 * Removes header and other unwanted output from the recout.mlf file format.
	 */
	private void extractWords() {
		for (int i = _output.size() - 1; i >= 0; i--) {
			if (_output.get(i).equals("sil") || _output.get(i).equals(".") || 
					_output.get(i).equals("#!MLF!#") || _output.get(i).equals("\"*/foo.rec\"")) {
				_output.remove(i);
			}
		}
	}
	
	/**
	 * Plays back the audio stored in the foo.wav file, using a bash command to do so.
	 */
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
	
	/**
	 * Method intended to be called once recording, reading and play back processes have been 
	 * completed, deletes the now unnecessary foo.wav audio file from the directory.
	 */
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

	/**
	 * Returns the text read after this objects readFile() method has been called.
	 * 
	 * @return output : ArrayList<String>
	 */
	public ArrayList<String> getText() {
		return _output;
	}
	
}
