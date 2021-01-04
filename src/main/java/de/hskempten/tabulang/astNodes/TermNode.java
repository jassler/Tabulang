package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public abstract class TermNode extends Node{


    public Table checkIfTable(Object o) {
        o = ifTupleTransform(o);
        if(o instanceof Table) {
            return (Table) o;
        } else {
            throw new IllegalArgumentException( o + " (" + o.getClass() + ") is not a table.");
        }
    }

    public Object ifTupleTransform(Object o){
        if (o instanceof Tuple) {
            o = ((Tuple<?>) o).transformIntoTable();
        }
        return o;
    }
}
