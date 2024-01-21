package main.gamelogic;

import main.utils.exceptions.InconsitentNumberOfNumbersException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; 


public class BoardTest {
    /**
     * Test case for the getNeighbor method of the Board class.
     * Simulates a simple 3x3 game board and tests whether neighbors can
     * be successfully and correctly retrieved in all four cardinal directions.
     */
    @Test
    public void testGetNeighbor() throws InconsitentNumberOfNumbersException {
        // Given
        int[][] numbers = {
                {0, 0, 0},
                {1, 0, 1},
                {0, 0, 0}
        };

        // Create 3x3 game board
        Board board = new Board(3, numbers);

        // When and Then
        // Retrieve the middle unit
        Unit unit = board.getBoard()[1][1];

        // Get and check neighbor above
        Unit neighbor = board.getNeighbor(unit, Moves.UP);
        Assertions.assertEquals(0, neighbor.getValue());
        Assertions.assertEquals(0, neighbor.getX());
        Assertions.assertEquals(1, neighbor.getY());

        // Get and check neighbor below
        neighbor = board.getNeighbor(unit, Moves.DOWN);
        Assertions.assertEquals(0, neighbor.getValue());
        Assertions.assertEquals(2, neighbor.getX());
        Assertions.assertEquals(1, neighbor.getY());

        // Get and check neighbor to the left
        neighbor = board.getNeighbor(unit, Moves.LEFT);
        Assertions.assertEquals(1, neighbor.getValue());
        Assertions.assertEquals(1, neighbor.getX());
        Assertions.assertEquals(0, neighbor.getY());

        // Get and check neighbor to the right
        neighbor = board.getNeighbor(unit, Moves.RIGHT);
        Assertions.assertEquals(1, neighbor.getValue());
        Assertions.assertEquals(1, neighbor.getX());
        Assertions.assertEquals(2, neighbor.getY());
    }
}