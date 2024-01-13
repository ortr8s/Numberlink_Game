package main.gui;

import main.controller.Controller;
import main.gamelogic.Moves;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class KeyBoardManager {
    private Controller controller;
    private NumberlinkGUI GUI;

    public KeyBoardManager(Controller controller, NumberlinkGUI GUI, JFrame frame) {
        this.controller = controller;
        this.GUI = GUI;

        addKeyBindings(frame);
    }

    private void addKeyBindings(JFrame frame) {
        InputMap inputMap = frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = frame.getRootPane().getActionMap();

        // Key binding for UP key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP_KEY");
        actionMap.put("UP_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null) return;
                boolean win = controller.makeMove(Moves.UP);
                System.out.println("UP");
                System.out.println(controller.currentPath);
                GUI.repaintButton(controller.currentPath.getLastAdded());
                if (controller.currentPath.getCompleted()){
                    GUI.darkenPath(controller.currentPath);
                }
                if (win) {
                    JOptionPane.showMessageDialog(frame, "You Won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Key binding for DOWN key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN_KEY");
        actionMap.put("DOWN_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null) return;
                boolean win = controller.makeMove(Moves.DOWN);
                System.out.println("Down");
                System.out.println(controller.currentPath);
                GUI.repaintButton(controller.currentPath.getLastAdded());
                if (controller.currentPath.getCompleted()){
                    GUI.darkenPath(controller.currentPath);
                }
                if (win) {
                    JOptionPane.showMessageDialog(frame, "You Won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    GUI.getMainGui().showFrame(true);
                    frame.dispose();
                }


            }
        });

        // Key binding for LEFT key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LEFT_KEY");
        actionMap.put("LEFT_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null) return;
                boolean win = controller.makeMove(Moves.LEFT);
                System.out.println("Left");
                System.out.println(controller.currentPath);
                GUI.repaintButton(controller.currentPath.getLastAdded());
                if (controller.currentPath.getCompleted()){
                    GUI.darkenPath(controller.currentPath);
                }
                if (win) {
                    JOptionPane.showMessageDialog(frame, "You Won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    GUI.getMainGui().showFrame(true);
                    frame.dispose();
                }

            }
        });

        // Key binding for RIGHT key
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RIGHT_KEY");
        actionMap.put("RIGHT_KEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.currentPath == null) return;
                boolean win = controller.makeMove(Moves.RIGHT);
                System.out.println("Right");
                System.out.println(controller.currentPath);
                GUI.repaintButton(controller.currentPath.getLastAdded());
                if (controller.currentPath.getCompleted()){
                    GUI.darkenPath(controller.currentPath);
                }
                if (win) {
                    JOptionPane.showMessageDialog(frame, "You Won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    GUI.getMainGui().showFrame(true);
                    frame.dispose();
                }
            }
        });
    }
}