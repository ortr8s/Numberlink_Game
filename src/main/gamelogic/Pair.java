package main.gamelogic;

public class Pair {
    Unit first;
    Unit last;
    Pair(Unit begin, Unit end){
        this.first = begin;
        this.last = end;
    }

    public Unit getFirst() {
        return first;
    }

    public Unit getLast() {
        return last;
    }
}
