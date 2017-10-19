package res.questionlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import res.TataiHandler;

public class TextQuestionListHandler extends TataiHandler implements ListHandler {

	/* Macros */
	public final static String TEMP_FILE_NAME = "temp.txt";
	public final static File LIST_DIRECTORY = new File(RES_DIRECTORY + System.getProperty("file.separator") + "lst");
	public final static File TEMP_FILE = new File(LIST_DIRECTORY + System.getProperty("file.separator") + TEMP_FILE_NAME);
	
	/* Fields */
	private final String _fileName;
	private final File _textFile;	
	
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
