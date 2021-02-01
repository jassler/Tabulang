package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class OrNode extends BinaryPredicateNode {
    public OrNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean leftBool = verifyAndReturnBoolean(getLeftNode(), interpretation);
        InternalBoolean rightBool = verifyAndReturnBoolean(getRightNode(), interpretation);
        return new InternalBoolean(leftBool.getaBoolean() || rightBool.getaBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " || " + getRightNode();
    }
}
