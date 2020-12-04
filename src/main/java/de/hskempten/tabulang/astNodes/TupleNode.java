package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.Iterator;
import java.util.Map;

public class TupleNode extends Node{
    private Tuple tuple;

    public TupleNode(Tuple tuple) {
        super(NodeType.TUPLE);
        this.tuple = tuple;
    }

    public Tuple getTuple() {
        return tuple;
    }

    public void setTuple(Tuple tuple) {
        this.tuple = tuple;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        //Evaluierung der Elemente ohne schreiben des Ergebnisses
        /*for(Object o : tuple.getElements()) {
            ((Node) o).evaluateNode(i);
        }*/

        //Mit Evaluierung und ändern des Tupels
        for(int j = 0; j < tuple.getElements().size(); j++) {
            tuple.getElements().set(j, ((Node) tuple.getElements().get(j)).evaluateNode(interpretation));
        }
        return tuple;
    }

    @Override
    public String toString() {
        return "TupleNode{" +
                "tuple=" + tuple +
                "} " ;
    }
}
