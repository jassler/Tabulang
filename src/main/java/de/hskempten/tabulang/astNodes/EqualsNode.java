package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class EqualsNode extends PredicateNode {
    public EqualsNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Boolean evaluateNode(Interpretation interpretation) {
        return convertLeftNodeToBigDecimal(interpretation).compareTo(convertRightNodeToBigDecimal(interpretation)) == 0;
    }

    @Override
    public String toString() {
        return "EqualsNode{} " + super.toString();
    }
}
