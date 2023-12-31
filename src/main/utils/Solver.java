package main.utils;

import main.gamelogic.Unit;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solver {
	//test manhattan distance
	public double calculateHeuristics(Unit current, Unit finish){
		double fScore = Math.abs(current.getX() - finish.getX()) + Math.abs(current.getY() - finish.getY());
		current.setF(fScore);
		return fScore;
	}

	public boolean solve(){
		//priority queue based on the heuristic value f
		PriorityQueue<Unit> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(Unit::getF));
		Unit startUnit = new Unit(2,8);
		Unit goalUnit = new Unit(1,1);
		startUnit.setG(0);
		startUnit.setH(calculateHeuristics(startUnit, goalUnit));
		startUnit.setF(startUnit.getG() + startUnit.getH());
		priorityQueue.add(startUnit);
		return false;
	}
	boolean isSolvable(char[][] tableToTest) {
		//TODO nie zawsze true
		return true;
	}
}
