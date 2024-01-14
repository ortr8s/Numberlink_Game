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
    private JFrame frame;
    private JPanel boardPanel;
    private final Controller controller;
    private JButton[][] buttons;
    private final MainGUI mainGui;

    public MainGUI getMainGui() {
        return mainGui;
    }

    private static final int TRANSPARENT = 0;

    public NumberlinkGUI(MainGUI mainGUI, Controller controller) {
        this.controller = controller;
        this.mainGui = mainGUI;
    }
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
    private void initializeBoard() {
        prepareBoardPanel();
        generateBoardButtons();
    }

    private void prepareBoardPanel() {
        boardPanel.removeAll();
        boardPanel.revalidate();
        boardPanel.repaint();
        boardPanel.setLayout(new GridLayout(controller.getBoardSize(), controller.getBoardSize()));
    }

    private void generateBoardButtons() {
        buttons = new JButton[controller.getBoardSize()][controller.getBoardSize()];
        for (int i = 0; i < controller.getBoardSize(); i++) {
            for (int j = 0; j < controller.getBoardSize(); j++) {
                Unit unit = controller.board.getUnitPosition(i, j);
                buttons[i][j] = createButtonWithProperties(i, j, unit);
                boardPanel.add(buttons[i][j]);
            }
        }
    }
    private JButton createButtonWithProperties(int i, int j, Unit unit) {
        JButton button;
        if (unit.getValue() > TRANSPARENT) {
            StartPathButton startPathButton = new StartPathButton();
            return customizeButton(startPathButton, unit, i, j);
        } else {
            button = new JButton();
            button.setEnabled(false);
        }
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return button;
    }
    private StartPathButton customizeButton(StartPathButton button, Unit unit, int x, int y) {
        button.setText(String.valueOf(unit.getValue()));
        button.setBackground(getBackgroundColor(unit.getValue())); // Set background color
        button.setForeground(Color.BLACK); // Set text color
        button.setFont(new Font(button.getFont().getName(), Font.BOLD, 18)); // Set font
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleCellClick(button, x, y);
            }
        });
        return button;
    }
    private void addControlButtons() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton solveButton = new JButton("Solve Board");
        JButton backButton = new JButton("Go Back");
        solveButton.addActionListener(e -> showSolution());
        backButton.addActionListener(e -> goBack());
        topPanel.add(solveButton);
        topPanel.add(backButton);
        frame.add(topPanel, BorderLayout.NORTH);
    }
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
    private StartPathButton getOppositeButton(int x, int y) {
        int[] oppositeCoords = controller.currentPath.getStartEndPair().getOpposite(x,y);
        return (StartPathButton) buttons[oppositeCoords[0]][oppositeCoords[1]];
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
    public void darkenPath(Path path){
        Color currentColor = getBackgroundColor(path.getUnit(0).getValue());
        for (int i = 0; i < path.getSize(); i++) {
            Unit unit = path.getUnit(i);
            buttons[unit.getX()][unit.getY()].setBackground(currentColor.darker());
        }
        boardPanel.repaint();

    }
    private void goBack() {
        mainGui.showFrame(true);
        frame.dispose();
    }
    public void repaintButton(Unit unit) {
        Color color = getBackgroundColor(controller.currentPath.getID());
        buttons[unit.getX()][unit.getY()].setBackground(color);
        frame.repaint();
    }
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
     static Color getBackgroundColor(int number) {
        int alpha = 200;
         return switch (number) {
             case 1 -> new Color(255, 255, 0, alpha);
             case 2 -> new Color(0, 128, 0, alpha);
             case 3 -> new Color(255, 0, 0, alpha);
             case 4 -> new Color(0, 0, 255, alpha);
             case 5 -> new Color(255, 165, 0, alpha);
             case 6 -> new Color(128, 0, 128, alpha);
             case 7 -> new Color(255, 192, 203, alpha);
             case 8 -> new Color(64, 224, 208, alpha);
             case 9 -> new Color(128, 128, 0, alpha);
             case 10 -> new Color(255, 69, 0, alpha);
             case 11 -> new Color(173, 216, 230, alpha);
             case 12 -> new Color(82, 43, 28, alpha);
             case 13 -> new Color(139, 69, 19, alpha);
             default -> new Color(0, 0, 0, 0);
         };
    }
}