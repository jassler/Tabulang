package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IffNode extends BinaryPredicateNode {
    public IffNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean leftBool = verifyAndReturnBoolean(getLeftNode(), interpretation);
        InternalBoolean rightBool = verifyAndReturnBoolean(getRightNode(), interpretation);
        return new InternalBoolean(leftBool.getBoolean() == rightBool.getBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " iff " + getRightNode();
    }
}
