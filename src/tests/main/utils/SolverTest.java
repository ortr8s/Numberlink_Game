package main.utils;

import main.gamelogic.Board;
import main.utils.exceptions.InconsitentNumberOfNumbersException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SolverTest {

    private static final String TEST_FOLDER_PATH = "src/tests/test_boards";
    private static final Generator generator = new Generator();
    private static Stream<Arguments> boardProvider() {
        return Stream.of(
                Arguments.of(0, 5, new int[][]{
                        {1, 2, 2, 5, 5},
                        {1, 2, 3, 5, 4},
                        {1, 2, 3, 5, 4},
                        {1, 2, 3, 5, 4},
                        {1, 1, 3, 4, 4}
                }),
                Arguments.of(1, 5, new int[][]{
                        {1, 1, 1, 1, 2},
                        {4, 4, 4, 1, 2},
                        {4, 3, 3, 1, 2},
                        {4, 3, 1, 1, 2},
                        {4, 3, 1, 2, 2}
                }),
                Arguments.of(2, 5, null),
                Arguments.of(0, 7, new int[][]{
                        {1, 7, 7, 7, 3, 4, 4},
                        {1, 7, 5, 7, 3, 3, 4},
                        {1, 7, 5, 7, 7, 6, 4},
                        {1, 7, 5, 5, 7, 6, 4},
                        {6, 6, 6, 7, 7, 6, 4},
                        {8, 8, 6, 6, 6, 6, 4},
                        {8, 4, 4, 4, 4, 4, 4}
                }),
                Arguments.of(1, 7, new int[][]{
                        {1, 7, 7, 7, 3, 4, 4},
                        {1, 7, 5, 7, 3, 3, 4},
                        {1, 7, 5, 7, 7, 6, 4},
                        {1, 7, 5, 5, 7, 6, 4},
                        {6, 6, 6, 7, 7, 6, 4},
                        {8, 8, 6, 6, 6, 6, 4},
                        {8, 4, 4, 4, 4, 4, 4}
                }),
                Arguments.of(2, 7, null),
                Arguments.of(0, 9, new int[][]{
                        {2, 2, 2, 2, 2, 2, 2, 1, 1},
                        {2, 3, 3, 3, 3, 3, 2, 2, 1},
                        {3, 3, 4, 4, 4, 4, 4, 2, 1},
                        {3, 4, 4, 2, 2, 2, 4, 2, 1},
                        {3, 7, 7, 7, 7, 2, 4, 2, 1},
                        {3, 7, 5, 6, 7, 2, 2, 2, 1},
                        {3, 7, 5, 6, 7, 7, 7, 1, 1},
                        {3, 7, 5, 6, 6, 6, 1, 1, 8},
                        {3, 3, 3, 3, 3, 3, 1, 8, 8}
                }),
                Arguments.of(1, 9, new int[][]{
                        {1, 1, 1, 1, 1, 1, 1, 2, 2},
                        {2, 2, 2, 2, 2, 2, 1, 1, 2},
                        {2, 8, 8, 8, 8, 2, 2, 1, 2},
                        {2, 8, 3, 3, 8, 4, 2, 1, 2},
                        {8, 8, 3, 5, 8, 4, 2, 6, 2},
                        {8, 7, 3, 5, 8, 4, 2, 6, 2},
                        {8, 7, 3, 5, 2, 2, 2, 6, 2},
                        {8, 7, 3, 5, 2, 6, 6, 6, 2},
                        {8, 8, 8, 5, 2, 2, 2, 2, 2}
                })
        );
    }
    private int[][] readTestBoard(int testNumber, int boardSize) {
        int[][] data = new int[boardSize][boardSize];
        String filePath = String.join("", TEST_FOLDER_PATH, String.format("/%dboard_%dx%d.csv", testNumber, boardSize, boardSize));
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

    private static Stream<Arguments> generatedBoardProvider() {
        return Stream.of(
                Arguments.of(5, generator.generateBoard(5)),
                Arguments.of(5, generator.generateBoard(5)),
                Arguments.of(6, generator.generateBoard(6)),
                Arguments.of(6, generator.generateBoard(6)),
                Arguments.of(7, generator.generateBoard(7)),
                Arguments.of(7, generator.generateBoard(7)),
                Arguments.of(8, generator.generateBoard(8)),
                Arguments.of(8, generator.generateBoard(8)),
                Arguments.of(9, generator.generateBoard(9)),
                Arguments.of(9, generator.generateBoard(9))
        );
    }
    /**
     * Testing the solve() method of the Solver class
     * The method is responsible for finding a solution to the game
     * The correctness of the solution is verified by the test
     */
    @ParameterizedTest
    @MethodSource("boardProvider")
    void solve(int testNumber, int boardSize, int[][] expected) {
        try {
            int[][] initialBoard = readTestBoard(testNumber, boardSize);
            Board board = new Board(boardSize, initialBoard);
            Solver solver = new Solver(board);
            boolean solvable = solver.solve();
            if (testNumber == 2){
                assertFalse(solvable, "Solver shouldn't have found a solution for board " + testNumber);
            } else {
                assertTrue(solvable, "Solver should have found a solution for board " + testNumber);
                assertArrayEquals(expected, solver.getSolution(), "The solution for board " + testNumber + " does not match the expected output.");
            }
        } catch (InconsitentNumberOfNumbersException inone){
            inone.printStackTrace();
        }

    }
    @ParameterizedTest
    @MethodSource("generatedBoardProvider")
    void solveGenerated(int boardSize, int[][] board){
        try {
            Board boardObj = new Board(boardSize, board);
            Solver solver = new Solver(boardObj);
            boolean solvable = solver.solve();
            assertTrue(solvable, "Solver should have found a solution for board " + boardSize);
        } catch (InconsitentNumberOfNumbersException inone){
            inone.printStackTrace();
        }
    }
}