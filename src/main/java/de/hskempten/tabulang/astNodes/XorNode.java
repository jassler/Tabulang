package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class XorNode extends BinaryPredicateNode{
    public XorNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean leftBool) || !(right instanceof InternalBoolean rightBool)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        return leftBool.getaBoolean() ^ rightBool.getaBoolean();
    }

    @Override
    public String toString() {
        return getLeftNode() + " ^ " + getRightNode();
    }
}
