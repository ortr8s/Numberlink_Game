package main.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private int id;
    private Pair startEndPair;

    private Unit[] units;
    private int size;

    public Path(Pair startEndPair) {
        this.id = startEndPair.getFirst().getValue();
        this.startEndPair = startEndPair;
        this.units = new Unit[100];
    }

    public Path() {
        this.units = new Unit[100];
    }

    public Pair getStartEndPair() {
        return startEndPair;
    }

    public void removeLast() {
        if (size > 0) {
            units[size] = null;
            size--;
        }
    }

    public Unit getThirdLast() {
        if (size > 2) {
            return units[size-3];
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
        return units[size-1];
    }
}
