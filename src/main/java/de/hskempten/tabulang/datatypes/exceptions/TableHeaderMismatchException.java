package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.datatypes.InternalString;

import java.util.List;
import java.util.stream.Collectors;

public class TableHeaderMismatchException extends DataTypeException {

    public TableHeaderMismatchException(List<InternalString> t1Header, List<InternalString> t2Header) {
        super(
                "Tables are expected to have the same column headers, instead got:\n"
                + "t1: " + t1Header.stream().map(InternalString::getString).collect(Collectors.joining(", ")) + "\n"
                + "t2: " + t2Header.stream().map(InternalString::getString).collect(Collectors.joining(", "))
        );
    }

}
