package main.gamelogic;

import java.util.Comparator;
import java.util.StringJoiner;

public class Pair{
    private Unit first;
    private Unit last;
    private int distance;
    Pair(Unit begin, Unit end){
        this.first = begin;
        this.last = end;
        this.distance = calculateDistance();
    }
    public Unit getFirst() {
        return first;
    }
    public Unit getLast() {
        return last;
    }

    public int getDistance() {
        return distance;
    }
    public int calculateDistance(){
        return Math.abs(first.getX() - last.getX()) + Math.abs(first.getY() - last.getY());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
                .add("first=" + first)
                .add("last=" + last)
                .add("distance=" + distance)
                .toString();
    }
}
