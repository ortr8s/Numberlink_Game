package main.gui;

import main.gamelogic.Board;
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

public class NumberlinkGUI implements Runnable {
    private JFrame frame;
    private JPanel boardPanel;
    private Controller controller;

    public NumberlinkGUI(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        frame = new JFrame("Numberlink Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(controller.getBoardSize(), controller.getBoardSize()));
        initializeBoard();

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    private void initializeGUI() {
        frame = new JFrame("Numberlink Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(controller.getBoardSize(), controller.getBoardSize()));
        initializeBoard();

        frame.add(boardPanel);
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < controller.getBoardSize(); i++) {
            for (int j = 0; j < controller.getBoardSize(); j++) {
                JButton button = new JButton();
                Unit unit = controller.board.getUnitPosition(i, j); // Assuming such a method exists
                customizeButton(button, unit, i, j);
                boardPanel.add(button);
            }
        }
    }
    private Color getBackgroundColor(Unit unit) {
        int alpha = 200;
        switch (unit.getValue()) {
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
        if (unit.getValue() > 0) {
            button.setText(String.valueOf(unit.getValue()));
            button.setBackground(getBackgroundColor(unit)); // Set background color
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

    private void handleCellClick(int x, int y) {
        if (controller.selectUnit(x, y)) {
            // Update the GUI based on the new game state
            System.out.println(x + " " + y);
        }
    }


    public static void main(String[] args) throws IOException, InvalidBoardSizeException {
        //CSVReader reader = new CSVReader(",");
        //Board board = new Board(5, reader.read(5)); // Create a board
        Generator generator = new Generator();
        Board newBoard = new Board(9,Board.convertGeneratedBoard(generator.generate(9),9));
        Controller controller = new Controller(newBoard); // Create a controller
        SwingUtilities.invokeLater(new NumberlinkGUI(controller));
    }

}
