package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class AverageNode extends BinaryTermNode{
    public AverageNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object tableObject = getRightNode().evaluateNode(interpretation);
        tableObject = ifTupleTransform(tableObject);

        if(!(tableObject instanceof Table<?> table))
            throw new IllegalArgumentException("Expected Table but got: " + tableObject.getClass().getSimpleName());

        Object columnIdentifierObject = getLeftNode().evaluateNode(interpretation);

        if(!(columnIdentifierObject instanceof InternalString columnIdentifier))
            throw new IllegalArgumentException("Expected String but got: " + columnIdentifierObject.getClass().getSimpleName());

        if(!table.getColNames().contains(columnIdentifier.getString()))
            throw new TupleNameNotFoundException(columnIdentifier.getString());

        InternalNumber sum  = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
        InternalNumber numberElements = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
        InternalNumber one = new InternalNumber(new BigInteger("1"), new BigInteger("1"));

        int index = table.getColumnIndex(columnIdentifier.getString());

        for (Tuple<?> a : table) {
            //TODO remove println after further testing
            //System.out.println("......");
            //System.out.println(a);
            Object columnValue = a.getFromIndex(index);

            if(!(columnValue instanceof InternalNumber))
                throw new IllegalArgumentException("Can not calculate average over non-numerical value. Object of class " + columnValue.getClass().getSimpleName() + " found in column with name '" + columnIdentifier + "'");

            sum = sum.add((InternalNumber) columnValue);
            numberElements = numberElements.add(one);
        }
        return sum.divide(numberElements);
    }
}
