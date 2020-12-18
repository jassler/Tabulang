package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class HorizontalTupleNode extends TermNode{
    private TermNode node;

    public HorizontalTupleNode(TermNode tupleNode) {
        this.node = tupleNode;
    }

    public TermNode getTupleNode() {
        return node;
    }

    public void setTupleNode(TermNode tupleNode) {
        this.node = tupleNode;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tuple = node.evaluateNode(interpretation);
        if(tuple instanceof Tuple){
            ((Tuple<?>) tuple).setHorizontal(true);
            return tuple;
        } else {
            throw new IllegalArgumentException("Expected Tuple but got " + tuple.getClass().getSimpleName());
        }
    }
}
