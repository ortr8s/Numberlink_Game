package main.utils;

import main.gamelogic.Board;
import main.gamelogic.Pair;
import main.gamelogic.Path;
import main.gamelogic.Unit;

import java.util.*;

/**
 * Solver class to solve a Numberlink puzzle with a given board.
 * This class utilizes depth-first search (DFS) algorithm with backtracking to find solutions.
 */
public class Solver {
	private static final int[][] MOVE_DIRECTIONS =
			{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Movement directions: up, down, right, left
	private final Unit[][] board; // Grid representing the board
	private final ArrayList < Pair > pairs; // List of pairs (numbered cells) to be connected
	private final int[][] checkedCells; // Grid to keep track of cells already visited in the current path
	private int currentPairIndex; // Index for the current pair being processed
	private final int size; // Dimension of the board (assumed square)
	private boolean isSolvable; // Flag indicating whether the puzzle has been solved
	private boolean stop; // Flag to terminate the search prematurely
	private double startTime; //Determines the time when solver was started
	private final HashMap <Integer, Path> paths; //Stores paths and maps them to their value

	/**
	 * Constructor to initialize the Solver with a specific board.
	 *
	 * @param board The Board object representing the Numberlink puzzle.
	 */
	public Solver(Board board) {
		this.board = board.getBoard();
		this.size = board.getSize();
		this.pairs = board.extractPairs();
		this.checkedCells = new int[size][size];
		this.stop = false;
		this.isSolvable = false;
		this.currentPairIndex = 0;
		this.paths = new HashMap <> (20);
	}

	/**
	 * Gets the array representing solved board.
	 *
	 * @return 2D array of solved board.
	 */
	public int[][] getSolution() {
		return checkedCells;
	}

	/**
	 * Sorts pairs ascending by their manhattan distance.
	 * Heuristic used to start connecting pairs that are closet to each other.
	 */
	private void sortPairsByDistance() {
		pairs.sort(Comparator.comparingInt(Pair::getDistance));
	}

	/**
	 * Retrieves neighboring units for the given coordinates.
	 *
	 * @param x X-coordinate on the board.
	 * @param y Y-coordinate on the board.
	 * @return List of neighboring units.
	 */
	private Unit[] getNeighbors(int x, int y) {
		Unit[] neighbours = new Unit[4];
		int i = 0;
		for (int[] dir: MOVE_DIRECTIONS) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			if (isValidMove(newX, newY)) {
				neighbours[i] = (board[newX][newY]);
			}
			i++;
		}
		return neighbours;
	}

	/**
	 * Checks if the given move is valid within the board.
	 *
	 * @param x X-coordinate of the move.
	 * @param y Y-coordinate of the move.
	 * @return True if the move is valid, false otherwise.
	 */
	private boolean isValidMove(int x, int y) {
		return x >= 0 && y >= 0 && x < size && y < size;
	}

	/**
	 * Determines if a move results in a curve in the path.
	 * A move is considered 'curved' if the new unit's position forms a complete 2x2 grid with only one number.
	 *
	 * @param path The current path being traced.
	 * @param neighbour The neighbouring unit to be considered for the next move.
	 * @return True if the move creates a curve in the path, false otherwise. False also when the size of path is less than 3.
	 */
	public static boolean isMoveCurved(Path path, Unit neighbour) {
		Unit thirdLastUnit = path.getThirdLast();
		if (thirdLastUnit == null) {
			return false;
		}
		return new Pair(path.getThirdLast(), neighbour).calculateDistance() == 1;
	}

	/**
	 * Initializes the solving algorithm.
	 *
	 * @return True if the puzzle is solvable, false otherwise.
	 */
	public boolean solve() {
		this.startTime = System.nanoTime();
		System.out.println("Solving!");
		sortPairsByDistance(); //distance heuristic which can not always give the best result
		Unit initialUnit = pairs.get(currentPairIndex).getFirst();
		Path currentPath = new Path();
		currentPath.addUnit(initialUnit);
		paths.put(initialUnit.getValue(), currentPath);
		DFS(initialUnit.getX(), initialUnit.getY(), initialUnit.getValue());
		return isSolvable;
	}

	/**
	 * Depth-first search algorithm to explore possible paths for connecting pairs.
	 * It recursively explores neighboring cells with a few constraints, backtracking when a dead end is reached.
	 * The method updates the state of the search, including the checkedCells grid and paths map.
	 *
	 * @param x   X-coordinate to start the search from.
	 * @param y   Y-coordinate to start the search from.
	 * @param val Value of the current Unit, representing a number on the board.
	 */
	private void DFS(int x, int y, int val) {
		if (stop) return; //if the solution was already found don't recurse further

		checkedCells[x][y] = val;
		Unit[] neighbours = getNeighbors(x, y); //store neighbouring units

		for (Unit currentNeighbour: neighbours) {
			if (currentNeighbour != null) {

				int neighbourX = currentNeighbour.getX();
				int neighbourY = currentNeighbour.getY();
				int neighbourValue = currentNeighbour.getValue();
				if (checkedCells[neighbourX][neighbourY] == 0
						&& !isMoveCurved(paths.get(val), currentNeighbour)) {
					//check to make sure if paths don't overlap and there are no curves

					if (currentNeighbour.equals(pairs.get(currentPairIndex).getLast())) {
						currentPairIndex++; //increment to extract the next pair
						checkedCells[neighbourX][neighbourY] = val;
						if (currentPairIndex == pairs.size()) { //if all numbers were checked print out the solution
							if (validateSolution()){
								//print();
								System.out.println("Time: " + (System.nanoTime() - startTime) / 777600000);
								stop = true;
								isSolvable = true;
								return;
							}
						}

						Unit newFirstUnit = pairs.get(currentPairIndex).getFirst(); //get the unit from next pair
						int newX = newFirstUnit.getX();
						int newY = newFirstUnit.getY();
						int newVal = newFirstUnit.getValue();

						paths.put(newVal, new Path(pairs.get(currentPairIndex))); //place new path in the hashmap
						paths.get(newVal).addUnit(newFirstUnit); //add first unit to the path

						checkedCells[newX][newY] = newVal;
						DFS(newX, newY, newVal);
						if (stop) return;

						currentPairIndex--; //backtrack and go back to the recent pair
						checkedCells[neighbourX][neighbourY] = 0;

					} else if (neighbourValue == 0) { //recurse if the neighbouring unit is 0
						paths.get(val).addUnit(currentNeighbour); //add unit to the path
						DFS(neighbourX, neighbourY, val);
						if (stop) return;
						paths.get(val).removeLast(); //when backtracking remove the recently added units from the current path
					}
				}
			}
		}
		checkedCells[x][y] = 0;
	}

	private boolean validateSolution(){
		int counter = 0;
		for (Map.Entry<Integer, Path> entry : paths.entrySet()){
			counter++;
			counter += entry.getValue().getSize();
		}
		return counter == size*size;
	}

	/**
	 * Prints the solved board to the standard output.
	 * Each cell of the board is printed with its value, representing the path of the solution.
	 * This method is typically called after the puzzle is successfully solved.
	 * Saves the answer to solution array
	 */
	private void print() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(checkedCells[i][j] + " ");
			}
			System.out.println();
		}
	}

	public HashMap<Integer, Path> getPaths() {
		return paths;
	}
}