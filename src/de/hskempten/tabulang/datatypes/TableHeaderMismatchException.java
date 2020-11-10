package de.hskempten.tabulang.datatypes;

import java.util.List;

public class TableHeaderMismatchException extends DataTypeException {

    public TableHeaderMismatchException(List<String> t1Header,List<String> t2Header) {
        super("Tables are expected to have the same column headers, instead got:\nt1: " + t1Header + "\nt2: " + t2Header);
    }

}
