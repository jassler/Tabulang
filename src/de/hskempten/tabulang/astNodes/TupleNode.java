package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

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
    public Object evaluateNode(Interpretation i) {
        return tuple;
    }
}
