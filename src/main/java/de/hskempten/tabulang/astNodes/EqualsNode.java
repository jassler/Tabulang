package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class EqualsNode extends BinaryPredicateNode {
    public EqualsNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        Object right = getRightNode().evaluateNode(interpretation);

        if(left instanceof InternalString l && right instanceof InternalString r) {
            return new InternalBoolean(l.equals(r));
        }

        if (!(left instanceof InternalNumber leftNumber) || !(right instanceof InternalNumber rightNumber)) {
            throw new IllegalBooleanOperandArgumentException(toString());
        }
        return new InternalBoolean(leftNumber.compareTo(rightNumber) == 0);
    }

    @Override
    public String toString() {
        return getLeftNode() + " == " + getRightNode();
    }
}
