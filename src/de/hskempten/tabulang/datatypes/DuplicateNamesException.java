package de.hskempten.tabulang.datatypes;

public class DuplicateNamesException extends RuntimeException {

    public DuplicateNamesException(String varName) {
        super("Duplicate variable \"" + varName + "\" in Tuple names");
    }

}
