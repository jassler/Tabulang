package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class AddNode extends BinaryArithmeticNode {
    public AddNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        try {
            if ((left instanceof InternalString) || (right instanceof InternalString)) {
                return new InternalString(left.toString() + right.toString());
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
