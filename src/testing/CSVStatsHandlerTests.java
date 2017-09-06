package testing;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import stats.CSVStatsHandler;

public class CSVStatsHandlerTests {

	private CSVStatsHandler _statsHandler;

	@Before
	public void setup() {
		_statsHandler = new CSVStatsHandler();
	}

	@Test
	public void testFileReading() {
		_statsHandler.readCSVFile();

		assertEquals(10, _statsHandler.totalPlayed());

		assertEquals(2, _statsHandler.totalCorrect());

		assertEquals(8, _statsHandler.totalIncorrect());

		assertEquals(2, _statsHandler.average());
	}
}
