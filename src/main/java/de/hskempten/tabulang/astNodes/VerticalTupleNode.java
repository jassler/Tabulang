package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class VerticalTupleNode extends TermNode{
    private TermNode node;

    public VerticalTupleNode(TermNode tupleNode) {
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
        Object o = node.evaluateNode(interpretation);
        if (!(o instanceof Tuple tuple)) {
            throw new IllegalTupleOperandArgumentException(toString());
        }
        tuple.setHorizontal(false);
        return o;
    }

    @Override
    public String toString() {
        return "vertical " + node;
    }
}
