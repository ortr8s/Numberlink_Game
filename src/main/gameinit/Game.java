package main.gameinit;

import main.gui.MainGUI;

import java.io.File;
import java.io.IOException;

/**
 * The Game class is responsible for creating blank files and initializing the MainGUI.
 */
public class Game {
    /**
     * The BASE_PATH_FORMAT variable is a string that represents the file path format for generating board files.
     * The format includes two placeholders (%d) for the width and height of the board.
     * <p>
     * Example usage:
     * String basePath = String.format(BASE_PATH_FORMAT, width, height);
     *
     * @see Game#createBlankFiles()
     * @see Game#createFileIfNotExists(int)
     * @see Game#createNewFile(File)
     */
    private static final String BASE_PATH_FORMAT = "resources/boards/generated_%dx%d.csv";
    /**
     * The FILE_SIZE_START constant represents the starting value for file size.
     * It is used in the Game class to create blank files if they don't already exist.
     */
    private static final int FILE_SIZE_START = 5;
    /**
     * The FILE_SIZE_END variable specifies the ending value for the file size when creating blank files.
     * <p>
     * It is defined in the Game class and is used in the createBlankFiles() method.
     * This variable indicates the highest value of the file size for which the blank files will be created.
     * The createBlankFiles() method iterates from FILE_SIZE_START to FILE_SIZE_END and calls createFileIfNotExists()
     * method to create the files if they don't already exist.
     * <p>
     * Example Usage:
     * int fileSizeEnd = FILE_SIZE_END;
     */
    private static final int FILE_SIZE_END = 9;


    public static void main(String[] args) {
        createBlankFiles();
        MainGUI main = new MainGUI();
        main.initGUI();
    }

    private static void createBlankFiles() {
        for (int i = FILE_SIZE_START; i <= FILE_SIZE_END; i++) {
            createFileIfNotExists(i);
        }
    }

    /**
     * Creates a file if it does not already exist.
     *
     * @param i the value used to format the file name
     */
    private static void createFileIfNotExists(int i) {
        String fileName = String.format(BASE_PATH_FORMAT, i, i);
        File csvFile = new File(fileName);
        if (!csvFile.exists()) {
            createNewFile(csvFile);
        }
    }

    /**
     * Creates a new file at the specified location.
     * If the file already exists, no action is taken.
     *
     * @param file the file to be created
     */
    private static void createNewFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error while creating file " + file.getName());
            e.printStackTrace();
        }
    }
}
