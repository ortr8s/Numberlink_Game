package main.gamelogic;

import main.utils.CSVReader;

import java.io.IOException;
import java.util.*;

public class Board {
    private final int size;
    Unit[][] board;
    ArrayList<Unit> pairs;
    public Board(int size, int[][] numbers){
        this.size = size;
        this.board = new Unit[size][size];;
        pairs = new ArrayList<>();

        for( int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                Unit current = new Unit(i,j,numbers[i][j]);
                this.board[i][j] = current;
                if (numbers[i][j] != 0){
                    this.pairs.add(current);
                }
            }
        }

    }
    private ArrayList<Unit[]> extractPairs() {
        ArrayList<Unit[]> finalPairs = new ArrayList<>();
        //sorts Units with numbers other than 0
        Collections.sort(pairs, Comparator.comparingInt(Unit::getValue));
        for (int i = 0; i < pairs.size()-1; i+=2) {
            finalPairs.add(new Unit[]{pairs.get(i), pairs.get(i + 1)});
        }
        return finalPairs;
    }
    public Unit[][] getBoard() {
        return board;
    }
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Board.class.getSimpleName() + "[", "]")
                .add("board=" + Arrays.deepToString(board))
                .toString();
    }
    //TODO add uneven numbers exception
    public static void main(String[] args) {
        try {
            CSVReader test = new CSVReader("resources/test_5x5.csv",",");
            Board myboard = new Board(5,test.read());
            System.out.println(myboard);
            System.out.println(myboard.extractPairs().get(0)[0]);
        } catch (IOException e){
            System.out.println("Nie znaleziono pliku");
        }
    }
}
