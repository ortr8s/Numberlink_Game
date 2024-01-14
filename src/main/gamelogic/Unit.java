package main.gamelogic;

import java.util.Objects;
import java.util.StringJoiner;

public class Unit {
    private final int x, y;
    private final int value;
    private boolean isPartOfPath;
    private final Board board;
    public Unit(int x, int y, int value, Board board) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.board = board;
        this.isPartOfPath = false;
    }

    public int getValue() {
        return value;
    }
    public void setPartOfPath(boolean partOfPath){
        this.isPartOfPath = partOfPath;
    }
    public boolean isPartOfPath(){
        return isPartOfPath;
    }
    public int getX() {
        return x;
    }

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
