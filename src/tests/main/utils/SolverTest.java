package tests.main.utils;

import main.gamelogic.Board;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import main.utils.Solver;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverTest {

    private static final String TEST_FOLDER_PATH = "src/tests/test_boards";

    private int[][] readTestBoard(int testNumber, int boardSize) {
            int[][] data = new int[boardSize][boardSize];
            String filePath = String.join("", TEST_FOLDER_PATH, String.format("/%dboard_%dx%d.csv",testNumber ,boardSize, boardSize));
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                int i = 0;
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    data[i] = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
    }
    /**
     * Testing the solve() method of the Solver class
     * The method is responsible for finding a solution to the game
     * The correctness of the solution is verified by the test
     */
    @Test
    void solve() {

        Board board1 = new Board(5,readTestBoard(0,5));
        Solver solver = new Solver(board1);
        int[][] expected = new int[][]{{1,2,2,5,5},{1,2,3,5,4},{1,2,3,5,4},{1,2,3,5,4},{1,1,3,4,4}};
        solver.solve();
        System.out.println(Arrays.deepToString(solver.getSolution()));
        assertArrayEquals(expected, solver.getSolution());


    }
}