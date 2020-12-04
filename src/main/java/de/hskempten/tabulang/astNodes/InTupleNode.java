package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class InTupleNode extends PredicateNode {
    public InTupleNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if(((Tuple) getRightNode().evaluateNode(interpretation)).getElements().contains(getLeftNode().evaluateNode(interpretation))){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "InTupleNode{} " + super.toString();
    }
}
