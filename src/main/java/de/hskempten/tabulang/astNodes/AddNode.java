package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class AddNode extends BinaryArithmeticNode {
    public AddNode(TermNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        try {
            if ((left instanceof InternalString) || (right instanceof InternalString)) {
                return left.toString() + right.toString();
            } else if (left instanceof InternalNumber leftNumber && right instanceof InternalNumber rightNumber) {
                return leftNumber.add(rightNumber);
            } else {
                throw new IllegalOperandArgumentException("Operation '" + toString() + "' can not be executed. " +
                        "Allowed operands: Strings and/or Numbers.");
            }
        } catch (Exception e) {
            interpretation.exitProgram(e);
        }
        return null;
    }

    @Override
    public String toString() {
        return getLeftNode() + " + " + getRightNode();
    }
}
