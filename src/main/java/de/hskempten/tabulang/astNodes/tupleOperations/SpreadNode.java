package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;
import java.util.ArrayList;

public class SpreadNode extends BinaryTermNode {
    public SpreadNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object leftObject = getLeftNode().evaluateNode(interpretation);
        Object rightObject = getRightNode().evaluateNode(interpretation);

        if (!(leftObject instanceof InternalNumber leftNumber))
            throw new IllegalNumberArgumentException(getTextPosition(), leftObject.getClass().getSimpleName(), getLeftNode().getTextPosition().getContent());

        if (!(rightObject instanceof InternalNumber rightNumber))
            throw new IllegalNumberArgumentException(getTextPosition(), rightObject.getClass().getSimpleName(), getRightNode().getTextPosition().getContent());

        if (leftNumber.compareTo(rightNumber) > 0)
            throw new IllegalNumberArgumentException(getTextPosition(), " Left value has to be less than or equal to right value.");


        ArrayList<InternalNumber> intervalArray = new ArrayList<>();

        Object leftValueObject = leftNumber.getValue();
        Object rightValueObject = rightNumber.getValue();

        if (!(leftValueObject instanceof Integer leftValue && rightValueObject instanceof Integer rightValue))
            throw new InterpreterException("Operation '" + leftValueObject + " ... " + rightValueObject + "' can not be executed. " +
                    "Operands need to be integer.");

        for (int j = leftValue; j <= rightValue; j++) {
            intervalArray.add(new InternalNumber(new BigInteger(Integer.toString(j)), new BigInteger("1")));
        }

        return new Tuple<>(intervalArray);
    }
}
