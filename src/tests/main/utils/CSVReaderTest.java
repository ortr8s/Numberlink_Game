package tests.main.utils;

import main.utils.CSVReader;
import main.utils.exceptions.InvalidBoardSizeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * This class contains tests for the CSVReader class read method.
 */
public class CSVReaderTest {

    /**
     * Test case for the read method.
     * Tests the scenario where the board size is valid and the CSV file is properly formatted and located at correct path.
     */
    @Test
    public void read_ValidSize_CorrectPath() throws IOException, InvalidBoardSizeException {
        CSVReader reader = new CSVReader(",");
        int[][] result = reader.read("board_7x7.csv");
        Assertions.assertNotNull(result, "Expected non-null result but was null");
        Assertions.assertEquals(7, result.length, "Expected board size of 7");
    }

    /**
     * Test case for the read method.
     * Tests the scenario where the board size is too large.
     */
    @Test
    public void read_InvalidSize_TooLarge() {
        CSVReader reader = new CSVReader(",");
        Assertions.assertThrows(InvalidBoardSizeException.class, () -> reader.read("board_10x10.csv"));
    }

    /**
     * Test case for the read method.
     * Tests the scenario where the board size is too small.
     */
    @Test
    public void read_InvalidSize_TooSmall() {
        CSVReader reader = new CSVReader(",");
        Assertions.assertThrows(InvalidBoardSizeException.class, () -> reader.read("board_4x4.csv"));
    }

    /**
     * Test case for the read method.
     * Tests the scenario where the file does not exist.
     */
    @Test
    public void read_NonexistentFile() {
        CSVReader reader = new CSVReader(",");
        Assertions.assertThrows(IOException.class, () -> reader.read("nonexistent.csv"));
    }

}