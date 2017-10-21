package main.java.questionlist;

public interface ListHandler {
	
	/**
	 * Appends the string to the current file represented by the ListHandler.
	 * @param appendable
	 */
	public void writeToFile(String appendable);
	
	/**
	 * Deletes the first occurrence of the String found in the text file
	 * @param deletable
	 */
	public void delete(String deletable);
	
	/**
	 * Deletes the line number of the file specified by the input parameter
	 * @param lineNo
	 */
	public void delete(int lineNo);
	
	/**
	 * Returns the length of the list in line numbers
	 * @return lineNo : int
	 */
	public int size();
	
	/**
	 * Returns the String content found on the line of the file specified by the input
	 * parameter lineNo. (Line numbers start at 1). If line number doesn't exist null is
	 * returned;
	 * @param lineNo
	 * @return lineContent : String
	 */
	public String getLine(int lineNo);
}
