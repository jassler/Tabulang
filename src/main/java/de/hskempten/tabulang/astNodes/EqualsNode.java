package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class EqualsNode extends AtomicPredicateNode {
    public EqualsNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Boolean evaluateNode(Interpretation i) {
        return convertLeftNodeToBigDecimal(i).compareTo(convertRightNodeToBigDecimal(i)) == 0;
    }
}
