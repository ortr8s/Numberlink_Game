package main.gamelogic;

import main.utils.Generator;

import java.util.*;

public class Board {
    private final int size;
    Unit[][] board;
    ArrayList<Unit> pairs;
    public Board(int size, int[][] numbers){
        this.size = size;
        this.board = new Unit[size][size];
        pairs = new ArrayList<>();
        convertToUnitBoard(numbers);

    }

    private void convertToUnitBoard(int[][] numbers){
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
    static int[][] convertGeneratedBoard(char[][] a, int size){
        int[][] numbers= new int[size][size];
        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++){
                int c = a[i][j] - 48;
                numbers[i][j] = (c<0 || c > 21) ? 0: c;
            }
        }
        return numbers;
    }
    public ArrayList<Pair> extractPairs() {
        ArrayList<Pair> finalPairs = new ArrayList<>();
        //sorts Units with numbers other than 0
        Collections.sort(pairs, Comparator.comparingInt(Unit::getValue));
        for (int i = 0; i < pairs.size()-1; i+=2) {
            finalPairs.add(new Pair(pairs.get(i), pairs.get(i+1)));
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
        Generator generator = new Generator();
        char[][] generatedBoard = Generator.fillWithNumbers(generator.generate(7));
        int [][] a = convertGeneratedBoard(generatedBoard,7);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length;j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        Board board1 = new Board(9,a);
        System.out.println(board1);

    }
}
