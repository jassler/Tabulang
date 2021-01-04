package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class OrNode extends BinaryPredicateNode{
    public OrNode(PredicateNode leftNode, PredicateNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean l) || !(right instanceof InternalBoolean r)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        return new InternalBoolean(l.getaBoolean() || r.getaBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " || " + getRightNode();
    }
}
