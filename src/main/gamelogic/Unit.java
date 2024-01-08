package main.gamelogic;

import java.util.Objects;
import java.util.StringJoiner;

public class Unit {
    private final int x,y;
    private int value;
    private boolean isPartOfPath;

    public Unit(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.isPartOfPath = false;
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isPartOfPath() {
        return isPartOfPath;
    }
    public int calculateDistanceFromCenter(int size){
        int center = (size - 1)  / 2;
        return Math.abs(x - center) + Math.abs(y - center);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit unit)) return false;
        return x == unit.x && y == unit.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Unit.class.getSimpleName() + "[", "]")
                .add("x=" + x)
                .add("y=" + y)
                .add("val=" + value)
                .toString();
    }
}
