package main.utils;

import main.gamelogic.Board;

import java.util.Random;

public class Generator {

	static Random random = new Random();
	Solver solver = new Solver(new Board(0,null));

	static char[][] board5 = new char[5][5];
	static char[][] board7 = new char[7][7];
	static char[][] board9 = new char[9][9];

	// Zestawy możliwych charów w tablicy oraz co może być po czym

	// 'O' Reprezentuje dowolną cyfrę, będą one wstawiane pozniej
	static final char[] availableCharsNumber = { '0' };
	// '-' i '|' pojawiają się kiedy ścieżka rozwiązująca przechodzi przez jakieś pole "na wprost"
	static final char[] availableCharsCommon = { '-', '|' };
	// 'F', 'T', 'L', 'J' pojawiają się kiedy ścieżka rozwiązująca przechodząc przez jakieś pole skręca tak jak wygląda litera
	// Dla F ścieżka idzie z południa i skręca na wschód (albo oczywiście ze wschodu na południe)
	// Dla T ścieżka idzie z zachodu na południe (w niektórych czcionkach T nie ma tak wyrrazistego "daszka" w prawo)
	// Dla L ścieżka idzie z północy na wschód
	// Dla J ścieżka idzie z zachodu na północ
	static final char[] availableCharsCurves = { 'F', 'T', 'L', 'J' };

	// Co można wstawić na początek wiersza?
	static final char[] availableCharsStartRow = { '|', '|', '0', 'F', 'L' };

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
	static final char[] availableCharsEndRowAfterPipe = { '|', '0' };
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
	static final char[] availableChars1stEndRowAfterPipe = { '0' };
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

	
	
	public static boolean hasMandatoryBottomConnectionTypeF(char a) {
		if ((a == '|') || (a == 'F')) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasMandatoryBottomConnectionTypeT(char a) {
		if ((a == '|') || (a == 'T')) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasMandatoryBottomConnection(char a) {
		if ((a == '|') || (a == 'F') || (a == 'T')) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasNoBottomConnection(char a) {
		if ((a == '-') || (a == 'L') || (a == 'J')) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasMandatoryConnectionUP(char a) {
		if ((a == '|') || (a == 'L') || (a == 'J')) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasMandatoryConnectionRight(char a) {
		if ((a == '-') || (a == 'F') || (a == 'L')) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean hasMandatoryConnectionLeft(char a) {
		if ((a == '-') || (a == 'T') || (a == 'J')) {
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
	static final char[] availableCharsLastConnectingUPAfterNumber = { '0', 'L', 'J' };

	// F
	static final char[] availableCharsLastConnectingUPAfterF = { '0', 'J' };
	// T
	static final char[] availableCharsLastConnectingUPAfterT = { '0', 'L' };
	// L
	static final char[] availableCharsLastConnectingUPAfterL = { '0' };
	// J
	static final char[] availableCharsLastConnectingUPAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsLastConnectingUPEndRow = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterMinus = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterPipe = { '0' };
	static final char[] availableCharsLastConnectingUPEndRowAfterNumber = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterF = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPEndRowAfterT = { '0' };
	static final char[] availableCharsLastConnectingUPEndRowAfterL = { '0' };
	static final char[] availableCharsLastConnectingUPEndRowAfterJ = { '0' };
	
	
	// OBOWIĄZKOWE POŁĄCZENIE Z TYM CO POWYŻEJ TYP F
	// Co można wstawić na początek wiersza?
	static final char[] availableCharsConnectingUPTypeFStartRow = { '|', '0' };

	// Co może być dalej?
	// -
	static final char[] availableCharsConnectingUPTypeFAfterMinus = { '0', 'J' };
	// |
	static final char[] availableCharsConnectingUPTypeFAfterPipe = { '|', '0' };
	// Number
	static final char[] availableCharsConnectingUPTypeFAfterNumber = { '|', '0', 'J' };

	// F
	static final char[] availableCharsConnectingUPTypeFAfterF = { '0', 'J' };
	// T
	static final char[] availableCharsConnectingUPTypeFAfterT = { '|', '0' };
	// L
	static final char[] availableCharsConnectingUPTypeFAfterL = { '0' };
	// J
	static final char[] availableCharsConnectingUPTypeFAfterJ = { '|', '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsConnectingUPTypeFEndRow = { '|', '0', 'J' };
	static final char[] availableCharsConnectingUPTypeFEndRowAfterMinus = { '0', 'J' };
	static final char[] availableCharsConnectingUPTypeFEndRowAfterPipe = { '|', '0' };
	static final char[] availableCharsConnectingUPTypeFEndRowAfterNumber = { '|', '0', 'J' };
	static final char[] availableCharsConnectingUPTypeFEndRowAfterF = { '0', 'J' };
	static final char[] availableCharsConnectingUPTypeFEndRowAfterT = { '|', '0' };
	static final char[] availableCharsConnectingUPTypeFEndRowAfterL = { '0' };
	static final char[] availableCharsConnectingUPTypeFEndRowAfterJ = { '|', '0' };
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	static final char[] availableCharsLastConnectingUPTypeFStartRow = { '0' };

	// Co może być dalej?
	// -
	static final char[] availableCharsLastConnectingUPTypeFAfterMinus = { '0', 'J' };
	// |
	static final char[] availableCharsLastConnectingUPTypeFAfterPipe = { '0' };
	// Number
	static final char[] availableCharsLastConnectingUPTypeFAfterNumber = { '0', 'J' };

	// F
	static final char[] availableCharsLastConnectingUPTypeFAfterF = { '0', 'J' };
	// T
	static final char[] availableCharsLastConnectingUPTypeFAfterT = { '0', };
	// L
	static final char[] availableCharsLastConnectingUPTypeFAfterL = { '0' };
	// J
	static final char[] availableCharsLastConnectingUPTypeFAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsLastConnectingUPTypeFEndRow = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPTypeFEndRowAfterMinus = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPTypeFEndRowAfterPipe = { '0' };
	static final char[] availableCharsLastConnectingUPTypeFEndRowAfterNumber = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPTypeFEndRowAfterF = { '0', 'J' };
	static final char[] availableCharsLastConnectingUPTypeFEndRowAfterT = { '0' };
	static final char[] availableCharsLastConnectingUPTypeFEndRowAfterL = { '0' };
	static final char[] availableCharsLastConnectingUPTypeFEndRowAfterJ = { '0' };
	
	// OBOWIĄZKOWE POŁĄCZENIE Z TYM CO POWYŻEJ TYP T
	// Co można wstawić na początek wiersza?
	static final char[] availableCharsConnectingUPTypeTStartRow = { '|', '0', 'L' };

	// Co może być dalej?
	// -
	static final char[] availableCharsConnectingUPTypeTAfterMinus = { '0' };
	// |
	static final char[] availableCharsConnectingUPTypeTAfterPipe = { '|', '0', 'L' };
	// Number
	static final char[] availableCharsConnectingUPTypeTAfterNumber = { '|', '0', 'L' };

	// F
	static final char[] availableCharsConnectingUPTypeTAfterF = { '0' };
	// T
	static final char[] availableCharsConnectingUPTypeTAfterT = { '|', '0', 'L' };
	// L
	static final char[] availableCharsConnectingUPTypeTAfterL = { '0' };
	// J
	static final char[] availableCharsConnectingUPTypeTAfterJ = { '|', '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsConnectingUPTypeTEndRow = { '|', '0' };
	static final char[] availableCharsConnectingUPTypeTEndRowAfterMinus = { '0' };
	static final char[] availableCharsConnectingUPTypeTEndRowAfterPipe = { '|', '0' };
	static final char[] availableCharsConnectingUPTypeTEndRowAfterNumber = { '|', '0' };
	static final char[] availableCharsConnectingUPTypeTEndRowAfterF = { '0' };
	static final char[] availableCharsConnectingUPTypeTEndRowAfterT = { '|', '0' };
	static final char[] availableCharsConnectingUPTypeTEndRowAfterL = { '0' };
	static final char[] availableCharsConnectingUPTypeTEndRowAfterJ = { '|', '0' };
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	static final char[] availableCharsLastConnectingUPTypeTStartRow = { '0', 'L' };

	// Co może być dalej?
	// -
	static final char[] availableCharsLastConnectingUPTypeTAfterMinus = { '0' };
	// |
	static final char[] availableCharsLastConnectingUPTypeTAfterPipe = { '0', 'L' };
	// Number
	static final char[] availableCharsLastConnectingUPTypeTAfterNumber = { '0', 'L' };

	// F
	static final char[] availableCharsLastConnectingUPTypeTAfterF = { '0' };
	// T
	static final char[] availableCharsLastConnectingUPTypeTAfterT = { '0', 'L' };
	// L
	static final char[] availableCharsLastConnectingUPTypeTAfterL = { '0' };
	// J
	static final char[] availableCharsLastConnectingUPTypeTAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] availableCharsLastConnectingUPTypeTEndRow = { '0' };
	static final char[] availableCharsLastConnectingUPTypeTEndRowAfterMinus = { '0' };
	static final char[] availableCharsLastConnectingUPTypeTEndRowAfterPipe = { '0' };
	static final char[] availableCharsLastConnectingUPTypeTEndRowAfterNumber = { '0' };
	static final char[] availableCharsLastConnectingUPTypeTEndRowAfterF = { '0' };
	static final char[] availableCharsLastConnectingUPTypeTEndRowAfterT = { '0' };
	static final char[] availableCharsLastConnectingUPTypeTEndRowAfterL = { '0' };
	static final char[] availableCharsLastConnectingUPTypeTEndRowAfterJ = { '0' };
	

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
	
	
	
	
	
	
	
	// Dostosowanie częstości występowania
	

	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableCharsStartRow = { '|', '0', 'F', 'F', 'L', 'L' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsAfterMinus = { '-', '-', '-', '-', '0', 'T', 'T', 'J', 'J' };
	// |
	static final char[] adjustedAvailableCharsAfterPipe = { '|', '|', '0', 'F', 'F', 'L', 'L' };
	// Number
	static final char[] adjustedAvailableCharsAfterNumber = { '-', '-', '-', '-', '|', '|', '0', 'T', 'T', 'J', 'J', 'F', 'F', 'L', 'L' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] adjustedAvailableCharsAfterF = { '-', '-', '-', '-', '0', 'J', 'J' };
	// T
	static final char[] adjustedAvailableCharsAfterT = { '|', '|', '0', 'L', 'L' };
	// L
	static final char[] adjustedAvailableCharsAfterL = { '-', '-', '-', '-', '0', 'T', 'T' };
	// J
	static final char[] adjustedAvailableCharsAfterJ = { '|', '|', '0', 'F', 'F'};

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsEndRow = { '|', '|', '0', 'T', 'T', 'J', 'J' };
	static final char[] adjustedAvailableCharsEndRowAfterMinus = { '0', 'T', 'T', 'J', 'J' };
	static final char[] adjustedAvailableCharsEndRowAfterPipe = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsEndRowAfterNumber = { '|', '|', '0', 'T', 'T', 'J', 'J' };
	static final char[] adjustedAvailableCharsEndRowAfterF = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsEndRowAfterT = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsEndRowAfterL = { '0', 'T', 'T' };
	static final char[] adjustedAvailableCharsEndRowAfterJ = { '|', '|', '0' };

	// PIERWSZY WIERSZ
	// Dla pierwszego wiersza nie może być |, J, L

	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableChars1stStartRow = { '0', 'F', 'F'};

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableChars1stAfterMinus = { '-', '-', '-', '-', '0', 'T', 'T' };
	// |
	static final char[] adjustedAvailableChars1stAfterPipe = { '0', 'F', 'F'};
	// Number
	static final char[] adjustedAvailableChars1stAfterNumber = { '-', '-', '-', '-', '0', 'T', 'T' , 'F', 'F' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] adjustedAvailableChars1stAfterF = { '-', '-', '-', '-', '0' };
	// T
	static final char[] adjustedAvailableChars1stAfterT = { '0' };
	// L
	static final char[] adjustedAvailableChars1stAfterL = { '-', '-', '-', '-', '0', 'T', 'T' };
	// J
	static final char[] adjustedAvailableChars1stAfterJ = { '0', 'F', 'F'};

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableChars1stEndRow = { '0', 'T', 'T' };
	static final char[] adjustedAvailableChars1stEndRowAfterMinus = { '0', 'T', 'T' };
	static final char[] adjustedAvailableChars1stEndRowAfterPipe = { '0' };
	static final char[] adjustedAvailableChars1stEndRowAfterNumber = { '0', 'T', 'T' };
	static final char[] adjustedAvailableChars1stEndRowAfterF = { '0' };
	static final char[] adjustedAvailableChars1stEndRowAfterT = { '0' };
	static final char[] adjustedAvailableChars1stEndRowAfterL = { '0', 'T', 'T' };
	static final char[] adjustedAvailableChars1stEndRowAfterJ = { '0' };

	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T

	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableCharsLastStartRow = { '0', 'L', 'L' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsLastAfterMinus = { '-', '-', '-', '-', '0', 'J', 'J' };
	// |
	static final char[] adjustedAvailableCharsLastAfterPipe = { '0', 'L', 'L' };
	// Number
	static final char[] adjustedAvailableCharsLastAfterNumber = { '-', '-', '-', '-', '0', 'J', 'J', 'L', 'L' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] adjustedAvailableCharsLastAfterF = { '-', '-', '-', '-', '0', 'J', 'J' };
	// T
	static final char[] adjustedAvailableCharsLastAfterT = { '0', 'L', 'L' };
	// L
	static final char[] adjustedAvailableCharsLastAfterL = { '-', '-', '-', '-', '0' };
	// J
	static final char[] adjustedAvailableCharsLastAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsLastEndRow = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastEndRowAfterMinus = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastEndRowAfterPipe = { '0' };
	static final char[] adjustedAvailableCharsLastEndRowAfterNumber = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastEndRowAfterF = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastEndRowAfterT = { '0' };
	static final char[] adjustedAvailableCharsLastEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsLastEndRowAfterJ = { '0' };

	
	
	// SPRAWDZANIE CO JEST POWYŻEJ
	
	// OBOWIĄZKOWE POŁĄCZENIE Z TYM CO POWYŻEJ
	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableCharsConnectingUPStartRow = { '|', '|', '0', 'L', 'L' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsConnectingUPAfterMinus = { '0', 'J', 'J' };
	// |
	static final char[] adjustedAvailableCharsConnectingUPAfterPipe = { '|', '|', '0', 'L', 'L' };
	// Number
	static final char[] adjustedAvailableCharsConnectingUPAfterNumber = { '|', '|', '0', 'L', 'L', 'J', 'J' };

	// F
	static final char[] adjustedAvailableCharsConnectingUPAfterF = { '0', 'J', 'J' };
	// T
	static final char[] adjustedAvailableCharsConnectingUPAfterT = { '|', '|', '0', 'L', 'L' };
	// L
	static final char[] adjustedAvailableCharsConnectingUPAfterL = { '0' };
	// J
	static final char[] adjustedAvailableCharsConnectingUPAfterJ = { '|', '|', '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsConnectingUPEndRow = { '|', '|', '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPEndRowAfterMinus = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPEndRowAfterPipe = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPEndRowAfterNumber = { '|', '|', '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPEndRowAfterF = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPEndRowAfterT = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsConnectingUPEndRowAfterJ = { '|', '|', '0' };
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	static final char[] adjustedAvailableCharsLastConnectingUPStartRow = { '0', 'L', 'L' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsLastConnectingUPAfterMinus = { '0', 'J', 'J' };
	// |
	static final char[] adjustedAvailableCharsLastConnectingUPAfterPipe = { '0', 'L', 'L' };
	// Number
	static final char[] adjustedAvailableCharsLastConnectingUPAfterNumber = { '0', 'L', 'L', 'J', 'J' };

	// F
	static final char[] adjustedAvailableCharsLastConnectingUPAfterF = { '0', 'J', 'J' };
	// T
	static final char[] adjustedAvailableCharsLastConnectingUPAfterT = { '0', 'L', 'L' };
	// L
	static final char[] adjustedAvailableCharsLastConnectingUPAfterL = { '0' };
	// J
	static final char[] adjustedAvailableCharsLastConnectingUPAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsLastConnectingUPEndRow = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPEndRowAfterMinus = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPEndRowAfterPipe = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPEndRowAfterNumber = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPEndRowAfterF = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPEndRowAfterT = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPEndRowAfterJ = { '0' };
	
	// OBOWIĄZKOWE POŁĄCZENIE Z TYM CO POWYŻEJ TYP F
	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableCharsConnectingUPTypeFStartRow = { '|', '|', '0' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsConnectingUPTypeFAfterMinus = { '0', 'J', 'J' };
	// |
	static final char[] adjustedAvailableCharsConnectingUPTypeFAfterPipe = { '|', '|', '0' };
	// Number
	static final char[] adjustedAvailableCharsConnectingUPTypeFAfterNumber = { '|', '|', '0', 'J', 'J' };

	// F
	static final char[] adjustedAvailableCharsConnectingUPTypeFAfterF = { '0', 'J', 'J' };
	// T
	static final char[] adjustedAvailableCharsConnectingUPTypeFAfterT = { '|', '|', '0' };
	// L
	static final char[] adjustedAvailableCharsConnectingUPTypeFAfterL = { '0' };
	// J
	static final char[] adjustedAvailableCharsConnectingUPTypeFAfterJ = { '|', '|', '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRow = { '|', '|', '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber = { '|', '|', '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterF = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterT = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ = { '|', '|', '0' };
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFStartRow = { '0' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterMinus = { '0', 'J', 'J' };
	// |
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterPipe = { '0' };
	// Number
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterNumber = { '0', 'J', 'J' };

	// F
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterF = { '0', 'J', 'J' };
	// T
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterT = { '0', };
	// L
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterL = { '0' };
	// J
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRow = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF = { '0', 'J', 'J' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ = { '0' };
	
	// OBOWIĄZKOWE POŁĄCZENIE Z TYM CO POWYŻEJ TYP T
	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableCharsConnectingUPTypeTStartRow = { '|', '|', '0', 'L', 'L' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsConnectingUPTypeTAfterMinus = { '0' };
	// |
	static final char[] adjustedAvailableCharsConnectingUPTypeTAfterPipe = { '|', '|', '0', 'L', 'L' };
	// Number
	static final char[] adjustedAvailableCharsConnectingUPTypeTAfterNumber = { '|', '|', '0', 'L', 'L' };

	// F
	static final char[] adjustedAvailableCharsConnectingUPTypeTAfterF = { '0' };
	// T
	static final char[] adjustedAvailableCharsConnectingUPTypeTAfterT = { '|', '|', '0', 'L', 'L' };
	// L
	static final char[] adjustedAvailableCharsConnectingUPTypeTAfterL = { '0' };
	// J
	static final char[] adjustedAvailableCharsConnectingUPTypeTAfterJ = { '|', '|', '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRow = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus = { '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterF = { '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterT = { '|', '|', '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ = { '|', '|', '0' };
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTStartRow = { '0', 'L', 'L' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterMinus = { '0' };
	// |
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterPipe = { '0', 'L', 'L' };
	// Number
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterNumber = { '0', 'L', 'L' };

	// F
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterF = { '0' };
	// T
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterT = { '0', 'L', 'L' };
	// L
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterL = { '0' };
	// J
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRow = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ = { '0' };

	// OBOWIĄZKOWY BRAK POŁĄCZENIA Z TYM CO POWYŻEJ
	
	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableCharsNotConnectingUPStartRow = adjustedAvailableChars1stStartRow;

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsNotConnectingUPAfterMinus = adjustedAvailableChars1stAfterMinus;
	// |
	static final char[] adjustedAvailableCharsNotConnectingUPAfterPipe = adjustedAvailableChars1stAfterPipe;
	// Number
	static final char[] adjustedAvailableCharsNotConnectingUPAfterNumber = adjustedAvailableChars1stAfterNumber;
		// F
	static final char[] adjustedAvailableCharsNotConnectingUPAfterF = adjustedAvailableChars1stAfterF;
	// T
	static final char[] adjustedAvailableCharsNotConnectingUPAfterT = adjustedAvailableChars1stAfterT;
	// L
	static final char[] adjustedAvailableCharsNotConnectingUPAfterL = adjustedAvailableChars1stAfterL;
	// J
	static final char[] adjustedAvailableCharsNotConnectingUPAfterJ = adjustedAvailableChars1stAfterJ;
		// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsNotConnectingUPEndRow = adjustedAvailableChars1stEndRow;
	static final char[] adjustedAvailableCharsNotConnectingUPEndRowAfterMinus = adjustedAvailableChars1stEndRowAfterMinus;
	static final char[] adjustedAvailableCharsNotConnectingUPEndRowAfterPipe = adjustedAvailableChars1stEndRowAfterPipe;
	static final char[] adjustedAvailableCharsNotConnectingUPEndRowAfterNumber = adjustedAvailableChars1stEndRowAfterNumber;
	static final char[] adjustedAvailableCharsNotConnectingUPEndRowAfterF = adjustedAvailableChars1stEndRowAfterF;
	static final char[] adjustedAvailableCharsNotConnectingUPEndRowAfterT = adjustedAvailableChars1stEndRowAfterT;
	static final char[] adjustedAvailableCharsNotConnectingUPEndRowAfterL = adjustedAvailableChars1stEndRowAfterL;
	static final char[] adjustedAvailableCharsNotConnectingUPEndRowAfterJ = adjustedAvailableChars1stEndRowAfterJ;
	
	
	// OSTATNI WIERSZ
	// Dla ostatniego wiersza nie może być |, F, T
	
	// Co można wstawić na początek wiersza?
	static final char[] adjustedAvailableCharsLastNotConnectingUPStartRow = { '0' };

	// Co może być dalej?
	// -
	static final char[] adjustedAvailableCharsLastNotConnectingUPAfterMinus = { '-', '-', '-', '-', '0' };
	// |
	static final char[] adjustedAvailableCharsLastNotConnectingUPAfterPipe = { '0' };
	// Number
	static final char[] adjustedAvailableCharsLastNotConnectingUPAfterNumber = { '-', '-', '-', '-', '0' };

	// Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
	// kierunku (analogocznie dla pozostałych)
	// F
	static final char[] adjustedAvailableCharsLastNotConnectingUPAfterF = { '-', '-', '-', '-', '0' };
	// T
	static final char[] adjustedAvailableCharsLastNotConnectingUPAfterT = { '0' };
	// L
	static final char[] adjustedAvailableCharsLastNotConnectingUPAfterL = { '-', '-', '-', '-', '0' };
	// J
	static final char[] adjustedAvailableCharsLastNotConnectingUPAfterJ = { '0' };

	// Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRow = { '0' };
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus = { '0' };
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe = { '0' };
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber = { '0' };
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterF = { '0' };
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterT = { '0' };
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterL = { '0' };
	static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ = { '0' };
	
	
	public Generator() {
	}

	public Generator(int size) {
	}

	public static void main(String[] args) {
		Generator generator = new Generator();
		
		char[][] generatedBoard = generator.generate(5);

		for (int j = 0; j < generatedBoard.length; j++) {
			for (int i = 0; i < generatedBoard.length; i++) {
				System.out.print(generatedBoard[j][i]);
				System.out.print("  ");
			}
			System.out.println(" ");
		// TODO Test
		}
		fillWithNumbers(generatedBoard);
		for (int j = 0; j < generatedBoard.length; j++) {
			for (int i = 0; i < generatedBoard.length; i++) {
				System.out.print(generatedBoard[j][i]);
				System.out.print("  ");
			}
			System.out.println(" ");
		}
	}

	char[][] generate(int size) {
		char[][] board = null;
		switch (size) {

		case 4:
			board = new char[4][4];
			
		case 5:
			if (board == null) {
				board = new char[5][5];
			}

			do {
				// Pierwszy wiersz wymaga osobnego rozpatrywania
				boolean is1stRowDone = false;
				// countingTillTheEnd odlicza w praktyce wykonania pętli foreach
				int countingTillTheEnd = 0;

				for (char[] row : board) {

					if (!is1stRowDone) {
						// Na każde pole w pierwszym wierszu bieżemy znak z właściwej tablicy
						row[0] = adjustedAvailableChars1stStartRow[random.nextInt(adjustedAvailableChars1stStartRow.length)];

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								row[i] = adjustedAvailableChars1stAfterMinus[random
										.nextInt(adjustedAvailableChars1stAfterMinus.length)];
								break;
							case '|':
								row[i] = adjustedAvailableChars1stAfterPipe[random.nextInt(adjustedAvailableChars1stAfterPipe.length)];
								break;
							case '0':
								row[i] = adjustedAvailableChars1stAfterNumber[random
										.nextInt(adjustedAvailableChars1stAfterNumber.length)];
								break;
							case 'F':
								row[i] = adjustedAvailableChars1stAfterF[random.nextInt(adjustedAvailableChars1stAfterF.length)];
								break;
							case 'T':
								row[i] = adjustedAvailableChars1stAfterT[random.nextInt(adjustedAvailableChars1stAfterT.length)];
								break;
							case 'L':
								row[i] = adjustedAvailableChars1stAfterL[random.nextInt(adjustedAvailableChars1stAfterL.length)];
								break;
							case 'J':
								row[i] = adjustedAvailableChars1stAfterJ[random.nextInt(adjustedAvailableChars1stAfterJ.length)];
								break;
							}
						}
						{
							switch (row[row.length - 2]) {
							case '-':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterMinus[random.nextInt(adjustedAvailableChars1stEndRowAfterMinus.length)];
								break;
							case '|':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterPipe[random.nextInt(adjustedAvailableChars1stEndRowAfterPipe.length)];
								break;
							case '0':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterNumber[random.nextInt(adjustedAvailableChars1stEndRowAfterNumber.length)];
								break;
							case 'F':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterF[random.nextInt(adjustedAvailableChars1stEndRowAfterF.length)];
								break;
							case 'T':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterT[random.nextInt(adjustedAvailableChars1stEndRowAfterT.length)];
								break;
							case 'L':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterL[random.nextInt(adjustedAvailableChars1stEndRowAfterL.length)];
								break;
							case 'J':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterJ[random.nextInt(adjustedAvailableChars1stEndRowAfterJ.length)];
								break;
							}
						}
						// Jak już pierwszy wiersz się napisał to więcej go nie piszemy
						is1stRowDone = true;
						countingTillTheEnd++;
						continue;

					} else if (countingTillTheEnd < board.length - 1) {
						// Pozostałe wiersze z wyjątkiem ostatniego

						// Pierwszy znak w każdym wierszu wymaga osobnych instrukcji, ponieważ nie ma sąsiada z lewej strony
						if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsConnectingUPTypeFStartRow[random.nextInt(adjustedAvailableCharsConnectingUPTypeFStartRow.length)];
						}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsConnectingUPTypeTStartRow[random.nextInt(adjustedAvailableCharsConnectingUPTypeTStartRow.length)];
						}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsNotConnectingUPStartRow[random.nextInt(adjustedAvailableCharsNotConnectingUPStartRow.length)];
						}; if (board[countingTillTheEnd - 1][0] == '0') {
							row[0] = row[0] = adjustedAvailableCharsStartRow[random.nextInt(adjustedAvailableCharsStartRow.length)];
						}

						// Dla wszytkich pól w wierszu z wyjątkiem pierwszego i ostatniego
						for (int i = 1; i < row.length - 1; i++) {

							// Dla każedo przypadku pola sąsiada z lewej strony
							switch (row[i - 1]) {
							case '-':
								// Sprawdzamy sąsiada z góry, czytamy co jest w tablicy powyżej miejsca w które chcemy coś wstawić
								// Jeśli sąsiad z góry ma połączenie w dół to musimy wstawić znak który ma połączenie w górę
								// Ale jak ma połączenie w dół to musimy rozpatrzyć dwa przypadki, aby nie tworzyły się krztałty w stylu C i lustrzanego C, bo są niezgodne z zasadami numberlink
								// Powstają one gdy poniżej F jest L, a poniżj T jest J, więc tablica Type F zawiera F i |, a Type T zawiera T i |
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterMinus.length)];
								// Jeśli sąsiad z góry nie może się łączyś z tym co na dole to wstawiamy odpowiedni znak
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterMinus[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterMinus.length)];
								// Cyfra może się łączyć w dół ale nie musi, obojętnie
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterMinus[random.nextInt(adjustedAvailableCharsAfterMinus.length)];
								}
								break;
								// Dla pozostałych przypadków analogicznie
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterPipe[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterPipe[random.nextInt(adjustedAvailableCharsAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterNumber[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterNumber[random.nextInt(adjustedAvailableCharsAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterF[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterF.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterF[random.nextInt(adjustedAvailableCharsAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterT[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterT.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterT[random.nextInt(adjustedAvailableCharsAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterL[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterL.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterL[random.nextInt(adjustedAvailableCharsAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterJ[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterJ[random.nextInt(adjustedAvailableCharsAfterJ.length)];
								}
								break;
							}
						}
						{
							// Ostatnie pole w wierszu wymaga własnych instrukcji i tablic
							switch (row[row.length - 2]) {
							case '-':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterMinus[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterMinus.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterMinus[random.nextInt(adjustedAvailableCharsEndRowAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterPipe[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterPipe[random.nextInt(adjustedAvailableCharsEndRowAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterNumber[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterNumber[random.nextInt(adjustedAvailableCharsEndRowAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterF[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterF.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterF[random.nextInt(adjustedAvailableCharsEndRowAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterT[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterT.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterT[random.nextInt(adjustedAvailableCharsEndRowAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterL[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterL.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterL[random.nextInt(adjustedAvailableCharsEndRowAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterJ[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterJ[random.nextInt(adjustedAvailableCharsEndRowAfterJ.length)];
								}
								break;
							}
						}

						countingTillTheEnd++;
						System.out.println("Robi sie srodkowe");

					} else {
						// Ostatni wiersz wymaga własnych instrukcji i tablic
						if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsLastConnectingUPTypeFStartRow[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFStartRow.length)];
						}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsLastConnectingUPTypeTStartRow[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTStartRow.length)];
						}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsLastNotConnectingUPStartRow[random.nextInt(adjustedAvailableCharsLastNotConnectingUPStartRow.length)];
						}; if (board[countingTillTheEnd - 1][0] == '0') {
							row[0] = adjustedAvailableCharsLastStartRow[random.nextInt(adjustedAvailableCharsLastStartRow.length)];
						}

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterMinus.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterMinus[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterMinus.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterMinus[random.nextInt(adjustedAvailableCharsLastAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterPipe[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterPipe[random.nextInt(adjustedAvailableCharsLastAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterNumber[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterNumber[random.nextInt(adjustedAvailableCharsLastAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterF[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterF.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterF[random.nextInt(adjustedAvailableCharsLastAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterT[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterT.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterT[random.nextInt(adjustedAvailableCharsLastAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterL[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterL.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterL[random.nextInt(adjustedAvailableCharsLastAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterJ[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterJ[random.nextInt(adjustedAvailableCharsLastAfterJ.length)];
								}
								break;
							}
						}
						{
							switch (row[row.length - 2]) {
							case '-':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastEndRowAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastEndRowAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastEndRowAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterF[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterF.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterF[random.nextInt(adjustedAvailableCharsLastEndRowAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterT[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterT.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterT[random.nextInt(adjustedAvailableCharsLastEndRowAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterL[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterL.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterL[random.nextInt(adjustedAvailableCharsLastEndRowAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastEndRowAfterJ.length)];
								}
								break;
							}
						}
						// TODO Remove Robisie
						System.out.println("ROBI SIE OSTATNIE");
					}
				}
			} while (!isClassic(board));
			System.out.println("Wygenerowano planszę o wielkości " + size);
			break;

		case 6: 
			board = new char[6][6];
			
		case 7:
			if (board == null) {
				board = new char[7][7];
			}
			
		case 8:
			if (board == null) {
				board = new char[8][8];
			}
			
			do {
				// Pierwszy wiersz wymaga osobnego rozpatrywania
				boolean is1stRowDone = false;
				// countingTillTheEnd odlicza w praktyce wykonania pętli foreach
				int countingTillTheEnd = 0;

				for (char[] row : board) {

					if (!is1stRowDone) {
						// Na każde pole w pierwszym wierszu bieżemy znak z właściwej tablicy
						row[0] = adjustedAvailableChars1stStartRow[random.nextInt(adjustedAvailableChars1stStartRow.length)];

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								row[i] = adjustedAvailableChars1stAfterMinus[random
										.nextInt(adjustedAvailableChars1stAfterMinus.length)];
								break;
							case '|':
								row[i] = adjustedAvailableChars1stAfterPipe[random.nextInt(adjustedAvailableChars1stAfterPipe.length)];
								break;
							case '0':
								row[i] = adjustedAvailableChars1stAfterNumber[random
										.nextInt(adjustedAvailableChars1stAfterNumber.length)];
								break;
							case 'F':
								row[i] = adjustedAvailableChars1stAfterF[random.nextInt(adjustedAvailableChars1stAfterF.length)];
								break;
							case 'T':
								row[i] = adjustedAvailableChars1stAfterT[random.nextInt(adjustedAvailableChars1stAfterT.length)];
								break;
							case 'L':
								row[i] = adjustedAvailableChars1stAfterL[random.nextInt(adjustedAvailableChars1stAfterL.length)];
								break;
							case 'J':
								row[i] = adjustedAvailableChars1stAfterJ[random.nextInt(adjustedAvailableChars1stAfterJ.length)];
								break;
							}
						}
						{
							switch (row[row.length - 2]) {
							case '-':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterMinus[random.nextInt(adjustedAvailableChars1stEndRowAfterMinus.length)];
								break;
							case '|':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterPipe[random.nextInt(adjustedAvailableChars1stEndRowAfterPipe.length)];
								break;
							case '0':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterNumber[random.nextInt(adjustedAvailableChars1stEndRowAfterNumber.length)];
								break;
							case 'F':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterF[random.nextInt(adjustedAvailableChars1stEndRowAfterF.length)];
								break;
							case 'T':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterT[random.nextInt(adjustedAvailableChars1stEndRowAfterT.length)];
								break;
							case 'L':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterL[random.nextInt(adjustedAvailableChars1stEndRowAfterL.length)];
								break;
							case 'J':
								row[row.length - 1] = adjustedAvailableChars1stEndRowAfterJ[random.nextInt(adjustedAvailableChars1stEndRowAfterJ.length)];
								break;
							}
						}
						// Jak już pierwszy wiersz się napisał to więcej go nie piszemy
						is1stRowDone = true;
						countingTillTheEnd++;
						continue;

					} else if (countingTillTheEnd < board.length - 1) {
						// Pozostałe wiersze z wyjątkiem ostatniego

						// Pierwszy znak w każdym wierszu wymaga osobnych instrukcji, ponieważ nie ma sąsiada z lewej strony
						if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsConnectingUPTypeFStartRow[random.nextInt(adjustedAvailableCharsConnectingUPTypeFStartRow.length)];
						}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsConnectingUPTypeTStartRow[random.nextInt(adjustedAvailableCharsConnectingUPTypeTStartRow.length)];
						}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsNotConnectingUPStartRow[random.nextInt(adjustedAvailableCharsNotConnectingUPStartRow.length)];
						}; if (board[countingTillTheEnd - 1][0] == '0') {
							row[0] = row[0] = adjustedAvailableCharsStartRow[random.nextInt(adjustedAvailableCharsStartRow.length)];
						}

						// Dla wszytkich pól w wierszu z wyjątkiem pierwszego i ostatniego
						for (int i = 1; i < row.length - 1; i++) {

							// Dla każedo przypadku pola sąsiada z lewej strony
							switch (row[i - 1]) {
							case '-':
								// Sprawdzamy sąsiada z góry, czytamy co jest w tablicy powyżej miejsca w które chcemy coś wstawić
								// Jeśli sąsiad z góry ma połączenie w dół to musimy wstawić znak który ma połączenie w górę
								// Ale jak ma połączenie w dół to musimy rozpatrzyć dwa przypadki, aby nie tworzyły się krztałty w stylu C i lustrzanego C, bo są niezgodne z zasadami numberlink
								// Powstają one gdy poniżej F jest L, a poniżj T jest J, więc tablica Type F zawiera F i |, a Type T zawiera T i |
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterMinus.length)];
								// Jeśli sąsiad z góry nie może się łączyś z tym co na dole to wstawiamy odpowiedni znak
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterMinus[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterMinus.length)];
								// Cyfra może się łączyć w dół ale nie musi, obojętnie
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterMinus[random.nextInt(adjustedAvailableCharsAfterMinus.length)];
								}
								break;
								// Dla pozostałych przypadków analogicznie
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterPipe[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterPipe[random.nextInt(adjustedAvailableCharsAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterNumber[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterNumber[random.nextInt(adjustedAvailableCharsAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterF[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterF.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterF[random.nextInt(adjustedAvailableCharsAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterT[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterT.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterT[random.nextInt(adjustedAvailableCharsAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterL[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterL.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterL[random.nextInt(adjustedAvailableCharsAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeFAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeFAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsConnectingUPTypeTAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeTAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsNotConnectingUPAfterJ[random.nextInt(adjustedAvailableCharsNotConnectingUPAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsAfterJ[random.nextInt(adjustedAvailableCharsAfterJ.length)];
								}
								break;
							}
						}
						{
							// Ostatnie pole w wierszu wymaga własnych instrukcji i tablic
							switch (row[row.length - 2]) {
							case '-':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterMinus[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterMinus.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterMinus[random.nextInt(adjustedAvailableCharsEndRowAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterPipe[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterPipe[random.nextInt(adjustedAvailableCharsEndRowAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterNumber[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterNumber[random.nextInt(adjustedAvailableCharsEndRowAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterF[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterF[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterF.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterF[random.nextInt(adjustedAvailableCharsEndRowAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterT[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterT[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterT.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterT[random.nextInt(adjustedAvailableCharsEndRowAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterL[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterL[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterL.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterL[random.nextInt(adjustedAvailableCharsEndRowAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ[random.nextInt(adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsNotConnectingUPEndRowAfterJ[random.nextInt(adjustedAvailableCharsNotConnectingUPEndRowAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsEndRowAfterJ[random.nextInt(adjustedAvailableCharsEndRowAfterJ.length)];
								}
								break;
							}
						}

						countingTillTheEnd++;
						System.out.println("Robi sie srodkowe");

					} else {
						// Ostatni wiersz wymaga własnych instrukcji i tablic
						if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsLastConnectingUPTypeFStartRow[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFStartRow.length)];
						}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsLastConnectingUPTypeTStartRow[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTStartRow.length)];
						}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][0])) {
							row[0] = adjustedAvailableCharsLastNotConnectingUPStartRow[random.nextInt(adjustedAvailableCharsLastNotConnectingUPStartRow.length)];
						}; if (board[countingTillTheEnd - 1][0] == '0') {
							row[0] = adjustedAvailableCharsLastStartRow[random.nextInt(adjustedAvailableCharsLastStartRow.length)];
						}

						for (int i = 1; i < row.length - 1; i++) {

							switch (row[i - 1]) {
							case '-':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterMinus.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterMinus[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterMinus.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterMinus[random.nextInt(adjustedAvailableCharsLastAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterPipe[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterPipe[random.nextInt(adjustedAvailableCharsLastAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterNumber[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterNumber[random.nextInt(adjustedAvailableCharsLastAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterF[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterF.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterF[random.nextInt(adjustedAvailableCharsLastAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterT[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterT.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterT[random.nextInt(adjustedAvailableCharsLastAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterL[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterL.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterL[random.nextInt(adjustedAvailableCharsLastAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeFAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastConnectingUPTypeTAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
									row[i] = adjustedAvailableCharsLastNotConnectingUPAfterJ[random.nextInt(adjustedAvailableCharsLastNotConnectingUPAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][i] == '0') {
									row[i] = adjustedAvailableCharsLastAfterJ[random.nextInt(adjustedAvailableCharsLastAfterJ.length)];
								}
								break;
							}
						}
						{
							switch (row[row.length - 2]) {
							case '-':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterMinus[random.nextInt(adjustedAvailableCharsLastEndRowAfterMinus.length)];
								}
								break;
							case '|':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterPipe[random.nextInt(adjustedAvailableCharsLastEndRowAfterPipe.length)];
								}
								break;
							case '0':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterNumber[random.nextInt(adjustedAvailableCharsLastEndRowAfterNumber.length)];
								}
								break;
							case 'F':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterF[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterF.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterF[random.nextInt(adjustedAvailableCharsLastEndRowAfterF.length)];
								}
								break;
							case 'T':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterT[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterT.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterT[random.nextInt(adjustedAvailableCharsLastEndRowAfterT.length)];
								}
								break;
							case 'L':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterL[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterL.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterL[random.nextInt(adjustedAvailableCharsLastEndRowAfterL.length)];
								}
								break;
							case 'J':
								if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ.length)];
								}; if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ.length)];
								}; if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
									row[row.length - 1] = adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ.length)];
								}; if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
									row[row.length - 1] = adjustedAvailableCharsLastEndRowAfterJ[random.nextInt(adjustedAvailableCharsLastEndRowAfterJ.length)];
								}
								break;
							}
						}
						// TODO Remove Robisie
						System.out.println("ROBI SIE OSTATNIE");
					}
				}
				fix(board);
			} while (!isClassic(board));
			System.out.println("Wygenerowano planszę o wielkości " + size);
			break;

		case 9:
			do {
				for (char[] row : board9) {
					for (int i = 0; i < row.length; i++) {
						// TODO
						row[i] = '?';
					}
				}
			} while (!isClassic(board7));
			System.out.println("Wygenerowano planszę o wielkości 9");
			break;

		default:
			System.out.println("Wprowadzono niepoprawną wielkość planszy");
		}
		
		return board;
	}
	
	@SuppressWarnings("finally")
	void fix(char[][] tableToFix) {
		int countingTillTheEnd = 0;
		for (char[] row : tableToFix) {
			for (int i = 0; i < row.length; i++) { 
				boolean occupied = false;
				if (row[i] == '0') {
					// Konstrukcja z for, switch i try - finally continue pozwala uniknąć błędów czytania poza tablicą (fajne obejście)
					// Pętla wykonuje się 4 razy szukając połączenia do cyfry z każdej strony
					// Jeśli je znajdzie to znak posiadający to połączenie zostaje zastąpiony przez cyfrę
					for (int condition = 0; condition < 4; condition++) {
						System.out.println("Robia sie warunki");
						switch (condition) {
						case 0:
							System.out.println("Robi sie 0");
							try {
								if (Generator.hasMandatoryBottomConnection(tableToFix[countingTillTheEnd - 1][i])) {
									if (occupied == true) {
										tableToFix[countingTillTheEnd - 1][i] = '0';
									}
									occupied = true;
								} 
							} finally {
								continue;
							}
						case 1:
							System.out.println("Robi sie 1");
							try {
								if (Generator.hasMandatoryConnectionUP(tableToFix[countingTillTheEnd + 1][i])) {
									if (occupied == true) {
										tableToFix[countingTillTheEnd + 1][i] = '0';
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 2:
							System.out.println("Robi sie 2");
							try {
								if (Generator.hasMandatoryConnectionLeft(tableToFix[countingTillTheEnd][i + 1])) {
									if (occupied == true) {
										tableToFix[countingTillTheEnd][i + 1] = '0';
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						case 3:
							System.out.println("Robi sie 3");
							try {
								if (Generator.hasMandatoryConnectionRight(tableToFix[countingTillTheEnd][i - 1])) {
									if (occupied == true) {
										tableToFix[countingTillTheEnd][i - 1] = '0';
									}
									occupied = true;
								} 
							} finally {
								continue;
							} 
						}
					}
				}
			}
			countingTillTheEnd++;
		}
	}
	
	@SuppressWarnings("finally")
	static boolean isClassic(char[][] tableToTest) {
		// Jeśli plansza nie ma pażystej liczby cyfr to nie jest klasyczna
		int numbercounter = 0;
		for (char[] row : tableToTest) {
			for (char a : row) {
				if (a == 'O') {
					numbercounter++;
				}
			}
		}
		if (numbercounter % 2 != 0) {
			return false;
		}
		boolean isnotWrong = true;
		int countingTillTheEnd = 0;
		for (char[] row : tableToTest) {
			for (int i = 0; i < row.length; i++) { 
				// Jeśli z cyfry wychodzą więcej niż 1 ścieżka albo 0 ścieżek to nie jest klasyczna
				boolean occupied = false;
				if (row[i] == '0') {
					// Konstrukcja z for, switch i try - finally continue pozwala uniknąć błędów czytania poza tablicą (fajne obejście)
					// Pętla wykonuje się 4 razy szukając połączenia do cyfry z każdej strony
					// Jeśli je znajdzie to ustawia occupied na true, return false nie działa poprawnie w try
					// Jeśli przy sprawdzaniu kolejnego warunku okaże się, że zmienna occupied to już true to plansza jest do kasacji według zasad klasycznych
					// Jeśli na koniec czyli w condition4 okaże się, że nie znaleziono wcześniej żadnej ścieżki (occupied = false) to mamy odizolowaną cyfrę i plansza jest do kasacji według zasad klasycznych
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
	
	@SuppressWarnings("finally")
	static char[][] fillWithNumbers(char[][] tableToFill) {
		int countingTillTheEnd = 0;
		char numberCounter = 49;
		for (char[] row : tableToFill) {
			
			gotoHere:
			for (int i = 0; i < row.length; i++) { 
				
				if (row[i] == '0') {
					int horizontalMoveCounter = 0;
					int verticalMoveCounter = 0;
					boolean goUpBlocked = false;
					boolean goDownBlocked = false;
					boolean goRightBlocked = false;
					boolean goLeftBlocked = false;
					for (int condition = 0; condition < 5; condition++) {
						switch (condition) {
						case 0:
							try {
								if (!goUpBlocked && (Generator.hasMandatoryBottomConnection(tableToFill[countingTillTheEnd - 1 + verticalMoveCounter][i + horizontalMoveCounter]))) {
									verticalMoveCounter--;
								}
							} finally {
								continue;
							}
						case 1:
							try {
								if (!goDownBlocked && (Generator.hasMandatoryConnectionUP(tableToFill[countingTillTheEnd + 1 + verticalMoveCounter][i + horizontalMoveCounter]))) {
									verticalMoveCounter++;
								}
									
							} finally {
								continue;
							} 
						case 2:
							try {
								if (!goRightBlocked && (Generator.hasMandatoryConnectionLeft(tableToFill[countingTillTheEnd + verticalMoveCounter][i + 1 + horizontalMoveCounter]))) {
									horizontalMoveCounter++;
								}
							} finally {
								continue;
							} 
						case 3:
							try {
								if (!goLeftBlocked && (Generator.hasMandatoryConnectionRight(tableToFill[countingTillTheEnd + verticalMoveCounter][i - 1 + horizontalMoveCounter]))) {
									horizontalMoveCounter--;
								}
							} finally {
								continue;
							} 
						case 4:
							try {
								
							} finally {
								continue;
							}
						}
					}
					
					for (int cos = 0; cos < 200; cos++) {
					for (int condition = 0; condition < 5; condition++) {
						switch (condition) {
						case 0:
							try {
								for (int maxPathLength = 0; maxPathLength < 20; maxPathLength++) {
									if (!goUpBlocked && (Generator.hasMandatoryBottomConnection(tableToFill[countingTillTheEnd - 1 + verticalMoveCounter][i + horizontalMoveCounter]))) {
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '|') {
											verticalMoveCounter--;
											System.out.println("Idzie w górę");
											goUpBlocked = false;
											goDownBlocked = true;
											goRightBlocked = false;
											goLeftBlocked = false;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'F') {
											horizontalMoveCounter++;
											System.out.println("Idzie w górę i prawo");
											goUpBlocked = false;
											goDownBlocked = true;
											goRightBlocked = false;
											goLeftBlocked = false;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'T') {
											horizontalMoveCounter--;
											System.out.println("Idzie w górę i lewo");
											goUpBlocked = false;
											goDownBlocked = true;
											goRightBlocked = false;
											goLeftBlocked = false;
										}
									}
									continue;
								} 
							} finally {
								continue;
							}
						case 1:
							try {
								for (int maxPathLength = 0; maxPathLength < 20; maxPathLength++) {
									if (!goDownBlocked && (Generator.hasMandatoryConnectionUP(tableToFill[countingTillTheEnd + 1 + verticalMoveCounter][i + horizontalMoveCounter]))) {
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '|') {
											verticalMoveCounter++;
											System.out.println("Idzie w dół");
											goUpBlocked = true;
											goDownBlocked = false;
											goRightBlocked = false;
											goLeftBlocked = false;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'L') {
											horizontalMoveCounter++;
											System.out.println("Idzie w dół i lewo");
											goUpBlocked = true;
											goDownBlocked = false;
											goRightBlocked = false;
											goLeftBlocked = false;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'J') {
											horizontalMoveCounter--;
											System.out.println("Idzie w dół i prawo");
											goUpBlocked = true;
											goDownBlocked = false;
											goRightBlocked = false;
											goLeftBlocked = false;
										}
									}
									continue;
								}
							} finally {
								continue;
							} 
						case 2:
							try {
								for (int maxPathLength = 0; maxPathLength < 20; maxPathLength++) {
									if (!goRightBlocked && (Generator.hasMandatoryConnectionLeft(tableToFill[countingTillTheEnd + verticalMoveCounter][i + 1 + horizontalMoveCounter]))) {
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '-') {
											horizontalMoveCounter++;
											System.out.println("Idzie w prawo");
											goUpBlocked = false;
											goDownBlocked = false;
											goRightBlocked = false;
											goLeftBlocked = true;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'T') {
											verticalMoveCounter++;
											System.out.println("Idzie w prawo i w dół");
											goUpBlocked = false;
											goDownBlocked = false;
											goRightBlocked = false;
											goLeftBlocked = true;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'J') {
											verticalMoveCounter--;
											System.out.println("Idzie w prawo i w górę");
											goUpBlocked = false;
											goDownBlocked = false;
											goRightBlocked = false;
											goLeftBlocked = true;
										}
									}
									continue;
								}
							} finally {
								continue;
							} 
						case 3:
							try {
								for (int maxPathLength = 0; maxPathLength < 20; maxPathLength++) {
									if (!goLeftBlocked && (Generator.hasMandatoryConnectionRight(tableToFill[countingTillTheEnd + verticalMoveCounter][i - 1 + horizontalMoveCounter]))) {
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '-') {
											horizontalMoveCounter--;
											System.out.println("Idzie w lewo");
											goUpBlocked = false;
											goDownBlocked = false;
											goRightBlocked = true;
											goLeftBlocked = false;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'F') {
											verticalMoveCounter++;
											System.out.println("Idzie w lewo i w dół");
											goUpBlocked = false;
											goDownBlocked = false;
											goRightBlocked = true;
											goLeftBlocked = false;
										}
										if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'L') {
											verticalMoveCounter--;
											System.out.println("Idzie w lewo i w górę");
											goUpBlocked = false;
											goDownBlocked = false;
											goRightBlocked = true;
											goLeftBlocked = false;
										}
									}
									continue;
								}
							} finally {
								continue;
							} 
						case 4:
							try {
								if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '0') {
									row[i] = numberCounter;
									tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] = numberCounter;
									numberCounter++;
									continue gotoHere;
								}
							} finally {
								if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '0') {
									row[i] = numberCounter;
									tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] = numberCounter;
									numberCounter++;
									continue gotoHere;
								}
								continue;
							}
						}
					}
					}
				}
			}
			countingTillTheEnd++;
		}
		return tableToFill;
	}
}
