package main.utils;

import main.utils.CSVReader;
import main.utils.InvalidBoardSizeException;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

    @Test
    public void testRead_validBoardSize_returnsArray() {
        // Arrange
        CSVReader csvReader = new CSVReader(",");
        String originalContent = "5,5,5,5,5\n5,5,5,5,5\n5,5,5,5,5\n5,5,5,5,5\n5,5,5,5,5\n";
        try {
            try (PrintWriter out = new PrintWriter("resources/boards/board_5x5.csv")) {
                out.println(originalContent);
            }
            // Act
            int[][] result = csvReader.read(5);

            // Assert
            int[][] expected = {{5}, {5, 5, 5, 5, 5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
            assertArrayEquals(expected, result);

        } catch (IOException | InvalidBoardSizeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testRead_invalidBoardSize_throwsException() {
        // Arrange
        CSVReader csvReader = new CSVReader(",");
        // Act & Assert
        assertThrows(InvalidBoardSizeException.class, () -> csvReader.read(0));
    }
}