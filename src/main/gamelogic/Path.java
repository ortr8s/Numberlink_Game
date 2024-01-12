package main.gamelogic;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class Path {
    /**
     * Represents a pair of units in the Numberlink game that are to be connected.
     * A start-end pair consists of two units - the start unit and the end unit.
     */
    private Pair startEndPair;
    /**
     * Represents an array of Unit objects.
     * Provides methods for retrieving and manipulating the units.
     */
    private final Unit[] units;

    private int size;

    public int getSize() {
        return size;
    }

    /**
     * Represents a path in the Numberlink game that connects a pair of units.
     * A path consists of units in a specific order, starting from the start unit and ending with the end unit.
     *
     */
    public Path(Pair startEndPair) {
        this.startEndPair = startEndPair;
        this.units = new Unit[100];
    }

    public Path() {
        this.units = new Unit[100];
    }
    public Unit getUnit(int n){
        if(n < size){
            return units[n];
        }
        return null;
    }

    /**
     * Retrieves the start-end pair of units in the given Path.
     *
     * @return The start-end pair of units.
     */
    public Pair getStartEndPair() {
        return startEndPair;
    }

    /**
     * Removes the last Unit from the Path.
     * Decreases the size of the Path by one.
     */
    public void removeLast() {
        if (size > 0) {
            units[size] = null;
            size--;
        }
    }

    /**
     * Retrieves the third-to-last Unit from the Path.
     *
     * @return The third-to-last Unit in the Path, or null if the Path size is less than 3.
     */
    public Unit getThirdLast() {
        if (size > 2) {
            return units[size - 3];
        }
        return null;
    }

    /**
     * Adds a Unit to the Path if it is not already present.
     *
     * @param unit the Unit to be added
     */
    public void addUnit(Unit unit) {
        units[size] = unit;
        size++;
    }

    public Unit getLastAdded() {
        return units[size - 1];
    }

    /**
     * Determines if the path is completed.
     *
     * @return {@code true} if the path is completed, {@code false} otherwise.
     */
    public boolean isCompleted() {
        if (units[0] == null) return false;
        if (units[0].equals(startEndPair.getLast()) || units[0].equals(startEndPair.getFirst())) {
            if (getLastAdded().equals(startEndPair.getLast()) || getLastAdded().equals(startEndPair.getFirst())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Retrieves the value of the Units this Path consists of.
     *
     * @return The ID of the Path - value of the Units.
     */
    public int getID() {
        return startEndPair.getFirst().getValue();
    }
    /**
     * Advances the path to the neighbouring unit based on the given move.
     *
     * @param move The move to be made.
     * @return The neighbouring unit based on the given move.
     */
    public void advance(Moves move) {
        addUnit(getLastAdded().getNeighbour(move));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return size == path.size && Objects.equals(startEndPair, path.startEndPair) && Arrays.equals(units, path.units);
    }
    @Override
    public int hashCode() {
        int result = Objects.hash(startEndPair, size);
        result = 31 * result + Arrays.hashCode(units);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Path.class.getSimpleName() + "[", "]")
                .add("startEndPair=" + startEndPair)
                .add("units=" + Arrays.toString(units))
                .add("size=" + size)
                .toString();
    }
}
