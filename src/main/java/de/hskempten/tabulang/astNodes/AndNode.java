package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class AndNode extends BinaryPredicateNode {
    public AndNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean leftBool = verifyAndReturnBoolean(getLeftNode(), interpretation);
        InternalBoolean rightBool = verifyAndReturnBoolean(getRightNode(), interpretation);
        return new InternalBoolean(leftBool.getBoolean() && rightBool.getBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode().toString() + " && " + getRightNode().toString();
    }
}
