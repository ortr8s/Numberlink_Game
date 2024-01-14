package main.gui;

import javax.swing.*;

public class StartPathButton extends JButton {
    /**
     * A private boolean variable that represents whether a button is clicked or not.
     * This variable is used in the StartPathButton class to keep track of the button's clicked state.
     * <p>
     * Initially, the value of isClicked is set to false. When the button is clicked, the value
     * of isClicked is set to true. To reset the clicked state, the reset() method can be used
     * to set the value of isClicked back to false.
     *
     * @see StartPathButton
     */
    private boolean isClicked;

    /**
     * Represents a custom button used for starting a path in a Numberlink game.
     * Extends the JButton class.
     * <p>
     * The button is initially not clicked.
     */
    public StartPathButton() {
        isClicked = false;
    }

    /**
     * Checks if the button has been clicked.
     *
     * @return true if the button has been clicked, false otherwise.
     */
    public boolean isClicked() {
        return isClicked;
    }

    /**
     * Resets the state of the button by setting the isClicked flag to false.
     * This method is used to reset the button after it has been clicked.
     */
    public void reset() {
        isClicked = false;
    }
    /**
     * Sets the "clicked" flag to true.
     * This method is used to mark a button as clicked.
     */
    public void setClicked() { isClicked = true; }
}
