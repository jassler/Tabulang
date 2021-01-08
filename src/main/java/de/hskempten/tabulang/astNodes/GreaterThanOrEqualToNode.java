package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class GreaterThanOrEqualToNode extends BinaryPredicateNode {
    public GreaterThanOrEqualToNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        throwExceptionIfNotNumbers(left, right);
        return new InternalBoolean(((InternalNumber)left).compareTo(((InternalNumber) right)) >= 0);
    }

    @Override
    public String toString() {
        return getLeftNode() + " >= " + getRightNode();
    }
}
