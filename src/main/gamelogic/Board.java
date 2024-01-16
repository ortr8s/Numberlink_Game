package main.gamelogic;

import main.utils.OddNumberOfNumbersException;
import main.utils.Solver;

import java.util.*;

public class Board {
    /**
     * Private final variable that represents the size of a board.
     * <p>
     * The size variable determines the dimensions of the board, which is a square grid.
     * It is used in various methods of the containing class {@link Board} to perform operations on the board.
     */
    private final int size;
    /**
     * Represents a game board consisting of Unit objects.
     * A Unit represents a cell in the board.
     */
    Unit[][] board;
    /**
     * Represents a list of pairs in the Numberlink game.
     * A pair is a connection between two units.
     * The pairs are stored in an ArrayList, which allows for easy access and manipulation.
     */
    ArrayList<Unit> pairs;
    private HashMap<Integer, Path> paths;

    public Board(int size, int[][] numbers) throws OddNumberOfNumbersException{
        this.size = size;
        this.board = new Unit[size][size];
        pairs = new ArrayList<>();
        convertToUnitBoard(numbers);
        if(pairs.size() % 2 == 1 || pairs.size() == 0){
            throw new OddNumberOfNumbersException("Odd number of numbers");
        }
        initializePaths();
    }

    public HashMap<Integer, Path> getPaths() {
        return paths;
    }

    /**
     * Converts a 2D array of numbers to a unit board.
     * <p>
     * This method takes a 2D array of numbers and converts them into Unit objects,
     * which are then assigned to the board. If the number is non-zero, the corresponding
     * Unit object is added to the pairs list. The size of the board is determined by
     * the size field of the containing class Board.
     *
     * @param numbers the 2D array of numbers to be converted
     */
    private void convertToUnitBoard(int[][] numbers){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Unit current = new Unit(i, j, numbers[i][j], this);
                this.board[i][j] = current;
                if (numbers[i][j] != 0) {
                    this.pairs.add(current);
                }
            }
        }
//        if (pairs.size() % 2 != 0){
//            throw new OddNumberOfNumbersException("Uneven number of numbers");
//        }
    }

    /**
     * Initializes the paths on the board.
     * <p>
     * This method sorts the pairs of units in ascending order based on their values.
     * Then, it creates paths by pairing adjacent units, starting from the first unit and ending with the last unit.
     *
     * @throws NullPointerException if the pairs list is null
     */
    private void initializePaths() {
        pairs.sort(Comparator.comparingInt(Unit::getValue));
        this.paths = new HashMap<>((pairs.size() / 2));
        for (byte i = 1; i <= pairs.size() - 1; i += 2) {
            Unit first = pairs.get(i - 1);
            Unit last = pairs.get(i);
            paths.put(first.getValue() ,new Path(new Pair(first, last)));
        }
    }

    /**
     * Does an exhaustive search in the cells' matrix to find the coordinates of the original cell, then return the
     * neighbor cell according to the direction.
     *
     * @param unit the cell we want to get neighbor from.
     * @param move the direction of the neighbor with respect to the cell
     * @return the neighboring cell in the specified direction
     */
    public Unit getNeighbor(Unit unit, Moves move) {

        int[] cellCoordinates = new int[]{unit.getX(), unit.getY()};

        int neighborRow = cellCoordinates[0]; // Initialize neighbor coordinates to cell coordinates
        int neighborCol = cellCoordinates[1];

        // Calculate neighbor coordinates based on direction
        switch (move) {
            case UP -> neighborRow -= 1;
            case DOWN -> neighborRow += 1;
            case LEFT -> neighborCol -= 1;
            case RIGHT -> neighborCol += 1;
        }

        // Return the cell itself the neighbor coordinates are invalid
        if (neighborRow < 0 || neighborCol < 0 || neighborRow >= size || neighborCol >= size) {
            return unit;
        }

        return board[neighborRow][neighborCol];
    }
    /**
     * Extracts pairs of Units from the Board.
     * Each pair contains the beginning and the end of the path.
     *
     * @return An ArrayList of Pair objects representing the extracted pairs.
     */
    public ArrayList<Pair> extractPairs() {
        ArrayList<Pair> finalPairs = new ArrayList<>();
        //sorts Units with numbers other than 0
        pairs.sort(Comparator.comparingInt(Unit::getValue));
        for (int i = 0; i < pairs.size() - 1; i += 2) {
            Unit one = pairs.get(i);
            Unit two = pairs.get(i + 1);
            finalPairs.add(new Pair(one, two));
        }
        return finalPairs;
    }

    /**
     * Retrieves the game board.
     *
     * @return The 2D array of Unit objects representing the current game board.
     */
    public Unit[][] getBoard() {
        return board;
    }

    /**
     * Retrieves the size of the board.
     *
     * @return The size of the board.
     */
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Board.class.getSimpleName() + "[", "]")
                .add("board=" + Arrays.deepToString(board))
                .toString();
    }

    /**
     * Determines if a given path has curves.
     * Checks if there are no 4x4 areas of the Units with the same value.
     *
     * @param path      The path to check for curves.
     * @param neighbour The neighbouring unit.
     * @return {@code true} if the path has curves, {@code false} otherwise.
     */
    public boolean hasCurves(Path path, Unit neighbour) {
        return !Solver.isMoveCurved(path, neighbour);
    }
    public Unit getUnitPosition(int x, int y) {
        return board[x][y];
    }
}
