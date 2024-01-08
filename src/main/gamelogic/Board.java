package main.gamelogic;

import main.utils.CSVReader;
import main.utils.Generator;
import main.utils.Solver;

import java.util.*;

public class Board {
    private final int size;
    Unit[][] board;
    ArrayList<Unit> pairs;
    private List<Path> paths;

    public Board(int size, int[][] numbers) {
        this.size = size;
        this.board = new Unit[size][size];
        pairs = new ArrayList<>();
        convertToUnitBoard(numbers);
        initializePaths();

    }

    public List<Path> getPaths() {
        return paths;
    }

    private void convertToUnitBoard(int[][] numbers) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Unit current = new Unit(i, j, numbers[i][j]);
                this.board[i][j] = current;
                if (numbers[i][j] != 0) {
                    this.pairs.add(current);
                }
            }
        }

    }

    private void initializePaths() {
        pairs.sort(Comparator.comparingInt(Unit::getValue));
        this.paths = new ArrayList<>((pairs.size() / 2));
        for (byte i = 1; i <= pairs.size() - 1; i += 2) {
            Unit first = pairs.get(i - 1);
            Unit last = pairs.get(i);
            paths.add(new Path(new Pair(first, last)));
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


    static int[][] convertGeneratedBoard(char[][] a, int size) {
        int[][] numbers = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int c = a[i][j] - 48;
                numbers[i][j] = (c < 0 || c > 21) ? 0 : c;
            }
        }
        return numbers;
    }

    public ArrayList<Pair> extractPairs() {
        ArrayList<Pair> finalPairs = new ArrayList<>();
        //sorts Units with numbers other than 0
        Collections.sort(pairs, Comparator.comparingInt(Unit::getValue));
        for (int i = 0; i < pairs.size() - 1; i += 2) {
            Unit one = pairs.get(i);
            Unit two = pairs.get(i + 1);
//            if(one.calculateDistanceFromCenter(size) < two.calculateDistanceFromCenter(size)){
//                finalPairs.add(new Pair(one, two));
//            } else {
//                finalPairs.add(new Pair(two, one));
//            }
            finalPairs.add(new Pair(one, two));
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

    public boolean hasCurves(Path path, Unit neighbour) {
        return Solver.checkForCurves(path, neighbour);
    }

    //TODO add uneven numbers exception
    public static void main(String[] args) {
//        Generator generator = new Generator();
//        char[][] generatedBoard = Generator.fillWithNumbers(generator.generate(9));
//        int [][] a = convertGeneratedBoard(generatedBoard,9);
//        System.out.println(Arrays.deepToString(a));
        CSVReader reader = new CSVReader(",");
        Board board1 = null;
        try {
            board1 = new Board(9, reader.read(9));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Solver solver = new Solver(board1);
        solver.solve();


    }
}
