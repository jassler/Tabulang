package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ModNode extends BinaryArithmeticNode{
    public ModNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(left instanceof InternalNumber leftNumber) || !(right instanceof InternalNumber rightNumber)) {
            throw new IllegalNumberOperandArgumentException(getTextPosition(), getLeftNode().getTextPosition(), left.getClass().getSimpleName(), getRightNode().getTextPosition(), right.getClass().getSimpleName());
        }
        return leftNumber.mod(rightNumber);
    }

    @Override
    public String toString() {
        return getLeftNode() + " mod " + getRightNode();
    }
}
