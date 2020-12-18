package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.TupleNameNotFoundException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AverageNode<E> extends BinaryTermNode{
    public AverageNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object table = getRightNode().evaluateNode(interpretation);
        if(table instanceof Table) {
            Object columnIdentifier = getLeftNode().evaluateNode(interpretation);
            if(columnIdentifier instanceof String) {
                if(((Table)table).getColNames().getNames().contains(columnIdentifier)) {
                    BigDecimal sum = BigDecimal.ZERO;
                    BigDecimal numberElements = BigDecimal.ZERO;
                    for (ArrayList<E> a : ((Table<E>) table).getRows()) {
                        //TODO remove println after further testing
                        System.out.println("......");
                        System.out.println(a);
                        Object columnValue = a.get(((Table) table).getColumnIndex((String) columnIdentifier));
                        if(columnValue instanceof BigDecimal) {
                            sum = sum.add((BigDecimal) columnValue);
                            numberElements = numberElements.add(BigDecimal.ONE);
                        } else {
                            throw new IllegalArgumentException("Can not calculate average over non-numerical value. Object of class " + columnValue.getClass().getSimpleName() + " found in column with name '" + columnIdentifier + "'");
                        }
                    }
                    return sum.divide(numberElements);
                } else {
                    throw new TupleNameNotFoundException((String)columnIdentifier);
                }
            } else {
                throw new IllegalArgumentException("Expected String but got: " + columnIdentifier.getClass().getSimpleName());
            }
        } else {
            throw new IllegalArgumentException("Expected Table but got: " + table.getClass().getSimpleName());
        }
    }
}
