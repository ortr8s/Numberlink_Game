package main.utils;

import java.util.Random;

public class Generator {

	static Random random = new Random();
	Solver solver = new Solver();

	static char[][] board5 = new char[5][5];
	static char[][] board7 = new char[7][7];
	static char[][] board9 = new char[9][9];

	// Zestawy możliwych charów w tablicy oraz co może być po czym

	static char[] availableCharsCommon = { '-', '|', '0' };
	static char[] availableCharsCurves = { 'F', 'T', 'L', 'J' };

	// Co można wstawić na początek wiersza?
	static char[] availableCharsStartRow = { '|', '0', 'F', 'L' };

	// Co może być dalej?
	// -
	static char[] availableCharsAfterMinus = { '-', '0', 'T', 'J' };
	// |
	static char[] availableCharsAfterPipe = { '|', '0', 'F', 'L' };
	// Number
	static char[] availableCharsAfterNumber = { '-', '|', '0', 'T', 'J' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static char[] availableCharsAfterF = { '-', '0', 'J' };
	// T
	static char[] availableCharsAfterT = { '|', '0', 'L' };
	// L
	static char[] availableCharsAfterL = { '-', '0', 'T' };
	// J
	static char[] availableCharsAfterJ = { '|', '0', 'F' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static char[] availableCharsEndRow = { '|', '0', 'T', 'J' };
	static char[] availableCharsEndRowAfterMinus = { '0', 'T', 'J' };
	static char[] availableCharsEndRowAfterPipe = { '|', '0', 'T', 'J' };
	static char[] availableCharsEndRowAfterNumber = { '|', '0', 'T', 'J' };
	static char[] availableCharsEndRowAfterF = { '0', 'J' };
	static char[] availableCharsEndRowAfterT = { '|', '0' };
	static char[] availableCharsEndRowAfterL = { '0', 'T' };
	static char[] availableCharsEndRowAfterJ = { '|', '0' };

	// PIERWSZY WIERSZ
	// Dla pierwszego wiersza nie może być |, J, L

	// Co można wstawić na początek wiersza?
	static char[] availableChars1stStartRow = { '0', 'F' };

	// Co może być dalej?
	// -
	static char[] availableChars1stAfterMinus = { '-', '0', 'T' };
	// |
	static char[] availableChars1stAfterPipe = { '0', 'F' };
	// Number
	static char[] availableChars1stAfterNumber = { '-', '0', 'T' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static char[] availableChars1stAfterF = { '-', '0' };
	// T
	static char[] availableChars1stAfterT = { '0' };
	// L
	static char[] availableChars1stAfterL = { '-', '0', 'T' };
	// J
	static char[] availableChars1stAfterJ = { '0', 'F' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static char[] availableChars1stEndRow = { '0', 'T' };
	static char[] availableChars1stEndRowAfterMinus = { '0', 'T' };
	static char[] availableChars1stEndRowAfterPipe = { '0', 'T' };
	static char[] availableChars1stEndRowAfterNumber = { '0', 'T' };
	static char[] availableChars1stEndRowAfterF = { '0', 'J' };
	static char[] availableChars1stEndRowAfterT = { '0' };
	static char[] availableChars1stEndRowAfterL = { '0', 'T' };
	static char[] availableChars1stEndRowAfterJ = { '0' };
	
	// TODO OSTATNI WIERSZ

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
			System.out.println(" ");
		}

	}

	void generate(int size) {
		switch (size) {

		case 5:
			do {
				boolean is1stRowDone = false;
				for (char[] row : board5) {
					
					if (!is1stRowDone) {
						row[0] = availableChars1stStartRow[random.nextInt(availableChars1stStartRow.length)];

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								row[i] = availableChars1stAfterMinus[random
										.nextInt(availableChars1stAfterMinus.length)];
								break;
							case '|':
								row[i] = availableChars1stAfterPipe[random.nextInt(availableChars1stAfterPipe.length)];
								break;
							case '0':
								row[i] = availableChars1stAfterNumber[random
										.nextInt(availableChars1stAfterNumber.length)];
								break;
							case 'F':
								row[i] = availableChars1stAfterF[random.nextInt(availableChars1stAfterF.length)];
								break;
							case 'T':
								row[i] = availableChars1stAfterT[random.nextInt(availableChars1stAfterT.length)];
								break;
							case 'L':
								row[i] = availableChars1stAfterL[random.nextInt(availableChars1stAfterL.length)];
								break;
							case 'J':
								row[i] = availableChars1stAfterJ[random.nextInt(availableChars1stAfterJ.length)];
								break;
							}
						}
						row[row.length - 1] = availableCharsEndRow[random.nextInt(availableCharsEndRow.length)];
						is1stRowDone = true;
						continue;
						
					} else {

						row[0] = availableCharsStartRow[random.nextInt(availableCharsStartRow.length)];

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								row[i] = availableCharsAfterMinus[random.nextInt(availableCharsAfterMinus.length)];
								break;
							case '|':
								row[i] = availableCharsAfterPipe[random.nextInt(availableCharsAfterPipe.length)];
								break;
							case '0':
								row[i] = availableCharsAfterNumber[random.nextInt(availableCharsAfterNumber.length)];
								break;
							case 'F':
								row[i] = availableCharsAfterF[random.nextInt(availableCharsAfterF.length)];
								break;
							case 'T':
								row[i] = availableCharsAfterT[random.nextInt(availableCharsAfterT.length)];
								break;
							case 'L':
								row[i] = availableCharsAfterL[random.nextInt(availableCharsAfterL.length)];
								break;
							case 'J':
								row[i] = availableCharsAfterJ[random.nextInt(availableCharsAfterJ.length)];
								break;
							}
						}

						row[row.length - 1] = availableCharsEndRow[random.nextInt(availableCharsEndRow.length)];
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
