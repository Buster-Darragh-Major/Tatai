package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import stats.CSVStatsHandler;
import stats.StatsException;

public class CSVStatsHandlerTests {

	private CSVStatsHandler _statsHandler;

	@Before
	public void setup() {
		if (CSVStatsHandler.STATS_FILE.exists()) {
			deleteFile(CSVStatsHandler.FILENAME);
		}
		_statsHandler = new CSVStatsHandler();
	}
	
	@Test
	public void testNegativeNumbers() {
		try {
			_statsHandler.updateStats(-10, -4, -6);
			fail();
		} catch (StatsException e) {
			
		}
	}
	
	@Test
	public void testStatsExceptionOnGamesPlayed() {
		try {
			_statsHandler.updateStats(11, 4, 6);
			fail();
		} catch (StatsException e) {
			
		}
	}
	
	@Test
	public void testMultipleUpdates() {
		_statsHandler.updateStats(10, 4, 6);
		_statsHandler.updateStats(10, 9, 1);
		
		assertEquals(20, _statsHandler.totalPlayed());

		assertEquals(13, _statsHandler.totalCorrect());

		assertEquals(7, _statsHandler.totalIncorrect());

		assertEquals(6, _statsHandler.average());
	}
	
	@Test
	public void testSingleUpdate() {
		_statsHandler.updateStats(10, 4, 6);
		
		assertEquals(10, _statsHandler.totalPlayed());

		assertEquals(4, _statsHandler.totalCorrect());

		assertEquals(6, _statsHandler.totalIncorrect());

		assertEquals(4, _statsHandler.average());
	}

	@Test
	public void testDefaultFileReading() {

		assertEquals(0, _statsHandler.totalPlayed());

		assertEquals(0, _statsHandler.totalCorrect());

		assertEquals(0, _statsHandler.totalIncorrect());

		assertEquals(0, _statsHandler.average());
	}
		
	private void deleteFile(String filename) {
		Path path = Paths.get(CSVStatsHandler.FILEPATH + System.getProperty("file.separator") + filename);
		try {
			Files.delete(path);
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", path);
		} catch (IOException x) {
			// File permission problems are caught here.
			System.err.println(x);
		}
}
}
