package main.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private int id;
    private final ArrayList<Unit> units;
    private Pair startEndPair;

    public Path(Pair startEndPair) {
        this.id = startEndPair.getFirst().getValue();
        this.startEndPair = startEndPair;
        this.units = new ArrayList<>(); // For auto resize, and O(1) time complexity when accessing the elements
    }

    public Path() {
        this.units = new ArrayList<>();
    }

    public Pair getStartEndPair() {
        return startEndPair;
    }

    public void removeLast() {
        if (!units.isEmpty()) {
            units.removeLast();
        }
    }

    public Unit getThirdLast() {
        if (units.size() > 2) {
            return units.get(units.size() - 3);
        }
        return null;
    }

    /**
     * Adds a Unit to the Path if it is not already present.
     *
     * @param unit the Unit to be added
     */
    public void addUnit(Unit unit) {
        if (!units.contains(unit)) {
            units.add(unit);
        }
    }

    public Unit getLastAdded() {
        return units.get(units.size() - 1);
    }
}
