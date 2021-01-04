package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class IffNode extends BinaryPredicateNode{
    public IffNode(PredicateNode leftNode, PredicateNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);
        if (!(left instanceof InternalBoolean leftBool) || !(right instanceof InternalBoolean rightBool)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        return new InternalBoolean(leftBool.getaBoolean() == rightBool.getaBoolean());
    }

    @Override
    public String toString() {
        return getLeftNode() + " iff " + getRightNode();
    }
}
