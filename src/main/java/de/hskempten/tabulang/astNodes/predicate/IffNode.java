package de.hskempten.tabulang.astNodes.predicate;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IffNode extends BinaryPredicateNode {
    public IffNode(PredicateNode leftNode, PredicateNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Compares two evaluated nodes to decide if both boolean values are equal.
     *
     * @return InternalBoolean with value true if both boolean values are equal,
     * InternalBoolean with value false otherwise.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalBoolean leftBool = getLeftNode().verifyAndReturnBoolean(interpretation);
        InternalBoolean rightBool = getRightNode().verifyAndReturnBoolean(interpretation);
        return new InternalBoolean(leftBool.getBoolean() == rightBool.getBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " iff " + getRightNode();
    }
}
