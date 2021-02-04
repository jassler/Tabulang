package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class AverageNode extends BinaryTermNode {
    public AverageNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Calculates the average over every element in a specified column.
     *
     * @return the average over a column.
     * @throws TupleNameNotFoundException     if table does not contain a column with specified name.
     * @throws IllegalNumberArgumentException if the column contains a non-numerical value.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Table<?> table = getRightNode().verifyAndReturnTable(interpretation);
        InternalString columnIdentifier = getLeftNode().verifyAndReturnString(interpretation);
        if (!table.getColNames().contains(columnIdentifier)) {
            throw new TupleNameNotFoundException(getTextPosition(), columnIdentifier.getString(), table.getClass().getSimpleName(), getRightNode().getTextPosition().getContent(), table.getColNames().getNames());
        }
        InternalNumber sum = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
        InternalNumber numberElements = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
        InternalNumber one = new InternalNumber(new BigInteger("1"), new BigInteger("1"));

        int index = table.getColumnIndex(columnIdentifier);

        for (Tuple<?> a : table) {
            Object columnValue = a.getFromIndex(index);

            if (!(columnValue instanceof InternalNumber internalNumber))
                throw new IllegalNumberArgumentException(getTextPosition(), "Can not calculate average over non-numerical object. Object " + columnValue + " of class " + columnValue.getClass().getSimpleName() + " found in column with name '" + columnIdentifier + "'");

            sum = sum.add(internalNumber);
            numberElements = numberElements.add(one);
        }
        return sum.divide(numberElements);
    }
}
