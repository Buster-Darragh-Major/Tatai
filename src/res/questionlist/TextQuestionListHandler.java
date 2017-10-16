package res.questionlist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import res.TataiHandler;

public class TextQuestionListHandler extends TataiHandler implements ListHandler {

	/* Macros */
	public final static String TEMP_FILE_NAME = "temp.txt";
	public final static File TEMP_FILE = new File(FILEPATH + System.getProperty("file.separator") + TEMP_FILE_NAME);
	
	/* Fields */
	private final String _fileName;
	private final File _textFile;
	private List<String> _listContent;
	
	
	public TextQuestionListHandler(String listName) {
		_fileName = listName + ".txt";
		_textFile = new File(FILEPATH + System.getProperty("file.separator") + _fileName);
		fileSetup();
	}
	
	
	
	private void fileSetup() {
		_listContent = new ArrayList<String>();
		
		if (!_textFile.exists()) {
			try {
				_textFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	@Override
	public String getLine(int lineNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
