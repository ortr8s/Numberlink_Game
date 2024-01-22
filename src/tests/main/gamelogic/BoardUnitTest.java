package main.gamelogic;

import main.utils.exceptions.InconsistentNumberOfNumbersException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardUnitTest {

    /*
     * This class contains unit tests for the extractPairs method in the Board class,
     * which is designed to return a list of pair objects where each pair represents two units
     * in the Numberlink game with identical non-zero values on the game board
     */

    @Test
    public void extractPairs_noNumbers_shouldThrowException() {
        // Test to verify if an exception is thrown when no numbers are provided
        int boardSize = 3;
        int[][] numbers = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

        assertThrows(InconsistentNumberOfNumbersException.class, () -> new Board(boardSize, numbers));
    }

    @Test
    public void extractPairs_multiplePairs_validPairsReturned() throws InconsistentNumberOfNumbersException {
        // Test to verify correct pair extraction where there are multiple distinct pairs
        int boardSize = 4;
        int[][] numbers = new int[][]{{1, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 2}};

        Board board = new Board(boardSize, numbers);
        ArrayList<Pair> pairs = board.extractPairs();

        assertEquals(2, pairs.size(), "Expected pair count does not match actual");
        assertEquals(1, pairs.get(0).getFirst().getValue(), "Expected pair value does not match actual");
        assertEquals(1, pairs.get(0).getLast().getValue(), "Expected pair value does not match actual");
        assertEquals(2, pairs.get(1).getFirst().getValue(), "Expected pair value does not match actual");
        assertEquals(2, pairs.get(1).getLast().getValue(), "Expected pair value does not match actual");
    }
}