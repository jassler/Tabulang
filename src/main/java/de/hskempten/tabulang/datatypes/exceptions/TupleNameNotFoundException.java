package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.datatypes.HeaderNames;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class TupleNameNotFoundException extends IllegalOperandArgumentException{
    public TupleNameNotFoundException(String name, ArrayList<InternalString> names){
        super("Name '" + name + "' does not exist in tuple. Tuple contains following names: " + names);
    }

    public TupleNameNotFoundException(TextPosition term, String headerName, String termClassName, String termContent, ArrayList<InternalString> names) {
        super(term, "Name '" + headerName + "' does not exist in " + termClassName + "(" + termContent + "). ", termContent + " contains following names: " + names);
    }

}
