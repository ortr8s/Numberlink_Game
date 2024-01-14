package main.gui;

import javax.swing.*;

public class StartPathButton extends JButton {
    private boolean isClicked;

    public StartPathButton() {
        isClicked = false;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void reset() {
        isClicked = false;
    }
    public void setClicked() { isClicked = true; }
}
