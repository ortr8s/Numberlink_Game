package main.utils;

import main.gamelogic.Board;
import main.gamelogic.Pair;
import main.gamelogic.Path;
import main.gamelogic.Unit;

import java.io.IOException;
import java.util.*;

public class Solver {
	final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // up, down, right, left
	private Unit[][] board;
	private Path path;
	private ArrayList<Pair> pairs;
	private int size;
	private int[][] checked;
	int pairIndex;
	private boolean stop;
	public Solver(Board board){
		this.board = board.getBoard();
		this.size = board.getSize();
		this.pairs = board.extractPairs();
		this.checked = new int[size][size];
		this.stop = false;
		this.pairIndex = 0;
	}
	private List<Unit> getNeighbors(int x, int y, ArrayList neighbours){
		neighbours.clear();
		for (int[] dir : directions) {
			int newX = x + dir[0];
			int newY = y + dir[1];
			if (isValidMove(newX, newY)) {
				neighbours.add(board[newX][newY]);
			}
		}

		return neighbours;

	}

	private boolean isValidMove(int x, int y){
		return x >= 0 && y >= 0 && x < size && y < size ;

	}

	public boolean solve(){
		Unit first = pairs.get(pairIndex).getFirst();
		int x = first.getX();
		int y = first.getY();
		int val = first.getValue();
		DFS(x,y,val);
		return true;
	}

	private void DFS(int x, int y, int val){
		if (stop) return;

		checked[x][y] = val;
		ArrayList<Unit> neighbours = new ArrayList<>();
		getNeighbors(x,y, neighbours);
		for (Unit currentNeighbour: neighbours) {

			int nextX = currentNeighbour.getX();
			int nextY = currentNeighbour.getY();
			int nextVal = currentNeighbour.getValue();
			if (checked[nextX][nextY] == 0) {
				if (nextVal == val) {
					pairIndex++;
					checked[nextX][nextY] = val;
					if (pairIndex == pairs.size()) {
						print();
						stop = true;
						return;
					}
					Unit currentBegin = pairs.get(pairIndex).getFirst();
					int curx = currentBegin.getX();
					int cury = currentBegin.getY();
					int newval = currentBegin.getValue();
					if (checked[curx][cury] == 0) {
						checked[curx][cury] = newval;
						DFS(curx, cury, newval);
					}
					pairIndex--;
					checked[nextX][nextY] = 0;

				} else {
					if (nextVal == 0) {
						DFS(nextX, nextY, val);
					}
				}
			}
		}
		checked[x][y] = 0;
	}

	private void print(){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++){
				System.out.print(checked[i][j] + " ");
			}
			System.out.println();
		}
	}
	//TODO delete
	public Unit[][] getBoard() {
		return board;
	}
	public static void main(String[] args) throws IOException, InvalidBoardSizeException {
		CSVReader reader = new CSVReader(",");
		int [][] data = reader.read(5);
		Board board1 = new Board(5,data);
		Solver solver = new Solver(board1);
		solver.solve();
	}
}
