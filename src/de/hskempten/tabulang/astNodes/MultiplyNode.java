package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class MultiplyNode extends ArithmeticNode{
    public MultiplyNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation i) {
        return convertLeftNodeToBigDecimal(i).multiply(convertRightNodeToBigDecimal(i));
    }
}
