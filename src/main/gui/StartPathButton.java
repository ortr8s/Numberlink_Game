package main.gui;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartPathButton extends JButton {
    private boolean wasClicked = false;

    public StartPathButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wasClicked = true;
            }
        });
    }

    public boolean wasClicked() {
        return wasClicked;
    }

    public void reset() {
        wasClicked = false;
    }
}
