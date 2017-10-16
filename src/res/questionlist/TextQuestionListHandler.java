package res.questionlist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import res.TataiHandler;

public class TextQuestionListHandler extends TataiHandler implements ListHandler {

	/* Macros */
	
	
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
			output.append(appendable);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String deletable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int lineNo) {
		// TODO Auto-generated method stub
		
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
