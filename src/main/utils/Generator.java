package main.utils;

import java.util.Random;


/**
 * Provides pseudo-random Numberlink board generation functionality
 */
public class Generator extends ConnectionTypes {

    static Random random = new Random();

//	Usage:
//	Generator generator = new Generator();
//	char[][] generatedBoard = generator.generate(x);
//	where x is board dimension

    // Zestawy możliwych charów w tablicy oraz co może być po czym znajdują się w klasie wewnętrzenej tableHub

    // 'O' Reprezentuje dowolną cyfrę, będą one wstawiane pozniej

    // '-' i '|' pojawiają się kiedy ścieżka rozwiązująca przechodzi przez jakieś pole "na wprost"

    // 'F', 'T', 'L', 'J' pojawiają się kiedy ścieżka rozwiązująca przechodząc przez jakieś pole skręca tak jak wygląda litera
    // Dla F ścieżka idzie z południa i skręca na wschód (albo oczywiście ze wschodu na południe)
    // Dla T ścieżka idzie z zachodu na południe (w niektórych czcionkach T nie ma tak wyrrazistego "daszka" w prawo)
    // Dla L ścieżka idzie z północy na wschód
    // Dla J ścieżka idzie z zachodu na północ

    // Słowa kluczowe w nazwach tablic
    // availableChars - bazowe
    // adjusted - z dostosowaną częstością występowania
    // ConnectingUP - wstawiany znak ma połączenie z górą
    // TypeT - typ T połączenia w górę (podział służy uniknięciu ścieżek w krztałcie litery C
    // TypeF - typ F połączenia w górę
    // NotConnectingUP - nie ma połączenia w górę
    // 1st - w pierwszym wierszu
    // Last - w ostatnim wierszu
    // StartRow - na początku wiersza
    // EndRow - na koniec wiersza
    // AfterX - po znaku x który jest sąsiadem z lewej strony



//    public static void main(String[] args) {
//        Generator generator = new Generator();
//
//        char[][] generatedBoard = generator.generate(9);
//
//        for (int j = 0; j < generatedBoard.length; j++) {
//            for (int i = 0; i < generatedBoard.length; i++) {
//                System.out.print(generatedBoard[j][i]);
//                System.out.print("  ");
//            }
//            System.out.println(" ");
//        }
//    }
//
//
    /**
     * An all-in-one generate method
     *
     * @param size for example board with size 5 is 5x5 char[][]
     * @return generated board
     */
    public char[][] generate(int size) {
        return fillWithNumbers(generatePaths(size));
    }
    /**
     * Generates a board in a gameplay-ready format
     *
     * @return generated board in a gameplay-ready format
     */
    public int[][] generateBoard(int size){
        char[][] generated = generate(size);
        int[][] converted = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int c = generated[i][j] - 48;
                converted[i][j] = (c < 0 || c > 21) ? 0 : c;
            }
        }
        return converted;
    }
    /**
     * Creates a char[][] Numberlink board filled with paths represented by straight lines '-', '|' and curves 'F', 'T', 'L', 'J'
     * For now all numbers are represented by zeros
     * Paths are pseudo-random, but have to fit each other geometry-wise and don't have patterns forbidden by Numberlink rules
     * Method repeats the generation process until the board fully adheres to classic Numberlink rules and has no more numbers than expected for it's size
     *
     * @param size for example board with size 5 is 5x5 char[][]
     * @return generated board with all numbers being 0
     */
    public char[][] generatePaths(int size) {
        char[][] board = new char[size][size];

        do {
            // Pierwszy wiersz wymaga osobnego rozpatrywania
            boolean is1stRowDone = false;
            // countingTillTheEnd odlicza w praktyce wykonania pętli foreach
            int countingTillTheEnd = 0;

            for (char[] row : board) {

                if (!is1stRowDone) {
                    // Na każde pole w pierwszym wierszu bieżemy znak z właściwej tablicy
                    row[0] = tableHub.adjustedAvailableChars1stStartRow[random.nextInt(tableHub.adjustedAvailableChars1stStartRow.length)];

                    for (int i = 1; i < row.length - 1; i++) {

                        switch (row[i - 1]) {
                            case '-':
                                row[i] = tableHub.adjustedAvailableChars1stAfterMinus[random
                                        .nextInt(tableHub.adjustedAvailableChars1stAfterMinus.length)];
                                break;
                            case '|':
                                row[i] = tableHub.adjustedAvailableChars1stAfterPipe[random.nextInt(tableHub.adjustedAvailableChars1stAfterPipe.length)];
                                break;
                            case '0':
                                row[i] = tableHub.adjustedAvailableChars1stAfterNumber[random
                                        .nextInt(tableHub.adjustedAvailableChars1stAfterNumber.length)];
                                break;
                            case 'F':
                                row[i] = tableHub.adjustedAvailableChars1stAfterF[random.nextInt(tableHub.adjustedAvailableChars1stAfterF.length)];
                                break;
                            case 'T':
                                row[i] = tableHub.adjustedAvailableChars1stAfterT[random.nextInt(tableHub.adjustedAvailableChars1stAfterT.length)];
                                break;
                            case 'L':
                                row[i] = tableHub.adjustedAvailableChars1stAfterL[random.nextInt(tableHub.adjustedAvailableChars1stAfterL.length)];
                                break;
                            case 'J':
                                row[i] = tableHub.adjustedAvailableChars1stAfterJ[random.nextInt(tableHub.adjustedAvailableChars1stAfterJ.length)];
                                break;
                        }
                    }
                    {
                        switch (row[row.length - 2]) {
                            case '-':
                                row[row.length - 1] = tableHub.adjustedAvailableChars1stEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableChars1stEndRowAfterMinus.length)];
                                break;
                            case '|':
                                row[row.length - 1] = tableHub.adjustedAvailableChars1stEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableChars1stEndRowAfterPipe.length)];
                                break;
                            case '0':
                                row[row.length - 1] = tableHub.adjustedAvailableChars1stEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableChars1stEndRowAfterNumber.length)];
                                break;
                            case 'F':
                                row[row.length - 1] = tableHub.adjustedAvailableChars1stEndRowAfterF[random.nextInt(tableHub.adjustedAvailableChars1stEndRowAfterF.length)];
                                break;
                            case 'T':
                                row[row.length - 1] = tableHub.adjustedAvailableChars1stEndRowAfterT[random.nextInt(tableHub.adjustedAvailableChars1stEndRowAfterT.length)];
                                break;
                            case 'L':
                                row[row.length - 1] = tableHub.adjustedAvailableChars1stEndRowAfterL[random.nextInt(tableHub.adjustedAvailableChars1stEndRowAfterL.length)];
                                break;
                            case 'J':
                                row[row.length - 1] = tableHub.adjustedAvailableChars1stEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableChars1stEndRowAfterJ.length)];
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
                        row[0] = tableHub.adjustedAvailableCharsConnectingUPTypeFStartRow[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFStartRow.length)];
                    }
                    if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][0])) {
                        row[0] = tableHub.adjustedAvailableCharsConnectingUPTypeTStartRow[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTStartRow.length)];
                    }
                    if (hasNoBottomConnection(board[countingTillTheEnd - 1][0])) {
                        row[0] = tableHub.adjustedAvailableCharsNotConnectingUPStartRow[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPStartRow.length)];
                    }
                    if (board[countingTillTheEnd - 1][0] == '0') {
                        row[0] = row[0] = tableHub.adjustedAvailableCharsStartRow[random.nextInt(tableHub.adjustedAvailableCharsStartRow.length)];
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
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeFAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFAfterMinus.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeTAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTAfterMinus.length)];
                                    // Jeśli sąsiad z góry nie może się łączyś z tym co na dole to wstawiamy odpowiedni znak
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsNotConnectingUPAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPAfterMinus.length)];
                                    // Cyfra może się łączyć w dół ale nie musi, obojętnie
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsAfterMinus.length)];
                                }
                                break;
                            // Dla pozostałych przypadków analogicznie
                            case '|':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeFAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFAfterPipe.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeTAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTAfterPipe.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsNotConnectingUPAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPAfterPipe.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsAfterPipe.length)];
                                }
                                break;
                            case '0':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeFAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFAfterNumber.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeTAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTAfterNumber.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsNotConnectingUPAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPAfterNumber.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsAfterNumber.length)];
                                }
                                break;
                            case 'F':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeFAfterF[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFAfterF.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeTAfterF[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTAfterF.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsNotConnectingUPAfterF[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPAfterF.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsAfterF[random.nextInt(tableHub.adjustedAvailableCharsAfterF.length)];
                                }
                                break;
                            case 'T':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeFAfterT[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFAfterT.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeTAfterT[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTAfterT.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsNotConnectingUPAfterT[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPAfterT.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsAfterT[random.nextInt(tableHub.adjustedAvailableCharsAfterT.length)];
                                }
                                break;
                            case 'L':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeFAfterL[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFAfterL.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeTAfterL[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTAfterL.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsNotConnectingUPAfterL[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPAfterL.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsAfterL[random.nextInt(tableHub.adjustedAvailableCharsAfterL.length)];
                                }
                                break;
                            case 'J':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeFAfterJ[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFAfterJ.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsConnectingUPTypeTAfterJ[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTAfterJ.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsNotConnectingUPAfterJ[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPAfterJ.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsAfterJ[random.nextInt(tableHub.adjustedAvailableCharsAfterJ.length)];
                                }
                                break;
                        }
                    }
                    {
                        // Ostatnie pole w wierszu wymaga własnych instrukcji i tablic
                        switch (row[row.length - 2]) {
                            case '-':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterMinus.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsEndRowAfterMinus.length)];
                                }
                                break;
                            case '|':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterPipe.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsEndRowAfterPipe.length)];
                                }
                                break;
                            case '0':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterNumber.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsEndRowAfterNumber.length)];
                                }
                                break;
                            case 'F':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterF.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterF.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterF.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsEndRowAfterF.length)];
                                }
                                break;
                            case 'T':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterT.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterT.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterT.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsEndRowAfterT.length)];
                                }
                                break;
                            case 'L':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterL.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterL.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterL.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsEndRowAfterL.length)];
                                }
                                break;
                            case 'J':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsNotConnectingUPEndRowAfterJ.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsEndRowAfterJ.length)];
                                }
                                break;
                        }
                    }

                    countingTillTheEnd++;
//						System.out.println("Robi sie srodkowe");

                } else {
                    // Ostatni wiersz wymaga własnych instrukcji i tablic
                    if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][0])) {
                        row[0] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFStartRow[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFStartRow.length)];
                    }
                    if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][0])) {
                        row[0] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTStartRow[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTStartRow.length)];
                    }
                    if (hasNoBottomConnection(board[countingTillTheEnd - 1][0])) {
                        row[0] = tableHub.adjustedAvailableCharsLastNotConnectingUPStartRow[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPStartRow.length)];
                    }
                    if (board[countingTillTheEnd - 1][0] == '0') {
                        row[0] = tableHub.adjustedAvailableCharsLastStartRow[random.nextInt(tableHub.adjustedAvailableCharsLastStartRow.length)];
                    }

                    for (int i = 1; i < row.length - 1; i++) {

                        switch (row[i - 1]) {
                            case '-':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterMinus.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterMinus.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastNotConnectingUPAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPAfterMinus.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsLastAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastAfterMinus.length)];
                                }
                                break;
                            case '|':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterPipe.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterPipe.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastNotConnectingUPAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPAfterPipe.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsLastAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastAfterPipe.length)];
                                }
                                break;
                            case '0':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterNumber.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterNumber.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastNotConnectingUPAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPAfterNumber.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsLastAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastAfterNumber.length)];
                                }
                                break;
                            case 'F':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterF.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterF.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastNotConnectingUPAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPAfterF.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsLastAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastAfterF.length)];
                                }
                                break;
                            case 'T':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterT.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterT.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastNotConnectingUPAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPAfterT.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsLastAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastAfterT.length)];
                                }
                                break;
                            case 'L':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterL.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterL.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastNotConnectingUPAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPAfterL.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsLastAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastAfterL.length)];
                                }
                                break;
                            case 'J':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFAfterJ.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTAfterJ.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][i])) {
                                    row[i] = tableHub.adjustedAvailableCharsLastNotConnectingUPAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPAfterJ.length)];
                                }
                                if (board[countingTillTheEnd - 1][i] == '0') {
                                    row[i] = tableHub.adjustedAvailableCharsLastAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastAfterJ.length)];
                                }
                                break;
                        }
                    }
                    {
                        switch (row[row.length - 2]) {
                            case '-':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastEndRowAfterMinus[random.nextInt(tableHub.adjustedAvailableCharsLastEndRowAfterMinus.length)];
                                }
                                break;
                            case '|':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastEndRowAfterPipe[random.nextInt(tableHub.adjustedAvailableCharsLastEndRowAfterPipe.length)];
                                }
                                break;
                            case '0':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastEndRowAfterNumber[random.nextInt(tableHub.adjustedAvailableCharsLastEndRowAfterNumber.length)];
                                }
                                break;
                            case 'F':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterF.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastEndRowAfterF[random.nextInt(tableHub.adjustedAvailableCharsLastEndRowAfterF.length)];
                                }
                                break;
                            case 'T':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterT.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastEndRowAfterT[random.nextInt(tableHub.adjustedAvailableCharsLastEndRowAfterT.length)];
                                }
                                break;
                            case 'L':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterL.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastEndRowAfterL[random.nextInt(tableHub.adjustedAvailableCharsLastEndRowAfterL.length)];
                                }
                                break;
                            case 'J':
                                if (hasMandatoryBottomConnectionTypeF(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ.length)];
                                }
                                if (hasMandatoryBottomConnectionTypeT(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ.length)];
                                }
                                if (hasNoBottomConnection(board[countingTillTheEnd - 1][row.length - 1])) {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ.length)];
                                }
                                if (board[countingTillTheEnd - 1][row.length - 1] == '0') {
                                    row[row.length - 1] = tableHub.adjustedAvailableCharsLastEndRowAfterJ[random.nextInt(tableHub.adjustedAvailableCharsLastEndRowAfterJ.length)];
                                }
                                break;
                        }
                    }
//						System.out.println("ROBI SIE OSTATNIE");
                }
            }
            if (size > 5) {
                fix2Connections(board);
                fix(board);
//					System.out.println("Fixing");
            }
        } while ((!isClassic(board)) || (countNumbers(board) > maxNumberCount(size)));
        System.out.println("Wygenerowano planszę o wielkości " + size);
//        CSVSaver.rawGeneratorTableToTxt(board);

        return board;
    }

    /**
     * For internal use only, counts numbers
     *
     * @param tableToCountNumbers
     * @return count of numbers in a table
     */
    private int countNumbers(char[][] tableToCountNumbers) {
        int numbercounter = 0;
        for (char[] row : tableToCountNumbers) {
            for (char a : row) {
                if (a == '0') {
                    numbercounter++;
                }
            }
        }
        return numbercounter;

    }

    /**
     * Determines max number count for boards of a given size
     *
     * @param size board size
     * @return max number count
     */
    public int maxNumberCount(int size) {
        switch (size) {
            case 5:
                return Integer.MAX_VALUE;
            case 6:
                return 14;
            case 7:
                return 16;
            case 8:
                return 18;
            case 9:
                return 24;
            default:
                return Integer.MAX_VALUE;
        }
    }

    /**
     * Fix board by adding more numbers when number's connection count is not 1
     *
     * @param tableToFix
     */
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
//						System.out.println("Fixing...");
                        switch (condition) {
                            case 0:
//							System.out.println("Robi sie 0");
                                try {
                                    if (Generator.hasMandatoryBottomConnection(tableToFix[countingTillTheEnd - 1][i])) {
                                        if (occupied) {
                                            tableToFix[countingTillTheEnd - 1][i] = '0';
                                        }
                                        occupied = true;
                                    }
                                } finally {
                                    continue;
                                }
                            case 1:
//							System.out.println("Robi sie 1");
                                try {
                                    if (Generator.hasMandatoryConnectionUP(tableToFix[countingTillTheEnd + 1][i])) {
                                        if (occupied) {
                                            tableToFix[countingTillTheEnd + 1][i] = '0';
                                        }
                                        occupied = true;
                                    }
                                } finally {
                                    continue;
                                }
                            case 2:
//							System.out.println("Robi sie 2");
                                try {
                                    if (Generator.hasMandatoryConnectionLeft(tableToFix[countingTillTheEnd][i + 1])) {
                                        if (occupied) {
                                            tableToFix[countingTillTheEnd][i + 1] = '0';
                                        }
                                        occupied = true;
                                    }
                                } finally {
                                    continue;
                                }
                            case 3:
//							System.out.println("Robi sie 3");
                                try {
                                    if (Generator.hasMandatoryConnectionRight(tableToFix[countingTillTheEnd][i - 1])) {
                                        if (occupied) {
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

    /**
     * Fix boards containing numbers which have 2 connections by replacing numbers with paths
     *
     * @param tableToFix
     */
    @SuppressWarnings("finally")
    void fix2Connections(char[][] tableToFix) {
        int countingTillTheEnd = 0;
        for (char[] row : tableToFix) {
            for (int i = 0; i < row.length; i++) {
                int connectionCounter = 0;
                if (row[i] == '0') {
                    // Konstrukcja z for, switch i try - finally continue pozwala uniknąć błędów czytania poza tablicą (fajne obejście)
                    // Pętla wykonuje się 4 razy szukając połączenia do cyfry z każdej strony
                    // Jeśli je znajdzie to znak posiadający to połączenie zostaje zastąpiony przez cyfrę
                    for (int condition = 0; condition < 10; condition++) {
//						System.out.println("Fixing...");
                        switch (condition) {
                            case 0:
//							System.out.println("Robi sie 0");
                                try {
                                    if (Generator.hasMandatoryBottomConnection(tableToFix[countingTillTheEnd - 1][i])) {
                                        connectionCounter++;
                                    }
                                } finally {
                                    continue;
                                }
                            case 1:
//							System.out.println("Robi sie 1");
                                try {
                                    if (Generator.hasMandatoryConnectionUP(tableToFix[countingTillTheEnd + 1][i])) {
                                        connectionCounter++;
                                    }
                                } finally {
                                    continue;
                                }
                            case 2:
//							System.out.println("Robi sie 2");
                                try {
                                    if (Generator.hasMandatoryConnectionLeft(tableToFix[countingTillTheEnd][i + 1])) {
                                        connectionCounter++;
                                    }
                                } finally {
                                    continue;
                                }
                            case 3:
//							System.out.println("Robi sie 3");
                                try {
                                    if (Generator.hasMandatoryConnectionRight(tableToFix[countingTillTheEnd][i - 1])) {
                                        connectionCounter++;
                                    }
                                } finally {
                                    continue;
                                }
                            case 4:
                                try {
                                    if (Generator.hasMandatoryBottomConnection(tableToFix[countingTillTheEnd - 1][i]) && Generator.hasMandatoryConnectionUP(tableToFix[countingTillTheEnd + 1][i])) {
                                        if (connectionCounter == 2) {
                                            tableToFix[countingTillTheEnd][i] = '|';
                                        }
                                    }
                                } finally {
                                    continue;
                                }
                            case 5:
                                try {
                                    if (Generator.hasMandatoryConnectionLeft(tableToFix[countingTillTheEnd][i + 1]) && Generator.hasMandatoryConnectionRight(tableToFix[countingTillTheEnd][i - 1])) {
                                        if (connectionCounter == 2) {
                                            tableToFix[countingTillTheEnd][i] = '-';
                                        }
                                    }
                                } finally {
                                    continue;
                                }
                            case 6:
                                try {
                                    if (Generator.hasMandatoryConnectionLeft(tableToFix[countingTillTheEnd][i + 1]) && Generator.hasMandatoryConnectionUPTypeJ(tableToFix[countingTillTheEnd + 1][i]) && tableToFix[countingTillTheEnd][i + 1] != 'T') {
                                        if (connectionCounter == 2) {
                                            tableToFix[countingTillTheEnd][i] = 'F';
                                        }
                                    }
                                } finally {
                                    continue;
                                }
                            case 7:
                                try {
                                    if (Generator.hasMandatoryConnectionRight(tableToFix[countingTillTheEnd][i - 1]) && Generator.hasMandatoryConnectionUPTypeL(tableToFix[countingTillTheEnd + 1][i]) && tableToFix[countingTillTheEnd][i - 1] != 'F') {
                                        if (connectionCounter == 2) {
                                            tableToFix[countingTillTheEnd][i] = 'T';
                                        }
                                    }
                                }
                                finally {
                                    continue;
                                }
                            case 8:
                                try {
                                    if (Generator.hasMandatoryConnectionLeft(tableToFix[countingTillTheEnd][i + 1]) && Generator.hasMandatoryBottomConnectionTypeT(tableToFix[countingTillTheEnd - 1][i]) && tableToFix[countingTillTheEnd][i + 1] != 'J') {
                                        if (connectionCounter == 2) {
                                            tableToFix[countingTillTheEnd][i] = 'L';
                                        }
                                    }
                                }
                                finally {
                                    continue;
                                }
                            case 9:
                                try {
                                    if (Generator.hasMandatoryConnectionRight(tableToFix[countingTillTheEnd][i - 1]) && Generator.hasMandatoryBottomConnectionTypeF(tableToFix[countingTillTheEnd - 1][i]) && tableToFix[countingTillTheEnd][i - 1] != 'L') {
                                        if (connectionCounter == 2) {
                                            tableToFix[countingTillTheEnd][i] = 'J';
                                        }
                                    }
                                }
                                finally {
                                    continue;
                                }
                        }
                    }
                }
            }
            countingTillTheEnd++;
        }
    }

    /**
     * Classic boards adhere to classic Numberlink rules
     *
     * @param tableToTest
     * @return
     */
    @SuppressWarnings("finally")
    boolean isClassic(char[][] tableToTest) {
        // Jeśli plansza nie ma pażystej liczby cyfr to nie jest klasyczna
        if (countNumbers(tableToTest) % 2 != 0) {
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
//						System.out.println("It's going REALLY FAST...");
                        switch (condition) {
                            case 0:
//							System.out.println("Robi sie 0");
                                try {
                                    if (Generator.hasMandatoryBottomConnection(tableToTest[countingTillTheEnd - 1][i])) {
                                        if (occupied) {
                                            isnotWrong = false;
                                        }
                                        occupied = true;
                                    }
                                } finally {
                                    continue;
                                }
                            case 1:
//							System.out.println("Robi sie 1");
                                try {
                                    if (Generator.hasMandatoryConnectionUP(tableToTest[countingTillTheEnd + 1][i])) {
                                        if (occupied) {
                                            isnotWrong = false;
                                        }
                                        occupied = true;
                                    }
                                } finally {
                                    continue;
                                }
                            case 2:
//							System.out.println("Robi sie 2");
                                try {
                                    if (Generator.hasMandatoryConnectionLeft(tableToTest[countingTillTheEnd][i + 1])) {
                                        if (occupied) {
                                            isnotWrong = false;
                                        }
                                        occupied = true;
                                    }
                                } finally {
                                    continue;
                                }
                            case 3:
//							System.out.println("Robi sie 3");
                                try {
                                    if (Generator.hasMandatoryConnectionRight(tableToTest[countingTillTheEnd][i - 1])) {
                                        if (occupied) {
                                            isnotWrong = false;
                                        }
                                        occupied = true;
                                    }
                                } finally {
                                    continue;
                                }
                            case 4:
//							System.out.println("Robi sie 4");
                                if (!occupied) {
                                    isnotWrong = false;
                                }
                        }
                    }
                }
            }
            countingTillTheEnd++;
        }
        return isnotWrong;
    }

    /**
     * Replaces 0s with actual numbers
     *
     * @param tableToFill
     * @return returns filled table
     */
    @SuppressWarnings("finally")
    public static char[][] fillWithNumbers(char[][] tableToFill) {
        // licznik do foreach
        int countingTillTheEnd = 0;
        // char 49 to 1
        char numberCounter = 49;
        for (char[] row : tableToFill) {

            // Kiedy wstawimy nową cyfrę to continue tej pętli
            processRow:
            for (int i = 0; i < row.length; i++) {

                if (row[i] == '0') {
                    // liczniki ruchów
                    int horizontalMoveCounter = 0;
                    int verticalMoveCounter = 0;
                    // nie wracamy się
                    boolean goUpBlocked = false;
                    boolean goDownBlocked = false;
                    boolean goRightBlocked = false;
                    boolean goLeftBlocked = false;
                    // co było ostatnio?
                    String lastMove = null;
                    // omijamy problem czytania poza tablicą opisane wyżej
                    for (int condition = 0; condition < 5; condition++) {
                        switch (condition) {
                            // spardzamy gdzie możemy uczynić pierwszy ruch, jak już się odbijemy od zera to łatwiej będzie sprawdzać co powinno być dalej
                            case 0:
                                try {
                                    if (!goUpBlocked && (Generator.hasMandatoryBottomConnection(tableToFill[countingTillTheEnd - 1 + verticalMoveCounter][i + horizontalMoveCounter]))) {
//									System.out.println("Rozpoczęto w górę");
                                        lastMove = "up";
                                        goDownBlocked = true;
                                        verticalMoveCounter--;
                                    }
                                } finally {
                                    continue;
                                }
                            case 1:
                                try {
                                    if (!goDownBlocked && (Generator.hasMandatoryConnectionUP(tableToFill[countingTillTheEnd + 1 + verticalMoveCounter][i + horizontalMoveCounter]))) {
//									System.out.println("Rozpoczęto w dół");
                                        lastMove = "down";
                                        goUpBlocked = true;
                                        verticalMoveCounter++;
                                    }

                                } finally {
                                    continue;
                                }
                            case 2:
                                try {
                                    if (!goRightBlocked && (Generator.hasMandatoryConnectionLeft(tableToFill[countingTillTheEnd + verticalMoveCounter][i + 1 + horizontalMoveCounter]))) {
//									System.out.println("Rozpoczęto w prawo");
                                        lastMove = "right";
                                        goLeftBlocked = true;
                                        horizontalMoveCounter++;
                                    }
                                } finally {
                                    continue;
                                }
                            case 3:
                                try {
                                    if (!goLeftBlocked && (Generator.hasMandatoryConnectionRight(tableToFill[countingTillTheEnd + verticalMoveCounter][i - 1 + horizontalMoveCounter]))) {
//									System.out.println("Rozpoczęto w lewo");
                                        lastMove = "left";
                                        goRightBlocked = true;
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
                    // to mogło by być while true ale tak sie na pewno zakończy w razie jakiegoś problemu
                    // ścieżki na ponad 2000 ruchów raczej nigdy nie będzie
                    more:
                    for (int pathLength = 0; pathLength < 2000; pathLength++) {
                        // (nie) czytamy poza tablicą :)
                        switcher:
                        for (int condition = 0; condition < 5; condition++) {
                            switch (condition) {
                                case 0:
                                    try {
                                        // jeśli ostatni ruch był do góry to sprawdzamy jaki jest znak w miejscu w którym aktualnie jesteśmy i idziemy dalej tam gdzie on wskazuje
                                        if (!goUpBlocked && (lastMove == "up")) {
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '|') {
                                                verticalMoveCounter--;
//											System.out.println("Idzie w górę");
                                                lastMove = "up";
                                                goUpBlocked = false;
                                                goDownBlocked = true;
                                                goRightBlocked = false;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'F') {
                                                horizontalMoveCounter++;
//											System.out.println("Idzie w górę i prawo");
                                                lastMove = "right";
                                                goUpBlocked = false;
                                                goDownBlocked = false;
                                                goRightBlocked = false;
                                                goLeftBlocked = true;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'T') {
                                                horizontalMoveCounter--;
//											System.out.println("Idzie w górę i lewo");
                                                lastMove = "left";
                                                goUpBlocked = false;
                                                goDownBlocked = false;
                                                goRightBlocked = true;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            continue switcher;
                                        }
                                    } finally {
                                        continue;
                                    }
                                case 1:
                                    try {
//								jeśli ostatni ruch w dół to analogicznie
                                        if (!goDownBlocked && (/*Generator.hasMandatoryBottomConnection(tableToFill[countingTillTheEnd - 1 + verticalMoveCounter][i + horizontalMoveCounter]) ||*/ lastMove == "down")) {
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '|') {
                                                verticalMoveCounter++;
//											System.out.println("Idzie w dół");
                                                lastMove = "down";
                                                goUpBlocked = true;
                                                goDownBlocked = false;
                                                goRightBlocked = false;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'L') {
                                                horizontalMoveCounter++;
//											System.out.println("Idzie w dół i prawo");
                                                lastMove = "right";
                                                goUpBlocked = false;
                                                goDownBlocked = false;
                                                goRightBlocked = false;
                                                goLeftBlocked = true;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'J') {
                                                horizontalMoveCounter--;
//											System.out.println("Idzie w dół i lewo");
                                                lastMove = "left";
                                                goUpBlocked = false;
                                                goDownBlocked = false;
                                                goRightBlocked = true;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            continue switcher;
                                        }
                                    } finally {
                                        continue;
                                    }
                                case 2:
                                    try {
//								analogicznie
                                        if (!goRightBlocked && (lastMove == "right")) {
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '-') {
                                                horizontalMoveCounter++;
//											System.out.println("Idzie w prawo");
                                                lastMove = "right";
                                                goUpBlocked = false;
                                                goDownBlocked = false;
                                                goRightBlocked = false;
                                                goLeftBlocked = true;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'T') {
                                                verticalMoveCounter++;
//											System.out.println("Idzie w prawo i w dół");
                                                lastMove = "down";
                                                goUpBlocked = true;
                                                goDownBlocked = false;
                                                goRightBlocked = false;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'J') {
                                                verticalMoveCounter--;
//											System.out.println("Idzie w prawo i w górę");
                                                lastMove = "up";
                                                goUpBlocked = false;
                                                goDownBlocked = true;
                                                goRightBlocked = false;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                        }
                                        continue switcher;
//								}
                                    } finally {
                                        continue;
                                    }
                                case 3:
                                    try {
//								analogicznie
                                        if (!goLeftBlocked && (lastMove == "left")) {
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '-') {
                                                horizontalMoveCounter--;
//											System.out.println("Idzie w lewo");
                                                lastMove = "left";
                                                goUpBlocked = false;
                                                goDownBlocked = false;
                                                goRightBlocked = true;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'F') {
                                                verticalMoveCounter++;
//											System.out.println("Idzie w lewo i w dół");
                                                lastMove = "down";
                                                goUpBlocked = true;
                                                goDownBlocked = false;
                                                goRightBlocked = false;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == 'L') {
                                                verticalMoveCounter--;
//											System.out.println("Idzie w lewo i w górę");
                                                lastMove = "up";
                                                goUpBlocked = false;
                                                goDownBlocked = true;
                                                goRightBlocked = false;
                                                goLeftBlocked = false;
                                                continue switcher;
                                            }
                                            continue switcher;
                                        }
                                    } finally {
                                        continue;
                                    }
                                case 4:
                                    try {
                                        // jeśli znajdziemy inne 0 to zamieniamy je i to z którego przyszliśmy na te same cyfry
                                        if (tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] == '0') {
                                            row[i] = numberCounter;
                                            tableToFill[countingTillTheEnd + verticalMoveCounter][i + horizontalMoveCounter] = numberCounter;
//									System.out.println("Zapisano" + numberCounter);
                                            // zwiększmy licznik cyfr o 1
                                            numberCounter++;
                                            continue processRow;
                                        }
                                    } finally {
                                        // jak trzeba zrobić więcej ruchów to wracamy do pętli more
                                        continue more;
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
    
    class tableHub {
    	// Non-adjusted tables moved to resource folder as they are only templates

        // Dostosowanie częstości występowania

        // Co można wstawić na początek wiersza?
        static final char[] adjustedAvailableCharsStartRow = {'|', '0', 'F', 'F', 'L', 'L'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableCharsAfterMinus = {'-', '-', '-', '-', '0', 'T', 'T', 'J', 'J'};
        // |
        static final char[] adjustedAvailableCharsAfterPipe = {'|', '|', '0', 'F', 'F', 'L', 'L'};
        // Number
        static final char[] adjustedAvailableCharsAfterNumber = {'-', '-', '-', '-', '|', '|', '0', 'T', 'T', 'J', 'J', 'F', 'F', 'L', 'L'};

        // Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
        // kierunku (analogocznie dla pozostałych)
        // F
        static final char[] adjustedAvailableCharsAfterF = {'-', '-', '-', '-', '0', 'J', 'J'};
        // T
        static final char[] adjustedAvailableCharsAfterT = {'|', '|', '0', 'L', 'L'};
        // L
        static final char[] adjustedAvailableCharsAfterL = {'-', '-', '-', '-', '0', 'T', 'T'};
        // J
        static final char[] adjustedAvailableCharsAfterJ = {'|', '|', '0', 'F', 'F'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableCharsEndRow = {'|', '|', '0', 'T', 'T', 'J', 'J'};
        static final char[] adjustedAvailableCharsEndRowAfterMinus = {'0', 'T', 'T', 'J', 'J'};
        static final char[] adjustedAvailableCharsEndRowAfterPipe = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsEndRowAfterNumber = {'|', '|', '0', 'T', 'T', 'J', 'J'};
        static final char[] adjustedAvailableCharsEndRowAfterF = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsEndRowAfterT = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsEndRowAfterL = {'0', 'T', 'T'};
        static final char[] adjustedAvailableCharsEndRowAfterJ = {'|', '|', '0'};

        // PIERWSZY WIERSZ
        // Dla pierwszego wiersza nie może być |, J, L

        // Co można wstawić na początek wiersza?
        static final char[] adjustedAvailableChars1stStartRow = {'0', 'F', 'F'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableChars1stAfterMinus = {'-', '-', '-', '-', '0', 'T', 'T'};
        // |
        static final char[] adjustedAvailableChars1stAfterPipe = {'0', 'F', 'F'};
        // Number
        static final char[] adjustedAvailableChars1stAfterNumber = {'-', '-', '-', '-', '0', 'T', 'T', 'F', 'F'};

        // Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
        // kierunku (analogocznie dla pozostałych)
        // F
        static final char[] adjustedAvailableChars1stAfterF = {'-', '-', '-', '-', '0'};
        // T
        static final char[] adjustedAvailableChars1stAfterT = {'0'};
        // L
        static final char[] adjustedAvailableChars1stAfterL = {'-', '-', '-', '-', '0', 'T', 'T'};
        // J
        static final char[] adjustedAvailableChars1stAfterJ = {'0', 'F', 'F'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableChars1stEndRow = {'0', 'T', 'T'};
        static final char[] adjustedAvailableChars1stEndRowAfterMinus = {'0', 'T', 'T'};
        static final char[] adjustedAvailableChars1stEndRowAfterPipe = {'0'};
        static final char[] adjustedAvailableChars1stEndRowAfterNumber = {'0', 'T', 'T'};
        static final char[] adjustedAvailableChars1stEndRowAfterF = {'0'};
        static final char[] adjustedAvailableChars1stEndRowAfterT = {'0'};
        static final char[] adjustedAvailableChars1stEndRowAfterL = {'0', 'T', 'T'};
        static final char[] adjustedAvailableChars1stEndRowAfterJ = {'0'};

        // OSTATNI WIERSZ
        // Dla ostatniego wiersza nie może być |, F, T

        // Co można wstawić na początek wiersza?
        static final char[] adjustedAvailableCharsLastStartRow = {'0', 'L', 'L'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableCharsLastAfterMinus = {'-', '-', '-', '-', '0', 'J', 'J'};
        // |
        static final char[] adjustedAvailableCharsLastAfterPipe = {'0', 'L', 'L'};
        // Number
        static final char[] adjustedAvailableCharsLastAfterNumber = {'-', '-', '-', '-', '0', 'J', 'J', 'L', 'L'};

        // Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
        // kierunku (analogocznie dla pozostałych)
        // F
        static final char[] adjustedAvailableCharsLastAfterF = {'-', '-', '-', '-', '0', 'J', 'J'};
        // T
        static final char[] adjustedAvailableCharsLastAfterT = {'0', 'L', 'L'};
        // L
        static final char[] adjustedAvailableCharsLastAfterL = {'-', '-', '-', '-', '0'};
        // J
        static final char[] adjustedAvailableCharsLastAfterJ = {'0'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableCharsLastEndRow = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastEndRowAfterMinus = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastEndRowAfterPipe = {'0'};
        static final char[] adjustedAvailableCharsLastEndRowAfterNumber = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastEndRowAfterF = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastEndRowAfterT = {'0'};
        static final char[] adjustedAvailableCharsLastEndRowAfterL = {'0'};
        static final char[] adjustedAvailableCharsLastEndRowAfterJ = {'0'};


        // SPRAWDZANIE CO JEST POWYŻEJ

        // obowiązkowe połączenie z tym co powyżej TYP F
        // Co można wstawić na początek wiersza?
        static final char[] adjustedAvailableCharsConnectingUPTypeFStartRow = {'|', '|', '0'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableCharsConnectingUPTypeFAfterMinus = {'0', 'J', 'J'};
        // |
        static final char[] adjustedAvailableCharsConnectingUPTypeFAfterPipe = {'|', '|', '0'};
        // Number
        static final char[] adjustedAvailableCharsConnectingUPTypeFAfterNumber = {'|', '|', '0', 'J', 'J'};

        // F
        static final char[] adjustedAvailableCharsConnectingUPTypeFAfterF = {'0', 'J', 'J'};
        // T
        static final char[] adjustedAvailableCharsConnectingUPTypeFAfterT = {'|', '|', '0'};
        // L
        static final char[] adjustedAvailableCharsConnectingUPTypeFAfterL = {'0'};
        // J
        static final char[] adjustedAvailableCharsConnectingUPTypeFAfterJ = {'|', '|', '0'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRow = {'|', '|', '0', 'J', 'J'};
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterMinus = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterPipe = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterNumber = {'|', '|', '0', 'J', 'J'};
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterF = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterT = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterL = {'0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeFEndRowAfterJ = {'|', '|', '0'};

        // OSTATNI WIERSZ
        // Dla ostatniego wiersza nie może być |, F, T
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFStartRow = {'0'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterMinus = {'0', 'J', 'J'};
        // |
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterPipe = {'0'};
        // Number
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterNumber = {'0', 'J', 'J'};

        // F
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterF = {'0', 'J', 'J'};
        // T
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterT = {'0',};
        // L
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterL = {'0'};
        // J
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFAfterJ = {'0'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRow = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterMinus = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterPipe = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterNumber = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterF = {'0', 'J', 'J'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterT = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterL = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeFEndRowAfterJ = {'0'};

        // obowiązkowe połączenie z tym co powyżej TYP T
        // Co można wstawić na początek wiersza?
        static final char[] adjustedAvailableCharsConnectingUPTypeTStartRow = {'|', '|', '0', 'L', 'L'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableCharsConnectingUPTypeTAfterMinus = {'0'};
        // |
        static final char[] adjustedAvailableCharsConnectingUPTypeTAfterPipe = {'|', '|', '0', 'L', 'L'};
        // Number
        static final char[] adjustedAvailableCharsConnectingUPTypeTAfterNumber = {'|', '|', '0', 'L', 'L'};

        // F
        static final char[] adjustedAvailableCharsConnectingUPTypeTAfterF = {'0'};
        // T
        static final char[] adjustedAvailableCharsConnectingUPTypeTAfterT = {'|', '|', '0', 'L', 'L'};
        // L
        static final char[] adjustedAvailableCharsConnectingUPTypeTAfterL = {'0'};
        // J
        static final char[] adjustedAvailableCharsConnectingUPTypeTAfterJ = {'|', '|', '0'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRow = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterMinus = {'0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterPipe = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterNumber = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterF = {'0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterT = {'|', '|', '0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterL = {'0'};
        static final char[] adjustedAvailableCharsConnectingUPTypeTEndRowAfterJ = {'|', '|', '0'};

        // OSTATNI WIERSZ
        // Dla ostatniego wiersza nie może być |, F, T
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTStartRow = {'0', 'L', 'L'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterMinus = {'0'};
        // |
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterPipe = {'0', 'L', 'L'};
        // Number
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterNumber = {'0', 'L', 'L'};

        // F
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterF = {'0'};
        // T
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterT = {'0', 'L', 'L'};
        // L
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterL = {'0'};
        // J
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTAfterJ = {'0'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRow = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterMinus = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterPipe = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterNumber = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterF = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterT = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterL = {'0'};
        static final char[] adjustedAvailableCharsLastConnectingUPTypeTEndRowAfterJ = {'0'};

        // obowiązkowy brak połączenia z tym co powyżej

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
        static final char[] adjustedAvailableCharsLastNotConnectingUPStartRow = {'0'};

        // Co może być dalej?
        // -
        static final char[] adjustedAvailableCharsLastNotConnectingUPAfterMinus = {'-', '-', '-', '-', '0'};
        // |
        static final char[] adjustedAvailableCharsLastNotConnectingUPAfterPipe = {'0'};
        // Number
        static final char[] adjustedAvailableCharsLastNotConnectingUPAfterNumber = {'-', '-', '-', '-', '0'};

        // Aby nie było kwadratów 2x2 to po F nie może być T bo skręcają w tym samym
        // kierunku (analogocznie dla pozostałych)
        // F
        static final char[] adjustedAvailableCharsLastNotConnectingUPAfterF = {'-', '-', '-', '-', '0'};
        // T
        static final char[] adjustedAvailableCharsLastNotConnectingUPAfterT = {'0'};
        // L
        static final char[] adjustedAvailableCharsLastNotConnectingUPAfterL = {'-', '-', '-', '-', '0'};
        // J
        static final char[] adjustedAvailableCharsLastNotConnectingUPAfterJ = {'0'};

        // Jak będzie można zakończyć wiersz? Inaczej dla każdego znaku poprzedzającego!
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRow = {'0'};
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterMinus = {'0'};
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterPipe = {'0'};
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterNumber = {'0'};
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterF = {'0'};
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterT = {'0'};
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterL = {'0'};
        static final char[] adjustedAvailableCharsLastNotConnectingUPEndRowAfterJ = {'0'};
    }
}
