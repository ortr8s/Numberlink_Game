package main.controller;

import main.gamelogic.*;
import main.utils.Generator;
import main.utils.Solver;

import java.util.HashMap;

public class Controller {

    /**
     * Represents a game board consisting of Unit objects.
     * A Unit represents a cell in the board.
     */
    public Board board;
    /**
     * Stores paths in a game board.
     */
    public final HashMap<Integer, Path> paths;
    /**
     * Represents the current path on the board.
     * <p>
     * This variable stores a Path object that represents current path the user is advancing.
     * The Path class provides methods for managing and manipulating the units in the path.
     * <p>
     * Example usage:
     * Path path = currentPath;
     * path.addUnit(unit);
     * <p>
     * Note: The Path class must be imported to use this variable.
     */
    public Path currentPath;

    public Controller(Board board) {
        this.board = board;
        this.paths = board.getPaths();
    }

    /**
     * Selects a unit at the given position on the board.
     *
     * @param x The x-coordinate of the unit on the board.
     * @param y The y-coordinate of the unit on the board.
     * @return true if the unit was successfully selected, false otherwise.
     */
    public boolean selectUnit(int x, int y) {
        Unit initialUnit = board.getUnitPosition(x, y);
        int unitValue = initialUnit.getValue();

        if (!isSelectedUnitValid(initialUnit)) return false;

        if (!paths.get(unitValue).equals(currentPath)) {
            // mustn't replace a path in HashMap if currentPath is null
            if (currentPath != null) {
                paths.replace(unitValue, currentPath);
            }
        }
        currentPath = paths.get(unitValue);
        currentPath.addUnit(initialUnit);
        return true;
    }

    /**
     * Checks if the selected Unit is a valid cell for a Unit to be selected.
     *
     * @param selectedUnit The unit that is selected.
     * @return True if the selected Unit is a valid cell for the unit, otherwise false.
     */
    private boolean isSelectedUnitValid(Unit selectedUnit) {
        Pair ends = paths.get(selectedUnit.getValue()).getStartEndPair();
        // unit has to be in a Pair field of the Path
        boolean isEnd = selectedUnit.equals(ends.getFirst())
                || selectedUnit.equals(ends.getLast());
        // unit has to be initially non 0


        return isEnd;
    }

    public HashMap<Integer, Path> solveAndExtractPaths(){
        Solver solver = new Solver(board);
        solver.solve();
        return solver.getPaths();
    }

    //TODO find a better suitable place for this method
    public void generateBoard(int size){
        Generator generator = new Generator();
        char[][] generated = generator.generate(size);
        int[][] converted = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int c = generated[i][j] - 48;
                converted[i][j] = (c < 0 || c > 21) ? 0 : c;
            }
        }
        this.board = new Board(size, converted);
    }
    /**
     * Determines if a given move is valid.
     *
     * @param move The move to be checked.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */
    public boolean isMoveValid(Moves move) {
        return board.hasCurves(currentPath, currentPath.getLastAdded().getNeighbour(move));
    }

    /**
     * Makes a move for the game.
     *
     * @param move The move to be made.
     * @return {@code true} if the move was last in the Game to be finished, {@code false} otherwise.
     */
    public boolean makeMove(Moves move) {
        if (isMoveValid(move)) {
            currentPath.advance(move).setValue(currentPath.getID());
        }
        return isGameFinished();
    }

    /**
     * Checks if the game is finished by evaluating the completion status of all paths on the board.
     *
     * @return {@code true} if the game is finished, {@code false} otherwise.
     */
    private boolean isGameFinished() {
        int pathsLength = 0;
        for (Path path : paths.values()) {
            pathsLength += path.getSize();
            if (!path.isCompleted()) {
                return false;
            }
        }

        return pathsLength == (board.getSize() * board.getSize());
    }

    /**
     * Retrieves the size of the board.
     *
     * @return The size of the board.
     */
    public int getBoardSize(){
        return board.getSize();
    }
}
