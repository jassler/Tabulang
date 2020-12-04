package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class PowerNode extends ArithmeticNode{
    public PowerNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return convertLeftNodeToBigDecimal(interpretation).pow(convertRightNodeToBigDecimal(interpretation).intValue());
    }
}
