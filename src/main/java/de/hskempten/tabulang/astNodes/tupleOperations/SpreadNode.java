package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberArgumentException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;
import java.util.ArrayList;

public class SpreadNode extends BinaryTermNode {
    public SpreadNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Creates new Tuple with an integer interval ranging from the evaluated value from the first node
     * to the evaluated value of the second node.
     *
     * @return new Tuple with an integer interval.
     * @throws IllegalNumberArgumentException if left number is lower than right number or at least one of the
     *                                        operands is not integer
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = getLeftNode().verifyAndReturnNumber(interpretation);
        InternalNumber rightNumber = getRightNode().verifyAndReturnNumber(interpretation);

        if (leftNumber.compareTo(rightNumber) > 0)
            throw new IllegalNumberArgumentException(getTextPosition(), " Left value has to be less than or equal to right value.");

        ArrayList<InternalNumber> intervalArray = new ArrayList<>();

        Object leftValueObject = leftNumber.getValue();
        Object rightValueObject = rightNumber.getValue();

        if (!(leftValueObject instanceof Integer leftValue && rightValueObject instanceof Integer rightValue))
            throw new IllegalNumberArgumentException(getTextPosition(), "Operands need to be integer.");

        for (int j = leftValue; j <= rightValue; j++) {
            intervalArray.add(new InternalNumber(new BigInteger(Integer.toString(j)), new BigInteger("1")));
        }

        return new Tuple<>(intervalArray);
    }
}
