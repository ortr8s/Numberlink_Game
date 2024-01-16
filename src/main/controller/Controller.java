package main.controller;

import main.gamelogic.*;
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
     */
    public void selectUnit(int x, int y) {
        Unit initialUnit = board.getUnitPosition(x, y);
        int unitValue = initialUnit.getValue();

        if (!isSelectedUnitValid(initialUnit)) return;

        // If currentPath is not null (implies we've finished working with this path), put it back into the paths HashMap
        if (currentPath != null && currentPath != paths.get(unitValue)) {
            paths.put(currentPath.getID(), currentPath);
        }

        // Get the current path from the HashMap
        currentPath = paths.get(unitValue);
        currentPath.addUnit(initialUnit);

        System.out.println("IsPart: "+currentPath.getUnits()[0].isPartOfPath());
        System.out.println(paths.keySet());
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
        boolean hasValue = (selectedUnit.getValue()!=0);

        return isEnd && hasValue;
    }

    public HashMap<Integer, Path> solveAndExtractPaths(){
        Solver solver = new Solver(board);
        solver.solve();
        return solver.getPaths();
    }

    //TODO find a better suitable place for this method

    /**
     * Checks if a given move is valid.
     *
     * @param move The move to be checked.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */
    private boolean isMoveValid(Moves move) {
        if (currentPath == null || currentPath.getCompleted()) return false;

        Unit neighbour = currentPath.getLastAdded().getNeighbour(move);
        boolean hasCurves = board.hasCurves(currentPath, neighbour);
        boolean isNeighbourInPathEdge = isNeighbourInPathEdge(neighbour);

        if(!currentPath.getUnit(0).equals(neighbour) && hasCurves && isNeighbourInPathEdge) {
            currentPath.setCompleted(true);
            return true;
        }

        boolean isNeighbourValueZero = (neighbour.getValue() == 0);
        boolean isNeighbourNotInPath = !neighbour.isPartOfPath();

        return hasCurves && isNeighbourValueZero && isNeighbourNotInPath;
    }

    /**
     * Checks if the given unit is a neighbor of the first or last unit in the current path.
     *
     * @param neighbour The unit to check.
     * @return {@code true} if the unit is a neighbor of the first or last unit in the path, {@code false} otherwise.
     */
    private boolean isNeighbourInPathEdge(Unit neighbour) {
        return getFirst().equals(neighbour) || getLast().equals(neighbour);
    }

    /**
     * Makes a move for the game.
     *
     * @param move The move to be made.
     * @return {@code true} if the move was last in the Game to be finished, {@code false} otherwise.
     */
    public boolean makeMove(Moves move) {
        if (isMoveValid(move)) {
            currentPath.advance(move);
            currentPath.getLastAdded().setPartOfPath(true);
        }
        if (isGameFinished()) {
            System.out.println("COMPLETED");
            System.out.println();
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
    /**
     * Retrieves the first unit in the current path's start-end pair.
     *
     * @return The first unit in the current path.
     */
    public Unit getFirst() {
        return currentPath.getStartEndPair().getFirst();
    }
    /**
     * Retrieves the last unit in the current path.
     *
     * @return The last unit.
     */
    public Unit getLast() {
        return currentPath.getStartEndPair().getLast();
    }
    /**
     * Clears the current path by setting all units in the path to not be part of the path,
     * deleting the units from memory and resetting the completion status of the path.
     */
    public void clearPath() {
        currentPath.clearPath();
    }
}
