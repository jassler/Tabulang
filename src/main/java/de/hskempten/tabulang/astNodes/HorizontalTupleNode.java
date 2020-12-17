package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class HorizontalTupleNode extends Node{
    private TupleNode tupleNode;

    public HorizontalTupleNode() {
    }

    public TupleNode getTupleNode() {
        return tupleNode;
    }

    public void setTupleNode(TupleNode tupleNode) {
        this.tupleNode = tupleNode;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        tupleNode.getTuple().setHorizontal(true);
        return tupleNode.evaluateNode(interpretation);
    }
}
