package main.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Unit> units;

    private final Label label;


    public Path(Label label) {
        this.units = new ArrayList<>(); // For auto resize, and O(1) time complexity when accessing the elements
        this.label = label;
    }

    public Label getLabel() {
        return label;
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
