package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MultiplyNode extends BinaryArithmeticNode {
    public MultiplyNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        throwExceptionIfNotNumbers(left, right);
        return ((InternalNumber)left).multiply(((InternalNumber) right));
    }

    @Override
    public String toString() {
        return getLeftNode() + " * " + getRightNode();
    }
}
