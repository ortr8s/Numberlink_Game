package main.gamelogic;

import java.util.StringJoiner;

/**
 * Represents a pair of numbers to connect in the Numberlink game.
 * A pair consists of two units (first and last) and the distance between them.
 */
public class Pair{
    private final Unit first; // The first unit in the pair
    private final Unit last; // The last unit in the pair
    private final int distance; // The Manhattan distance between the first and last units

    /**
     * Constructor to create a pair of units.
     *
     * @param begin The first unit of the pair.
     * @param end The last unit of the pair.
     */
    Pair(Unit begin, Unit end){
        this.first = begin;
        this.last = end;
        this.distance = calculateDistance();
    }
    /**
     * Gets the first unit in the pair.
     *
     * @return The first unit.
     */
    public Unit getFirst() {
        return first;
    }

    /**
     * Gets the last unit in the pair.
     *
     * @return The last unit.
     */
    public Unit getLast() {
        return last;
    }

    /**
     * Gets the distance between the first and last units.
     *
     * @return The Manhattan distance.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Calculates the Manhattan distance between the first and last units.
     * Manhattan distance is the sum of the absolute differences of their coordinates.
     *
     * @return The calculated Manhattan distance.
     */
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
