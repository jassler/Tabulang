package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class AddNode extends BinaryArithmeticNode{
    public AddNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getStringOrNumericValue(getLeftNode(), interpretation);
        Object right = getStringOrNumericValue(getRightNode(), interpretation);
        if((left instanceof String) || (right instanceof String)){
            return left.toString() + right.toString();
        } else {
            return ((BigDecimal) left).add((BigDecimal) right);
        }
    }

    @Override
    public String toString() {
        return "AddNode{} " + super.toString();
    }
}
