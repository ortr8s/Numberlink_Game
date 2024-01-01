package main.gamelogic;

import main.utils.CSVReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;

public class Board {
    private final int size;
    Unit[][] board;
    public Board(int size, int[][] numbers){
        this.size = size;
        this.board = new Unit[size][size];
        for( int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                this.board[i][j] = new Unit(i,j,numbers[i][j]);
            }
        }
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
    //TODO delete
    public static void main(String[] args) {
        try {
            CSVReader test = new CSVReader("resources/test_5x5.csv",",");
            Board myboard = new Board(5,test.read());
            System.out.println(myboard);
        } catch (IOException e){
            System.out.println("Nie znaleziono pliku");
        }
    }
}
