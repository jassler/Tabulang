package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class InTupleNode extends BinaryPredicateNode {
    public InTupleNode(IdentifierNode leftNode, TermNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tupleObject = getRightNode().evaluateNode(interpretation);
        Object identifier = getLeftNode().evaluateNode(interpretation);
        if (!(tupleObject instanceof Tuple tuple)) {
            throw new IllegalOperandArgumentException("Operation '" + tupleObject + " (" + tupleObject.getClass() + ") in " + identifier + " (" + identifier.getClass() + ") can not be executed. " +
                    "Allowed operands: Boolean.");
        }
        if (tuple.getElements().contains(identifier)) {
            return new InternalBoolean(true);
        } else {
            return new InternalBoolean(false);
        }
    }

    @Override
    public String toString() {
        return "InTupleNode{} " + super.toString();
    }
}
