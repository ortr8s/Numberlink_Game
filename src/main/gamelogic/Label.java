package main.gamelogic;

import java.util.Objects;

public class Label {

    private final int nodeNumber;

    public Label(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public String getNodeNumber() {return Integer.toString(this.nodeNumber);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return nodeNumber == label.nodeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeNumber);
    }

    @Override
    public String toString() {
        return getNodeNumber();
    }
    public Path createNewPath(){
        return new Path(this);
    }
}
