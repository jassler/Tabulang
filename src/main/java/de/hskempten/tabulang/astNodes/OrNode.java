package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class OrNode extends BinaryPredicateNode{
    public OrNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean leftBoolean) || !(right instanceof InternalBoolean rightBoolean)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        return new InternalBoolean(leftBoolean.getaBoolean() || rightBoolean.getaBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " || " + getRightNode();
    }
}
