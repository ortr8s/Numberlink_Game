package main.utils;

import java.util.Random;

public class Generator {

	static Random random = new Random();
	Solver solver = new Solver();

	static char[][] board5 = new char[5][5];
	static char[][] board7 = new char[7][7];
	static char[][] board9 = new char[9][9];

	public Generator() {
	}

	public Generator(int size) {
		// TODO Tymczasowe new Generator
		Generator generator = new Generator();
		generator.generate(size);
	}

	public static void main(String[] args) {
		// TODO usunac printy
		System.out.println(board5);
		System.out.println(board7);
		System.out.println(board9);
		// TODO Tymczasowe new Generator
		Generator generator = new Generator();
		generator.generate(9);
		System.out.println(board9[4][4]);

	}

	void generate(int size) {
		switch (size) {

		case 5:
			do {
				for (char[] row : board5) {
					for (int i = 0; i < row.length; i++) {
						//TODO
						row[i] = '?';
					}
				}
			} while (!solver.isSolvable(board5));
			System.out.println("Wygenerowano planszę o wielkości 5");
			break;

		case 7:
			do {
				for (char[] row : board7) {
					for (int i = 0; i < row.length; i++) {
						//TODO
						row[i] = '?';
					}
				}
			} while (!solver.isSolvable(board7));
			System.out.println("Wygenerowano planszę o wielkości 7");
			break;

		case 9:
			do {
				for (char[] row : board9) {
					for (int i = 0; i < row.length; i++) {
						//TODO
						row[i] = '?';
					}
				}
			} while (!solver.isSolvable(board7));
			System.out.println("Wygenerowano planszę o wielkości 9");
			break;

		default:
			System.out.println("Wprowadzono niepoprawną wielkość planszy");
		}
	}
}
