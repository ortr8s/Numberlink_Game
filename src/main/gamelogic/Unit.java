package main.gamelogic;

import java.util.Objects;
import java.util.StringJoiner;

public class Unit {
    private final int x, y;
    /**
     * Represents the value of a Unit object.
     * <p>
     * The value field is a private final variable that holds the value of a Unit object.
     * It represents the number or character associated with the Unit on the game board.
     * It is an integer value.
     */
    private final int value;
    /**
     * Represents whether a Unit is part of a path or not.
     * A Unit can be marked as part of a path by setting the value of this variable to true.
     * This variable is used in the Numberlink game to track the units that are part of the current path being formed.
     */
    private boolean isPartOfPath;
    /**
     * Represents a game board consisting of Unit objects.
     * A Unit represents a cell in the board.
     */
    private final Board board;
    /**
     * Represents a cell in the game board.
     * The cell has an x and y coordinate, a value, and is associated with a board.
     * The isPartOfPath attribute indicates whether the cell is part of a path.
     */
    public Unit(int x, int y, int value, Board board) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.board = board;
        this.isPartOfPath = false;
    }

    /**
     * Retrieves the value of the Unit.
     *
     * @return The value of the Unit.
     */
    public int getValue() {
        return value;
    }
    /**
     * Sets the flag indicating whether the Unit is part of a path.
     *
     * @param partOfPath {@code true} if the Unit is part of a path, {@code false} otherwise.
     */
    public void setPartOfPath(boolean partOfPath){
        this.isPartOfPath = partOfPath;
    }
    /**
     * Checks if the unit is part of a path.
     *
     * @return {@code true} if the unit is part of a path, {@code false} otherwise.
     */
    public boolean isPartOfPath(){
        return isPartOfPath;
    }
    /**
     * Retrieves the x-coordinate of the Unit object.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of the Unit.
     *
     * @return The y-coordinate of the Unit.
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Unit.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .add("val=" + value)
                .toString();
    }

    /**
     * Retrieves the neighboring unit in the specified direction.
     *
     * @param move the direction of the neighbor with respect to the unit
     * @return the neighboring unit in the specified direction
     */
    public Unit getNeighbour(Moves move) {
        return board.getNeighbor(this, move);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return x == unit.x && y == unit.y && value == unit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, value);
    }
}
