package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class LessThanNode extends PredicateNode {
    public LessThanNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return convertLeftNodeToBigDecimal(interpretation).compareTo(convertRightNodeToBigDecimal(interpretation)) == -1;
    }

    @Override
    public String toString() {
        return "LessThanNode{}";
    }
}
