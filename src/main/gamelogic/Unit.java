package main.gamelogic;

import java.util.Objects;
import java.util.StringJoiner;

public class Unit {
    private final int x,y;
    private double g,h,f; //used for a* algorithm
    private int value;
    private Unit parent;
    private boolean isPartOfPath;

    public Unit(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.g = Double.MAX_VALUE;
        this.h = 0;
        this.f = Double.MAX_VALUE;
        this.parent = null;
        this.isPartOfPath = false;
        this.value = value;
    }
    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value = value;
    }

    public Unit getParent() {
        return parent;
    }

    public double getG() {
        return g;
    }

    public double getH() {
        return h;
    }
    public double getF() {
        return f;
    }
    public int getX() {
        return x;
    }
    public void setParent(Unit unit){
        this.parent = unit;
    }
    public int getY() {
        return y;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public boolean isPartOfPath() {
        return isPartOfPath;
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
