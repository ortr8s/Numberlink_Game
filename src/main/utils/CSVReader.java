package main.utils;

import java.io.*;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;


/**
 * CSVReader is a class that provides methods for reading CSV files and obtaining a 2-dimensional integer array representation of the data.
 */
public class CSVReader {
    /**
     * Minimum size of the board.
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
     * Reads a CSV file and returns a two-dimensional array of integers representing the data in the file.
     *
     * @param fileName the name of the CSV file to read
     * @return a two-dimensional array of integers representing the data in the file
     * @throws IOException if an I/O error occurs while reading the file
     * @throws InvalidBoardSizeException if the board size specified in the file name is invalid
     * @throws FileNotFoundException if no matching file is found for the given file name
     */
    public int[][] read(String fileName) throws IOException, InvalidBoardSizeException {
        // Create filename pattern
        Pattern pattern = Pattern.compile(".*\\d+.*\\.csv");

        // List files in directory
        File dir = new File(BOARD_FOLDER_PATH);
        File[] files = dir.listFiles();

        // Variable to hold the matching file
        File matchingFile = null;

        for (File file : files) {
            if (pattern.matcher(file.getName()).matches() && file.getName().equals(fileName)) {
                matchingFile = file;
                break;
            }
        }

        int[][] data = getInts(fileName, matchingFile);

        try (BufferedReader br = new BufferedReader(new FileReader(matchingFile))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                data[i] = Arrays.stream(line.split(separator)).mapToInt(Integer::parseInt).toArray();
                i++;
            }
        }
        return data;
    }

    /**
     * Retrieves the integer board data from a CSV file.
     *
     * @param fileName      the name of the CSV file
     * @param matchingFile  the matching File object for the given fileName
     * @return a 2D array of integers representing the board data
     * @throws FileNotFoundException        if no matching file is found for the given fileName
     * @throws InvalidBoardSizeException     if the board size is invalid
     */
    private static int[][] getInts(String fileName, File matchingFile) throws FileNotFoundException, InvalidBoardSizeException {
        if (matchingFile == null)
            throw new FileNotFoundException(String.format("No matching file found for the name %s.", fileName));

        String[] sizeStr = fileName.replaceAll(".csv","").split("_")[1].split("x");
        int boardSize = Integer.parseInt(sizeStr[0]); // it's recommended to handle possible NumberFormatException here

        if (!isBoardSizeValid(boardSize)) {
            throw new InvalidBoardSizeException(
                    String.format("The board size should be between %d and %d", MINIMUM_SIZE, MAXIMUM_SIZE)
            );
        }

        int[][] data = new int[boardSize][boardSize];
        return data;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CSVReader.class.getSimpleName() + "[", "]")
                .add("data=" + Arrays.deepToString(data))
                .toString();
    }

}
