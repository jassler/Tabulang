package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class AverageNode<E> extends BinaryTermNode{
    public AverageNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object table = getRightNode().evaluateNode(interpretation);
        table = ifTupleTransform(table);
        if(table instanceof Table) {
            Object columnIdentifier = getLeftNode().evaluateNode(interpretation);
            if(columnIdentifier instanceof InternalString) {
                if(((Table)table).getColNames().getNames().contains(((InternalString) columnIdentifier).getString())) {
                    InternalNumber sum  = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
                    InternalNumber numberElements = new InternalNumber(new BigInteger("0"), new BigInteger("1"));
                    InternalNumber one = new InternalNumber(new BigInteger("1"), new BigInteger("1"));
                    for (ArrayList<E> a : ((Table<E>) table).getRows()) {
                        //TODO remove println after further testing
                        System.out.println("......");
                        System.out.println(a);
                        Object columnValue = a.get(((Table) table).getColumnIndex(((InternalString) columnIdentifier).getString()));
                        if(columnValue instanceof InternalNumber) {
                            sum = sum.add((InternalNumber) columnValue);
                            numberElements = numberElements.add(one);
                        } else {
                            throw new IllegalArgumentException("Can not calculate average over non-numerical value. Object of class " + columnValue.getClass().getSimpleName() + " found in column with name '" + columnIdentifier + "'");
                        }
                    }
                    return sum.divide(numberElements);
                } else {
                    throw new TupleNameNotFoundException(((InternalString) columnIdentifier).getString());
                }
            } else {
                throw new IllegalArgumentException("Expected String but got: " + columnIdentifier.getClass().getSimpleName());
            }
        } else {
            throw new IllegalArgumentException("Expected Table but got: " + table.getClass().getSimpleName());
        }
    }
}
