package main.gui;

import main.controller.Controller;
import main.gamelogic.Moves;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class KeyBoardManager {
    private final Controller controller;
    private final NumberlinkGUI GUI;

    public KeyBoardManager(Controller controller, NumberlinkGUI GUI, JFrame frame) {
        this.controller = controller;
        this.GUI = GUI;

        addKeyBindings(frame);
    }

    /**
     * Adds key bindings for arrow keys to perform specific actions in the game.
     *
     * @param frame the JFrame object representing the game frame
     */
    private void addKeyBindings(JFrame frame) {
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        // Key binding for UP key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP_KEY");
        actionMap.put("UP_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null || controller.currentPath.getUnits()[0] == null) return;
                boolean win = controller.makeMove(Moves.UP);
                System.out.println("UP");
                executePlayerMoveAndCheckWin(win, frame);
            }
        });

        // Key binding for DOWN key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN_KEY");
        actionMap.put("DOWN_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null || controller.currentPath.getUnits()[0] == null) return;
                boolean win = controller.makeMove(Moves.DOWN);
                System.out.println("Down");
                executePlayerMoveAndCheckWin(win, frame);


            }
        });

        // Key binding for LEFT key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT_KEY");
        actionMap.put("LEFT_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null || controller.currentPath.getUnits()[0] == null) return;
                boolean win = controller.makeMove(Moves.LEFT);
                System.out.println("Left");
                executePlayerMoveAndCheckWin(win, frame);

            }
        });

        // Key binding for RIGHT key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT_KEY");
        actionMap.put("RIGHT_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null || controller.currentPath.getUnits()[0] == null) return;
                boolean win = controller.makeMove(Moves.RIGHT);
                System.out.println("Right");
                executePlayerMoveAndCheckWin(win, frame);
            }
        });
    }

    /**
     * Executes the player's move and checks for a win condition.
     *
     * @param win   a boolean indicating whether the player has won or not
     * @param frame the JFrame object representing the game frame
     */
    private void executePlayerMoveAndCheckWin(boolean win, JFrame frame) {
        System.out.println(controller.currentPath);
        GUI.repaintButton(controller.currentPath.getLastAdded());
        if (controller.currentPath.isCompleted()){
            GUI.darkenPath(controller.currentPath);
        }
        if (win) {
            float elapsedTime = (System.currentTimeMillis() - GUI.getStartTime()) / 1000.0f;
            String elapsedTimeStr = String.format("%.2f", elapsedTime);
            JOptionPane.showMessageDialog(frame, "You Won!\nElapsed time: " + elapsedTimeStr + " s",
                    "Game Over", JOptionPane.INFORMATION_MESSAGE);
            GUI.getMainGui().showFrame(true);
            frame.dispose();
        }
    }
}