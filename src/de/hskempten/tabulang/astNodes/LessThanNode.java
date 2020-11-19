package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class LessThanNode extends AtomicPredicateNode {
    public LessThanNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation i) {
        return convertLeftNodeToBigDecimal(i).compareTo(convertRightNodeToBigDecimal(i)) == -1;
    }

    @Override
    public String toString() {
        return "LessThanNode{}";
    }
}
