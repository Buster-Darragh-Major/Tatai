package main.java.questionlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import main.java.stats.TataiPaths;

/**
 * This class deals with writing custom list equations to particular files. On construction
 * the object takes a String relating to the file name it is to read/write off. (Note: the input
 * string should not include the '.txt' suffix in the file name). Once created, the objects
 * methods can be called as many times as desired and the file will be written to / read
 * accordingly, with the exception of the delete() method, where the file will be erased.
 * Use of the object then becomes nullified, and a new one must be created. If the relevant
 * files does not exist on creation, the object will create one in the constructor method.
 * This can only be done once in the constructor.
 * 
 * @author Buster-Darragh-Major
 */
public class TextQuestionListHandler implements ListHandler {

	/* Macros */
	public final static String TEMP_FILE_NAME = "temp.txt";
	public final static File LIST_DIRECTORY = new File(TataiPaths.DATA_DIR + System.getProperty("file.separator") + "lst");
	public final static File TEMP_FILE = new File(LIST_DIRECTORY + System.getProperty("file.separator") + TEMP_FILE_NAME);
	
	/* Fields */
	private final String _fileName;
	private final File _textFile;	
	
	/**
	 * Constructor
	 * @param listName : String
	 */
	public TextQuestionListHandler(String listName) {
		if (!LIST_DIRECTORY.exists()) {
			LIST_DIRECTORY.mkdirs();
		}
		_fileName = listName + ".txt";
		_textFile = new File(LIST_DIRECTORY + System.getProperty("file.separator") + _fileName);
	}
	
	
	/**
	 * Makes a blank text file list under the name of the file the handler holds reference to
	 */
	public void makeList() {
		if (!_textFile.exists()) {
			try {
				_textFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * Returns true if the file that the handler holds reverence to currently exists, and false otherwise.
	 * @return fileExists : boolean
	 */
	public boolean alreadyExists() {
		if (_textFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Writes the input string to the file. Every call of this method
	 * will write to the file on a new line.
	 */
	@Override
	public void writeToFile(String appendable) {
		try {
			// Create new writer with (...,boolean append) constructor, true indicating to append
			Writer output = new BufferedWriter(new FileWriter(_textFile, true));
			output.append(appendable + System.getProperty("line.separator"));
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void delete(String deletable) {
		try {
			// Create the temporary writing file
			TEMP_FILE.createNewFile();
		
			BufferedReader reader = new BufferedReader(new FileReader(_textFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE));
			
			// Loop through lines in current file, if line does not match string to delete
			// then copy over to temp file. If the first instance of the line has been found
			// then copy rest of file uninterrupted.
			String currentLine;
			boolean firstInstanceFound = false;
			while ((currentLine = reader.readLine()) != null) {
				if (!currentLine.equals(deletable) || firstInstanceFound) {
					writer.write(currentLine + System.getProperty("line.separator"));
				} else {
					firstInstanceFound = true;
				}
			}
			
			writer.close();
			reader.close();
			
			// Overwrite current file with text file
			TEMP_FILE.renameTo(_textFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Deletes the line number of the file, pushes all subsequent lines up to fill hole
	 */
	@Override
	public void delete(int lineNo) {
		try {
			// Create the temporary writing file
			TEMP_FILE.createNewFile();
		
			BufferedReader reader = new BufferedReader(new FileReader(_textFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE));
			
			// Loop through lines in current file, if line does not match string to delete
			// then copy over to temp file. If the first instance of the line has been found
			// then copy rest of file uninterrupted.
			String currentLine;
			int currentLineNo = 1;
			while ((currentLine = reader.readLine()) != null) {
				if (currentLineNo != lineNo) {
					writer.write(currentLine + System.getProperty("line.separator"));
				}
				
				currentLineNo++;
			}
			
			writer.close();
			reader.close();
			
			// Overwrite current file with text file
			TEMP_FILE.renameTo(_textFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Returns the size of the file list in number of lines
	 */
	@Override
	public int size() {
		int lineNo = 0;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(_textFile));
			
			while (reader.readLine() != null) {
				lineNo++;
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return lineNo;
	}

	
	/**
	 * Gets the line of the file by line number (line numbering starts at 1)
	 */
	@Override
	public String getLine(int lineNo) {
		String line  = null;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(_textFile));
			
			// Loop through to required line and set output string to equal content
			for (int i = 0; i < lineNo; i++) {
				line = reader.readLine();
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return line;
	}

	
	
	/**
	 * Clears all content of file
	 */
	public void clear() {
		try {
			// Create blank file and overwrite current file with temp file
			TEMP_FILE.createNewFile();
			TEMP_FILE.renameTo(_textFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Deletes the file related to the handler. Nullifies use of object, only invoke if object
	 * will no longer be used.
	 */
	public void delete() {
		try {
			_textFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
