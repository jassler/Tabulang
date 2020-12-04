package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class MultiplyNode extends ArithmeticNode{
    public MultiplyNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return convertLeftNodeToBigDecimal(interpretation).multiply(convertRightNodeToBigDecimal(interpretation));
    }
}
