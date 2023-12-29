package main.utils;

import java.util.Random;

public class Generator {

	static Random random = new Random();
	Solver solver = new Solver();

	static char[][] board5 = new char[5][5];
	static char[][] board7 = new char[7][7];
	static char[][] board9 = new char[9][9];

	static char[] availableCharsCommon = { '-', '|', '0' };
	static char[] availableCharsCurves = { 'F', 'T', 'L', 'J' };

	static char[] availableCharsStartRow = { '|', '0', 'F', 'L' };

	// -
	static char[] availableCharsAfterMinus = { '-', '0', 'T', 'J' };
	// |
	static char[] availableCharsAfterPipe = { '|', '0', 'F', 'L' };
	// Number
	static char[] availableCharsAfterNumber = { '-', '|', '0', 'T', 'J' };

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
		generator.generate(5);

		for (int j = 0; j < board5.length; j++) {
			for (int i = 0; i < board5.length; i++) {
				System.out.println(board5[j][i]);
			}
		}

	}

	void generate(int size) {
		switch (size) {

		case 5:
			do {
				for (char[] row : board5) {
					for (int i = 1; i < row.length; i++) {
						row[0] = availableCharsStartRow[random.nextInt(availableCharsStartRow.length)];

						switch (row[i - 1]) {
						case 'F':
						case 'L':
						case '-':
							row[i] = availableCharsAfterMinus[random.nextInt(availableCharsAfterMinus.length)];
							break;
						case 'T':
						case 'J':
						case '|':
							row[i] = availableCharsAfterPipe[random.nextInt(availableCharsAfterPipe.length)];
							break;
						case '0':
							row[i] = availableCharsAfterNumber[random.nextInt(availableCharsAfterNumber.length)];
							break;

						}

					}
				}
			} while (!solver.isSolvable(board5));
			System.out.println("Wygenerowano planszę o wielkości 5");
			break;

		case 7:
			do {
				for (char[] row : board7) {
					for (int i = 0; i < row.length; i++) {
						// TODO
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
						// TODO
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
