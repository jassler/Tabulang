package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class PowerNode extends BinaryArithmeticNode{
    public PowerNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return "Pow Operation not yet implemented";
        //return getNumericValue(getLeftNode(), interpretation).pow((getNumericValue(getRightNode(), interpretation)).intValue());
    }
}
