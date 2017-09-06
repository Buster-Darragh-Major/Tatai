package stats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class gets information stored in the following format ....
 * total questions answered, total questions correct, total questions incorrect, average
 * It extracts the last line from a csv file, representing the most up-to-date statistic.
 * from a csv file and uses it to display user statistics for the
 * Tatai pronunciation tutoring tool.
 * 
 * @author Nathan Cairns
 *
 */
public class CSVStatsHandler implements StatisticHandler {
	/* MACROS */
	public static final String SEPARATOR = ",";
	public static final File FILEPATH = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "stats");
	public static final String FILENAME = "user_statistics.csv";
	public static final File STATS_FILE = new File(FILEPATH + System.getProperty("file.separator") + FILENAME);
	public static final int START_VALUE = 0;
	
	/* Fields */
	private Map<String, Integer> _valueMap;
	
	private final String[] _keys = {"totalPlayed", "totalCorrect", "totalIncorrect", "average"};
	
	/**
	 * Default Constructor
	 */
	public CSVStatsHandler() {
		_valueMap = new HashMap<String, Integer>();
		
		createStatsFolder();
		
		if (STATS_FILE.exists()) {
			readCSVFile();
		} else {
			for (String key: _keys) {
				_valueMap.put(key, START_VALUE);
			}
			
			writeToFile();
		}
	}
	
	/**
	 * Writes all the fields to a new line in a csv file.
	 */
	private void writeToFile() {
		// TODO implement
	}
	
	/**
	 * Makes the appropriate directory if it doesn't already exist.
	 */
	private void createStatsFolder() {
		if (!FILEPATH.exists()) {
			FILEPATH.mkdir();
		}
	}
	
	/**
	 * Reads the stats from the csv file.
	 */
	public void readCSVFile() {
		BufferedReader br = null;
		String line = "";
		String[] stats = null;
		
		try {
			br = new BufferedReader(new FileReader(STATS_FILE));
			
			while((line = br.readLine()) != null) {
				
				stats = line.split(SEPARATOR);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();	
		 } catch (IOException e) {
	        e.printStackTrace();
		 } finally {
			 if (br != null) {
				 try {
					 br.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 } 
		
		if (stats != null) {
			loadData(stats);
		} else {
			throw new StatsException("No data found in CSV file!");
		}
	}
	
	/**
	 * Takes an array of strings representing the statistics 
	 * and assigns them to the corresponding values in the map.
	 * 
	 * @param stats
	 */
	private void loadData(String[] stats) {
		if (stats.length != 4) {
			throw new StatsException("Invalid data format in " + FILENAME);
		}
		
		for (int i = 0; i < stats.length; i++) {
			String value = stats[i];
			if (value.matches("^-?\\d+$")) {
				Integer valueAsInteger = Integer.parseInt(value);
				_valueMap.put(_keys[i], valueAsInteger);
			} else {
				throw new StatsException ("Invalid data format in " + FILENAME);
			}
		}
		
	}

	@Override
	public int totalPlayed() {
		return _valueMap.get(_keys[0]);
	}	
	
	@Override
	public int totalCorrect() {
		return _valueMap.get(_keys[1]);
	}
	
	@Override
	public int totalIncorrect() {
		return _valueMap.get(_keys[2]);
	}

	@Override
	public int average() {
		return _valueMap.get(_keys[3]);
	}

	@Override
	public void updateStats(int played, int incorrect, int correct) {
		// TODO Auto-generated method stub
		
	}

}
