package main.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class CSVReaderTest {

    @Test
    public void testRead_validBoardSize_returnsArray() {
        // Arrange
        CSVReader csvReader = new CSVReader(",");
        String originalContent = "1,0,2,0,5\n0,0,3,0,4\n0,0,0,0,0\n0,2,0,5,0\n0,1,3,4,0\n";
        try {
            try (PrintWriter out = new PrintWriter("resources/boards/board_5x5.csv")) {
                out.println(originalContent);
            }
            // Act
            int[][] result = csvReader.read(5);

            // Assert
            int[][] expected = {{1, 0, 2, 0, 5}, {0, 0, 3, 0, 4}, {0, 0, 0, 0, 0}, {0, 2, 0, 5, 0}, {0, 1, 3, 4, 0}};
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