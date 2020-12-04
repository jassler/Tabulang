package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class GreaterThanNode extends PredicateNode {
    public GreaterThanNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return convertLeftNodeToBigDecimal(interpretation).compareTo(convertRightNodeToBigDecimal(interpretation)) == 1;

    }
}
