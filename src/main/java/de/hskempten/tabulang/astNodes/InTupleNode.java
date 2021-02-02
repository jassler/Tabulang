package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.TupleOperation;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleOperandArgumentException;
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
        tupleObject = ifTupleTransform(tupleObject);
        if (!(tupleObject instanceof TupleOperation<?> tupleOperation)) {
            throw new IllegalTupleOperandArgumentException(getTextPosition(), tupleObject.getClass().getSimpleName(), getRightNode().getTextPosition().getContent());
        }
        if (tupleObject instanceof Tuple<?> tuple) {
            if (tuple.getElements().contains(identifier)) {
                return new InternalBoolean(true);
            } else {
                return new InternalBoolean(false);
            }
        } else {
            for(Tuple<?> t : (Table<?>) tupleObject) {
                if (t.equals(identifier))
                    return new InternalBoolean(true);
                if(t.getElements().contains(identifier))
                    return new InternalBoolean(true);
            }
            return new InternalBoolean(false);
        }
    }

    @Override
    public String toString() {
        return getLeftNode() + " in " + getRightNode();
    }
}
