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
	@SuppressWarnings("finally")
	boolean isSolvable(char[][] tableToTest) {
		boolean isnotWrong = true;
		int countingTillTheEnd = 0;
		for (char[] row : tableToTest) {
			for (int i = 0; i < row.length; i++) { 
				boolean occupied = false;
				if (row[i] == '0') {
					for (int condition = 0; condition < 5; condition++) {
						System.out.println("Robia sie warunki");
						switch (condition) {
						case 0:
							System.out.println("Robi sie 0");
							try {
								if (Generator.hasMandatoryBottomConnection(tableToTest[countingTillTheEnd - 1][i])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							}
						case 1:
							System.out.println("Robi sie 1");
							try {
								if (Generator.hasMandatoryConnectionUP(tableToTest[countingTillTheEnd + 1][i])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 2:
							System.out.println("Robi sie 2");
							try {
								if (Generator.hasMandatoryConnectionLeft(tableToTest[countingTillTheEnd][i + 1])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 3:
							System.out.println("Robi sie 3");
							try {
								if (Generator.hasMandatoryConnectionRight(tableToTest[countingTillTheEnd][i - 1])) {
									if (occupied == true) {
										isnotWrong = false;
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 4:
							System.out.println("Robi sie 4");
							if (occupied == false) {
								isnotWrong = false;
							}
						}
					}
				}
			}
			countingTillTheEnd++;
		}
		if (!isnotWrong) {
			return false;
		} else {
			return true;
		}
	}
}
