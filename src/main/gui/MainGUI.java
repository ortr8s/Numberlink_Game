package main.gui;

import main.controller.Controller;
import main.gamelogic.Board;
import main.utils.CSVReader;
import main.utils.CSVSaver;
import main.utils.Generator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

public class MainGUI {
    /**
     * The font used for the title label.
     * This font is set to "Arial", bold, with a size of 38.
     */
    private static final Font TITLE_LABEL_FONT = new Font("Arial", Font.BOLD, 38);
    /**
     * The SIZE_LABEL_FONT variable represents the font used for the size label.
     * <p>
     * The font is set to "Arial" with a bold style and a size of 70.
     * <p>
     * Example usage:
     *     SIZE_LABEL.setFont(SIZE_LABEL_FONT);
     */
    private static final Font SIZE_LABEL_FONT = new Font("Arial", Font.BOLD, 70);
    /**
     * Constant variable used to define the font for buttons in the MainGUI class.
     * The font is set to Arial, bold style and a size of 20.
     */
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);

    /**
     * Constant that represents the available sizes for maps.
     * <p>
     * The MAP_SIZES variable is an array of integers that define the sizes of maps.
     * It is a private static final variable, indicating that it cannot be modified and can only be accessed within the class.
     * Each integer in the array represents a specific size for maps, such as 5, 6, 7, 8, and 9.
     * </p>
     * <p>
     * Example usage:
     * <pre>{@code
     * int[] sizes = MAP_SIZES;
     * for (int size : sizes) {
     *     // Perform actions with each map size
     * }
     * }</pre>
     * </p>
     */
    private static final int[] MAP_SIZES = {5,6, 7,8, 9};
    /**
     * The currentIndex variable represents the current index value for some kind of sequence or collection.
     * It is used internally within the MainGUI class and has private access scope.
     */
    private static int currentIndex = 0;
    /**
     * A generator for producing variables.
     */
    private final Generator generator;
    /**
     * Represents a private JFrame variable in a software application.
     * <p>
     * The JFrame is a class that provides a window for creating graphical user interfaces using Swing. Typically,
     * a JFrame is used as the main window of a desktop application where you can add components like buttons, labels,
     * text fields, etc.
     * <p>
     * This variable is marked as private, which means it can only be accessed within the class where it is defined.
     * Private variables help to encapsulate the internal state of an object and prevent direct access from other classes.
     * <p>
     * It is important to note that this documentation does not contain example code or information regarding the author or version
     * of the software, as per the provided requirements.
     *
     * @see JFrame
     */
    private JFrame frame;
    /**
     * This variable represents a dropdown menu for selecting a map from a list.
     * It is of type JComboBox<String> and is declared private within the MainGUI class.
     * The dropdown menu allows the user to select from a list of available map options.
     * The selected map can be accessed through the methods provided by the JComboBox class.
     *
     * @see MainGUI
     * @see JComboBox
     */
    private JComboBox<String> mapSelectionDropdown;

    /**
     * MainGUI class represents the graphical user interface of the application.
     * This class provides a constructor to create an instance of the MainGUI.
     */
    public MainGUI() {
        generator = new Generator();
    }

    /**
     * Initializes the Graphical User Interface (GUI) for the Map Size Selector.
     * This method creates and configures the main frame, sets up the layout and components, and displays the frame on the screen.
     */
    public void initGUI() {
        frame = new JFrame("Map Size Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.setResizable(false);
        frame.setLocation(new java.awt.Point(400, 100));
        JPanel panel = new JPanel(new GridBagLayout());

        JLabel titleLabel = new JLabel("Choose map size");
        titleLabel.setFont(TITLE_LABEL_FONT);
        GridBagConstraints gbc = createGridBagConstraints();

        panel.add(titleLabel, gbc);

        JLabel sizeLabel = new JLabel(String.valueOf(MAP_SIZES[currentIndex]));
        sizeLabel.setFont(SIZE_LABEL_FONT);
        arrangeGridBagConstraintsForSizeLabel(gbc);

        panel.add(sizeLabel, gbc);

        setUpButtons(panel, sizeLabel, gbc);
    }

    /**
     * Creates and returns a new GridBagConstraints object with predefined values.
     *
     * @return a new GridBagConstraints object
     */
    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        return gbc;
    }

    /**
     * Adjusts the GridBagConstraints for the size label in the grid layout.
     *
     * @param gbc The GridBagConstraints to manipulate.
     */
    private void arrangeGridBagConstraintsForSizeLabel(GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
    }

    /**
     * Sets up buttons and performs other UI-related tasks.
     *
     * @param panel the panel to add the buttons to
     * @param sizeLabel the label to update with the selected size
     * @param gbc the grid bag constraints for positioning the buttons
     */
    private void setUpButtons(JPanel panel, JLabel sizeLabel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(createDecreaseButton(sizeLabel), gbc);

        gbc.gridx = 2;
        panel.add(createIncreaseButton(sizeLabel), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(createConfirmButton(), gbc);

        setUpMapSelectionDropdown();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(mapSelectionDropdown, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Creates a decrease button with the specified size label.
     *
     * @param sizeLabel the label to display the size value
     * @return the created decrease button
     */
    private JButton createDecreaseButton(JLabel sizeLabel) {
        JButton decreaseButton = new JButton("<");
        decreaseButton.setFont(BUTTON_FONT);
        decreaseButton.setPreferredSize(new Dimension(100, 50));
        decreaseButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                sizeLabel.setText(String.valueOf(MAP_SIZES[currentIndex]));
            }
        });
        return decreaseButton;
    }

    /**
     * Creates an increase button with the given size label.
     *
     * @param sizeLabel the label displaying the size
     * @return the increase button
     */
    private JButton createIncreaseButton(JLabel sizeLabel) {
        JButton increaseButton = new JButton(">");
        increaseButton.setFont(BUTTON_FONT);
        increaseButton.setPreferredSize(new Dimension(100, 50));
        increaseButton.addActionListener(e -> {
            if (currentIndex < MAP_SIZES.length - 1) {
                currentIndex++;
                sizeLabel.setText(String.valueOf(MAP_SIZES[currentIndex]));
            }
        });
        return increaseButton;
    }

    /**
     * Creates a confirm button for the game.
     *
     * @return the created confirm button
     */
    private JButton createConfirmButton() {
        JButton chooseButton = new JButton("OK");
        chooseButton.setFont(BUTTON_FONT);
        chooseButton.setPreferredSize(new Dimension(100, 50));
        chooseButton.addActionListener(e -> createGameBoard());

        return chooseButton;
    }
    /**
     * Sets up the map selection dropdown.
     */
    private void setUpMapSelectionDropdown() {
        String[] mapFiles = listMapFiles();
        mapSelectionDropdown = new JComboBox<>(mapFiles);
        mapSelectionDropdown.setFont(BUTTON_FONT);
        mapSelectionDropdown.addActionListener(e -> {
            String selectedMap = (String) mapSelectionDropdown.getSelectedItem();
            if (selectedMap != null && !selectedMap.isEmpty()) {
                loadGameBoardFromFile(selectedMap);
            }
        });
    }
    /**
     * Returns an array of file names of all the map files located in the "resources/boards" directory.
     * Only the files ending with ".csv" are included in the returned array.
     *
     * @return an array of file names of map files in the "resources/boards" directory, or an empty array if no files are found
     */
    private String[] listMapFiles() {
        File folder = new File("resources/boards"); // Adjust the path to your resources folder
        FilenameFilter filter = (dir, name) -> name.endsWith(".csv");
        String[] files = folder.list(filter);

        return files != null ? files : new String[0];
    }

    /**
     * Create a game board for the Numberlink puzzle.
     * If the current map size is valid, generate a board using the specified size from the generator.
     * Create a NumberlinkGUI with the MainGUI as the parent frame, a Controller initialized with the generated board,
     * and display the GUI using SwingUtilities.invokeLater().
     * Save the generated board to a CSV file using CSVSaver.save().
     * If any exception occurs during the process, print the exception message to the console.
     * If the current map size is invalid, display an error message using JOptionPane.showMessageDialog().
     * Dispose the frame after creating the game board.
     */
    private void createGameBoard() {
        if (MAP_SIZES[currentIndex] != -1) {
            try{
                int[][] generatedBoard = generator.generateBoard(MAP_SIZES[currentIndex]);
            NumberlinkGUI gui = new NumberlinkGUI(MainGUI.this,
                    new Controller(new Board(MAP_SIZES[currentIndex], generatedBoard)));
            CSVSaver.save(generatedBoard);
            SwingUtilities.invokeLater(gui);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "ERROR");
        }
        frame.dispose();
    }
    /**
     * Loads a game board from a specified file.
     *
     * @param selectedMap the name of the file containing the game board data
     */
    private void loadGameBoardFromFile(String selectedMap) {
        CSVReader reader = new CSVReader(",");
        try {
            //TODO change the method of extracting the number from file
            int[][] loadedBoard = reader.read(selectedMap);
            Board board = new Board(loadedBoard.length, loadedBoard);
            NumberlinkGUI gui = new NumberlinkGUI(MainGUI.this, new Controller(board));

            SwingUtilities.invokeLater(gui);
            frame.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading map: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retrieves the JFrame object associated with the current instance of the MainGUI class.
     *
     * @return The JFrame object representing the graphical frame of the MainGUI instance.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Shows or hides the frame according to the specified visibility.
     *
     * @param isVisible true if the frame should be visible, false otherwise
     */
    public void showFrame(boolean isVisible) {
        frame.setVisible(isVisible);
    }

}