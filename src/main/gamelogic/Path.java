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
    private boolean isCompleted;


    /**
     * Represents a path in the Numberlink game that connects a pair of units.
     * A path consists of units in a specific order, starting from the start unit and ending with the end unit.
     *
     */
    public Path(Pair startEndPair) {
        this.startEndPair = startEndPair;
        this.units = new Unit[100];
        this.isCompleted = false;
    }

    public Path() {
        this.units = new Unit[100];
    }

    public int getSize() {
        return size;
    }
    public boolean getCompleted(){
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
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
     * Retrieves the value of the Units this Path consists of.
     *
     * @return The ID of the Path - value of the Units.
     */
    public int getID() {
        return startEndPair.getFirst().getValue();
    }

    public Unit[] getUnits() {
        return units;
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
        unit.setPartOfPath(true);
    }

    public Unit getLastAdded() {
        return units[size - 1];
    }
    /**
     * Advances the path to the neighbouring unit based on the given move.
     *
     * @param move The move to be made.
     */
    public void advance(Moves move) {
        addUnit(getLastAdded().getNeighbour(move));
    }
    public void clearPath() {
        for (int i = 0; i < size; i++) {
            units[i].setPartOfPath(false);
            units[i] = null;
            isCompleted = false;
        }
        size = 0;
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
