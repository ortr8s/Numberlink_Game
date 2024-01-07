package main.utils;

import main.gamelogic.Board;
import main.gamelogic.Pair;
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
	private final List<Pair> pairs; // List of pairs (numbered cells) to be connected
	private final int[][] checkedCells; // Grid to keep track of cells already visited in the current path
	private int[][] solution;
	private int currentPairIndex; // Index for the current pair being processed
	private final int size; // Dimension of the board (assumed square)
	private boolean isSolvable; // Flag indicating whether the puzzle has been solved
	private boolean stop; // Flag to terminate the search prematurely

	/**
	 * Constructor to initialize the Solver with a specific board.
	 *
	 * @param board The Board object representing the Numberlink puzzle.
	 */
	public Solver(Board board){
		this.board = board.getBoard();
		this.size = board.getSize();
		this.pairs = board.extractPairs();
		this.checkedCells = new int[size][size];
		this.solution = new int[size][size];
		this.stop = false;
		this.isSolvable = false;
		this.currentPairIndex = 0;
	}

	/**
	 * Gets the array representing solved board.
	 *
	 * @return 2D array of solved board.
	 */
	public int[][] getSolution() {
		return solution;
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
	private ArrayList<Unit> getNeighbors(int x, int y){
		ArrayList<Unit> neighbours = new ArrayList<>();
		for (int[] dir : MOVE_DIRECTIONS) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			if (isValidMove(newX, newY)) {
				neighbours.add(board[newX][newY]);
			}
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
	private boolean isValidMove(int x, int y){
		return x >= 0 && y >= 0 && x < size && y < size;
	}

	/**
	 * Initializes the solving algorithm.
	 *
	 * @return True if the puzzle is solvable, false otherwise.
	 */
	public boolean solve(){
		System.out.println("Solving!");
		sortPairsByDistance();	//distance heuristic which can not always give the best result
		Unit initialUnit = pairs.get(currentPairIndex).getFirst();
		DFS(initialUnit.getX(), initialUnit.getY(), initialUnit.getValue());
		return isSolvable;
	}

	/**
	 * Performs a depth-first search (DFS) to find a solution.
	 *
	 * @param x   X-coordinate to start the search from.
	 * @param y   Y-coordinate to start the search from.
	 * @param val Value of a current Unit.
	 */
	private void DFS(int x, int y, int val){
		if (stop) return;

		checkedCells[x][y] = val;
		ArrayList<Unit> neighbours = getNeighbors(x,y);
		for (Unit currentNeighbour: neighbours) {
			int nextX = currentNeighbour.getX();
			int nextY = currentNeighbour.getY();
			int nextVal = currentNeighbour.getValue();
			if (checkedCells[nextX][nextY] == 0) {
				if (nextVal == val) {
					currentPairIndex++;
					checkedCells[nextX][nextY] = val;
					if (currentPairIndex == pairs.size()) {
						print();
						stop = true;
						isSolvable = true;
						return;
					}
					Unit newFirstUnit = pairs.get(currentPairIndex).getFirst();
					int newX = newFirstUnit.getX();
					int newY = newFirstUnit.getY();
					int newVal = newFirstUnit.getValue();
					if (checkedCells[newX][newY] == 0) {
						checkedCells[newX][newY] = newVal;
						DFS(newX, newY, newVal);
					}
					currentPairIndex--;
					checkedCells[nextX][nextY] = 0;
				} else if (nextVal == 0) {
					DFS(nextX, nextY, val);
				}
			}
		}
		this.checkedCells[x][y] = 0;
	}

	/**
	 * Prints the solved board.
	 */
	private void print(){
		copyToSolution();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++){
				System.out.print(checkedCells[i][j] + " ");
			}
			System.out.println();
		}
	}
	private void copyToSolution() {
		for (int i = 0; i < size; i++) {
			System.arraycopy(checkedCells[i], 0, this.solution[i], 0, size);
		}
	}

}
