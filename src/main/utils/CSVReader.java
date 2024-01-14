package main.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringJoiner;


/**
 * The CSVReader class is used to read the contents of a CSV file and
 * convert it into a 2-dimensional integer array representing a board.
 */
public class CSVReader {
    /**
     * The minimum size of the board.
     * The value of this constant is set to 5.
     * It is used to check if the given board size is valid.
     * The board size should be greater than or equal to MINIMUM_SIZE and
     * less than or equal to the maximum board size.
     *
     * @see CSVReader#isBoardSizeValid(int)
     * @see CSVReader#read(int)
     */
    public static final int MINIMUM_SIZE = 5;
    /**
     * The maximum size for a board.
     * The value of this constant is 9.
     */
    public static final int MAXIMUM_SIZE = 9;
    /**
     * The separator used in a CSV file.
     */
    private final String separator;
    /**
     * The file path to the folder where board files are stored.
     */
    private static final String BOARD_FOLDER_PATH = "resources/boards";
    private int[][] data;

    /**
     * Constructs a new CSVReader with the specified separator.
     *
     * @param separator the separator used in the CSV file
     */
    public CSVReader(String separator) {
        this.separator = separator;
    }

    /**
     * Checks if the given board size is valid.
     *
     * @param boardSize the size of the board to check
     * @return true if the board size is valid, false otherwise
     */
    private static boolean isBoardSizeValid(int boardSize) {
        return (boardSize >= MINIMUM_SIZE && boardSize <= MAXIMUM_SIZE);
    }

    /**
     * Reads the contents of a CSV file and returns a 2-dimensional integer array representing the board.
     *
     * @param boardSize the size of the board
     * @return a 2-dimensional integer array representing the board
     * @throws IOException if an I/O error occurs
     * @throws InvalidBoardSizeException if the board size is invalid
     */
    public int[][] read(int boardSize) throws IOException, InvalidBoardSizeException {
        if (!isBoardSizeValid(boardSize)) {
            throw new InvalidBoardSizeException(
                    String.format("The board size should be between %d and %d", MINIMUM_SIZE, MAXIMUM_SIZE)
            );
        }

        String filePath = String.join("", BOARD_FOLDER_PATH, String.format("/board_%dx%d.csv", boardSize, boardSize));
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            data = new int[boardSize][boardSize];
            int i = 0;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                data[i] = Arrays.stream(line.split(separator)).mapToInt(Integer::parseInt).toArray();
                i++;
            }
        }
        return data;
    }
    @Override
    public String toString() {
        return new StringJoiner(", ", CSVReader.class.getSimpleName() + "[", "]")
                .add("data=" + Arrays.deepToString(data))
                .toString();
    }

    public static void main(String[] args) {
        try {
            CSVReader test = new CSVReader(",");
            test.read(5);
            System.out.println(test);

        } catch (IOException e) {
            System.out.println("Nie odnaleziono pliku");
            e.printStackTrace();
        } catch (InvalidBoardSizeException e) {
            System.out.println("Wprowadzono niepoprawny rozmiar mapy");
            e.printStackTrace();
        }
    }

}
