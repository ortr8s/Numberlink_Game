package main.utils;

import java.util.Random;

public class Generator {

	static Random random = new Random();
	Solver solver = new Solver();

	static char[][] board5 = new char[5][5];
	static char[][] board7 = new char[7][7];
	static char[][] board9 = new char[9][9];

	// Zestawy możliwych charów w tablicy oraz co może być po czym

	static final char[] availableCharsNumber = { '0' };
	static final char[] availableCharsCommon = { '-', '|' };
	static final char[] availableCharsCurves = { 'F', 'T', 'L', 'J' };

	// Co można wstawić na początek wiersza?
	static final char[] availableCharsStartRow = { '|', '0', 'F', 'L' };

	// Co może być dalej?
	// -
	static final char[] availableCharsAfterMinus = { '-', '0', 'T', 'J' };
	// |
	static final char[] availableCharsAfterPipe = { '|', '0', 'F', 'L' };
	// Number
	static final char[] availableCharsAfterNumber = { '-', '|', '0', 'T', 'J', 'F', 'L' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] availableCharsAfterF = { '-', '0', 'J' };
	// T
	static final char[] availableCharsAfterT = { '|', '0', 'L' };
	// L
	static final char[] availableCharsAfterL = { '-', '0', 'T' };
	// J
	static final char[] availableCharsAfterJ = { '|', '0', 'F' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsEndRow = { '|', '0', 'T', 'J' };
	static final char[] availableCharsEndRowAfterMinus = { '0', 'T', 'J' };
	static final char[] availableCharsEndRowAfterPipe = { '|', '0', 'T' };
	static final char[] availableCharsEndRowAfterNumber = { '|', '0', 'T', 'J' };
	static final char[] availableCharsEndRowAfterF = { '0', 'J' };
	static final char[] availableCharsEndRowAfterT = { '|', '0' };
	static final char[] availableCharsEndRowAfterL = { '0', 'T' };
	static final char[] availableCharsEndRowAfterJ = { '|', '0' };

	// PIERWSZY WIERSZ
	// Dla pierwszego wiersza nie może być |, J, L

	// Co można wstawić na początek wiersza?
	static final char[] availableChars1stStartRow = { '0', 'F' };

	// Co może być dalej?
	// -
	static final char[] availableChars1stAfterMinus = { '-', '0', 'T' };
	// |
	static final char[] availableChars1stAfterPipe = { '0', 'F' };
	// Number
	static final char[] availableChars1stAfterNumber = { '-', '0', 'T' , 'F'};

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] availableChars1stAfterF = { '-', '0' };
	// T
	static final char[] availableChars1stAfterT = { '0' };
	// L
	static final char[] availableChars1stAfterL = { '-', '0', 'T' };
	// J
	static final char[] availableChars1stAfterJ = { '0', 'F' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableChars1stEndRow = { '0', 'T' };
	static final char[] availableChars1stEndRowAfterMinus = { '0', 'T' };
	static final char[] availableChars1stEndRowAfterPipe = { '0', 'T' };
	static final char[] availableChars1stEndRowAfterNumber = { '0', 'T' };
	static final char[] availableChars1stEndRowAfterF = { '0' };
	static final char[] availableChars1stEndRowAfterT = { '0' };
	static final char[] availableChars1stEndRowAfterL = { '0', 'T' };
	static final char[] availableChars1stEndRowAfterJ = { '0' };

	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T

	// Co można wstawić na początek wiersza?
	static final char[] availableCharsLastStartRow = { '0', 'L' };

	// Co może być dalej?
	// -
	static final char[] availableCharsLastAfterMinus = { '-', '0', 'J' };
	// |
	static final char[] availableCharsLastAfterPipe = { '0', 'L' };
	// Number
	static final char[] availableCharsLastAfterNumber = { '-', '0', 'J', 'L' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] availableCharsLastAfterF = { '-', '0', 'J' };
	// T
	static final char[] availableCharsLastAfterT = { '0', 'L' };
	// L
	static final char[] availableCharsLastAfterL = { '-', '0' };
	// J
	static final char[] availableCharsLastAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsLastEndRow = { '0', 'J' };
	static final char[] availableCharsLastEndRowAfterMinus = { '0', 'J' };
	static final char[] availableCharsLastEndRowAfterPipe = { '0' };
	static final char[] availableCharsLastEndRowAfterNumber = { '0', 'J' };
	static final char[] availableCharsLastEndRowAfterF = { '0', 'J' };
	static final char[] availableCharsLastEndRowAfterT = { '0' };
	static final char[] availableCharsLastEndRowAfterL = { '0' };
	static final char[] availableCharsLastEndRowAfterJ = { '0' };

	
	
	static boolean hasMandatoryBottomConnection(char a) {
		if ((a == '|') || (a == 'F') || (a == 'T')) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean hasNoBottomConnection(char a) {
		if ((a == '-') || (a == 'L') || (a == 'J')) {
			return true;
		} else {
			return false;
		}
	}
	
	// SPRAWDZANIE CO JEST POWYŻEJ
	
	// OBOWIĄZKOWE POŁĄCZENIE Z TYM CO POWYŻEJ
	// Co można wstawić na początek wiersza?
	static final char[] availableCharsConnectingUPStartRow = { '|', '0', 'L' };

	// Co może być dalej?
	// -
	static final char[] availableCharsConnectingUPAfterMinus = { '0', 'J' };
	// |
	static final char[] availableCharsConnectingUPAfterPipe = { '|', '0', 'L' };
	// Number
	static final char[] availableCharsConnectingUPAfterNumber = { '|', '0', 'L', 'J' };

	// F
	static final char[] availableCharsConnectingUPAfterF = { '0', 'J' };
	// T
	static final char[] availableCharsConnectingUPAfterT = { '|', '0', 'L' };
	// L
	static final char[] availableCharsConnectingUPAfterL = { '0' };
	// J
	static final char[] availableCharsConnectingUPAfterJ = { '|', '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsConnectingUPEndRow = { '|', '0', 'J' };
	static final char[] availableCharsConnectingUPEndRowAfterMinus = { '0', 'J' };
	static final char[] availableCharsConnectingUPEndRowAfterPipe = { '|', '0' };
	static final char[] availableCharsConnectingUPEndRowAfterNumber = { '|', '0', 'J' };
	static final char[] availableCharsConnectingUPEndRowAfterF = { '0', 'J' };
	static final char[] availableCharsConnectingUPEndRowAfterT = { '|', '0' };
	static final char[] availableCharsConnectingUPEndRowAfterL = { '0' };
	static final char[] availableCharsConnectingUPEndRowAfterJ = { '|', '0' };
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	static final char[] availableCharsLastConnectingUPStartRow = { '0', 'L' };

	// Co może być dalej?
	// -
	static final char[] availableCharsLastConnectingUPAfterMinus = { '0', 'J' };
	// |
	static final char[] availableCharsLastConnectingUPAfterPipe = { '0', 'L' };
	// Number
	static final char[] availableCharsLastConnectingUPAfterNumber = { '|', '0', 'L', 'J' };

	// F
	static final char[] availableCharsLastConnectingUPAfterF = { '0', 'J' };
	// T
	static final char[] availableCharsLastConnectingUPAfterT = { '0', 'L' };
	// L
	static final char[] availableCharsLastConnectingUPAfterL = { '0' };
	// J
	static final char[] availableCharsLastConnectingUPAfterJ = { '|', '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsLastConnectingUPEndRow = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterMinus = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterPipe = { '0' };
	static final char[] availableCharsLastConnectingUPEndRowAfterNumber = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterF = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterT = { '0' };
	static final char[] availableCharsLastConnectingUPEndRowAfterL = { '0' };
	static final char[] availableCharsLastConnectingUPEndRowAfterJ = { '0' };

	// OBOWIĄZKOWY BRAK POŁĄCZENIA Z TYM CO POWYŻEJ
	
	// Co można wstawić na początek wiersza?
	static final char[] availableCharsNotConnectingUPStartRow = availableChars1stStartRow;

	// Co może być dalej?
	// -
	static final char[] availableCharsNotConnectingUPAfterMinus = availableChars1stAfterMinus;
	// |
	static final char[] availableCharsNotConnectingUPAfterPipe = availableChars1stAfterPipe;
	// Number
	static final char[] availableCharsNotConnectingUPAfterNumber = availableChars1stAfterNumber;
		// F
	static final char[] availableCharsNotConnectingUPAfterF = availableChars1stAfterF;
	// T
	static final char[] availableCharsNotConnectingUPAfterT = availableChars1stAfterT;
	// L
	static final char[] availableCharsNotConnectingUPAfterL = availableChars1stAfterL;
	// J
	static final char[] availableCharsNotConnectingUPAfterJ = availableChars1stAfterJ;
		// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsNotConnectingUPEndRow = availableChars1stEndRow;
	static final char[] availableCharsNotConnectingUPEndRowAfterMinus = availableChars1stEndRowAfterMinus;
	static final char[] availableCharsNotConnectingUPEndRowAfterPipe = availableChars1stEndRowAfterPipe;
	static final char[] availableCharsNotConnectingUPEndRowAfterNumber = availableChars1stEndRowAfterNumber;
	static final char[] availableCharsNotConnectingUPEndRowAfterF = availableChars1stEndRowAfterF;
	static final char[] availableCharsNotConnectingUPEndRowAfterT = availableChars1stEndRowAfterT;
	static final char[] availableCharsNotConnectingUPEndRowAfterL = availableChars1stEndRowAfterL;
	static final char[] availableCharsNotConnectingUPEndRowAfterJ = availableChars1stEndRowAfterJ;
	
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	
	// Co można wstawić na początek wiersza?
	static final char[] availableCharsLastNotConnectingUPStartRow = { '0' };

	// Co może być dalej?
	// -
	static final char[] availableCharsLastNotConnectingUPAfterMinus = { '-', '0' };
	// |
	static final char[] availableCharsLastNotConnectingUPAfterPipe = { '0' };
	// Number
	static final char[] availableCharsLastNotConnectingUPAfterNumber = { '-', '0' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] availableCharsLastNotConnectingUPAfterF = { '-', '0' };
	// T
	static final char[] availableCharsLastNotConnectingUPAfterT = { '0' };
	// L
	static final char[] availableCharsLastNotConnectingUPAfterL = { '-', '0' };
	// J
	static final char[] availableCharsLastNotConnectingUPAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsLastNotConnectingUPEndRow = { '0' };
	static final char[] availableCharsLastNotConnectingUPEndRowAfterMinus = { '0' };
	static final char[] availableCharsLastNotConnectingUPEndRowAfterPipe = { '0' };
	static final char[] availableCharsLastNotConnectingUPEndRowAfterNumber = { '0' };
	static final char[] availableCharsLastNotConnectingUPEndRowAfterF = { '0' };
	static final char[] availableCharsLastNotConnectingUPEndRowAfterT = { '0' };
	static final char[] availableCharsLastNotConnectingUPEndRowAfterL = { '0' };
	static final char[] availableCharsLastNotConnectingUPEndRowAfterJ = { '0' };
	
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
				int countingTillTheEnd = 0;

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
						{
							switch (row[row.length - 2]) {
							case '-':
								row[row.length - 1] = availableChars1stEndRowAfterMinus[random.nextInt(availableChars1stEndRowAfterMinus.length)];
								break;
							case '|':
								row[row.length - 1] = availableChars1stEndRowAfterPipe[random.nextInt(availableChars1stEndRowAfterPipe.length)];
								break;
							case '0':
								row[row.length - 1] = availableChars1stEndRowAfterNumber[random.nextInt(availableChars1stEndRowAfterNumber.length)];
								break;
							case 'F':
								row[row.length - 1] = availableChars1stEndRowAfterF[random.nextInt(availableChars1stEndRowAfterF.length)];
								break;
							case 'T':
								row[row.length - 1] = availableChars1stEndRowAfterT[random.nextInt(availableChars1stEndRowAfterT.length)];
								break;
							case 'L':
								row[row.length - 1] = availableChars1stEndRowAfterL[random.nextInt(availableChars1stEndRowAfterL.length)];
								break;
							case 'J':
								row[row.length - 1] = availableChars1stEndRowAfterJ[random.nextInt(availableChars1stEndRowAfterJ.length)];
								break;
							}
						}
						is1stRowDone = true;
						countingTillTheEnd++;
						continue;

					} else if (countingTillTheEnd < board5.length - 1) {

						if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][0])) {
							row[0] = availableCharsConnectingUPStartRow[random.nextInt(availableCharsConnectingUPStartRow.length)];
						}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][0])) {
							row[0] = availableCharsNotConnectingUPStartRow[random.nextInt(availableCharsNotConnectingUPStartRow.length)];
						}; if (board5[countingTillTheEnd - 1][0] == 0) {
							row[0] = row[0] = availableCharsStartRow[random.nextInt(availableCharsStartRow.length)];
						}

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsConnectingUPAfterMinus[random.nextInt(availableCharsConnectingUPAfterMinus.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsNotConnectingUPAfterMinus[random.nextInt(availableCharsNotConnectingUPAfterMinus.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsAfterMinus[random.nextInt(availableCharsAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsConnectingUPAfterPipe[random.nextInt(availableCharsConnectingUPAfterPipe.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsNotConnectingUPAfterPipe[random.nextInt(availableCharsNotConnectingUPAfterPipe.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsAfterPipe[random.nextInt(availableCharsAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsConnectingUPAfterNumber[random.nextInt(availableCharsConnectingUPAfterNumber.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsNotConnectingUPAfterNumber[random.nextInt(availableCharsNotConnectingUPAfterNumber.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsAfterNumber[random.nextInt(availableCharsAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsConnectingUPAfterF[random.nextInt(availableCharsConnectingUPAfterF.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsNotConnectingUPAfterF[random.nextInt(availableCharsNotConnectingUPAfterF.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsAfterF[random.nextInt(availableCharsAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsConnectingUPAfterT[random.nextInt(availableCharsConnectingUPAfterT.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsNotConnectingUPAfterT[random.nextInt(availableCharsNotConnectingUPAfterT.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsAfterT[random.nextInt(availableCharsAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsConnectingUPAfterL[random.nextInt(availableCharsConnectingUPAfterL.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsNotConnectingUPAfterL[random.nextInt(availableCharsNotConnectingUPAfterL.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsAfterL[random.nextInt(availableCharsAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsConnectingUPAfterJ[random.nextInt(availableCharsConnectingUPAfterJ.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsNotConnectingUPAfterJ[random.nextInt(availableCharsNotConnectingUPAfterJ.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsAfterJ[random.nextInt(availableCharsAfterJ.length)];
								}
								break;
							}
						}
						{
							switch (row[row.length - 2]) {
							case '-':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsConnectingUPEndRowAfterMinus[random.nextInt(availableCharsConnectingUPEndRowAfterMinus.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsNotConnectingUPEndRowAfterMinus[random.nextInt(availableCharsNotConnectingUPEndRowAfterMinus.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsEndRowAfterMinus[random.nextInt(availableCharsEndRowAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsConnectingUPEndRowAfterPipe[random.nextInt(availableCharsConnectingUPEndRowAfterPipe.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsNotConnectingUPEndRowAfterPipe[random.nextInt(availableCharsNotConnectingUPEndRowAfterPipe.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsEndRowAfterPipe[random.nextInt(availableCharsEndRowAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsConnectingUPEndRowAfterNumber[random.nextInt(availableCharsConnectingUPEndRowAfterNumber.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsNotConnectingUPEndRowAfterNumber[random.nextInt(availableCharsNotConnectingUPEndRowAfterNumber.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsEndRowAfterNumber[random.nextInt(availableCharsEndRowAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsConnectingUPEndRowAfterF[random.nextInt(availableCharsConnectingUPEndRowAfterF.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsNotConnectingUPEndRowAfterF[random.nextInt(availableCharsNotConnectingUPEndRowAfterF.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsEndRowAfterF[random.nextInt(availableCharsEndRowAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsConnectingUPEndRowAfterT[random.nextInt(availableCharsConnectingUPEndRowAfterT.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsNotConnectingUPEndRowAfterT[random.nextInt(availableCharsNotConnectingUPEndRowAfterT.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsEndRowAfterT[random.nextInt(availableCharsEndRowAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsConnectingUPEndRowAfterL[random.nextInt(availableCharsConnectingUPEndRowAfterL.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsNotConnectingUPEndRowAfterL[random.nextInt(availableCharsNotConnectingUPEndRowAfterL.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsEndRowAfterL[random.nextInt(availableCharsEndRowAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsConnectingUPEndRowAfterJ[random.nextInt(availableCharsConnectingUPEndRowAfterJ.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsNotConnectingUPEndRowAfterJ[random.nextInt(availableCharsNotConnectingUPEndRowAfterJ.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsEndRowAfterJ[random.nextInt(availableCharsEndRowAfterJ.length)];
								}
								break;
							}
						}

						countingTillTheEnd++;

					} else {
						if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][0])) {
							row[0] = availableCharsLastConnectingUPStartRow[random.nextInt(availableCharsLastConnectingUPStartRow.length)];
						}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][0])) {
							row[0] = availableCharsLastNotConnectingUPStartRow[random.nextInt(availableCharsLastNotConnectingUPStartRow.length)];
						}; if (board5[countingTillTheEnd - 1][0] == 0) {
							row[0] = availableCharsLastStartRow[random.nextInt(availableCharsLastStartRow.length)];
						}

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastConnectingUPAfterMinus[random.nextInt(availableCharsLastConnectingUPAfterMinus.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastNotConnectingUPAfterMinus[random.nextInt(availableCharsLastNotConnectingUPAfterMinus.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsLastAfterMinus[random.nextInt(availableCharsLastAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastConnectingUPAfterPipe[random.nextInt(availableCharsLastConnectingUPAfterPipe.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastNotConnectingUPAfterPipe[random.nextInt(availableCharsLastNotConnectingUPAfterPipe.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsLastAfterPipe[random.nextInt(availableCharsLastAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastConnectingUPAfterNumber[random.nextInt(availableCharsLastConnectingUPAfterNumber.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastNotConnectingUPAfterNumber[random.nextInt(availableCharsLastNotConnectingUPAfterNumber.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsLastAfterNumber[random.nextInt(availableCharsLastAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastConnectingUPAfterF[random.nextInt(availableCharsLastConnectingUPAfterF.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastNotConnectingUPAfterF[random.nextInt(availableCharsLastNotConnectingUPAfterF.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsLastAfterF[random.nextInt(availableCharsLastAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastConnectingUPAfterT[random.nextInt(availableCharsLastConnectingUPAfterT.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastNotConnectingUPAfterT[random.nextInt(availableCharsLastNotConnectingUPAfterT.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsLastAfterT[random.nextInt(availableCharsLastAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastConnectingUPAfterL[random.nextInt(availableCharsLastConnectingUPAfterL.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastNotConnectingUPAfterL[random.nextInt(availableCharsLastNotConnectingUPAfterL.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsLastAfterL[random.nextInt(availableCharsLastAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastConnectingUPAfterJ[random.nextInt(availableCharsLastConnectingUPAfterJ.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][i])) {
									row[i] = availableCharsLastNotConnectingUPAfterJ[random.nextInt(availableCharsLastNotConnectingUPAfterJ.length)];
								}; if (board5[countingTillTheEnd - 1][i] == 0) {
									row[i] = availableCharsLastAfterJ[random.nextInt(availableCharsLastAfterJ.length)];
								}
								break;
							}
						}
						{
							switch (row[row.length - 2]) {
							case '-':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastConnectingUPEndRowAfterMinus[random.nextInt(availableCharsLastConnectingUPEndRowAfterMinus.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastNotConnectingUPEndRowAfterMinus[random.nextInt(availableCharsLastNotConnectingUPEndRowAfterMinus.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsLastEndRowAfterMinus[random.nextInt(availableCharsLastEndRowAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastConnectingUPEndRowAfterPipe[random.nextInt(availableCharsLastConnectingUPEndRowAfterPipe.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastNotConnectingUPEndRowAfterPipe[random.nextInt(availableCharsLastNotConnectingUPEndRowAfterPipe.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsLastEndRowAfterPipe[random.nextInt(availableCharsLastEndRowAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastConnectingUPEndRowAfterNumber[random.nextInt(availableCharsLastConnectingUPEndRowAfterNumber.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastNotConnectingUPEndRowAfterNumber[random.nextInt(availableCharsLastNotConnectingUPEndRowAfterNumber.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsLastEndRowAfterNumber[random.nextInt(availableCharsLastEndRowAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastConnectingUPEndRowAfterF[random.nextInt(availableCharsLastConnectingUPEndRowAfterF.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastNotConnectingUPEndRowAfterF[random.nextInt(availableCharsLastNotConnectingUPEndRowAfterF.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsLastEndRowAfterF[random.nextInt(availableCharsLastEndRowAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastConnectingUPEndRowAfterT[random.nextInt(availableCharsLastConnectingUPEndRowAfterT.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastNotConnectingUPEndRowAfterT[random.nextInt(availableCharsLastNotConnectingUPEndRowAfterT.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsLastEndRowAfterT[random.nextInt(availableCharsLastEndRowAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastConnectingUPEndRowAfterL[random.nextInt(availableCharsLastConnectingUPEndRowAfterL.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastNotConnectingUPEndRowAfterL[random.nextInt(availableCharsLastNotConnectingUPEndRowAfterL.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsLastEndRowAfterL[random.nextInt(availableCharsLastEndRowAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastConnectingUPEndRowAfterJ[random.nextInt(availableCharsLastConnectingUPEndRowAfterJ.length)];
								}; if (hasNoBottomConnection(board5[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = availableCharsLastNotConnectingUPEndRowAfterJ[random.nextInt(availableCharsLastNotConnectingUPEndRowAfterJ.length)];
								}; if (board5[countingTillTheEnd - 1][row.length - 1] == 0) {
									row[row.length - 1] = availableCharsLastEndRowAfterJ[random.nextInt(availableCharsLastEndRowAfterJ.length)];
								}
								break;
							}
						}
						// TODO Remove Robisie
						System.out.println("ROBI SIE OSTATNIE");
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
