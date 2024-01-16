package main.utils;

/**
 * Contains methods determining connection types for chars used in the Generator
 */

class ConnectionTypes {
	
	protected final static boolean hasMandatoryBottomConnectionTypeF(char a) {
        return (a == '|') || (a == 'F');
    }

    protected final static boolean hasMandatoryBottomConnectionTypeT(char a) {
        return (a == '|') || (a == 'T');
    }

    protected final static boolean hasMandatoryBottomConnection(char a) {
        return (a == '|') || (a == 'F') || (a == 'T');
    }

    protected final static boolean hasNoBottomConnection(char a) {
        return (a == '-') || (a == 'L') || (a == 'J');
    }

    protected  final static boolean hasMandatoryConnectionUP(char a) {
        return (a == '|') || (a == 'L') || (a == 'J');
    }
    
    protected final static boolean hasMandatoryConnectionUPTypeL(char a) {
        return (a == '|') || (a == 'L');
    }
    
    protected final static boolean hasMandatoryConnectionUPTypeJ(char a) {
        return (a == '|') || (a == 'J');
    }

    protected final static boolean hasMandatoryConnectionRight(char a) {
        return (a == '-') || (a == 'F') || (a == 'L');
    }

    protected final static boolean hasMandatoryConnectionLeft(char a) {
        return (a == '-') || (a == 'T') || (a == 'J');
    }

}
