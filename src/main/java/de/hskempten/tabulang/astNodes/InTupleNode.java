package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class InTupleNode extends BinaryPredicateNode {
    public InTupleNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = getRightNode().evaluateNode(interpretation);
        if(o instanceof Tuple) {
            if (((Tuple) o).getElements().contains(getLeftNode().evaluateNode(interpretation))) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new IllegalArgumentException("Expected Tuple but got: " + o.getClass().getSimpleName());
        }
    }

    @Override
    public String toString() {
        return "InTupleNode{} " + super.toString();
    }
}
