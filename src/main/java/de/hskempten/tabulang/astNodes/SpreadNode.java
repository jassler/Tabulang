package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
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

        if (!(leftObject instanceof InternalNumber left && rightObject instanceof InternalNumber right))
            throw new IllegalOperandArgumentException("Operation '" + leftObject + " (" + leftObject.getClass() + ") ... " + rightObject + " (" + rightObject.getClass() + ") can not be executed. " +
                    "Allowed operands: Numbers.");

        if (left.compareTo(right) > 0)
            throw new IllegalOperandArgumentException("Operation '" + left + " (" + left.getClass() + ") ... " + right + " (" + right.getClass() + ") can not be executed. " +
                    "Left value has to be less than or equal to right value.");


        ArrayList<InternalNumber> a = new ArrayList<>();

        Object leftValueObject = left.getValue();
        Object rightValueObject = right.getValue();

        //TODO Integer -> InternalNumber integer so they can be styleable
        if (!(leftValueObject instanceof Integer leftValue && rightValueObject instanceof Integer rightValue))
            throw new IllegalOperandArgumentException("Operation '" + leftValueObject + " ... " + rightValueObject + "' can not be executed. " +
                    "Operands need to be integer.");

        for (int j = leftValue; j <= rightValue; j++) {
            a.add(new InternalNumber(new BigInteger(Integer.toString(j)), new BigInteger("1")));
        }

        return new Tuple<>(a);
    }
}
