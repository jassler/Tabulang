package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class GreaterThanOrEqualToNode extends PredicateNode {
    public GreaterThanOrEqualToNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return convertLeftNodeToBigDecimal(interpretation).compareTo(convertRightNodeToBigDecimal(interpretation)) >= 0;
    }
}
