package main.gameinit;

import main.gui.MainGUI;

import java.io.File;
import java.io.IOException;

public class Game {
    private static final String BASE_PATH_FORMAT = "resources/boards/generated_%dx%d.csv";
    private static final int FILE_SIZE_START = 5;
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

    private static void createFileIfNotExists(int i) {
        String fileName = String.format(BASE_PATH_FORMAT, i, i);
        File csvFile = new File(fileName);
        if (!csvFile.exists()) {
            createNewFile(csvFile);
        }
    }

    private static void createNewFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error while creating file " + file.getName());
            e.printStackTrace();
        }
    }
}
