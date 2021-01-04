package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class AndNode extends BinaryPredicateNode{
    public AndNode(PredicateNode leftNode, PredicateNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        try {
            Object left = getLeftNode().evaluateNode(interpretation);
            Object right = getRightNode().evaluateNode(interpretation);
            if (!(left instanceof InternalBoolean leftBool) || !(right instanceof InternalBoolean rightBool)) {
                throw new IllegalBooleanOperandArgumentException(toString());
            }
            return new InternalBoolean(leftBool.getaBoolean() && rightBool.getaBoolean());
        } catch (IllegalBooleanOperandArgumentException booleanOperandArgumentException){
            interpretation.exitProgram(booleanOperandArgumentException);
        }
        return null;
    }

    @Override
    public String toString() {
        return getLeftNode().toString() + " && " + getRightNode().toString();
    }
}
