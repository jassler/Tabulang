package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class AndNode extends BinaryPredicateNode {
    public AndNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        throwExceptionIfNotBoolean(left, right);
        return new InternalBoolean(((InternalBoolean)left).getaBoolean() && ((InternalBoolean) right).getaBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode().toString() + " && " + getRightNode().toString();
    }
}
