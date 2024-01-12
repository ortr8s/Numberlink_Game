package main.gui;

import main.gamelogic.Board;
import main.gamelogic.Path;
import main.gamelogic.Unit;
import main.controller.Controller;
import main.utils.CSVReader;
import main.utils.Generator;
import main.utils.InvalidBoardSizeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NumberlinkGUI implements Runnable {
    private JFrame frame;
    private JPanel boardPanel;
    private Controller controller;
    private JButton[][] buttons;

    public NumberlinkGUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        frame = new JFrame("Numberlink Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        addControlButtons();
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(controller.getBoardSize(), controller.getBoardSize()));
        boardPanel.setDoubleBuffered(true);
        initializeBoard();

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        boardPanel.removeAll();
        boardPanel.revalidate();
        boardPanel.repaint();
        boardPanel.setLayout(new GridLayout(controller.getBoardSize(), controller.getBoardSize()));
        buttons = new JButton[controller.getBoardSize()][controller.getBoardSize()];
        for (int i = 0; i < controller.getBoardSize(); i++) {
            for (int j = 0; j < controller.getBoardSize(); j++) {
                JButton button = new JButton();
                Unit unit = controller.board.getUnitPosition(i, j);
                customizeButton(button, unit, i, j);
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
    }
    private Color getBackgroundColor(int number) {
        int alpha = 200;
        switch (number) {
            case 1:
                return new Color(255, 255, 0, alpha); // Yellow
            case 2:
                return new Color(0, 128, 0, alpha); // Green
            case 3:
                return new Color(255, 0, 0, alpha); // Red
            case 4:
                return new Color(0, 0, 255, alpha); // Blue
            case 5:
                return new Color(255, 165, 0, alpha); // Orange
            case 6:
                return new Color(128, 0, 128, alpha); // Purple
            case 7:
                return new Color(255, 192, 203, alpha); // Pink
            case 8:
                return new Color(64, 224, 208, alpha); // Turquoise
            case 9:
                return new Color(128, 128, 0, alpha); // Olive
            case 10:
                return new Color(255, 69, 0, alpha); // Red-Orange
            case 11:
                return new Color(173, 216, 230, alpha); // Light Blue
            case 12:
                return new Color(0, 100, 0, alpha); // Dark Green
            case 13:
                return new Color(139, 69, 19, alpha); // Brown
            default:
                return new Color(0, 0, 0, 0); // Transparent for any other value
        }
    }
    private void customizeButton(JButton button, Unit unit, int x, int y) {
        if (unit.getValue() > 0 && unit != null) {
            button.setText(String.valueOf(unit.getValue()));
            button.setBackground(getBackgroundColor(unit.getValue())); // Set background color
            button.setForeground(Color.BLACK); // Set text color
            button.setFont(new Font(button.getFont().getName(), Font.BOLD, 18)); // Set font
            button.setBorderPainted(true);
            button.setFocusPainted(false);

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleCellClick(x, y);
                }
            });
        } else {
            button.setEnabled(false); // Disable buttons with value 0
        }

        // Set common styles for all buttons
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
    private void addControlButtons() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Solve button
        JButton solveButton = new JButton("Solve Board");
        solveButton.addActionListener(e -> showSolution());
        topPanel.add(solveButton);

        // Generate button
        JButton generateButton = new JButton("Generate Board");
        generateButton.addActionListener(e -> showDifficultySelection());
        topPanel.add(generateButton);

        // Add the top panel to the frame
        frame.add(topPanel, BorderLayout.NORTH);
    }

    private void showDifficultySelection() {
        String[] difficulties = {"5", "7", "9"};
        String selectedDifficulty = (String) JOptionPane.showInputDialog(
                frame,
                "Select Difficulty",
                "Difficulty Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                difficulties,
                difficulties[0]);

        if (selectedDifficulty != null && selectedDifficulty.length() > 0) {
            controller.generateBoard(Integer.parseInt(selectedDifficulty));
            initializeBoard();
        }
    }

    private void handleCellClick(int x, int y) {
        if (controller.selectUnit(x, y)) {
            // Update the GUI based on the new game state
            System.out.println(controller.selectUnit(x,y));
        }
    }

    private void showSolution(){
        HashMap<Integer, Path > paths = controller.solveAndExtractPaths();
        for (Map.Entry<Integer, Path> entry : paths.entrySet()) {
            int key = entry.getKey();
            Path value = entry.getValue();
            Color color = getBackgroundColor(key);
            for (int i = 1; i < value.getSize(); i++) {
                buttons[value.getUnit(i).getX()][value.getUnit(i).getY()].setBackground(color);
            }
        }
        boardPanel.repaint();
    }
    public static void main(String[] args) throws IOException, InvalidBoardSizeException {
        //CSVReader reader = new CSVReader(",");
        Board board = new Board(5, new int[][]{{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}}); //default board
        Controller controller = new Controller(board); // create a controller
        SwingUtilities.invokeLater(new NumberlinkGUI(controller));
    }

}
