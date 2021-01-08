package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class TupleNameNotFoundException extends RuntimeException{
    public TupleNameNotFoundException(String name){
        super("Name '" + name + "' does not exist in tuple");
    }

    public TupleNameNotFoundException(TextPosition term, String headerName, String termClassName, String termContent) {
        super(term + "can not be executed. Name '" + headerName + "' does not exist in " + termClassName + "(" + termContent + ").");
    }

}
