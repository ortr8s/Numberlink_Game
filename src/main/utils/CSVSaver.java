package main.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CSVSaver class is responsible for saving a game board to a CSV file.
 * The class contains a static method `save` that takes a 2D integer array representing the game board
 * and saves it to a file in CSV format.
 * The class also contains a private method `getFileWriter` that creates and returns a FileWriter object
 * initialized with the provided parameters.
 */
public class CSVSaver {
    private static final String BOARD_FOLDER_PATH = "resources/boards";
    /**
     * Saves the given game board to a CSV file.
     *
     * @param board the game board represented as a 2D integer array
     */
    public static void save(int[][] board) {
        int size = board.length;

        try {
            String fileName = String.format("/generated_%dx%d.csv", size, size);
            String filePath = String.join("", BOARD_FOLDER_PATH, fileName);

            // Create parent directories of the file if they do not exist
            FileWriter txtWriter = getFileWriter(board, filePath, size);
            txtWriter.close();

            System.out.println("Board saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the text to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Returns a FileWriter object initialized with the provided parameters.
     *
     * @param board    the game board represented as a 2D integer array
     * @param filePath the path to the file to be created or overwritten
     * @param size     the size of the game board
     * @return a FileWriter object
     * @throws IOException if an I/O error occurs while creating the FileWriter or writing to the file
     */
    private static FileWriter getFileWriter(int[][] board, String filePath, int size) throws IOException {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        FileWriter txtWriter = new FileWriter(file);

        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                txtWriter.write(Integer.toString(board[j][i]));
                if (i == size - 1) {
                    continue;
                }
                txtWriter.write(",");
            }
            txtWriter.write("\n");
        }
        return txtWriter;
    }

}
