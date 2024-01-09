package main.gamelogic;

import java.util.Objects;
import java.util.StringJoiner;

public class Unit {
    private final int x, y;
    private int value;
    private final Board board;
    public Unit(int x, int y, int value, Board board) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.board = board;
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int calculateDistanceFromCenter(int size) {
        int center = (size - 1) / 2;
        return Math.abs(x - center) + Math.abs(y - center);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Unit.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .add("val=" + value)
                .toString();
    }

    public void setValue(int value) {
        this.value = value;
    }
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
