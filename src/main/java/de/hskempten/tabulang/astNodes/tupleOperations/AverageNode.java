package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class AverageNode extends BinaryTermNode {
    public AverageNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tableObject = getRightNode().evaluateNode(interpretation);
        tableObject = ifTupleTransform(tableObject);

        if (!(tableObject instanceof Table<?> table)) {
            throw new IllegalArgumentException("Expected Table but got: " + tableObject.getClass().getSimpleName());
        }
        Object columnIdentifierObject = getLeftNode().evaluateNode(interpretation);

        if (!(columnIdentifierObject instanceof InternalString columnIdentifier)) {
            throw new IllegalArgumentException("Expected String but got: " + columnIdentifierObject.getClass().getSimpleName());
        }
        if (!table.getColNames().contains(columnIdentifier)) {
            throw new TupleNameNotFoundException(getTextPosition(), columnIdentifier.getString(), tableObject.getClass().getSimpleName(), getRightNode().getTextPosition().getContent(), table.getColNames().getNames());
        }
        InternalNumber sum = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
        InternalNumber numberElements = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
        InternalNumber one = new InternalNumber(new BigInteger("1"), new BigInteger("1"));

        int index = table.getColumnIndex(columnIdentifier);

        for (Tuple<?> a : table) {
            Object columnValue = a.getFromIndex(index);

            if (!(columnValue instanceof InternalNumber internalNumber))
                throw new InterpreterException("Can not calculate average over non-numerical object. Object " + columnValue + " of class " + columnValue.getClass().getSimpleName() + " found in column with name '" + columnIdentifier + "'");

            sum = sum.add(internalNumber);
            numberElements = numberElements.add(one);
        }
        return sum.divide(numberElements);
    }
}