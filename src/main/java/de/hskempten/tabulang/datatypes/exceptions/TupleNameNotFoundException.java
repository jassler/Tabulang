package de.hskempten.tabulang.datatypes.exceptions;

import de.hskempten.tabulang.datatypes.Tuple;

public class TupleNameNotFoundException extends RuntimeException{
    public TupleNameNotFoundException(String name){
        super("Name '" + name + "' does not exist in tuple");
    }
}
