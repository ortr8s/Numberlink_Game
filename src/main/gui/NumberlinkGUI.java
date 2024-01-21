package main.gui;

import main.controller.Controller;
import main.gamelogic.Path;
import main.gamelogic.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class NumberlinkGUI implements Runnable {
    /**
     * Represents a graphical frame used in the NumberlinkGUI class.
     * It is used to display the game board and control buttons.
     */
    private JFrame frame;
    /**
     * Represents a JPanel that serves as the game board.
     * This panel contains various game elements such as buttons and paths.
     * The board panel is used by the NumberlinkGUI class to display the game board.
     */
    private JPanel boardPanel;
    /**
     * Represents a controller for a Numberlink game.
     * <p>
     * The Controller class is responsible for managing the game board,
     * paths on the board, and handling user interactions with the board.
     * It provides methods for selecting units on the board, making moves,
     * and checking the completion status of the game.
     * <p>
     * Example usage:
     * <pre>{@code
     * Controller controller = new Controller(board);
     * controller.selectUnit(3, 4);
     * controller.makeMove(Moves.UP);
     * boolean isFinished = controller.isGameFinished();
     * }</pre>
     */
    private final Controller controller;
    /**
     * Represents a 2D array of JButton objects.
     * <p>
     * This variable stores a grid of JButton objects, which can be used to display and interact with a graphical user interface.
     * Each element in the array represents a button in the grid.
     * <p>
     * Example usage:
     * JButton button = buttons[row][column];
     * button.addActionListener(actionListener);
     * <p>
     * Note: The JButton class must be imported to use this variable.
     */
    private JButton[][] buttons;
    /**
     * Represents the main GUI component of the Numberlink application.
     */
    private final MainGUI mainGui;
    /**
     * Represents the start time of a task or event.
     * <p>
     * The startTime variable stores the starting time of a task or event as a long value.
     * It is typically used to measure the duration of the task or event by calculating the difference
     * between the start time and the current time.
     * <p>
     * Example usage:
     * long startTime = System.currentTimeMillis();
     * <p>
     * Note: The System.currentTimeMillis() method returns the current time in milliseconds.
     */
    private final long startTime;
    /**
     * Represents a transparent value.
     * <p>
     * This constant is used to represent a transparent value. It is often used in graphics programming to indicate that
     * a specific pixel or element should be transparent and not visible.
     * <p>
     * Example usage:
     * <pre>
     *     int color = TRANSPARENT;
     *     // Set the color to transparent
     * </pre>
     *
     * @see NumberlinkGUI
     * @see Controller
     */
    private static final int TRANSPARENT = 0;
    /**
     * Returns the main GUI instance.
     *
     * @return The main GUI instance.
     */
    public MainGUI getMainGui() {
        return mainGui;
    }
    /**
     * Returns the start time of the game.
     *
     * @return the start time in milliseconds
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Represents the graphical user interface for the Numberlink game.
     *
     * @param mainGUI     The main GUI object associated with the game.
     * @param controller  The controller object responsible for handling game logic.
     */
    public NumberlinkGUI(MainGUI mainGUI, Controller controller) {
        this.controller = controller;
        this.mainGui = mainGUI;
        startTime = System.currentTimeMillis();
    }
    /**
     * Creates a new JFrame for the Numberlink game, initializes the game board, and sets up the necessary components.
     */
    @Override
    public void run() {
        frame = new JFrame("Numberlink Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        addControlButtons();
        new KeyBoardManager(controller,this,frame);
        boardPanel = new JPanel();
        boardPanel.setDoubleBuffered(true);
        initializeBoard();
        frame.add(boardPanel);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(new Point(
                mainGui.getFrame().getX()-50,
                mainGui.getFrame().getY()-50)
        );
    }
    /**
     * Initializes the board by preparing the board panel and generating the board buttons.
     * This method is private and does not return any value.
     */
    private void initializeBoard() {
        prepareBoardPanel();
        generateBoardButtons();
    }

    /**
     * Removes all components from the boardPanel, refreshes the view, and sets the layout for the boardPanel.
     * The boardPanel is the panel that displays the game board.
     */
    private void prepareBoardPanel() {
        boardPanel.removeAll();
        boardPanel.revalidate();
        boardPanel.repaint();
        boardPanel.setLayout(new GridLayout(controller.getBoardSize(), controller.getBoardSize()));
    }

    /**
     * Generates the buttons for the game board.
     * Creates a JButton array with the size of the board. Each element in the array represents a cell on the board.
     * The buttons are customized based on the unit (cell) properties.
     * The buttons are then added to the board panel.
     * This method is called during the initialization of the board.
     */
    private void generateBoardButtons() {
        buttons = new JButton[controller.getBoardSize()][controller.getBoardSize()];
        for (int i = 0; i < controller.getBoardSize(); i++) {
            for (int j = 0; j < controller.getBoardSize(); j++) {
                Unit unit = controller.board.getUnitByPosition(i, j);
                buttons[i][j] = createButtonWithProperties(i, j, unit);
                boardPanel.add(buttons[i][j]);
            }
        }
    }
    /**
     * Creates a JButton with customized properties based on the given parameters.
     *
     * @param i The x-coordinate of the button.
     * @param j The y-coordinate of the button.
     * @param unit The Unit object associated with the button.
     * @return The JButton with the customized properties.
     */
    private JButton createButtonWithProperties(int i, int j, Unit unit) {
        JButton button;
        if (unit.getValue() > TRANSPARENT) {
            StartPathButton startPathButton = new StartPathButton();
            button = customizeButton(startPathButton, unit, i, j);
        } else {
            button = new JButton();
            button.setEnabled(false);
        }
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return button;
    }
    /**
     * Customizes a StartPathButton with properties based on the provided Unit and coordinates.
     *
     * @param button The StartPathButton to customize.
     * @param unit The Unit associated with the button.
     * @param x The x-coordinate on the game board.
     * @param y The y-coordinate on the game board.
     * @return The customized StartPathButton.
     */
    private StartPathButton customizeButton(StartPathButton button, Unit unit, int x, int y) {
        button.setText(String.valueOf(unit.getValue()));
        button.setBackground(getBackgroundColor(unit.getValue())); // Set background color
        button.setForeground(Color.BLACK); // Set text color
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, 18)); // Set font
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleCellClick(button, x, y);
            }
        });
        return button;
    }
    /**
     * Adds control buttons to the top panel of the frame.
     */
    private void addControlButtons() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton solveButton = new JButton("Solve Board");
        JButton backButton = new JButton("Go Back");
        JButton clearButton = new JButton("Clear");
        solveButton.addActionListener(e -> showSolution());
        backButton.addActionListener(e -> goBack());
        clearButton.addActionListener(e -> clearBoard());

        topPanel.add(solveButton);
        topPanel.add(clearButton);
        topPanel.add(backButton);
        frame.add(topPanel, BorderLayout.NORTH);
    }
    /**
     * Handles the click event on a cell button.
     * If the button is already clicked and there is a current path selected, it performs the following actions:
     * - Selects the unit at the given position
     * - Clears the buttons in the current path
     * - Clears the current path
     * - Resets the button's clicked state
     * - Resets the opposite button's clicked state
     * - Sets the button's background to its original background color
     * - Repaints the frame
     * If the button is not clicked, it performs the following actions:
     * - Selects the unit at the given position
     * - Sets the button's clicked state
     * - Sets the opposite button's clicked state
     * - Darkens the button's background color
     * - Repaints the frame
     *
     * @param button The StartPathButton that was clicked
     * @param x      The x-coordinate of the cell button
     * @param y      The y-coordinate of the cell button
     */
    private void handleCellClick(StartPathButton button, int x, int y) {
            if (button.isClicked() && controller.currentPath != null) {
                controller.selectUnit(x, y);
                clearButtons(controller.currentPath);
                controller.clearPath();
                button.reset();
                getOppositeButton(x,y).reset();
                System.out.println(controller.currentPath);
                button.setBackground(button.getBackground());
                frame.repaint();
            } else {
                controller.selectUnit(x, y);
                button.setClicked();
                getOppositeButton(x,y).setClicked();
                button.setBackground(button.getBackground().darker());
                frame.repaint();
                System.out.println(controller.currentPath);
            }
    }
    /**
     * Returns the opposite StartPathButton for the given coordinates (x, y).
     *
     * @param x The x-coordinate of the button.
     * @param y The y-coordinate of the button.
     * @return The opposite StartPathButton.
     */
    private StartPathButton getOppositeButton(int x, int y) {
        int[] oppositeCoords = controller.currentPath.getStartEndPair().getOpposite(x,y);
        return (StartPathButton) buttons[oppositeCoords[0]][oppositeCoords[1]];
    }
    /**
     * Displays the solution on the game board by changing the background color of the corresponding buttons.
     */
    private void showSolution(){
        try {
            HashMap<Integer, Path> paths = controller.solveAndExtractPaths();
            for (Map.Entry<Integer, Path> entry : paths.entrySet()) {
                int key = entry.getKey();
                Path value = entry.getValue();
                Color color = getBackgroundColor(key);
                for (int i = 1; i < value.getSize(); i++) {
                    buttons[value.getUnit(i).getX()][value.getUnit(i).getY()].setBackground(color);
                }
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        boardPanel.repaint();
        //JOptionPane.showMessageDialog(frame, "Solver solved the board!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Clears the game board by removing all buttons and units, clearing all paths,
     * and repainting the board panel.
     */
    public void clearBoard(){
        clearButtonsAndUnits();
        clearPaths();
        boardPanel.repaint();
    }

    /**
     * Clears all buttons and units on the game board.
     * This method iterates over all buttons on the board and calls the {@link #clearButtonAndUnit(int, int)} method
     * to clear the button and unit at each position.
     */
    private void clearButtonsAndUnits() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++){
                clearButtonAndUnit(i, j);
            }
        }
    }

    /**
     * Clears the button and unit at the specified position on the game board.
     *
     * @param i The x-coordinate of the position.
     * @param j The y-coordinate of the position.
     */
    private void clearButtonAndUnit(int i, int j) {
        Unit unit = controller.board.getUnitByPosition(i, j);
        if(unit.getValue() == 0) {
            controller.board.getUnitByPosition(i,j).setPartOfPath(false);
            buttons[i][j].setBackground(new Color(238,238,238));
        } else {
            ((StartPathButton) buttons[i][j]).reset();
            Color color = getBackgroundColor(unit.getValue());
            buttons[i][j].setBackground(color);
        }

    }

    /**
     * Clears all paths in the controller.
     */
    private void clearPaths() {
        for(Path path : controller.paths.values()) path.clearPath();
    }
    /**
     * Darkens the background color of the path units by one shade and repaints the board panel.
     *
     * @param path The path whose units' background color will be darkened.
     */
    public void darkenPath(Path path){
        Color currentColor = getBackgroundColor(path.getUnit(0).getValue());
        for (int i = 0; i < path.getSize(); i++) {
            Unit unit = path.getUnit(i);
            buttons[unit.getX()][unit.getY()].setBackground(currentColor.darker());
        }
        boardPanel.repaint();

    }
    /**
     * Navigates back to the main GUI frame and disposes the current frame.
     */
    private void goBack() {
        mainGui.showFrame(true);
        frame.dispose();
    }
    /**
     * Repaints the button associated with the given unit by changing its background color.
     *
     * @param unit The unit whose button needs to be repainted.
     */
    public void repaintButton(Unit unit) {
        Color color = getBackgroundColor(controller.currentPath.getID());
        buttons[unit.getX()][unit.getY()].setBackground(color);
        frame.repaint();
    }
    /**
     * Clears the background color of the buttons in the given path according to certain conditions.
     *
     * @param path The path containing the buttons to be cleared.
     */
    public void clearButtons(Path path) {
        Color clear = getBackgroundColor(14);
        Color regular = getBackgroundColor(path.getID());
        Unit[] contents = path.getUnits();
        for (int i = 0; i < path.getSize(); i++) {
            if (contents[i].equals(controller.getFirst())
                    || contents[i].equals(controller.getLast())) {
                buttons[contents[i].getX()][contents[i].getY()].setBackground(regular);
            } else {
                buttons[contents[i].getX()][contents[i].getY()].setBackground(clear);
            }
        }
        frame.repaint();
    }
     /**
      * Retrieves the background color based on the given number.
      *
      * @param number the number used to determine the background color
      * @return the background color corresponding to the given number
      */
     static Color getBackgroundColor(int number) {
         return switch (number) {
             case 1 -> new Color(255, 255, 0);
             case 2 -> new Color(0, 128, 0);
             case 3 -> new Color(255, 0, 0);
             case 4 -> new Color(0, 0, 255);
             case 5 -> new Color(255, 165, 0);
             case 6 -> new Color(128, 0, 128);
             case 7 -> new Color(255, 192, 203);
             case 8 -> new Color(64, 224, 208);
             case 9 -> new Color(128, 128, 0);
             case 10 -> new Color(255, 90, 40);
             case 11 -> new Color(173, 216, 230);
             case 12 -> new Color(82, 43, 28);
             case 13 -> new Color(139, 69, 19);
             default -> new Color(0, 0, 0, 0);
         };
    }
}