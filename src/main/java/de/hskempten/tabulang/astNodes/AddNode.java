package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class AddNode extends BinaryArithmeticNode{
    public AddNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
            Object left = getLeftNode().evaluateNode(interpretation);
            Object right = getRightNode().evaluateNode(interpretation);
            if ((left instanceof String) || (right instanceof String)) {
                return left.toString() + right.toString();
            } else if (left instanceof InternalNumber && right instanceof InternalNumber) {
                return ((InternalNumber) left).add((InternalNumber) right);
            } else {
                throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") + " + right + " (" + right.getClass() + ") can not be executed. " +
                        "Allowed operands: Strings and/or Numbers.");
            }
    }

    @Override
    public String toString() {
        return "AddNode{} " + super.toString();
    }
}
