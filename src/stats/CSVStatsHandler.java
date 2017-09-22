package stats;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class gets information stored in the following format .... total
 * questions answered, total questions correct, total questions incorrect,
 * average It extracts the last line from a csv file, representing the most
 * up-to-date statistic. from a csv file and uses it to display user statistics
 * for the Tatai pronunciation tutoring tool.
 * 
 * @author Nathan Cairns
 *
 */
public class CSVStatsHandler implements StatisticHandler {
	/* MACROS */
	public static final String SEPARATOR = ",";
	public static final File FILEPATH = new File(
			System.getProperty("user.dir") + System.getProperty("file.separator") + "stats");
	public static final String FILENAME = "user_statistics.csv";
	public static final File STATS_FILE = new File(FILEPATH + System.getProperty("file.separator") + FILENAME);
	public static final int START_VALUE = 0;

	/* Fields */
	private Map<String, String> _valueMap;
	private final String[] _keys = { "totalPlayed", "totalCorrect", "totalIncorrect", "average" };

	/**
	 * Default Constructor
	 */
	public CSVStatsHandler() {
		_valueMap = new HashMap<String, String>();

		createStatsFolder();

		// Try to read stats file, if fails create a new one.
		try {
			readCSVFile();
		} catch (Exception e) {
			for (String key : _keys) {
				_valueMap.put(key, "" + START_VALUE);
			}
			writeToFile();
		}
	}

	/**
	 * Writes all the fields to a new line in a csv file.
	 */
	private void writeToFile() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		createStatsFile();
		
		try {
			fw = new FileWriter(STATS_FILE.getAbsolutePath(), true);
			bw = new BufferedWriter(fw);

			boolean first = true;
			StringBuilder sb = new StringBuilder();

			for (String key : _keys) {
				String value = _valueMap.get(key);
				if (!first) {
					sb.append(SEPARATOR);
				}
				sb.append(value);

				first = false;
			}

			sb.append("\n");
			bw.append(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks if stats file exists and creates it if it doesn't.
	 */
	private void createStatsFile() {
		if (!STATS_FILE.exists()) {
			try {
				STATS_FILE.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	 * 
	 * @throws Exception
	 */
	private void readCSVFile() throws Exception {
		BufferedReader br = null;
		String line = "";
		String[] stats = null;

		br = new BufferedReader(new FileReader(STATS_FILE));

		while ((line = br.readLine()) != null) {

			stats = line.split(SEPARATOR);
		}
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (stats != null) {
			loadData(stats);
		} else {
			throw new StatsException("No data found in CSV file!");
		}
	}

	/**
	 * Takes an array of strings representing the statistics and assigns them to the
	 * corresponding values in the map.
	 * 
	 * @param stats
	 */
	private void loadData(String[] stats) {
		if (stats.length != 4) {
			throw new StatsException("Invalid data format in " + FILENAME);
		}

		for (int i = 0; i < stats.length - 1; i++) {
			String value = stats[i];
			if (value.matches("^-?\\d+$")) {
				_valueMap.put(_keys[i], value);
			} else {
				throw new StatsException("Invalid data format in " + FILENAME);
			}
		}

		String value = stats[3];
		if (value.matches("^\\d+(\\.\\d{1,2})?$")) {
			_valueMap.put(_keys[3], value);
		} else {
			throw new StatsException("Invalid data format in " + FILENAME);
		}

	}

	@Override
	public int totalPlayed() {
		return Integer.parseInt(_valueMap.get(_keys[0]));
	}

	@Override
	public int totalCorrect() {
		return Integer.parseInt(_valueMap.get(_keys[1]));
	}

	@Override
	public int totalIncorrect() {
		return Integer.parseInt(_valueMap.get(_keys[2]));
	}

	@Override
	public double average() {
		return Double.parseDouble(_valueMap.get(_keys[3]));
	}

	@Override
	public void updateStats(int played, int correct, int incorrect) {
		if (played > 10) {
			throw new StatsException("You can not have answered more than 10 questions in a session");
		}

		if (played < 0 || correct < 0 || incorrect < 0) {
			throw new StatsException("Scores cannot be negative!");
		}
		if ((correct + incorrect) > played) {
			throw new StatsException("The number of question you got correct and the number of questions you "
					+ "got wrong must be consistent with the number of questions you answered");
		}
		_valueMap.put(_keys[0], "" + (played + totalPlayed()));
		_valueMap.put(_keys[1], "" + (correct + totalCorrect()));
		_valueMap.put(_keys[2], "" + (incorrect + totalIncorrect()));
		_valueMap.put(_keys[3], "" + calculateAverage());

		writeToFile();
	}

	/**
	 * Calculate the average score from games the played and question correct.
	 * 
	 * @return the average
	 */
	private double calculateAverage() {
		
		if (totalPlayed() == 0) {
			return 0;
		}
		
		double average = (double) totalCorrect() / totalPlayed() * 10;
		
		average = Double.parseDouble(String.format("%.2f", average));
		
		return average;
	}

}
