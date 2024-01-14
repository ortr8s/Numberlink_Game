package main.gui;

import main.controller.Controller;
import main.gamelogic.Board;
import main.utils.Generator;
import javax.swing.*;
import java.awt.*;

public class MainGUI {
    private static final Font TITLE_LABEL_FONT = new Font("Arial", Font.BOLD, 38);
    private static final Font SIZE_LABEL_FONT = new Font("Arial", Font.BOLD, 70);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 20);

    private static final int[] MAP_SIZES = {5,6, 7,8, 9};
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
        frame.setLocation(new java.awt.Point(400, 100));
        JPanel panel = new JPanel(new GridBagLayout());

        JLabel titleLabel = new JLabel("Choose map size".toUpperCase());
        titleLabel.setFont(TITLE_LABEL_FONT);
        GridBagConstraints gbc = createGridBagConstraints();

        panel.add(titleLabel, gbc);

        JLabel sizeLabel = new JLabel(String.valueOf(MAP_SIZES[currentIndex]));
        sizeLabel.setFont(SIZE_LABEL_FONT);
        arrangeGridBagConstraintsForSizeLabel(gbc);

        panel.add(sizeLabel, gbc);

        setUpButtons(panel, sizeLabel, gbc);
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        return gbc;
    }

    private void arrangeGridBagConstraintsForSizeLabel(GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
    }

    private void setUpButtons(JPanel panel, JLabel sizeLabel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(createDecreaseButton(sizeLabel), gbc);

        gbc.gridx = 2;
        panel.add(createIncreaseButton(sizeLabel), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(createConfirmButton(), gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

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

    private JButton createConfirmButton() {
        JButton chooseButton = new JButton("OK");
        chooseButton.setFont(BUTTON_FONT);
        chooseButton.setPreferredSize(new Dimension(100, 50));
        chooseButton.addActionListener(e -> createGameBoard());

        return chooseButton;
    }

    private void createGameBoard() {
        if (MAP_SIZES[currentIndex] != -1) {
            NumberlinkGUI gui = new NumberlinkGUI(MainGUI.this,
                    new Controller(new Board(MAP_SIZES[currentIndex], generator.generateBoard(MAP_SIZES[currentIndex]))));
            SwingUtilities.invokeLater(gui);
        } else {
            JOptionPane.showMessageDialog(frame, "ERROR");
        }
        frame.dispose();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void showFrame(boolean isVisible) {
        frame.setVisible(isVisible);
    }

}