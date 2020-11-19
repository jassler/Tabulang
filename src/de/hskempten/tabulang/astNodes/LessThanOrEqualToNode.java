package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class LessThanOrEqualToNode extends AtomicPredicateNode {
    public LessThanOrEqualToNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation i) {
        return convertLeftNodeToBigDecimal(i).compareTo(convertRightNodeToBigDecimal(i)) <= 0;
    }
}
