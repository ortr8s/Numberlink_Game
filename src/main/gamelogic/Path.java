package main.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<Unit> units;

    private final Label label;


    public Path(){
        this.units = new ArrayList<>();
        this.label = null;
    }

    public Label getLabel() {
        return label;
    }
    public void removeLast(){
        if (!units.isEmpty()){
            units.removeLast();
        }
    }
    public Unit getThirdLast(){
        if(units.size() > 2){
            return units.get(units.size()-3);
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
