package de.hskempten.tabulang.datatypes.exceptions;

public class DuplicateNamesException extends DataTypeException {

    public DuplicateNamesException(String varName) {
        super("Duplicate variable \"" + varName + "\" in Tuple names");
    }

}
