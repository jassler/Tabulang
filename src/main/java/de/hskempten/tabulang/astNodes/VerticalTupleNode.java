package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class VerticalTupleNode extends Node{
    private TupleNode tupleNode;

    public VerticalTupleNode(TupleNode tupleNode) {
        super();
        this.tupleNode = tupleNode;
    }

    public TupleNode getTupleNode() {
        return tupleNode;
    }

    public void setTupleNode(TupleNode tupleNode) {
        this.tupleNode = tupleNode;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        tupleNode.getTuple().setHorizontal(false);
        return tupleNode.evaluateNode(interpretation);
    }
}
