package main.gui;

import main.controller.Controller;
import main.gamelogic.Board;
import main.utils.Generator;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    private static final int[] MAP_SIZES = {5, 7, 9};
    private static int currentIndex = 0;
    private final Generator generator;
    private JFrame frame;

    public MainGUI() {
        generator = new Generator();
    }

    public void initGUI() {
        frame = new JFrame("Map Size Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel titleLabel = new JLabel("Choose map size".toUpperCase());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 38));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(titleLabel, gbc);

        JLabel sizeLabel = new JLabel(String.valueOf(MAP_SIZES[currentIndex]));
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 70));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(sizeLabel, gbc);

        JButton decreaseButton = new JButton("<");
        decreaseButton.setFont(new Font("Arial", Font.BOLD, 20));
        decreaseButton.setPreferredSize(new Dimension(100, 50));
        decreaseButton.addActionListener(e -> {
            if (currentIndex > 0) {
                currentIndex--;
                sizeLabel.setText(String.valueOf(MAP_SIZES[currentIndex]));
            }
        });
        gbc.gridx = 0;
        panel.add(decreaseButton, gbc);

        JButton increaseButton = new JButton(">");
        increaseButton.setFont(new Font("Arial", Font.BOLD, 20));
        increaseButton.setPreferredSize(new Dimension(100, 50));
        increaseButton.addActionListener(e -> {
            if (currentIndex < MAP_SIZES.length - 1) {
                currentIndex++;
                sizeLabel.setText(String.valueOf(MAP_SIZES[currentIndex]));
            }
        });
        gbc.gridx = 2;
        panel.add(increaseButton, gbc);

        JButton chooseButton = getOkButton();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(chooseButton, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private JButton getOkButton() {
        JButton chooseButton = new JButton("OK");
        chooseButton.setFont(new Font("Arial", Font.BOLD, 20));
        chooseButton.setPreferredSize(new Dimension(100, 50));
        chooseButton.addActionListener(e -> {
            switch (MAP_SIZES[currentIndex]) {
                case 5:
                    NumberlinkGUI gui5 = new NumberlinkGUI(MainGUI.this,
                            new Controller(new Board(5,generator.generateBoard(5))));
                    SwingUtilities.invokeLater(gui5);
                    break;
                case 7:
                    NumberlinkGUI gui7 = new NumberlinkGUI(MainGUI.this,
                            new Controller(new Board(7,generator.generateBoard(7))));
                    SwingUtilities.invokeLater(gui7);
                    break;
                case 9:
                    NumberlinkGUI gui9 = new NumberlinkGUI(MainGUI.this,
                            new Controller(new Board(9,generator.generateBoard(9))));
                    SwingUtilities.invokeLater(gui9);
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "ERROR");
            }
            frame.dispose();
        });
        return chooseButton;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void showFrame(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.initGUI();
    }
}