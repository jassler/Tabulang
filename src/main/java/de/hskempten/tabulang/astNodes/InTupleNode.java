package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class InTupleNode extends BinaryPredicateNode {
    public InTupleNode(IdentifierNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tupleObject = getRightNode().evaluateNode(interpretation);
        Object identifier = getLeftNode().evaluateNode(interpretation);
        if (!(tupleObject instanceof Tuple tuple)) {
            throw new IllegalOperandArgumentException("Operation '" + tupleObject + " (" + tupleObject.getClass() + ") in " + identifier + " (" + identifier.getClass() + ") can not be executed. " +
                    "No tuple on left side of the 'in' operator.");
        }
        if (tuple.getElements().contains(identifier)) {
            return new InternalBoolean(true);
        } else {
            return new InternalBoolean(false);
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + " in " + getRightNode();
    }
}
