package main.utils;

import main.gamelogic.Board;
import main.gamelogic.Path;
import main.gamelogic.Unit;

import java.util.*;

public class Solver {
	private Unit[][] board;
	private Path path;
	private int size;
	public Solver(Board board){
		this.board = board.getBoard();
		this.size = board.getSize();
	}
	//test manhattan distance
	private double calculateHeuristics(Unit current, Unit finish){
		double fScore = Math.abs(current.getX() - finish.getX()) + Math.abs(current.getY() - finish.getY());
		current.setF(fScore);
		return fScore;
	}
	private List<Unit> getNeighbors(Unit current){
		List<Unit> neighbors = new ArrayList<>();
		int x = current.getX();
		int y = current.getY();
		Unit parent = current.getParent();
		//up
		if(isValidMove(x+1,y)){
			neighbors.add(board[x+1][y]);
		}
		//down
		if(isValidMove(x-1,y)){
			neighbors.add(board[x-1][y]);
		}
		//right
		if (isValidMove(x,y+1)){
			neighbors.add(board[x][y+1]);
		}
		//left
		if (isValidMove(x,y-1)){
			neighbors.add(board[x][y-1]);
		}

		return neighbors;

	}

	private boolean isValidMove(int x, int y){
		if (x < 0 || y < 0 || x >= size || y >= size) {
			return false;
		}

		// Check if the cell is already part of the path
		if (board[x][y].getValue() != 0) {
			return false;
		}

		// Check for 2x2 square constraint
		// Only check the 2x2 square where the current move is the top-left corner
		if ((x + 1 < size && y + 1 < size &&
				board[x][y].getValue() != 0 &&
				board[x][y+1].getValue() != 0 &&
				board[x+1][y].getValue() != 0 &&
				board[x+1][y+1].getValue() != 0) ||
				// Check the 2x2 square where the current move is the bottom-right corner
				(x - 1 >= 0 && y - 1 >= 0 &&
						board[x][y].getValue() != 0 &&
						board[x][y-1].getValue() != 0 &&
						board[x-1][y].getValue() != 0 &&
						board[x-1][y-1].getValue() != 0)) {
			return false;
		}

		return true;
	}

	public boolean solve(){
		//priority queue based on the heuristic value f
		PriorityQueue<Unit> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Unit::getF));
		HashSet<Unit> closedSet = new HashSet<>();

		Unit startUnit = new Unit(0,0,1);
		Unit goalUnit = new Unit(2,4,1);
		startUnit.setG(0);
		startUnit.setH(calculateHeuristics(startUnit, goalUnit));
		startUnit.setF(startUnit.getG() + startUnit.getH());
		priorityQueue.add(startUnit);

		while (!priorityQueue.isEmpty()) {
			Unit current = priorityQueue.poll();

			if (current.getValue() == goalUnit.getValue()) {
				//return reconstructPath(current);
			}

			closedSet.add(current);

			for (Unit neighbor : getNeighbors(current)) {
				if (closedSet.contains(neighbor)) {
					continue;
				}

				double tentativeG = current.getG() + calculateHeuristics(current, neighbor);

				if (!priorityQueue.contains(neighbor) || tentativeG < neighbor.getG()) {
					neighbor.setG(tentativeG);
					neighbor.setH(calculateHeuristics(neighbor, goalUnit));
					neighbor.setF(neighbor.getG() + neighbor.getH());
					neighbor.setParent(current);

					if (!priorityQueue.contains(neighbor)) {
						priorityQueue.add(neighbor);
					}
				}
			}
		}
		return false;
	}
	@SuppressWarnings("finally")
	boolean isSolvable(char[][] tableToTest) {
		boolean isnotWrong = true;
		int countingTillTheEnd = 0;
		for (char[] row : tableToTest) {
			for (int i = 0; i < row.length; i++) { 
				boolean occupied = false;
				if (row[i] == '0') {
					for (int condition = 0; condition < 5; condition++) {
						System.out.println("Robia sie warunki");
						switch (condition) {
						case 0:
							System.out.println("Robi sie 0");
							try {
								if (Generator.hasMandatoryBottomConnection(tableToTest[countingTillTheEnd - 1][i])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							}
						case 1:
							System.out.println("Robi sie 1");
							try {
								if (Generator.hasMandatoryConnectionUP(tableToTest[countingTillTheEnd + 1][i])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 2:
							System.out.println("Robi sie 2");
							try {
								if (Generator.hasMandatoryConnectionLeft(tableToTest[countingTillTheEnd][i + 1])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 3:
							System.out.println("Robi sie 3");
							try {
								if (Generator.hasMandatoryConnectionRight(tableToTest[countingTillTheEnd][i - 1])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 4:
							System.out.println("Robi sie 4");
							if (occupied == false) {
								isnotWrong = false;
							}
						}
					}
				}
			}
			countingTillTheEnd++;
		}
		if (!isnotWrong) {
			return false;
		} else {
			return true;
		}
	}
	//TODO delete
	public Unit[][] getBoard() {
		return board;
	}
	public static void main(String[] args) {
		Solver solver = new Solver(new Board(5,
				new int[][]{{1,0,0,0,0},{3,3,0,3,0},{0,3,0,0,0},{0,1,0,2,0},{0,2,0,0,0}}));
		System.out.println(solver.getNeighbors(solver.getBoard()[2][1]));
	}
}
