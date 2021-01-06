package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class DivisionNode extends BinaryArithmeticNode {
    public DivisionNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (left instanceof InternalNumber && right instanceof InternalNumber) {
            return ((InternalNumber) left).divide((InternalNumber) right);
        } else {
            throw new IllegalNumberOperandArgumentException(toString());
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + " / " + getRightNode();
    }
}
